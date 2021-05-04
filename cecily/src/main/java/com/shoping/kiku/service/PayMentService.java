package com.shoping.kiku.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoping.kiku.entity.ComAndOrderEntity;
import com.shoping.kiku.entity.CommerceEntity;
import com.shoping.kiku.entity.MyOrderEntity;
import com.shoping.kiku.entity.PaymentEntity;
import com.shoping.kiku.repository.ComAndOrderRepository;
import com.shoping.kiku.repository.CommerceRepository;
import com.shoping.kiku.repository.MyOrderRepositoty;
import com.shoping.kiku.repository.PayPriceRepository;
import com.shoping.kiku.repository.PaymentRepository;
import com.shoping.kiku.until.OrderUtils;

@Service
public class PayMentService {

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	CommerceRepository commerceRepository;

	@Autowired
	MyOrderRepositoty myOrderRepository;

	@Autowired
	PayPriceRepository payPriceRepository;

	@Autowired
	ComAndOrderRepository comAndOrderRepository;

	//AriPay
	public void crePayForm(int userId, int productId) {

		//自動生成
		String payId = OrderUtils.getPayCode(userId);
		int total = payPriceRepository.getpriceByUserIdAndProId(userId, productId);
		int quantity = payPriceRepository.getquantityByUserIdAndProId(userId, productId);
		//payment table
		PaymentEntity payment = new PaymentEntity();
		payment.setPaymentId(payId);
		payment.setUserId(userId);
		payment.setPayQuantity(quantity);
		payment.setPayTotal(total);
		//AriPay
		payment.setPayMethod(1);
		payment.setPayTime(new Timestamp(System.currentTimeMillis()));
		paymentRepository.save(payment);

		// find OrderId 
		ComAndOrderEntity ord = comAndOrderRepository.comAndOrder(userId, productId);
		// OrderId
		String orderId = ord.getOrderId();
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
		myord.setOrderStatus(1);
		myord.setModifyTime(new Timestamp(System.currentTimeMillis()));
		myord.setPurchasingPrice(total);
		myOrderRepository.save(myord);
	}
	
	
	//get payInfo
	
	public void getPayInfo(int userId) {
		
		
		
		
	}
	

}
