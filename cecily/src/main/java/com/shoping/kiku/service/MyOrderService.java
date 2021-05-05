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
import com.shoping.kiku.entity.ProductInCartEntity;
import com.shoping.kiku.entity.ProductInfoForOrderIdEntity;
import com.shoping.kiku.object.OrderInfoByUserIdDto;
import com.shoping.kiku.object.OrderMangerDto;
import com.shoping.kiku.object.OrderProInfoDto;
import com.shoping.kiku.object.ProductInfoForOrderIdDto;
import com.shoping.kiku.repository.CommerceRepository;
import com.shoping.kiku.repository.MyOrderItemRepository;
import com.shoping.kiku.repository.MyOrderRepositoty;
import com.shoping.kiku.repository.OrderInfoByRepository;
import com.shoping.kiku.repository.OrderManagerRepository;
import com.shoping.kiku.repository.OrderProInfoRepository;
import com.shoping.kiku.repository.PayPriceRepository;
import com.shoping.kiku.repository.ProductInCartRepository;
import com.shoping.kiku.repository.ProductInfoForOrderIdRepository;
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

	//オーダーフォーム生成
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

	//myOrderItemTblから取得 (Commerceを通じてorderInfo取得)

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

	//支払い画面の支払い金額(まだ支払していない状態)

	public int getPayPrice(int userId, String orderId) {
		int price = payPriceRepository.getpriceByUserIdAndOrdId(userId, orderId);
		return price;
	}
	//支払い画面の支払い数量(まだ支払していない状態)
	public int getPayQuantiy(int userId, String orderId) {
		int quantity = payPriceRepository.getquantityByUserIdAndOrdId(userId, orderId);
		return quantity;
	}
	

	//取消订单

	public void changeOrderStatus() {

	}

	//orderManger (store)

	public List<OrderMangerDto> getOrderInfo(int storeId) {
		List<OrderManagerEntity> storeOr = orderManagerRepository.getOrderInfoWithStoreId(storeId);
		List<OrderMangerDto> odm = new ArrayList<>();
		for (OrderManagerEntity hh : storeOr) {
			OrderMangerDto dto = new OrderMangerDto();
			dto.setCreatedate(hh.getCreatedate());
			dto.setDcyoumebanchi(hh.getDcyoumebanchi());
			dto.setDshikucyouson(hh.getDshikucyouson());
			dto.setDtodoufuken(hh.getDtodoufuken());
			dto.setModifyTime(hh.getModifyTime());
			dto.setOrderId(hh.getOrderId());
			dto.setOrderStatus(hh.getOrderStatus());
			dto.setPaymentId(hh.getPaymentId());
			dto.setPayMethod(hh.getPayMethod());
			dto.setPayQuantity(hh.getPayQuantity());
			dto.setPayTime(hh.getCreatedate());
			dto.setPayTotal(hh.getPayTotal());
			dto.setProductId(hh.getProductId());
			dto.setProductImg(hh.getProductImg());
			dto.setProductName(hh.getProductName());
			dto.setProductPrice(hh.getProductPrice());
			dto.setProductQuantity(hh.getProductQuantity());
			dto.setPurchasingPrice(hh.getPurchasingPrice());
			dto.setShippingId(hh.getShippingId());
			dto.setStoreId(hh.getStoreId());
			dto.setUserId(hh.getUserId());
			dto.setRenrakuname(hh.getRenrakuname());
			dto.setRenrakuphone(hh.getRenrakuphone());

			odm.add(dto);
		}
		return odm;
	}

	//store
	/*	public List<OrderInfoByUserIdDto> getOrderInfoByStoreId(int storeId) {
			List<OrderInfoByUserIdEntity> orderId = groupByOrderIdRepository.getOrderInfoGroupByOrderIdInStoreId(storeId);
			List<OrderInfoByUserIdDto> orders = new ArrayList<>();
			for (OrderInfoByUserIdEntity group : orderId) {
				OrderInfoByUserIdDto dto = new OrderInfoByUserIdDto();
				dto.setOrderId(group.getOrderId());
				orders.add(dto);
			}
			return orders;
		}*/

	//userId　によって、オーダー情報を取得
	public List<OrderInfoByUserIdDto> getOrderInfoByUserId(int userId) {
		List<OrderInfoByUserIdEntity> orderId = groupByOrderIdRepository.getOrderInfoGroupByOrderIdByUserId(userId);
		
		
		List<OrderInfoByUserIdDto> orderIdList = new ArrayList<>();
		for (OrderInfoByUserIdEntity ord : orderId) {
			int qqt =payPriceRepository.getquantityByUserIdAndOrdId(userId, ord.getOrderId());
			int total =payPriceRepository.getpriceByUserIdAndOrdId(userId, ord.getOrderId());
			OrderInfoByUserIdDto dto = new OrderInfoByUserIdDto();
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
			
			orderIdList.add(dto);
		}
		return orderIdList;
	}

	//orderId によって、商品情報を取得
	public List<ProductInfoForOrderIdDto> getProductOrderInfoByOrderId(int userId) {
		List<OrderInfoByUserIdEntity> orderId = groupByOrderIdRepository.getOrderInfoGroupByOrderIdByUserId(userId);

		List<ProductInfoForOrderIdDto> proInfo = new ArrayList<>();
		for (OrderInfoByUserIdEntity oid : orderId) {
			List<ProductInfoForOrderIdEntity> xx = productInfoForOrderIdRepository
					.productInfoByOrderId(oid.getOrderId());
			for (ProductInfoForOrderIdEntity hh : xx) {
				ProductInfoForOrderIdDto dto = new ProductInfoForOrderIdDto();
				dto.setOrderId(hh.getOrderId());
				dto.setProductId(hh.getProductId());
				dto.setProductImg(hh.getProductImg());
				dto.setProductName(hh.getProductName());
				dto.setProductPrice(hh.getProductPrice());
				dto.setProductQuantity(hh.getProductQuantity());
				proInfo.add(dto);

			}

		}

		return proInfo;

	}

}
