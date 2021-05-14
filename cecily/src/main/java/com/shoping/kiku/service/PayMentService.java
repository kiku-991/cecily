package com.shoping.kiku.service;

import java.sql.Timestamp;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoping.kiku.entity.CommerceEntity;
import com.shoping.kiku.entity.MyOrderEntity;
import com.shoping.kiku.entity.PaymentEntity;
import com.shoping.kiku.repository.ComAndOrderRepository;
import com.shoping.kiku.repository.CommerceRepository;
import com.shoping.kiku.repository.MyOrderRepositoty;
import com.shoping.kiku.repository.PaymentRepository;
import com.shoping.kiku.until.OrderUtils;
import com.shoping.kiku.until.Status;

@Service
public class PayMentService {

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	CommerceRepository commerceRepository;

	@Autowired
	MyOrderRepositoty myOrderRepository;

	@Autowired
	ComAndOrderRepository comAndOrderRepository;

	@Autowired
	MyOrderService myOrderService;

	/**
	 * AriPay
	 * @param userId
	 * @param orderId
	 */
	@Transactional
	public void creAriPayForm(int userId, String orderId) {

		//自動生成
		String payId = OrderUtils.getPayCode(userId);
		int total = myOrderService.getPayPrice(userId, orderId);
		int quantity = myOrderService.getPayQuantiy(userId, orderId);
		//payment table
		PaymentEntity payment = new PaymentEntity();
		payment.setPaymentId(payId);
		payment.setUserId(userId);
		payment.setPayQuantity(quantity);
		payment.setPayTotal(total);
		//AriPay
		payment.setPayMethod(Status.ARIPAY);
		payment.setPayTime(new Timestamp(System.currentTimeMillis()));
		paymentRepository.save(payment);

		//Commerce Table
		CommerceEntity commerce = commerceRepository.findByOrderId(orderId);
		CommerceEntity com = new CommerceEntity();
		com.setCreatedate(commerce.getCreatedate());
		com.setOrderId(commerce.getOrderId());
		com.setPaymentId(payId);
		com.setShippingId(null);
		com.setUserId(commerce.getUserId());
		commerceRepository.save(com);

		//MyOrder table
		MyOrderEntity myorder = myOrderRepository.findByOrderId(orderId);

		MyOrderEntity myord = new MyOrderEntity();
		myord.setCreateTime(myorder.getCreateTime());
		myord.setOrderId(orderId);
		//支払い完了
		myord.setOrderStatus(Status.ORDERDELIVEY);
		myord.setModifyTime(new Timestamp(System.currentTimeMillis()));
		myord.setPurchasingPrice(total);
		myOrderRepository.save(myord);
	}

	/**
	 * クレジットカード支払い
	 * @param userId
	 * @param orderId
	 */
	@Transactional
	public void creCreditPayForm(int userId, String orderId) {
		//自動生成
		String payId = OrderUtils.getPayCode(userId);
		int total = myOrderService.getPayPrice(userId, orderId);
		int quantity = myOrderService.getPayQuantiy(userId, orderId);
		//payment table
		PaymentEntity payment = new PaymentEntity();
		payment.setPaymentId(payId);
		payment.setUserId(userId);
		payment.setPayQuantity(quantity);
		payment.setPayTotal(total);
		//CreditPay
		payment.setPayMethod(Status.CREDITPAY);
		payment.setPayTime(new Timestamp(System.currentTimeMillis()));
		paymentRepository.save(payment);

		//Commerce Table
		CommerceEntity commerce = commerceRepository.findByOrderId(orderId);
		CommerceEntity com = new CommerceEntity();
		com.setCreatedate(commerce.getCreatedate());
		com.setOrderId(commerce.getOrderId());
		com.setPaymentId(payId);
		com.setShippingId(null);
		com.setUserId(commerce.getUserId());
		commerceRepository.save(com);

		//MyOrder table
		MyOrderEntity myorder = myOrderRepository.findByOrderId(orderId);

		MyOrderEntity myord = new MyOrderEntity();
		myord.setCreateTime(myorder.getCreateTime());
		myord.setOrderId(orderId);
		//支払い完了
		myord.setOrderStatus(Status.ORDERDELIVEY);
		myord.setModifyTime(new Timestamp(System.currentTimeMillis()));
		myord.setPurchasingPrice(total);
		myOrderRepository.save(myord);

	}

	/**
	 * CashOndelivery
	 * @param userId
	 * @param orderId
	 */
	@Transactional
	public void creCashOndeliveryForm(int userId, String orderId) {
		// TODO 自動生成されたメソッド・スタブ
		//自動生成
				String payId = OrderUtils.getPayCode(userId);
				int total = myOrderService.getPayPrice(userId, orderId);
				int quantity = myOrderService.getPayQuantiy(userId, orderId);
				//payment table
				PaymentEntity payment = new PaymentEntity();
				payment.setPaymentId(payId);
				payment.setUserId(userId);
				payment.setPayQuantity(quantity);
				payment.setPayTotal(total);
				//CashOndeliveryPay
				payment.setPayMethod(Status.CASHPAY);
				//このタイミングまだ支払っていない
				payment.setPayTime(null);
				paymentRepository.save(payment);

				//Commerce Table
				CommerceEntity commerce = commerceRepository.findByOrderId(orderId);
				CommerceEntity com = new CommerceEntity();
				com.setCreatedate(commerce.getCreatedate());
				com.setOrderId(commerce.getOrderId());
				com.setPaymentId(payId);
				com.setShippingId(null);
				com.setUserId(commerce.getUserId());
				commerceRepository.save(com);

				//MyOrder table
				MyOrderEntity myorder = myOrderRepository.findByOrderId(orderId);

				MyOrderEntity myord = new MyOrderEntity();
				myord.setCreateTime(myorder.getCreateTime());
				myord.setOrderId(orderId);
				//支払いまだ完了
				myord.setOrderStatus(Status.ORDERWAITPAY);
				myord.setModifyTime(new Timestamp(System.currentTimeMillis()));
				myord.setPurchasingPrice(total);
				myOrderRepository.save(myord);
		
	}

	

}
