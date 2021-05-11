package com.shoping.kiku.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoping.kiku.entity.MyCartEntity;
import com.shoping.kiku.entity.ProductEntity;
import com.shoping.kiku.entity.ProductInCartEntity;
import com.shoping.kiku.object.ProductInCartDto;
import com.shoping.kiku.repository.MyCartRepository;
import com.shoping.kiku.repository.ProductInCartRepository;
import com.shoping.kiku.repository.ProductRepository;
import com.shoping.kiku.until.Status;

@Service
public class MyCartService {

	@Autowired
	MyCartRepository myCartRepository;

	@Autowired
	ProductInCartRepository productAndCartRepository;

	@Autowired
	ProductRepository productRepository;

	
	/**
	 * SHOPPINGの買い物かご 商品を追加
	 * @param productId
	 * @param userId
	 * @return
	 */
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

	/**
	 * 買い物かごに'+'ボタンを押下数量追加
	 * @param userId
	 * @param productId
	 * @return
	 */
	public boolean updateInc(int userId, int productId) {
		int quantity = myCartRepository.getQuantity(userId, productId);
		//'+'ボタンを押下時数量
		int plusQuantity = quantity + 1;

		ProductEntity pro = productRepository.findByProductId(productId);
		//該当商品のストック
		int stock = pro.getStock();
		MyCartEntity now = new MyCartEntity();
		now.setUserId(userId);
		now.setProductId(productId);
		//ストック判断
		if (plusQuantity > stock) {
			//超える場合
			now.setQuantity(stock);
			myCartRepository.save(now);
			return false;
		} else {
			////超えない場合
			now.setQuantity(plusQuantity);
			myCartRepository.save(now);
			return true;
		}

	}

	/**
	 * 買い物かごに'-'ボタンを押下　数量減少
	 * @param userId
	 * @param productId
	 * @return
	 */
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

	/**
	 * checkbox が選択され時処理
	 * @param userId
	 * @param productId
	 */
	public void updateCheckStatus(int userId, int productId) {
		MyCartEntity old = myCartRepository.findByUserIdAndProductId(userId, productId);
		MyCartEntity now = new MyCartEntity();
		//チェックされる
		if (old.getCheckstatus() == 0) {
			now.setCheckstatus(Status.CHECKED);
		} else {
			//チェック外す
			now.setCheckstatus(Status.CHECKOUT);
		}
		now.setUserId(old.getUserId());
		now.setProductId(old.getProductId());
		now.setQuantity(old.getQuantity());
		myCartRepository.save(now);

	}

	/**
	 * 全部のチェックボックスが選択され処理
	 * @param userId
	 */
	public void udateAllChecked(int userId) {
		List<MyCartEntity> old = myCartRepository.findByUserId(userId);
		List<MyCartEntity> nowCart = new ArrayList<>();
		for (MyCartEntity now : old) {
			MyCartEntity ee = new MyCartEntity();
			ee.setUserId(userId);
			ee.setProductId(now.getProductId());
			ee.setQuantity(now.getQuantity());
			//チェックされる
			if (now.getCheckstatus() == 0) {
				ee.setCheckstatus(Status.CHECKED);
			} else {
				//チェック外す
				ee.setCheckstatus(Status.CHECKOUT);
			}

			myCartRepository.save(ee);
			nowCart.add(ee);
		}

	}

	/**
	 * Get 買い物かごの数量
	 * @param userId
	 * @return
	 */
	public int getcountInCartByUserId(int userId) {
		int count = myCartRepository.getCountByUserId(userId);
		return count;
	}

	/**
	 * 買い物かごにある商品を削除(单条削除)
	 * @param userId
	 * @param productId
	 */
	public void deleteCart(int userId, int productId) {
		MyCartEntity mcp = myCartRepository.findByUserIdAndProductId(userId, productId);
		myCartRepository.delete(mcp);
	}

	/**
	 * 該当ユーザの買い物かごにある商品を取得
	 * @param userId
	 * @return
	 */
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

	/**
	 * 該当ユーザの買い物かごを取得
	 * @param userId
	 * @return
	 */
	public List<MyCartEntity> findAllCartsByUserId(int userId) {
		List<MyCartEntity> mc = myCartRepository.findByUserId(userId);
		return mc;
	}

	/**
	 * すべての買い物かごにあるデータを取得(ADMIN)
	 * @return
	 */
	public List<MyCartEntity> findAllCart() {
		List<MyCartEntity> allUserCaits = myCartRepository.findAll();
		return allUserCaits;
	}

	/**
	 * Get User 買い物かごにすべて商品の金額（総価格）
	 * @param userId
	 * @return
	 */
	public int getAmountInCart(int userId) {
		List<ProductInCartDto> prc = getAllProuctInCartByUserId(userId);
		int amount = 0;
		for (ProductInCartDto pro : prc) {
			amount = amount + pro.getTotal();
		}

		return amount;

	}


	/**
	 * get 買い物かごにチェックされた商品の情報
	 * @param userId
	 * @return
	 */
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

	/**
	 * get 買い物かごにチェックされた商品の数量
	 * @param userId
	 * @return
	 */
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


	/**
	 * 該当ユーザの買い物かごにcheckbox=1 のすべての商品取得(结算ボタンを押下処理)
	 * @param userId
	 * @return
	 */
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

	/**
	 * 該当ユーザの買い物かごにcheckbox=1 のすべての商品の数量取得(ストック減少)
	 * @param userId
	 */
	public void desProStock(int userId) {
		//チェックされた商品を取得
		List<MyCartEntity> products = myCartRepository.getCheckedPro(userId);
		for (MyCartEntity pro : products) {
			MyCartEntity cart = new MyCartEntity();
			cart.setProductId(pro.getProductId());
			cart.setQuantity(pro.getQuantity());
			int quantity = cart.getQuantity();
			ProductEntity product = productRepository.findByProductId(cart.getProductId());
			int old = product.getStock();
			ProductEntity newPro = new ProductEntity();
			newPro.setProductContents(product.getProductContents());
			newPro.setProductId(product.getProductId());
			newPro.setProductImg(product.getProductImg());
			newPro.setProductName(product.getProductName());
			newPro.setProductPrice(product.getProductPrice());
			newPro.setStock(old - quantity);
			newPro.setMaker(product.getMaker());
			newPro.setStoreId(product.getStoreId());
			newPro.setStatus(product.getStatus());
			productRepository.save(newPro);
		}

	}

	/**
	 * チェックされた商品を削除 提交订单ボタン押す時処理
	 * @param userId
	 */
	public void deleteCheckedPro(int userId) {

		List<MyCartEntity> checkedPro = myCartRepository.getCheckedPro(userId);

		myCartRepository.deleteAll(checkedPro);
	}

	/**
	 * ユーザIDと商品IDによって、金額（TOTAL）を取得
	 * @param userId
	 * @param productId
	 * @return
	 */
	public int getTotalByUserIdAndProductId(int userId, int productId) {
		int total = productAndCartRepository.getTotalByUserIdAndProductId(userId, productId);
		return total;

	}

}