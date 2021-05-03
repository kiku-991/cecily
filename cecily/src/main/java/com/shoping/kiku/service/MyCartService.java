package com.shoping.kiku.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoping.kiku.entity.MyCartEntity;
import com.shoping.kiku.entity.ProductInCartEntity;
import com.shoping.kiku.object.ProductInCartDto;
import com.shoping.kiku.repository.MyCartRepository;
import com.shoping.kiku.repository.ProductInCartRepository;
import com.shoping.kiku.repository.ProductRepository;

@Service
public class MyCartService {

	@Autowired
	MyCartRepository myCartRepository;

	@Autowired
	ProductInCartRepository productAndCartRepository;

	@Autowired
	ProductRepository productRepository;

	//SHOPPINGの買い物かご 商品を追加
	public int addCart(int productId, int userId) {
		//判断該当商品が買い物かごに追加されてるかどうか
		MyCartEntity cartCheck = myCartRepository.findByUserIdAndProductId(userId, productId);
		//追加なし 新規
		if (cartCheck == null) {
			MyCartEntity mycart = new MyCartEntity();
			mycart.setProductId(productId);
			mycart.setUserId(userId);
			mycart.setQuantity(1);
			mycart.setCheckstatus(0);
			myCartRepository.save(mycart);
			return 0;
		} else {
			//追加あり 更新数量
			int quantity = myCartRepository.getQuantity(userId, productId);
			MyCartEntity now = new MyCartEntity();
			now.setUserId(cartCheck.getUserId());
			now.setProductId(cartCheck.getProductId());
			now.setQuantity(quantity + 1);
			now.setCheckstatus(cartCheck.getCheckstatus());
			myCartRepository.save(now);
			return 1;
		}

	}

	/*//買い物かごに'+'ボタンを押下数量追加 (ストック考慮)
	public int updateInc(int userId, int productId) {
		int quantity = myCartRepository.getQuantity(userId, productId);
		ProductEntity pro = productRepository.findByProductId(productId);
		int oldStock = pro.getStock();
		ProductEntity stockpr = new ProductEntity();
		stockpr.setProductId(pro.getProductId());
		stockpr.setProductImg(pro.getProductImg());
		stockpr.setProductName(pro.getProductName());
		stockpr.setProductContents(pro.getProductContents());
		stockpr.setProductPrice(pro.getProductPrice());
		stockpr.setProductContents(pro.getProductContents());
		stockpr.setMaker(pro.getMaker());
		stockpr.setStatus(pro.getStatus());
		stockpr.setStoreId(pro.getStoreId());
		
		//ストック減少
		stockpr.setStock(oldStock-quantity);
		productRepository.save(stockpr);
		if (quantity <= oldStock) {
			MyCartEntity now = new MyCartEntity();
			now.setUserId(userId);
			now.setProductId(productId);
			now.setQuantity(quantity + 1);
			myCartRepository.save(now);
			return quantity;
		}else {
			
			return oldStock;
		}
	
	}*/
	
	//買い物かごに'+'ボタンを押下数量追加
	public void updateInc(int userId, int productId) {
		int quantity = myCartRepository.getQuantity(userId, productId);
		MyCartEntity now = new MyCartEntity();
		now.setUserId(userId);
		now.setProductId(productId);
		now.setQuantity(quantity + 1);
		myCartRepository.save(now);

	}

	//買い物かごに'-'ボタンを押下　数量減少
	public int updateDec(int userId, int productId) {
		int quantity = myCartRepository.getQuantity(userId, productId);
		MyCartEntity old = myCartRepository.findByUserIdAndProductId(userId, productId);
		//数量が１の時 削除
		if (quantity == 1) {
			myCartRepository.delete(old);
			return 0;
		} else {
			MyCartEntity now = new MyCartEntity();
			now.setUserId(old.getUserId());
			now.setProductId(old.getProductId());
			now.setQuantity(quantity - 1);
			myCartRepository.save(now);
			return 1;
		}

	}

	//checkbox が選択され時処理
	public void updateCheckStatus(int userId, int productId) {
		MyCartEntity old = myCartRepository.findByUserIdAndProductId(userId, productId);
		MyCartEntity now = new MyCartEntity();
		//チェックされる
		if (old.getCheckstatus() == 0) {
			now.setCheckstatus(1);
		} else {
			//チェック外す
			now.setCheckstatus(0);
		}
		now.setUserId(old.getUserId());
		now.setProductId(old.getProductId());
		now.setQuantity(old.getQuantity());
		myCartRepository.save(now);

	}

	//全部選択処理
	public void udateAllChecked(int userId) {
		List<MyCartEntity> old = myCartRepository.findByUserId(userId);
		List<MyCartEntity> nowCart = new ArrayList<>();
		for (MyCartEntity now : old) {
			MyCartEntity ee = new MyCartEntity();
			ee.setUserId(userId);
			ee.setProductId(now.getProductId());
			ee.setQuantity(now.getQuantity());
			ee.setCheckstatus(1);
			myCartRepository.save(ee);
			nowCart.add(ee);
		}

	}

	//Get 数量
	public int getcountInCartByUserId(int userId) {
		int count = myCartRepository.getCountByUserId(userId);
		return count;
	}

	//買い物かごにある商品を削除(单条削除)
	public void deleteCart(int userId, int productId) {
		MyCartEntity mcp = myCartRepository.findByUserIdAndProductId(userId, productId);
		myCartRepository.delete(mcp);
	}

	//該当ユーザの買い物かごにある商品を取得
	public List<ProductInCartDto> getAllProuctInCartByUserId(int userId) {
		List<ProductInCartEntity> prc = productAndCartRepository.getProCart(userId);
		List<ProductInCartDto> productsCart = new ArrayList<>();
		if (prc != null) {
			for (ProductInCartEntity pactt : prc) {
				ProductInCartDto pros = new ProductInCartDto();
				pros.setProductId(pactt.getProductId());
				pros.setProductName(pactt.getProductName());
				pros.setProductImg(pactt.getProductImg());
				pros.setProductPrice(pactt.getProductPrice());
				pros.setProductContents(pactt.getProductContents());
				pros.setUserId(pactt.getProductId());
				pros.setQuantity(pactt.getQuantity());
				pros.setTotal(pactt.getTotal());

				productsCart.add(pros);
			}

		} else {
			productsCart = null;
		}

		return productsCart;

	}

	//該当ユーザの買い物かごを取得
	public List<MyCartEntity> findAllCartsByUserId(int userId) {
		List<MyCartEntity> mc = myCartRepository.findByUserId(userId);
		return mc;
	}

	//すべての買い物かごにあるデータを取得(ADMIN)
	public List<MyCartEntity> findAllCart() {
		List<MyCartEntity> allUserCaits = myCartRepository.findAll();
		return allUserCaits;
	}

	//Get User 買い物かごにすべて商品の金額（総価格）
	public int getAmountInCart(int userId) {
		List<ProductInCartDto> prc = getAllProuctInCartByUserId(userId);
		int amount = 0;
		for (ProductInCartDto pro : prc) {
			amount = amount + pro.getTotal();
		}

		return amount;

	}

	//get 買い物かごにチェックされた商品の情報

	public int getCheckedPriceInCart(int userId) {
		//checked=1時 の商品
		List<ProductInCartEntity> prc = productAndCartRepository.getcheckedpro(userId);
		List<ProductInCartDto> pros = new ArrayList<>();
		int amount = 0;
		for (ProductInCartEntity pro : prc) {
			ProductInCartDto p = new ProductInCartDto();

			//商品の金額
			p.setTotal(pro.getTotal());
			//総価格
			p.setAmount(p.getTotal());
			int po = p.getAmount();
			amount = po + amount;
			pros.add(p);
		}

		return amount;
	}

	//get 買い物かごにチェックされた商品の数量
	public int getCheckedQuantityInCart(int userId) {
		int quantity = 0;
		List<ProductInCartEntity> prc = productAndCartRepository.getcheckedpro(userId);
		for (ProductInCartEntity pro : prc) {
			ProductInCartDto p = new ProductInCartDto();
			//商品の数量
			p.setQuantity(pro.getQuantity());
			int qu = p.getQuantity();
			quantity = qu + quantity;
		}
		return quantity;
	}

	//該当ユーザの買い物かごにcheckbox=1 のすべての商品取得(结算ボタンを押下処理)

	public List<ProductInCartDto> getCheckedProductInCart(int userId) {

		List<ProductInCartEntity> products = productAndCartRepository.getcheckedpro(userId);
		List<ProductInCartDto> product = new ArrayList<>();
		for (ProductInCartEntity pro : products) {
			ProductInCartDto pros = new ProductInCartDto();
			pros.setProductId(pro.getProductId());
			pros.setProductPrice(pro.getProductPrice());
			pros.setProductImg(pro.getProductImg());
			pros.setQuantity(pro.getQuantity());
			pros.setUserId(userId);
			pros.setProductName(pro.getProductName());
			pros.setProductContents(pro.getProductContents());
			pros.setTotal(pro.getTotal());

			product.add(pros);
		}
		return product;

	}

	//チェックされた商品を削除 提交订单ボタン押す時処理
	public void deleteCheckedPro(int userId) {

		List<MyCartEntity> checkedPro = myCartRepository.getCheckedPro(userId);

		myCartRepository.deleteAll(checkedPro);
	}

	//ユーザIDと商品IDによって、金額（TOTAL）を取得
	public int getTotalByUserIdAndProductId(int userId, int productId) {
		int total = productAndCartRepository.getTotalByUserIdAndProductId(userId, productId);

		return total;

	}

}