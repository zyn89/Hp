/*
 * copywrite 2011 深圳证券信息有限公司
 * 不能修改和删除上面的版权声明
 * 此代码属于北京研究网编写，在未经允许的情况下不得传播复制
*/
package com.joyque.common.exception;

/**
 *@comment:dao异常类
 *@date 2011-5-9
 *@author treetree
 *@since 1.0
 */
public class BaseDaoException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 15282316936047469L;
	
	/**
	 * 默认构造器
	 */
	public BaseDaoException(){
		super();
	}
	/**
	 * 抛出message
	 * @param message exception信息
	 */
	public BaseDaoException(String message){
		super(message);
	}
	/**
	 * 抛出message和异常
	 * @param message
	 * @param cause
	 */
	public BaseDaoException(String message, Throwable cause){
		super(message,cause);
	}
	/**
	 * 抛出异常
	 * @param cause
	 */
	public BaseDaoException(Throwable cause){
		super(cause);
	}
}
