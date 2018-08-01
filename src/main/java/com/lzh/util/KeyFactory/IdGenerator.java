package com.lzh.util.KeyFactory;

/**
 * <p>Title: IdGenerator</P>
 * <p>Description: 统一的ID生成器接口，用来数据库表的主键</p>
 * <p>Copyright: 广州尚为软件有限公司 Copyright (c) 2015</p>
 * @author JimmyHong
 * @version 1.0
 * @since Jun 6, 2015
 */
public interface IdGenerator {
	/**
	 * <p>方法名称：nextId</p>
	 * <p>方法描述：获取下一个ID</p>
	 * @return String
	 * @author JimmyHong
	 * @since  Jun 6, 2015
	 * <p> history Jun 6, 2015 JimmyHong  创建   <p>
	 */
	public Long nextId( ) ;
}
