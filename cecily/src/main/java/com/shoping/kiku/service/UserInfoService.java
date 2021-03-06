package com.shoping.kiku.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoping.kiku.entity.PwdReSetEntity;
import com.shoping.kiku.entity.UserInfoEntity;
import com.shoping.kiku.entity.UserLoginEntity;
import com.shoping.kiku.object.UserInfoDto;
import com.shoping.kiku.repository.PwdReSetRepository;
import com.shoping.kiku.repository.UserInfoRepository;
import com.shoping.kiku.repository.UserLoginRepository;
import com.shoping.kiku.until.PwdHashing;
import com.shoping.kiku.until.Session;

@Service
public class UserInfoService {

	@Autowired
	UserInfoRepository userInfoRepository;

	@Autowired
	UserLoginRepository userLoginRepository;

	@Autowired
	PwdReSetRepository pwdReSetRepository;

	/**
	 * ユーザ情報登録・変更
	 * @param userDto
	 * @param userInfo
	 */
	public void creatUserInfo(int userId, String icon, UserInfoDto userInfo) {

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
			userInfoEntity.setIcon(icon);
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
		int userId = session.getUserId();
		UserLoginEntity usertt = userLoginRepository.findByUserId(userId);
		UserInfoEntity userInfo = userInfoRepository.findById(userId);

		UserInfoDto userinfodto = new UserInfoDto();

		//基本情報

		if (userInfo != null) {
			userinfodto.setId(usertt.getUserId());
			userinfodto.setUserMail(usertt.getUserMail());
			userinfodto.setStatus(usertt.getStatus());
			userinfodto.setName(userInfo.getName());
			userinfodto.setNickname(userInfo.getNickname());
			userinfodto.setSex(userInfo.getSex());
			userinfodto.setPostcode(userInfo.getPostcode());
			userinfodto.setTodoufuken(userInfo.getTodoufuken());
			userinfodto.setShikucyouson(userInfo.getShikucyouson());
			userinfodto.setCyoumebanchi(userInfo.getCyoumebanchi());
			userinfodto.setPhone(userInfo.getPhone());
			userinfodto.setBirth(userInfo.getBirth());
			userinfodto.setIcon(userInfo.getIcon());
		} else {
			userinfodto = null;
		}

		return userinfodto;

	}

	/**
	 * ユーザ情報更新
	 * @param session
	 * @param userInfoDto
	 */
	public void updateUserInfo(int userId, String icon, UserInfoDto userInfoDto) {

		UserInfoEntity userInfott = userInfoRepository.findById(userId);
		UserInfoEntity userInfoEntity = new UserInfoEntity();
		userInfoEntity.setId(userId);
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
		if (icon.equals("")) {
			userInfoEntity.setIcon(userInfott.getIcon());
		} else {
			userInfoEntity.setIcon(icon);
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
	 * @param userid
	 * @param userInfodto
	 */
	public void updateAllUserInfo(int userid, UserInfoDto userInfodto, String icon) {

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
		if (icon.equals("")) {
			userInfoEntity.setIcon(userInfott.getIcon());
		} else {
			userInfoEntity.setIcon(icon);
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

	/**
	 * アイコンアップロード
	 * @param userId
	 * @param userUrl
	 */
	public void updateUserUserUrl(UserInfoDto user, int userId) {

		UserInfoEntity userInfott = userInfoRepository.findById(userId);
		UserInfoEntity userInfoEntity = new UserInfoEntity();
		//基本情報
		userInfoEntity.setId(userId);
		userInfoEntity.setIcon(user.getIcon());

		//
		userInfoEntity.setSex(userInfott.getSex());
		userInfoEntity.setBirth(userInfott.getBirth());
		userInfoEntity.setName(userInfott.getName());
		userInfoEntity.setNickname(userInfott.getNickname());
		userInfoEntity.setPhone(userInfott.getPhone());
		userInfoEntity.setTodoufuken(userInfott.getTodoufuken());
		userInfoEntity.setShikucyouson(userInfott.getShikucyouson());
		userInfoEntity.setCyoumebanchi(userInfott.getCyoumebanchi());
		userInfoEntity.setPostcode(userInfott.getPostcode());

		userInfoRepository.save(userInfoEntity);

	}

	/**
	 * パスポート再設定
	 * @param name
	 * @param mail
	 * @param pwd
	 */
	public int pwdReset(String name, String mail, String pwd) {

		PwdReSetEntity xx = pwdReSetRepository.getUserInfoByUserMail(mail,name);
		String password = PwdHashing.pwdEnCode(pwd);
		if(xx!=null) {
			UserLoginEntity old = userLoginRepository.findByUserMail(mail);
			UserLoginEntity user = new UserLoginEntity();
			user.setCreateDate(old.getCreateDate());
			user.setRole(old.getRole());
			user.setStatus(old.getStatus());
			user.setUpdateDate(new Timestamp(System.currentTimeMillis()));
			user.setUserId(old.getUserId());
			user.setUserMail(mail);
			user.setUserPassword(password);
			userLoginRepository.save(user);
			return 1;
		}
		return 0;
	}
}
