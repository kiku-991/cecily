package com.shoping.kiku.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoping.kiku.entity.UserInfoEntity;
import com.shoping.kiku.entity.UserLoginEntity;
import com.shoping.kiku.object.UserInfoDto;
import com.shoping.kiku.repository.UserInfoRepository;
import com.shoping.kiku.repository.UserLoginRepository;
import com.shoping.kiku.until.Session;
import com.shoping.kiku.until.Url;

@Service
public class UserInfoService {
	

		@Autowired
		UserInfoRepository userInfoRepository;

		@Autowired
		UserLoginRepository userLoginRepository;

		/**
		 * ユーザ情報登録・変更
		 * @param userDto
		 * @param userInfo
		 */
		public void creatUserInfo(HttpServletRequest request, UserInfoDto userInfo) {
			Session ss = (Session) request.getSession().getAttribute("userLogin");
			int userId = ss.getUserId();
			UserInfoEntity userInfott = userInfoRepository.findById(userId);
			if (userInfott == null) {
				UserInfoEntity userInfoEntity = new UserInfoEntity();
				//個人情報用
				userInfoEntity.setId(userId);
				userInfoEntity.setName(userInfo.getName());
				userInfoEntity.setSex(userInfo.getSex());
				userInfoEntity.setBirth(userInfo.getBirth());
				userInfoEntity.setPhone(userInfo.getPhone());
				userInfoEntity.setNickname(userInfo.getNickname());
				userInfoEntity.setPostcode(userInfo.getPostcode());
				userInfoEntity.setTodoufuken(userInfo.getTodoufuken());
				userInfoEntity.setShikucyouson(userInfo.getShikucyouson());
				userInfoEntity.setCyoumebanchi(userInfo.getCyoumebanchi());
				userInfoEntity.setIcon(Url.SRC + userInfo.getIcon());
				userInfoRepository.save(userInfoEntity);
			}

		}

		/**
		 * Get 該ユーザ情報
		 * @param session
		 * @return userinfo
		 */
		public UserInfoDto getUserInfo(HttpServletRequest request) throws Exception {
			Session session = (Session) request.getSession().getAttribute("userLogin");
			int userId =session.getUserId();
			UserLoginEntity usertt = userLoginRepository.findByUserId(userId);
			UserInfoEntity userInfo = userInfoRepository.findById(userId);

			UserInfoDto userinfodto = new UserInfoDto();
			
			//基本情報
			
			if(userInfo!=null) {
				userinfodto.setId(usertt.getUserId());
				userinfodto.setUserMail(usertt.getUserMail());
				userinfodto.setStatus(usertt.getStatus());
				userinfodto.setName(userInfo.getName());
				userinfodto.setNickname(userInfo.getNickname());
				userinfodto.setSex(userInfo.getSex());
				userinfodto.setTodoufuken(userInfo.getTodoufuken());
				userinfodto.setShikucyouson(userInfo.getShikucyouson());
				userinfodto.setCyoumebanchi(userInfo.getCyoumebanchi());
				userinfodto.setPhone(userInfo.getPhone());
				userinfodto.setBirth(userInfo.getBirth());
				userinfodto.setIcon(userInfo.getIcon());
			}else {
				userinfodto=null;
			}
			
			
			return userinfodto;

		}

		/**
		 * ユーザ情報更新
		 * @param session
		 * @param userInfoDto
		 */
		public void updateUserInfo(HttpServletRequest request, UserInfoDto userInfoDto) {

			Session ss = (Session) request.getSession().getAttribute("userLogin");
			UserInfoEntity userInfott = userInfoRepository.findById(ss.getUserId());
			UserInfoEntity userInfoEntity = new UserInfoEntity();
			userInfoEntity.setId(ss.getUserId());
			//基本情報
			userInfoEntity.setSex(userInfoDto.getSex());
			userInfoEntity.setBirth(userInfoDto.getBirth());
			userInfoEntity.setName(userInfoDto.getName());
			userInfoEntity.setNickname(userInfoDto.getNickname());
			userInfoEntity.setPhone(userInfoDto.getPhone());
			userInfoEntity.setPostcode(userInfoDto.getPostcode());
			userInfoEntity.setTodoufuken(userInfoDto.getTodoufuken());
			userInfoEntity.setShikucyouson(userInfoDto.getShikucyouson());
			userInfoEntity.setCyoumebanchi(userInfoDto.getCyoumebanchi());

			//アイコン
			if (userInfoDto.getIcon().equals("")) {
				userInfoEntity.setIcon(userInfott.getIcon());
			} else {
				userInfoEntity.setIcon(Url.SRC + userInfoDto.getIcon());
			}
			
			
			userInfoRepository.save(userInfoEntity);
		}

		
		/**
		 * ユーザー情報 全検索(ADMIN)
		 * @return 検索結果
		 */
		public List<UserInfoDto> serchAll() {
			List<UserInfoEntity> list = userInfoRepository.findAllByOrderByIdAsc();
			List<UserInfoDto> usersInfo = new ArrayList<UserInfoDto>();
			for (UserInfoEntity userinfo : list) {
				UserInfoDto userInfo = new UserInfoDto();
				UserLoginEntity user = userLoginRepository.findById(userinfo.getId()).get();
				userInfo.setId(user.getUserId());
				userInfo.setUserMail(user.getUserMail());
				userInfo.setStatus(user.getStatus());
				userInfo.setRole(user.getRole());
				userInfo.setName(userinfo.getName());
				userInfo.setNickname(userinfo.getNickname());
				userInfo.setSex(userinfo.getSex());
				userInfo.setPhone(userinfo.getPhone());
				userInfo.setBirth(userinfo.getBirth());
				userInfo.setIcon(userinfo.getIcon());
				userInfo.setTodoufuken(userinfo.getTodoufuken());
				userInfo.setShikucyouson(userinfo.getShikucyouson());
				userInfo.setCyoumebanchi(userinfo.getCyoumebanchi());
				usersInfo.add(userInfo);

			}
			return usersInfo;

		}

		/**
		 * ユーザー情報編集(ADMIN)
		 * 
		 */
		public void updateAllUserInfo(int userid, UserInfoDto userInfodto) {

			UserInfoEntity userInfott = userInfoRepository.findById(userid);
			UserInfoEntity userInfoEntity = new UserInfoEntity();
			userInfoEntity.setId(userInfott.getId());
			//基本情報
			userInfoEntity.setSex(userInfodto.getSex());
			userInfoEntity.setBirth(userInfodto.getBirth());
			userInfoEntity.setName(userInfodto.getName());
			userInfoEntity.setNickname(userInfodto.getNickname());
			userInfoEntity.setPhone(userInfodto.getPhone());
			userInfoEntity.setTodoufuken(userInfodto.getTodoufuken());
			userInfoEntity.setShikucyouson(userInfodto.getShikucyouson());
			userInfoEntity.setCyoumebanchi(userInfodto.getCyoumebanchi());

			//アイコン
			if (userInfodto.getIcon().equals("")) {
				userInfoEntity.setIcon(userInfott.getIcon());
			} else {
				userInfoEntity.setIcon(Url.SRC + userInfodto.getIcon());
			}

		
			userInfoRepository.save(userInfoEntity);

			//ユーザ情報更新時ユーザの更新時間も変更

			UserLoginEntity usertt = userLoginRepository.findByUserId(userid);
			UserLoginEntity user = new UserLoginEntity();
			user.setUserId(usertt.getUserId());
			user.setRole(usertt.getRole());
			user.setStatus(usertt.getStatus());
			user.setUserMail(usertt.getUserMail());
			user.setUserPassword(usertt.getUserPassword());
			user.setCreateDate(usertt.getCreateDate());
			user.setUpdateDate(new Timestamp(System.currentTimeMillis()));
			userLoginRepository.save(user);
		}

		/**
		 * すべてのユーザ届け住所編集
		 * @param userid
		 * @param userInfodto
		 */
		public void updateAllUserAddInfo(int userid, UserInfoDto userInfodto) {
			UserInfoEntity userInfott = userInfoRepository.findById(userid);
			UserInfoEntity userInfoEntity = new UserInfoEntity();
			//基本情報
			userInfoEntity.setId(userInfott.getId());
			userInfoEntity.setSex(userInfott.getSex());
			userInfoEntity.setBirth(userInfott.getBirth());
			userInfoEntity.setName(userInfott.getName());
			userInfoEntity.setNickname(userInfott.getNickname());
			userInfoEntity.setPhone(userInfott.getPhone());
			userInfoEntity.setTodoufuken(userInfott.getTodoufuken());
			userInfoEntity.setShikucyouson(userInfott.getShikucyouson());
			userInfoEntity.setCyoumebanchi(userInfott.getCyoumebanchi());
			userInfoEntity.setIcon(userInfott.getIcon());
			userInfoRepository.save(userInfoEntity);

			//ユーザ届け住所情報更新時ユーザの更新時間も変更

			UserLoginEntity usertt = userLoginRepository.findByUserId(userid);
			UserLoginEntity user = new UserLoginEntity();
			user.setUserId(usertt.getUserId());
			user.setRole(usertt.getRole());
			user.setStatus(usertt.getStatus());
			user.setUserMail(usertt.getUserMail());
			user.setUserPassword(usertt.getUserPassword());
			user.setCreateDate(usertt.getCreateDate());
			user.setUpdateDate(new Timestamp(System.currentTimeMillis()));
			userLoginRepository.save(user);
			
		}
		/*
			//我的订单页面でユーザ届け住所削除
			public void deleteTodoke(int userId) {
				UserInfoEntity user = userInfoRepository.findById(userId);
				userInfoRepository.delete(user);
			}*/
	
}
