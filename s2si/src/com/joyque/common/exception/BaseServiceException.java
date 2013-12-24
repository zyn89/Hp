/*
 * copywrite 2011 深圳证券信息有限公司
 * 不能修改和删除上面的版权声明
 * 此代码属于北京研究网编写，在未经允许的情况下不得传播复制
*/
package com.joyque.common.exception;

/**
 *@comment:service 异常处理
 *@date 2011-5-9
 *@author treetree
 *@since 1.0
 */
public class BaseServiceException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2676727959600289313L;
	
	private BaseDaoException ex;
	
	/**
	 * 默认构造器
	 */
	public BaseServiceException(){
		super();
	}
	/**
	 * 抛出message
	 * @param message exception信息
	 */
	public BaseServiceException(String message,String identity){
		super(message);
	}
	/**
	 * 抛出message和异常
	 * @param message
	 * @param cause
	 */
	public BaseServiceException(String message, BaseDaoException cause){
		super(message,null);
		this.ex = cause;
	}
	/**
	 * 抛出异常
	 * @param cause
	 */
	public BaseServiceException(BaseDaoException cause){
		super(cause);
	}
	/**
	 * 获取DAO异常cause信息
	 * @param e DAO异常
	 * @return
	 */
	public Throwable initDaoCause(BaseDaoException e){
		return e.initCause(e.getCause());
	}
	
	/**
	 * 获取DAO异常信息
	 * @return
	 */
	public Throwable getException() {
        return ex;
    }

	/**
	 * 获取DAO异常信息
	 * @return
	 */
	public Throwable getCause() {
        return ex;
    }
}
