package com.shoping.kiku.control;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shoping.kiku.service.FavoriteService;
import com.shoping.kiku.until.Session;

@Controller
public class FavoriteController {

	@Autowired
	FavoriteService favoriteService;

	//気に入りボタンイベント

	@RequestMapping("/part/myfavorite")
	@ResponseBody
	public int kiniClick(@RequestBody String productId,
			HttpServletRequest request) {
		Session session = (Session) request.getSession().getAttribute("userLogin");
		String id = productId.replace("id=", "");

		int proId = Integer.valueOf(id);
		//ログインしなくても気に入りボタン押下可能 DBにデータをINSERTRだめ

		if (session != null) {
			//判断気に入り状態
			int favoriteCheck = favoriteService.creFavorite(session.getUserId(), proId);
			//気に入り追加 気に入り一覧に追加
			if (favoriteCheck == 1) {
				return proId;
			} else {
				//気に入りキャンセル 気に入り一覧に削除
				favoriteService.cancelFavorite(session.getUserId(), proId);
				proId = 0;

			}
		} else {
			return -1;
		}
		return proId;
	}

	@RequestMapping("/center/favorite/delete/{id}")
	public String deleteFavo(@PathVariable("id") int id, HttpServletRequest re) {
		Session ss = (Session) re.getSession().getAttribute("userLogin");
		favoriteService.cancelFavorite(ss.getUserId(),id);

		return "redirect:/center/favorite";
	}
}
