package com.shoping.kiku.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoping.kiku.entity.CommerceEntity;
import com.shoping.kiku.entity.MyOrderEntity;
import com.shoping.kiku.entity.MyOrderItemEntity;
import com.shoping.kiku.entity.OrderProInfoEntity;
import com.shoping.kiku.entity.ProductInCartEntity;
import com.shoping.kiku.object.OrderProInfoDto;
import com.shoping.kiku.repository.CommerceRepository;
import com.shoping.kiku.repository.MyOrderItemRepository;
import com.shoping.kiku.repository.MyOrderRepositoty;
import com.shoping.kiku.repository.OrderProInfoRepository;
import com.shoping.kiku.repository.PayPriceRepository;
import com.shoping.kiku.repository.ProductInCartRepository;
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
			order.setCreatedate(new Timestamp(System.currentTimeMillis()));
			order.setProductImg(ord.getProductImg());
			order.setProductContents(ord.getProductContents());
			order.setMaker(ord.getMaker());
			order.setStoreId(ord.getStoreId());
			order.setStoreName(ord.getStoreName());
			order.setShippingId(ord.getShippingId());
			order.setPaymentId(ord.getPaymentId());
			order.setOrderStatus(ord.getOrderStatus());
			order.setPurchasingPrice(ord.getPurchasingPrice());
			order.setModifyTime(null);

			orderList.add(order);
		}
		return orderList;

	}

	//支払い金額

	public int getPayPrice(int userId, int ProductId) {
		int price = payPriceRepository.getpriceByUserIdAndProId(userId, ProductId);
		return price;
	}

}
