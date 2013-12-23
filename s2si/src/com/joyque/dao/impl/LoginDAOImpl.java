package com.joyque.dao.impl;

import java.util.List;

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.joyque.dao.ILoginDAO;
import com.joyque.pojo.Account;

public class LoginDAOImpl extends SqlMapClientDaoSupport implements ILoginDAO {
	private static Logger logger=Logger.getLogger(LoginDAOImpl.class.getName());
	public String login() {
		System.out.println("LoginDAOImpl.login()");
		logger.info("LoginDAOImpl.login()");
	
//		logger.addAppender(newAppender);
		List<Account> accountList=getSqlMapClientTemplate().queryForList("selectAllAccounts");
		for(Account a:accountList){
			System.out.println(a.getId()+") "+a.getFirstName()+a.getLastName()+" 's email is "+a.getEmailAddress());
		}
		return null;
	}
}
