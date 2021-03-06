package com.shoping.kiku.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoping.kiku.entity.CommerceEntity;
import com.shoping.kiku.entity.MyOrderEntity;
import com.shoping.kiku.entity.MyOrderItemEntity;
import com.shoping.kiku.entity.OrderInfoByUserIdEntity;
import com.shoping.kiku.entity.OrderManagerEntity;
import com.shoping.kiku.entity.OrderProInfoEntity;
import com.shoping.kiku.entity.PayPriceEntity;
import com.shoping.kiku.entity.PaymentEntity;
import com.shoping.kiku.entity.ProductEntity;
import com.shoping.kiku.entity.ProductInCartEntity;
import com.shoping.kiku.entity.ProductInfoForOrderIdEntity;
import com.shoping.kiku.entity.ShippingEntity;
import com.shoping.kiku.object.OrderInfoByUserIdDto;
import com.shoping.kiku.object.OrderItemDto;
import com.shoping.kiku.object.OrderMangerDto;
import com.shoping.kiku.object.OrderProInfoDto;
import com.shoping.kiku.object.PayPriceDto;
import com.shoping.kiku.object.ProductInfoForOrderIdDto;
import com.shoping.kiku.object.ShippingDto;
import com.shoping.kiku.repository.CommerceRepository;
import com.shoping.kiku.repository.MyOrderItemRepository;
import com.shoping.kiku.repository.MyOrderRepositoty;
import com.shoping.kiku.repository.OrderInfoByRepository;
import com.shoping.kiku.repository.OrderManagerRepository;
import com.shoping.kiku.repository.OrderProInfoRepository;
import com.shoping.kiku.repository.PayPriceRepository;
import com.shoping.kiku.repository.PaymentRepository;
import com.shoping.kiku.repository.ProductInCartRepository;
import com.shoping.kiku.repository.ProductInfoForOrderIdRepository;
import com.shoping.kiku.repository.ProductRepository;
import com.shoping.kiku.repository.ShippingRepository;
import com.shoping.kiku.until.OrderUtils;
import com.shoping.kiku.until.Status;

@Service
public class MyOrderService {

	@Autowired
	MyOrderRepositoty orderRepositoty;

	@Autowired
	MyOrderItemRepository orderItemRepositoty;

	@Autowired
	CommerceRepository commerceRepository;

	@Autowired
	OrderProInfoRepository orderProInfoRepository;

	@Autowired
	ProductInCartRepository productAndCartRepository;

	@Autowired
	PayPriceRepository payPriceRepository;

	@Autowired
	OrderManagerRepository orderManagerRepository;

	@Autowired
	OrderInfoByRepository groupByOrderIdRepository;
	@Autowired
	ProductInfoForOrderIdRepository productInfoForOrderIdRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ShippingRepository shippingRepository;

	@Autowired
	PaymentRepository paymentRepository;

	/**
	 * ??????????????????????????????
	 * @param userId
	 */
	@Transactional
	public void createOrderForm(int userId) {
		//????????????
		String orderId = OrderUtils.getOrderCode(userId);
		List<ProductInCartEntity> products = productAndCartRepository.getcheckedpro(userId);
		for (ProductInCartEntity ord : products) {
			//?????????
			MyOrderEntity order = new MyOrderEntity();
			//?????????
			MyOrderItemEntity orderItem = new MyOrderItemEntity();

			order.setOrderId(orderId);

			//?????????????????????????????????????????? 0 ?????????
			order.setOrderStatus(Status.ORDERWAITPAY);
			//???????????? ?????????
			order.setPurchasingPrice(0);
			//??????????????????
			order.setCreateTime(new Timestamp(System.currentTimeMillis()));
			orderRepositoty.save(order);

			orderItem.setOrderId(orderId);
			//???????????????ID
			orderItem.setProductId(ord.getProductId());
			orderItem.setProductName(ord.getProductName());

			//???????????????????????????
			orderItem.setProductPrice(ord.getProductPrice());
			//???????????????????????????
			orderItem.setProductQuantity(ord.getQuantity());
			orderItemRepositoty.save(orderItem);

			//????????????????????????
			CommerceEntity com = new CommerceEntity();
			com.setCreatedate(new Timestamp(System.currentTimeMillis()));
			com.setOrderId(orderId);
			//???????????????
			com.setUserId(userId);
			commerceRepository.save(com);
		}

	}

	/**
	 * myOrderItemTbl???????????? (Commerce????????????orderInfo??????)
	 * @param userId
	 * @return
	 */

	public List<OrderProInfoDto> getOrderItemInfoByUserId(int userId) {

		List<OrderProInfoEntity> myOrder = orderProInfoRepository.getMyOrderInfo(userId);
		System.out.println(myOrder.size());
		List<OrderProInfoDto> orderList = new ArrayList<>();
		for (OrderProInfoEntity ord : myOrder) {
			OrderProInfoDto order = new OrderProInfoDto();
			order.setUserId(userId);
			order.setOrderId(ord.getOrderId());
			order.setCreatedate(ord.getCreatedate());
			order.setProductId(ord.getProductId());
			order.setProductPrice(ord.getProductPrice());
			order.setProductName(ord.getProductName());
			order.setProductQuantity(ord.getProductQuantity());
			order.setCreatedate(ord.getCreatedate());
			order.setProductImg(ord.getProductImg());
			order.setProductContents(ord.getProductContents());
			order.setMaker(ord.getMaker());
			order.setStoreId(ord.getStoreId());
			order.setStoreName(ord.getStoreName());
			order.setShippingId(ord.getShippingId());
			order.setPaymentId(ord.getPaymentId());
			order.setOrderStatus(ord.getOrderStatus());
			order.setPurchasingPrice(ord.getPurchasingPrice());
			order.setModifyTime(ord.getModifyTime());
			order.setPayMethod(ord.getPayMethod());
			order.setPayQuantity(ord.getPayQuantity());
			order.setPayTotal(ord.getPayTotal());
			order.setPayTime(ord.getPayTime());
			orderList.add(order);
		}
		return orderList;

	}

	/**
	 * ?????????????????????????????????(?????????????????????????????????) user
	 * @param userId
	 * @param orderId
	 * @return
	 */

	public int getPayPrice(int userId, String orderId) {
		List<PayPriceEntity> price = payPriceRepository.getpriceAndQuantityByUserIdAndOrdId(userId, orderId);
		int amount = 0;
		for (PayPriceEntity p : price) {
			PayPriceDto pay = new PayPriceDto();
			pay.setProductTotal(p.getProductPrice() * p.getProductQuantity());
			int total = pay.getProductTotal();
			amount = total + amount;
		}

		return amount;
	}

	/**
	 * store
	 * @param userId
	 * @param orderId
	 * @return
	 */

	public int getPayPriceStore(int userId, String orderId) {
		List<PayPriceEntity> price = payPriceRepository.getUserTotalAndQuantity(userId, orderId);
		int amount = 0;
		for (PayPriceEntity p : price) {
			PayPriceDto pay = new PayPriceDto();
			pay.setProductTotal(p.getProductPrice() * p.getProductQuantity());
			int total = pay.getProductTotal();
			amount = total + amount;
		}

		return amount;
	}

	/**
	 * ?????????????????????????????????(?????????????????????????????????) user
	 * @param userId
	 * @param orderId
	 * @return
	 */

	public int getPayQuantiy(int userId, String orderId) {
		List<PayPriceEntity> qqt = payPriceRepository.getpriceAndQuantityByUserIdAndOrdId(userId, orderId);
		int quantity = 0;
		for (PayPriceEntity q : qqt) {
			PayPriceDto pay = new PayPriceDto();
			pay.setProductQuantity(q.getProductQuantity());
			int total = pay.getProductQuantity();
			quantity = total + quantity;
		}

		return quantity;
	}

	/**
	 * store
	 * @param userId
	 * @param orderId
	 * @return
	 */

	public int getPayQuantiyStore(int userId, String orderId) {
		List<PayPriceEntity> qqt = payPriceRepository.getUserTotalAndQuantity(userId, orderId);
		int quantity = 0;
		for (PayPriceEntity q : qqt) {
			PayPriceDto pay = new PayPriceDto();
			pay.setProductQuantity(q.getProductQuantity());
			int total = pay.getProductQuantity();
			quantity = total + quantity;
		}

		return quantity;
	}

	/**
	 * get qqt (ADMIN)
	 * @param orderId
	 * @return
	 */

	public int getQqt(String orderId) {

		List<PayPriceEntity> qqt = payPriceRepository.getTotalAndQuantityByOrderId(orderId);
		int quantity = 0;
		for (PayPriceEntity q : qqt) {
			PayPriceDto pay = new PayPriceDto();
			pay.setProductQuantity(q.getProductQuantity());
			int total = pay.getProductQuantity();
			quantity = total + quantity;
		}

		return quantity;
	}

	/**
	 * get total (ADMIN)
	 * @param orderId
	 * @return
	 */

	public int getTotal(String orderId) {

		List<PayPriceEntity> price = payPriceRepository.getTotalAndQuantityByOrderId(orderId);
		int amount = 0;
		for (PayPriceEntity p : price) {
			PayPriceDto pay = new PayPriceDto();
			pay.setProductTotal(p.getProductPrice() * p.getProductQuantity());
			int total = pay.getProductTotal();
			amount = total + amount;
		}

		return amount;
	}

	/**
	 * ??????????????????
	 * @param orderId
	 */
	@Transactional
	public void cancelOrder(String orderId) {
		//myOrder table ?????????????????????????????????
		MyOrderEntity cancel = orderRepositoty.findByOrderId(orderId);
		//myOrder table ?????????????????????????????????
		orderRepositoty.delete(cancel);
		//OrderItem table ?????????????????????????????????
		List<MyOrderItemEntity> ordercancel = orderItemRepositoty.findByOrderId(orderId);
		int productId = 0;
		int stock = 0;
		for (MyOrderItemEntity ord : ordercancel) {
			OrderItemDto dto = new OrderItemDto();
			dto.setProductId(ord.getProductId());
			dto.setProductQuantity(ord.getProductQuantity());
			//OrderItem table ?????????????????????????????????ID?????????
			productId = dto.getProductId();
			//OrderItem table ??????????????????????????????????????????
			stock = dto.getProductQuantity();
			//??????ID???????????????product table ???????????????????????????
			ProductEntity oldpro = productRepository.findByProductId(productId);
			ProductEntity newpro = new ProductEntity();
			newpro.setProductId(productId);
			newpro.setMaker(oldpro.getMaker());
			newpro.setProductContents(oldpro.getProductContents());
			newpro.setProductImg(oldpro.getProductImg());
			newpro.setProductName(oldpro.getProductName());
			newpro.setProductPrice(oldpro.getProductPrice());
			newpro.setStatus(oldpro.getStatus());
			newpro.setStoreId(oldpro.getStoreId());
			//???????????????????????????
			newpro.setStock(oldpro.getStock() + stock);
			productRepository.save(newpro);
		}
		//OrderItem table ?????????????????????????????????
		orderItemRepositoty.deleteAll(ordercancel);

	}

	/**
	 * orderManger (store)
	 * @param userId
	 * @return
	 */

	public List<OrderMangerDto> getOrderInfoByStoreId(int userId) {
		List<OrderManagerEntity> storeOr = orderManagerRepository.getOrderInfoWithStoreId(userId);
		List<OrderMangerDto> idlist = new ArrayList<>();
		for (OrderManagerEntity hh : storeOr) {
			OrderMangerDto id = new OrderMangerDto();
			int qqt = getPayQuantiyStore(userId, hh.getOrderId());
			int total = getPayPriceStore(userId, hh.getOrderId());
			id.setQqt(qqt);
			id.setTotal(total);
			id.setOrderId(hh.getOrderId());
			id.setCreatedate(hh.getCreatedate());
			id.setDcyoumebanchi(hh.getDcyoumebanchi());
			id.setDshikucyouson(hh.getDshikucyouson());
			id.setDtodoufuken(hh.getDtodoufuken());
			id.setOrderStatus(hh.getOrderStatus());
			id.setPaymentId(hh.getPaymentId());
			id.setPayMethod(hh.getPayMethod());
			id.setPayQuantity(hh.getPayQuantity());
			id.setPayTime(hh.getPayTime());
			id.setPayTotal(hh.getPayTotal());
			id.setShippingId(hh.getShippingId());
			id.setRenrakuname(hh.getRenrakuname());
			id.setRenrakuphone(hh.getRenrakuphone());
			id.setCourierCompany(hh.getCourierCompany());
			id.setTrackingNumber(hh.getTrackingNumber());
			id.setDeliveryTime(hh.getDeliveryTime());
			id.setReceiptTime(hh.getReceiptTime());
			id.setCertainTime(hh.getReceiptTime());
			id.setName(hh.getName());
			if (id.getOrderStatus() == Status.ORDERWAITPAY) {
				id.setOrdStatus(Status.ORDERWAIT);
			} else if (id.getOrderStatus() == Status.ORDERDELIVEY) {
				id.setOrdStatus(Status.ORDETOBEDELIVED);
			} else if (id.getOrderStatus() == Status.ORDERRECEIVE) {
				id.setOrdStatus(Status.ORDETOBERECEIVED);
			} else {
				id.setOrdStatus(Status.ORDETOBECOM);
			}

			//orderId ????????????????????????????????????
			List<ProductInfoForOrderIdDto> proInfo = getproductInfoByOrderId(hh.getOrderId());
			id.setProduct(proInfo);
			idlist.add(id);
		}

		return idlist;
	}

	/**
	 * orderManger (ADMIN)
	 * @return orderList
	 */

	public List<OrderMangerDto> getAllOrderInfo() {
		List<OrderManagerEntity> allOrder = orderManagerRepository.getAllOrderInfo();
		List<OrderMangerDto> orderList = new ArrayList<>();
		for (OrderManagerEntity all : allOrder) {
			OrderMangerDto id = new OrderMangerDto();
			int total = getTotal(all.getOrderId());
			int qqt = getQqt(all.getOrderId());
			id.setQqt(qqt);
			id.setTotal(total);
			id.setOrderId(all.getOrderId());
			id.setCreatedate(all.getCreatedate());
			id.setDcyoumebanchi(all.getDcyoumebanchi());
			id.setDshikucyouson(all.getDshikucyouson());
			id.setDtodoufuken(all.getDtodoufuken());
			id.setOrderStatus(all.getOrderStatus());
			id.setPaymentId(all.getPaymentId());
			id.setPayMethod(all.getPayMethod());
			id.setPayQuantity(all.getPayQuantity());
			id.setPayTime(all.getPayTime());
			id.setPayTotal(all.getPayTotal());
			id.setShippingId(all.getShippingId());
			id.setRenrakuname(all.getRenrakuname());
			id.setRenrakuphone(all.getRenrakuphone());
			id.setCourierCompany(all.getCourierCompany());
			id.setTrackingNumber(all.getTrackingNumber());
			id.setDeliveryTime(all.getDeliveryTime());
			id.setReceiptTime(all.getReceiptTime());
			id.setCertainTime(all.getReceiptTime());

			if (id.getOrderStatus() == Status.ORDERWAITPAY) {
				id.setOrdStatus(Status.ORDERWAIT);
			} else if (id.getOrderStatus() == Status.ORDERDELIVEY) {
				id.setOrdStatus(Status.ORDETOBEDELIVED);
			} else if (id.getOrderStatus() == Status.ORDERRECEIVE) {
				id.setOrdStatus(Status.ORDETOBERECEIVED);
			} else {
				id.setOrdStatus(Status.ORDETOBECOM);
			}

			//orderId ????????????????????????????????????
			List<ProductInfoForOrderIdDto> proInfo = getproductInfoByOrderId(all.getOrderId());
			id.setProduct(proInfo);
			orderList.add(id);
		}
		return orderList;
	}

	/**
	 * userId?????????????????????????????????????????????(user)
	 * @param userId
	 * @return
	 */

	public List<OrderInfoByUserIdDto> getOrderInfoByUserId(int userId) {
		List<OrderInfoByUserIdEntity> orderId = groupByOrderIdRepository.getOrderInfoGroupByOrderIdByUserId(userId);
		List<OrderInfoByUserIdDto> orderIdList = new ArrayList<>();
		for (OrderInfoByUserIdEntity ord : orderId) {
			OrderInfoByUserIdDto dto = new OrderInfoByUserIdDto();
			//??????????????????
			int qqt = getPayQuantiy(userId, ord.getOrderId());
			int total = getPayPrice(userId, ord.getOrderId());
			dto.setOrderId(ord.getOrderId());
			dto.setCourierCompany(ord.getCourierCompany());
			dto.setCreateTime(ord.getCreateTime());
			dto.setDeliveryTime(ord.getDeliveryTime());
			dto.setModifyTime(ord.getModifyTime());
			dto.setOrderStatus(ord.getOrderStatus());

			dto.setPaymentId(ord.getPaymentId());
			dto.setPayMethod(ord.getPayMethod());
			dto.setPayQuantity(ord.getPayQuantity());
			dto.setPayTime(ord.getPayTime());
			dto.setPayTotal(ord.getPayTotal());
			dto.setPurchasingPrice(ord.getPurchasingPrice());
			dto.setReceiptTime(ord.getReceiptTime());
			dto.setShippingId(ord.getShippingId());
			dto.setTrackingNumber(ord.getTrackingNumber());
			dto.setTotal(total);
			dto.setQqt(qqt);
			dto.setCertainTime(ord.getReceiptTime());

			if (dto.getOrderStatus() == Status.ORDERWAITPAY) {
				dto.setOrdStatus(Status.ORDERWAIT);
			} else if (dto.getOrderStatus() == Status.ORDERDELIVEY) {
				dto.setOrdStatus(Status.ORDETOBEDELIVED);
			} else if (dto.getOrderStatus() == Status.ORDERRECEIVE) {
				dto.setOrdStatus(Status.ORDETOBERECEIVED);
			} else {
				dto.setOrdStatus(Status.ORDETOBECOM);
			}

			//orderId ????????????????????????????????????
			List<ProductInfoForOrderIdDto> proInfo = getproductInfoByOrderId(ord.getOrderId());

			dto.setProduct(proInfo);

			orderIdList.add(dto);

		}
		return orderIdList;
	}

	/**
	 * orderId ????????????????????????????????????
	 * @param orderId
	 * @return
	 */

	public List<ProductInfoForOrderIdDto> getproductInfoByOrderId(String orderId) {
		List<ProductInfoForOrderIdDto> proInfo = new ArrayList<>();
		List<ProductInfoForOrderIdEntity> xx = productInfoForOrderIdRepository
				.productInfoByOrderId(orderId);
		for (ProductInfoForOrderIdEntity hh : xx) {
			ProductInfoForOrderIdDto dt = new ProductInfoForOrderIdDto();
			dt.setOrderId(hh.getOrderId());
			dt.setProductId(hh.getProductId());
			dt.setProductImg(hh.getProductImg());
			dt.setProductName(hh.getProductName());
			dt.setProductPrice(hh.getProductPrice());
			dt.setProductQuantity(hh.getProductQuantity());
			proInfo.add(dt);

		}

		return proInfo;

	}

	/**
	 * ????????????
	 * @param orderId
	 * @param userId
	 * @param shipDto
	 */
	@Transactional
	public void productShip(String orderId, ShippingDto shipDto) {

		MyOrderEntity oldOrder = orderRepositoty.findByOrderId(orderId);
		MyOrderEntity newOrder = new MyOrderEntity();
		newOrder.setCreateTime(oldOrder.getCreateTime());
		newOrder.setModifyTime(new Timestamp(System.currentTimeMillis()));
		newOrder.setOrderId(orderId);
		//???????????? ????????????????????????
		newOrder.setOrderStatus(Status.ORDERRECEIVE);
		newOrder.setPurchasingPrice(oldOrder.getPurchasingPrice());
		orderRepositoty.save(newOrder);
		//commerce table ??????ID??????

		CommerceEntity comm = commerceRepository.findByOrderId(orderId);

		CommerceEntity commerce = new CommerceEntity();
		//??????ID??????
		Integer wuliu = 99;
		String shipId = OrderUtils.getShipCode(wuliu);
		commerce.setShippingId(shipId);

		commerce.setCreatedate(comm.getCreatedate());
		commerce.setOrderId(orderId);
		commerce.setPaymentId(comm.getPaymentId());
		commerce.setUserId(comm.getUserId());
		commerceRepository.save(commerce);

		//shipping table 
		//??????????????????
		String expressId = OrderUtils.getExpressCode(wuliu);
		ShippingEntity ship = new ShippingEntity();
		ship.setTrackingNumber(expressId);
		ship.setCourierCompany(shipDto.getCourierCompany());
		//????????????

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		ship.setDeliveryTime(timestamp);
		ship.setShippingId(shipId);

		shippingRepository.save(ship);
	}

	/**
	 * ?????? (user) ????????????
	 * @param orderId
	 */
	@Transactional
	public void completeOrder(String orderId) {
		MyOrderEntity oldOrder = orderRepositoty.findByOrderId(orderId);
		MyOrderEntity newOrder = new MyOrderEntity();
		newOrder.setCreateTime(oldOrder.getCreateTime());
		newOrder.setModifyTime(new Timestamp(System.currentTimeMillis()));
		newOrder.setOrderId(orderId);
		//???????????? ????????????????????????
		newOrder.setOrderStatus(Status.ORDERCOMPLETE);
		newOrder.setPurchasingPrice(oldOrder.getPurchasingPrice());
		orderRepositoty.save(newOrder);

		//shipping table
		ShippingEntity oldship = shippingRepository.getShipInfoFindByShippingId(orderId);
		ShippingEntity ship = new ShippingEntity();
		ship.setTrackingNumber(oldship.getTrackingNumber());
		ship.setCourierCompany(oldship.getCourierCompany());
		ship.setDeliveryTime(oldship.getDeliveryTime());
		ship.setShippingId(oldship.getShippingId());
		//?????? ????????????
		ship.setReceiptTime(new Timestamp(System.currentTimeMillis()));
		shippingRepository.save(ship);

	}

	/**
	 * ??????????????? 
	 * @param orderId
	 */
	@Transactional
	public void receivedAndPay(String orderId) {

		//shipping table
		ShippingEntity oldship = shippingRepository.getShipInfoFindByShippingId(orderId);
		ShippingEntity ship = new ShippingEntity();
		ship.setTrackingNumber(oldship.getTrackingNumber());
		ship.setCourierCompany(oldship.getCourierCompany());
		ship.setDeliveryTime(oldship.getDeliveryTime());
		ship.setShippingId(oldship.getShippingId());
		//?????? ????????????
		ship.setReceiptTime(new Timestamp(System.currentTimeMillis()));
		shippingRepository.save(ship);

		//??????

		//payment table
		String paymentId = commerceRepository.findByOrderId(orderId).getPaymentId();
		PaymentEntity oldpay = paymentRepository.findByPaymentId(paymentId);
		PaymentEntity payment = new PaymentEntity();
		payment.setPaymentId(oldpay.getPaymentId());
		payment.setUserId(oldpay.getUserId());
		payment.setPayQuantity(oldpay.getPayQuantity());
		payment.setPayTotal(oldpay.getPayTotal());
		payment.setPayMethod(oldpay.getPayMethod());
		//??????????????????????????????
		payment.setPayTime(new Timestamp(System.currentTimeMillis()));
		paymentRepository.save(payment);

		//order table
		MyOrderEntity oldOrder = orderRepositoty.findByOrderId(orderId);
		MyOrderEntity newOrder = new MyOrderEntity();
		newOrder.setCreateTime(oldOrder.getCreateTime());
		newOrder.setModifyTime(new Timestamp(System.currentTimeMillis()));
		newOrder.setOrderId(orderId);
		//?????? ????????????????????????
		newOrder.setOrderStatus(Status.ORDERCOMPLETE);
		newOrder.setPurchasingPrice(oldOrder.getPurchasingPrice());
		orderRepositoty.save(newOrder);

	}
}
