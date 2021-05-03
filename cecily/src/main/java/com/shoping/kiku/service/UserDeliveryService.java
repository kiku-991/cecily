package com.shoping.kiku.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoping.kiku.entity.UserDeliveryEntity;
import com.shoping.kiku.entity.UserLoginEntity;
import com.shoping.kiku.object.UserDeliveryDto;
import com.shoping.kiku.repository.UserDeliveryRepository;
import com.shoping.kiku.repository.UserLoginRepository;

@Service
public class UserDeliveryService {
	@Autowired
	UserDeliveryRepository userDeliveryRepository;
	@Autowired
	UserLoginRepository userLoginRepository;

	//CREATEユーザ届け(user)
	public void creUserDelivery(int userid, UserDeliveryDto user) {
		UserDeliveryEntity userDelivery = new UserDeliveryEntity();
		userDelivery.setUserId(userid);
		userDelivery.setDpostcode(user.getDpostcode());
		userDelivery.setDtodoufuken(user.getDtodoufuken());
		userDelivery.setDshikucyouson(user.getDshikucyouson());
		userDelivery.setDcyoumebanchi(user.getDcyoumebanchi());
		userDelivery.setRenrakuname(user.getRenrakuname());
		userDelivery.setRenrakuphone(user.getRenrakuphone());
		userDelivery.setDefaultadd(user.getDefaultadd());

		userDeliveryRepository.save(userDelivery);

	}

	//ユーザ届けを取得(user)

	public List<UserDeliveryDto> getUserDelivery(int userId) {
		List<UserDeliveryEntity> userDelivery = userDeliveryRepository.findByUserId(userId);
		List<UserDeliveryDto> userdelivery = new ArrayList<>();
		UserLoginEntity userlogin = userLoginRepository.findByUserId(userId);
		for (UserDeliveryEntity user : userDelivery) {
			UserDeliveryDto u = new UserDeliveryDto();
			u.setUserId(userId);
			u.setDeliveryId(user.getId());
			u.setUserMail(userlogin.getUserMail());
			u.setRenrakuname(user.getRenrakuname());
			u.setRenrakuphone(user.getRenrakuphone());
			u.setDpostcode(user.getDpostcode());
			u.setDtodoufuken(user.getDtodoufuken());
			u.setDshikucyouson(user.getDshikucyouson());
			u.setDcyoumebanchi(user.getDcyoumebanchi());
			u.setDefaultadd(user.getDefaultadd());
			userdelivery.add(u);

		}
		return userdelivery;
	}

	//ユーザ届けを編集(user)

	public void editUserDelivery(int deleveryId, int userId, UserDeliveryDto userDeliveryDto) {
		UserDeliveryEntity userDelivery = new UserDeliveryEntity();
		userDelivery.setId(deleveryId);
		userDelivery.setUserId(userId);
		userDelivery.setDpostcode(userDeliveryDto.getDpostcode());
		userDelivery.setDtodoufuken(userDeliveryDto.getDtodoufuken());
		userDelivery.setDshikucyouson(userDeliveryDto.getDshikucyouson());
		userDelivery.setDcyoumebanchi(userDeliveryDto.getDcyoumebanchi());
		userDelivery.setRenrakuname(userDeliveryDto.getRenrakuname());
		userDelivery.setRenrakuphone(userDeliveryDto.getRenrakuphone());
		userDelivery.setDefaultadd(userDeliveryDto.getDefaultadd());
		userDeliveryRepository.save(userDelivery);
	}

	//ユーザ届けを削除(user)
	public void deleteUserTodoke(int id) {

		userDeliveryRepository.deleteById(id);
	}

	//ユーザ届け一覧を取得(ADMIN）
	public List<UserDeliveryDto> getAllUserDelivery() {
		List<UserDeliveryEntity> usertt = userDeliveryRepository.findAll();
		List<UserDeliveryDto> usersDelivery = new ArrayList<>();
		for (UserDeliveryEntity users : usertt) {
			UserDeliveryDto u = new UserDeliveryDto();
			u.setUserId(users.getUserId());
			u.setDeliveryId(users.getId());
			u.setDpostcode(users.getDpostcode());
			u.setDtodoufuken(users.getDtodoufuken());
			u.setDshikucyouson(users.getDshikucyouson());
			u.setDcyoumebanchi(users.getDcyoumebanchi());
			u.setRenrakuname(users.getRenrakuname());
			u.setRenrakuphone(users.getRenrakuphone());
			u.setDefaultadd(users.getDefaultadd());
			usersDelivery.add(u);
		}
		return usersDelivery;
	}

	//ユーザデフォルト住所を取得(Buy画面とMyOrder画面)
	public UserDeliveryDto getDefaultadd(int userId) {
		UserDeliveryEntity users = userDeliveryRepository.getDefaultAddByUserId(userId);
		UserDeliveryDto userDefaultAdd = new UserDeliveryDto();
		userDefaultAdd.setUserId(users.getUserId());
		userDefaultAdd.setDeliveryId(users.getId());
		userDefaultAdd.setDpostcode(users.getDpostcode());
		userDefaultAdd.setDtodoufuken(users.getDtodoufuken());
		userDefaultAdd.setDshikucyouson(users.getDshikucyouson());
		userDefaultAdd.setDcyoumebanchi(users.getDcyoumebanchi());
		userDefaultAdd.setRenrakuname(users.getRenrakuname());
		userDefaultAdd.setRenrakuphone(users.getRenrakuphone());
		userDefaultAdd.setDefaultadd(users.getDefaultadd());
		return userDefaultAdd;

	}

}
