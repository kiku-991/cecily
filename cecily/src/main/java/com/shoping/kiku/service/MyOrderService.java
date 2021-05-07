package com.shoping.kiku.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoping.kiku.entity.CommerceEntity;
import com.shoping.kiku.entity.MyOrderEntity;
import com.shoping.kiku.entity.MyOrderItemEntity;
import com.shoping.kiku.entity.OrderInfoByUserIdEntity;
import com.shoping.kiku.entity.OrderManagerEntity;
import com.shoping.kiku.entity.OrderProInfoEntity;
import com.shoping.kiku.entity.PayPriceEntity;
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
import com.shoping.kiku.repository.ProductInCartRepository;
import com.shoping.kiku.repository.ProductInfoForOrderIdRepository;
import com.shoping.kiku.repository.ProductRepository;
import com.shoping.kiku.repository.ShippingRepository;
import com.shoping.kiku.until.OrderUtils;

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

	/**
	 * オーダーフォーム生成
	 * @param userId
	 */
	public void createOrderForm(int userId) {
		//自動生成
		String orderId = OrderUtils.getOrderCode(userId);
		List<ProductInCartEntity> products = productAndCartRepository.getcheckedpro(userId);
		for (ProductInCartEntity ord : products) {
			//主订单
			MyOrderEntity order = new MyOrderEntity();
			//子订单
			MyOrderItemEntity orderItem = new MyOrderItemEntity();

			order.setOrderId(orderId);

			//提交订单的时候把订单状态设为 0 待付款
			order.setOrderStatus(0);
			//购买总额 待付款
			order.setPurchasingPrice(0);
			//创建订单时间
			order.setCreateTime(new Timestamp(System.currentTimeMillis()));
			orderRepositoty.save(order);

			orderItem.setOrderId(orderId);
			//购物车里的ID
			orderItem.setProductId(ord.getProductId());
			orderItem.setProductName(ord.getProductName());

			//购物车里的商品单价
			orderItem.setProductPrice(ord.getProductPrice());
			//购物车里的商品数量
			orderItem.setProductQuantity(ord.getQuantity());
			orderItemRepositoty.save(orderItem);

			//買い物かご連動表
			CommerceEntity com = new CommerceEntity();
			com.setCreatedate(new Timestamp(System.currentTimeMillis()));
			com.setOrderId(orderId);
			//該当ユーザ
			com.setUserId(userId);
			commerceRepository.save(com);
		}

	}

	/**
	 * myOrderItemTblから取得 (Commerceを通じてorderInfo取得)
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
	 * 支払い画面の支払い金額(まだ支払していない状態) user
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
	 * 支払い画面の支払い数量(まだ支払していない状態) user
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
	 * オーダー削除
	 * @param orderId
	 */
	public void cancelOrder(String orderId) {
		//myOrder table から該当オーダーを取得
		MyOrderEntity cancel = orderRepositoty.findByOrderId(orderId);
		//myOrder table から該当オーダーを削除
		orderRepositoty.delete(cancel);
		//OrderItem table から該当オーダーを取得
		List<MyOrderItemEntity> ordercancel = orderItemRepositoty.findByOrderId(orderId);
		int productId = 0;
		int stock = 0;
		for (MyOrderItemEntity ord : ordercancel) {
			OrderItemDto dto = new OrderItemDto();
			dto.setProductId(ord.getProductId());
			dto.setProductQuantity(ord.getProductQuantity());
			//OrderItem table から該当オーダーの商品IDを取得
			productId = dto.getProductId();
			//OrderItem table から該当オーダーの数量を取得
			stock = dto.getProductQuantity();
			//商品IDによって、product table から商品情報を取得
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
			//商品ストックを戻す
			newpro.setStock(oldpro.getStock() + stock);
			productRepository.save(newpro);
		}
		//OrderItem table から該当オーダーを削除
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
			id.setName(hh.getName());
			//orderId によって、商品情報を取得
			List<ProductInfoForOrderIdDto> proInfo = getproductInfoByOrderId(hh.getOrderId());
			id.setProduct(proInfo);
			idlist.add(id);		
		}


		return idlist;
	}

	/**
	 * userId　によって、オーダー情報を取得(user)
	 * @param userId
	 * @return
	 */
	public List<OrderInfoByUserIdDto> getOrderInfoByUserId(int userId) {
		List<OrderInfoByUserIdEntity> orderId = groupByOrderIdRepository.getOrderInfoGroupByOrderIdByUserId(userId);
		List<OrderInfoByUserIdDto> orderIdList = new ArrayList<>();
		for (OrderInfoByUserIdEntity ord : orderId) {
			OrderInfoByUserIdDto dto = new OrderInfoByUserIdDto();
			//オーダー情報
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
			dto.setCertainTime(ord.getReceiptTime());	//orderId によって、商品情報を取得
			List<ProductInfoForOrderIdDto> proInfo = getproductInfoByOrderId(ord.getOrderId());

			dto.setProduct(proInfo);

			orderIdList.add(dto);

		}
		return orderIdList;
	}


	/**
	 * orderId によって、商品情報を取得
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
	 * 商品発送
	 * @param orderId
	 * @param userId
	 * @param shipDto
	 */

	public void productShip(String orderId,ShippingDto shipDto) {

		MyOrderEntity oldOrder = orderRepositoty.findByOrderId(orderId);
		MyOrderEntity newOrder = new MyOrderEntity();
		newOrder.setCreateTime(oldOrder.getCreateTime());
		newOrder.setModifyTime(new Timestamp(System.currentTimeMillis()));
		newOrder.setOrderId(orderId);
		//商品発送 订单状态为待收货
		newOrder.setOrderStatus(2);
		newOrder.setPurchasingPrice(oldOrder.getPurchasingPrice());
		orderRepositoty.save(newOrder);
		//commerce table 物流ID生成

		CommerceEntity comm = commerceRepository.findByOrderId(orderId);

		CommerceEntity commerce = new CommerceEntity();
		//物流ID生成
		Integer wuliu = 99;
		String shipId = OrderUtils.getShipCode(wuliu);
		commerce.setShippingId(shipId);

		//commerce.setCreatedate(comm.getCreatedate());
		commerce.setOrderId(orderId);
		commerce.setPaymentId(comm.getPaymentId());
		commerce.setUserId(comm.getUserId());
		commerceRepository.save(commerce);

		//shipping table 
		//快递编号生成
		String expressId = OrderUtils.getExpressCode(wuliu);
		ShippingEntity ship = new ShippingEntity();
		ship.setTrackingNumber(expressId);
		ship.setCourierCompany(shipDto.getCourierCompany());
		//发货时间
//		final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		  Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//		  sdf3.format(timestamp);
			/*  java.sql.Timestamp ts = java.sql.Timestamp.valueOf(sdf3);*/
			ship.setDeliveryTime(timestamp);
			ship.setShippingId(shipId);

		shippingRepository.save(ship);
	}

	/**
	 * 收貨 (user) 訂單完成
	 * @param orderId
	 */
	public void completeOrder(String orderId) {
		MyOrderEntity oldOrder = orderRepositoty.findByOrderId(orderId);
		MyOrderEntity newOrder = new MyOrderEntity();
		newOrder.setCreateTime(oldOrder.getCreateTime());
		newOrder.setModifyTime(new Timestamp(System.currentTimeMillis()));
		newOrder.setOrderId(orderId);
		//商品発送 订单状态为已完成
		newOrder.setOrderStatus(3);
		newOrder.setPurchasingPrice(oldOrder.getPurchasingPrice());
		orderRepositoty.save(newOrder);
		
		//shipping table
		ShippingEntity oldship= shippingRepository.getShipInfoFindByShippingId(orderId);
		ShippingEntity ship = new ShippingEntity();
		ship.setTrackingNumber(oldship.getTrackingNumber());
		ship.setCourierCompany(oldship.getCourierCompany());
		ship.setDeliveryTime(oldship.getDeliveryTime());
		ship.setShippingId(oldship.getShippingId());
		//設定 收貨時間
		ship.setReceiptTime(new Timestamp(System.currentTimeMillis()));
		shippingRepository.save(ship);
		
	}
}
