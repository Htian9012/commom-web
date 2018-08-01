package com.lzh.util.KeyFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Title: AbstractIdGenerator</P>
 * <p>Description: 抽象的ID生成器实现，为具体实现类提供一些基本准备</p>
 * <p>Copyright: 广州尚为软件有限公司 Copyright (c) 2015</p>
 * @author JimmyHong
 * @version 1.0
 * @since Jun 6, 2015
 */
public abstract class AbstractIdWorker implements IdGenerator {

	/**
	 * 日志工具
	 */
	protected final Logger  logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 统计工具
	 */
	// Stat.....
	
	public AbstractIdWorker(){
		super();
	}

	abstract  public long nextLongId() ;
}
