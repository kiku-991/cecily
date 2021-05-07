package com.shoping.kiku.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoping.kiku.entity.UserLoginEntity;
import com.shoping.kiku.object.UserLoginDto;
import com.shoping.kiku.repository.UserInfoRepository;
import com.shoping.kiku.repository.UserLoginRepository;
import com.shoping.kiku.until.MsgContents;
import com.shoping.kiku.until.PwdHashing;
import com.shoping.kiku.until.Session;
import com.shoping.kiku.until.Status;

@Service
public class UserLoginService {

	@Autowired
	UserLoginRepository userLoginRepository;

	@Autowired
	UserInfoRepository userInfoRepository;

	/**
	 * ログインチェック
	 * @param user
	 * @return
	 */
	public String loginProcess(UserLoginDto user) {

		UserLoginEntity um = userLoginRepository.findByUserMail(user.getUserMail());
		String pwd = PwdHashing.pwdEnCode(user.getUserPassword());
		//入力したメールアドレスとパスワードとDBのデータが一致する場合
		if (!um.getUserMail().equals(user.getUserMail()) ||
				!um.getUserPassword().equals(pwd)) {

			return MsgContents.CHECKFALSE;
		} else {
			//ログインさせ
			return MsgContents.CHECKTRUE;
		}

	}

	/**
	 * ユーザ作成
	 * @param user
	 * @return 0 作成成功 １作成失敗
	 */
	public int createUser(UserLoginDto user) {

		UserLoginEntity ue = userLoginRepository.findByUserMail(user.getUserMail());
		UserLoginEntity entity = new UserLoginEntity();
		//新規メールアドレス
		if (ue == null) {
			entity.setUserId(userLoginRepository.getID());
			entity.setUserMail(user.getUserMail());
			entity.setUserPassword(user.getUserPassword());
			entity.setCreateDate(new Timestamp(System.currentTimeMillis()));

			entity.setRole("user");
			entity.setStatus(Status.USERNOMAL);
			userLoginRepository.save(entity);
			return 0;

		} else {
			//入力したメールアドレスがDBにすでに存在(登録)
			//入力したメールアドレスが登録されいない(ログイン)
			return 1;
		}

	}

	/**
	 * メール存在するチェック
	 * @param user
	 * @return
	 */
	public String mailCheck(UserLoginDto user) {
		UserLoginEntity ue = userLoginRepository.findByUserMail(user.getUserMail());

		if (ue == null) {

			return MsgContents.MAILNO;
		} else {
			return MsgContents.MAILARI;
		}
	}

	/**
	 * パスワード変更
	 * @param session
	 * @param userDto
	 */

	public boolean passChange(HttpSession session, UserLoginDto userDto) {
		Session ss = (Session) session.getAttribute("userLogin");
		UserLoginEntity user = userLoginRepository.findByUserId(ss.getUserId());
		UserLoginEntity usertt = new UserLoginEntity();
		//暗号化
		String pwd = PwdHashing.pwdEnCode(userDto.getUserPassword());
		String oldpwd = PwdHashing.pwdEnCode(userDto.getOldPassword());
		if (oldpwd.equals(user.getUserPassword())) {
			usertt.setUserPassword(pwd);
			usertt.setUserId(user.getUserId());
			usertt.setCreateDate(user.getCreateDate());
			usertt.setUserMail(user.getUserMail());
			usertt.setRole(user.getRole());
			usertt.setStatus(user.getStatus());
			usertt.setUpdateDate(new Timestamp(System.currentTimeMillis()));
			userLoginRepository.save(usertt);
			return true;

		} else {
			return false;
		}
	}

	/**
	 * get セッションmail
	 * @param request
	 * @return
	 */
	public String getMail(HttpServletRequest request) {
		Session ss = (Session) request.getSession().getAttribute("userLogin");
		String mail = ss.getUserMail();
		return mail;
	}

	/**
	 * get DBのID
	 * @param userMail
	 * @return
	 */
	public int getId(String userMail) {

		return userLoginRepository.findByUserMail(userMail).getUserId();
	}

	/**
	 * get DBのPwd
	 * @param userMail
	 * @return
	 */
	public String getPwd(String userMail) {

		return userLoginRepository.findByUserMail(userMail).getUserPassword();
	}

	/**
	 * get DBのRole
	 * @param userMail
	 * @return
	 */
	public String getRole(String userMail) {

		return userLoginRepository.findByUserMail(userMail).getRole();
	}

	/**
	 * 店舗申込Role変更
	 * @param userId
	 */
	public void roleChange(int userId) {

		UserLoginEntity user = userLoginRepository.findByUserId(userId);
		String newRole = "store";
		UserLoginEntity usertt = new UserLoginEntity();
		usertt.setUserId(user.getUserId());
		usertt.setCreateDate(user.getCreateDate());
		usertt.setUserMail(user.getUserMail());
		usertt.setUserPassword(user.getUserPassword());
		usertt.setRole(newRole);
		usertt.setStatus(user.getStatus());
		usertt.setUpdateDate(new Timestamp(System.currentTimeMillis()));
		userLoginRepository.save(usertt);

	}

	/**
	 * get DBのStatus
	 * @param userMail
	 * @return
	 */
	public Integer getStatus(String userMail) {

		return userLoginRepository.findByUserMail(userMail).getStatus();
	}

	/**
	 * ユーザ情報編集(ADMIN)
	 * @param userid
	 * @param userdto
	 */
	public void changerUser(int userid, UserLoginDto userdto) {

		UserLoginEntity usertt = userLoginRepository.findByUserId(userid);
		UserLoginEntity us = new UserLoginEntity();
		us.setUserId(usertt.getUserId());
		us.setUserMail(usertt.getUserMail());
		us.setCreateDate(usertt.getCreateDate());
		us.setUserPassword(usertt.getUserPassword());
		//ROLE STATUS 編集
		us.setRole(userdto.getRole());
		us.setStatus(userdto.getStatus());
		us.setUpdateDate(new Timestamp(System.currentTimeMillis()));
		userLoginRepository.save(us);
	}

	/**
	 * ユーザIDによってユーザを削除
	 * @param userid
	 */
	public void deleteUser(int userid) {

		userLoginRepository.deleteById(userid);
		userInfoRepository.deleteById(userid);
	}

	/**
	 * すべてのユーザを取得
	 * @return
	 */
	public List<UserLoginDto> getAllUser() {
		List<UserLoginEntity> userlist = userLoginRepository.findAllUser();
		List<UserLoginDto> user = new ArrayList<UserLoginDto>();
		for (UserLoginEntity u : userlist) {
			UserLoginDto users = new UserLoginDto();
			users.setUserId(u.getUserId());
			users.setUserMail(u.getUserMail());
			users.setStatus(u.getStatus());
			users.setRole(u.getRole());
			users.setCreateDate(u.getCreateDate());
			users.setUpdateDate(u.getUpdateDate());
			user.add(users);
		}

		return user;
	}

}
