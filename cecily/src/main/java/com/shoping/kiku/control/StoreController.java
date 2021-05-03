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
	@RequestMapping("/center/myshop/shopApply")
	public String storeApp(HttpServletRequest re, StoreDto st) {

		Session ss = (Session) re.getSession().getAttribute("userLogin");
		storeService.creStore(ss.getUserId(), st);
		
		return "redirect:/center/myshop";
	}

	/**
	 * 申込同意(ADMIN)
	 * @param storeId
	 * @return
	 */
	@RequestMapping("/center/storemanger/agree/{id}")
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
	@RequestMapping("/center/storemanger/stop/{id}")
	public String storeBlock(@PathVariable("id") int storeId) {

		storeService.blockStore(storeId);
		return "redirect:/center/storemanger";
	}

	/**
	 * 店舗回復(ADMIN)
	 * @param storeId
	 * @return
	 */
	@RequestMapping("/center/storemanger/recovery/{id}")
	public String storeRecovery(@PathVariable("id") int storeId) {

		storeService.agreeStore(storeId);
		return "redirect:/center/storemanger";
	}

}
