package com.shoping.kiku.control;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoping.kiku.object.StoreDto;
import com.shoping.kiku.service.StoreService;
import com.shoping.kiku.service.UserLoginService;
import com.shoping.kiku.until.Session;
import com.shoping.kiku.until.Url;

@Controller
public class StoreController {

	@Autowired
	StoreService storeService;
	
	@Autowired
	UserLoginService userLoginService;

	/**
	 * 出店申込(USER)
	 * @param re
	 * @param st
	 * @return
	 */
	@RequestMapping(Url.SHOPAPPLY)
	public String storeApp(HttpServletRequest re, StoreDto st) {

		Session ss = (Session) re.getSession().getAttribute("userLogin");
		storeService.creStore(ss.getUserId(), st);
		
		return "redirect:/center/myshop";
	}
	
	/**
	 * 店舗編集
	 * @param res
	 * @param store
	 * @return
	 */
	@RequestMapping(Url.SHOPUPDATE)
	public String storeUpdate(@PathVariable("id")int userId,StoreDto store) {
		storeService.updateStore(userId,store);
		return "redirect:/center/myshop";
	}
	
	/**
	 * 店舗編集(ADMIN)
	 * @param res
	 * @param store
	 * @return
	 */
	@RequestMapping(Url.SHOPEDIT)
	public String storeEdit(@PathVariable("id")int storeId,StoreDto store) {
		storeService.editStore(storeId,store);
		return "redirect:/center/storemanger";
	}
	

	/**
	 * 申込同意(ADMIN)
	 * @param storeId
	 * @return
	 */
	@RequestMapping(Url.AGREEAPP)
	public String storeAgree(@PathVariable("id") int storeId) {

		storeService.agreeStore(storeId);
		StoreDto store = storeService.findByStoreId(storeId);
		
		//ROLECHANGE
		userLoginService.roleChange(store.getUserId());
		
		return "redirect:/center/storemanger";
	}

	/**
	 * 店舗ブロック(ADMIN)
	 * @param storeId
	 * @return
	 */
	@RequestMapping(Url.STOPSHOP)
	public String storeBlock(@PathVariable("id") int storeId) {

		storeService.blockStore(storeId);
		return "redirect:/center/storemanger";
	}

	/**
	 * 店舗回復(ADMIN)
	 * @param storeId
	 * @return
	 */
	@RequestMapping(Url.RECOVERYSHOP)
	public String storeRecovery(@PathVariable("id") int storeId) {

		storeService.agreeStore(storeId);
		return "redirect:/center/storemanger";
	}

}
