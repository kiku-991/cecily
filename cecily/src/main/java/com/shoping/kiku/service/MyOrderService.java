package com.shoping.kiku.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoping.kiku.entity.MyOrderEntity;
import com.shoping.kiku.entity.ProductInCartEntity;
import com.shoping.kiku.object.MyOrderDto;
import com.shoping.kiku.repository.MyOrderRepositoty;
import com.shoping.kiku.repository.ProductInCartRepository;
import com.shoping.kiku.until.OrderUtils;

@Service
public class MyOrderService {

	@Autowired
	MyOrderRepositoty orderRepositoty;
	@Autowired
	ProductInCartRepository productAndCartRepository;


	//オーダーフォーム生成
	public void createOrderForm(MyOrderDto oder, int userId) {
		//自動生成
		String orderId = OrderUtils.getOrderCode(userId);
		List<ProductInCartEntity> products = productAndCartRepository.getcheckedpro(userId);
		for (ProductInCartEntity ord : products) {
			MyOrderEntity order = new MyOrderEntity();
			order.setOrderId(orderId);
			//提交订单的时候把订单状态设为 0 待付款
			order.setOrderStatus(0);
			//总额
			order.setPurchasingPrice(ord.getTotal());
			//创建订单时间
			order.setCreateTime(new Timestamp(System.currentTimeMillis()));
			
		
			orderRepositoty.save(order);
		}

	}

	//myOrderTblから取得 UserId
	public void getOrderInfoByUserId(int userId) {
		/*List<OrdAndProAndUInfoEntity> orderInfo = ordUserProRepository.getInfoByUserId(userId);
		List<UserOrderInfo> orderDto = new ArrayList<>();
		for (OrdAndProAndUInfoEntity order : orderInfo) {
			UserOrderInfo ord = new UserOrderInfo();
			ord.setProductId(order.getProductId());
			ord.setProductImg(order.getProductImg());
			ord.setProductName(order.getProductName());
			ord.setProductPrice(order.getProductPrice());
			ord.setOrderId(order.getOrderId());
			ord.setNickname(order.getNickname());
			ord.setRenrakuname(order.getRenrakuname());
			ord.setRenrekuphone(order.getRenrekuphone());
			ord.setDcyoumebanchi(order.getDcyoumebanchi());
			ord.setDshikucyouson(order.getDshikucyouson());
			ord.setDtodoufuken(order.getDtodoufuken());
			ord.setOrderStatus(order.getOrderStatus());
			ord.setPaymentMethod(order.getPaymentMethod());
			ord.setPaymentTime(order.getPaymentTime());
			ord.setPurchasingPrice(order.getPurchasingPrice());
			ord.setPurchasingQuantity(order.getPurchasingQuantity());
			ord.setCreateTime(order.getCreateTime());
			ord.setConfirmTime(order.getConfirmTime());
			ord.setCanceleTime(order.getCanceleTime());
			ord.setUpdateTime(order.getUpdateTime());
			ord.setUserId(order.getUserId());
			orderDto.add(ord);
		}
		return orderDto;*/

	}

}
