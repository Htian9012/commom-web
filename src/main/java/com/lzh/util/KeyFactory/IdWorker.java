package com.lzh.util.KeyFactory;

/**
 * <p>Title: IdWorker</P>
 * <p>Description: ID生成器实现：基于twitter snowflake 算法实现<br></p>
 * 数据结构描述:<br>第一位为未使用（实际上也可作为long的符号位），接下来的41位为毫秒级时间，然后5位datacenter标<br>
 * 识位，5位机器ID（并不算标识符，实际是为线程标识），然后12位该毫秒内的当前毫秒内的计数，加起来刚好64位，为一个Long型。<br>
 * 0---0000000000 0000000000 0000000000 0000000000 0 --- 00000 ---00000 ---000000000000 <br>
 * 算法描述:格字段含义参考类内字段注释<br> 
 * scala源码参考： https://github.com/twitter/snowflake/blob/scala_28/src/main/scala/com/twitter/service/snowflake/IdWorker.scala <br>
 * 官方说明： https://github.com/twitter/snowflake/tree/updated_deps <br>
 * NEXT_ID= (CURRENT_TIMESTAMP - TWEPOCH) &lt;&lt; TIMESTAMP_SHIFT_BITS) |(DATA_CENTER_ID &lt;&lt; DATA_CENTER_ID_SHIFT_BITS) | (WORKER_ID &lt;&lt; WORKER_ID_SHIFT_BITS ) | SEQUENCE ; 
 * <p>Copyright: 广州尚为软件有限公司 Copyright (c) 2015</p>
 * @author JimmyHong
 * @version 1.0
 * @since Jun 6, 2015
 */
public class IdWorker extends AbstractIdWorker {

	/**
	 * 开始计算时间:毫秒计, 2015-01-01: 00:00:00
	 */
	private static final long  TWEPOCH = 1420041600626L ;
	
	/**
	 * 机器标识位数
	 */
	private static final long WORKER_ID_BITS = 5L ;
	
	/**
	 * 数据中心位数
	 */
	private static final long DATA_CENTER_ID_BITS = 5L ;
	
	/**
	 * 机器标识的最大值
	 */
	private static final long MAX_WORKER_ID = -1L ^ (-1L << WORKER_ID_BITS ) ;
	
	/**
	 * 数据中心最大值
	 */
	private static final long MAX_DATA_CENTER_ID = -1L ^ (-1L << DATA_CENTER_ID_BITS ) ;
	
	/**
	 * 毫秒内的自增序列位数
	 */
	private static final long SEQUENCE_BITS    = 12 ;
	
	/**
	 * 机制标识左移的位数：12
	 */
	private static final long WORKER_ID_SHIFT_BITS   = SEQUENCE_BITS ;
	
	/**
	 * 数据中心ID左移的位数17
	 */
	private static final long DATA_CENTER_ID_SHIFT_BITS = SEQUENCE_BITS + WORKER_ID_BITS ;
	
	/**
	 * 时间戳左移的位数 22 
	 */
	private static final long TIMESTAMP_SHIFT_BITS    = SEQUENCE_BITS + WORKER_ID_BITS + DATA_CENTER_ID_BITS ;
	
	/**
	 * 毫秒内自增序列掩码
	 */
	private static final long SEQUENCE_MASK = -1L ^ ( -1L << SEQUENCE_BITS ) ;
	
	/**
	 * 数据中心ID
	 */
	private final long dataCenterId ;
	
	/**
	 * 机器标识ID
	 */
	private final long workerId ;
	
	/**
	 * 上次使用的时间戳
	 */
	private long lastTimestamp =  -1 ;
	
	/**
	 * 毫秒内的当前的序列值
	 */
	private long sequence  = 0L;
	
	/**
	 * 默认构造器： 默认的dataCenterId和workerId分别为0，0
	 */
	public IdWorker(){
		//TODO
		//逻辑待加强： 根据HOST+IP+MAC 获取从zookeeper配置管理服务器中获取dataCenterId和workerId作为ID生成器分割标识
		// 
		this(0,0);
	}
	
	/**
	 * 构造器：判定数据中心标识和机器标识ID是否合法
	 * @param dataCenterId  数据中心ID
	 * @param workerId      机器标识ID
	 */
	public IdWorker(final long dataCenterId ,final long workerId ) {
		super();
		this.dataCenterId = dataCenterId ;
		this.workerId     = workerId ;
		
		if(this.dataCenterId > MAX_DATA_CENTER_ID || this.dataCenterId < 0){
			throw new RuntimeException(String.format("dataCenter Id can't be greater than %d or less than 0", MAX_DATA_CENTER_ID));
		}
		
		if(this.workerId > MAX_WORKER_ID || this.workerId < 0) {
			throw new RuntimeException(String.format("worker Id can't be greater than %d or less than 0", MAX_WORKER_ID));
		}
		
		this.logger.info(String.format("worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d, sequence bits %d,dataCenterId %d, workerid %d",
    TIMESTAMP_SHIFT_BITS, DATA_CENTER_ID_BITS, WORKER_ID_BITS, SEQUENCE_BITS, dataCenterId,workerId));
	}

	@Override
	public Long nextId() {
		return this.nextLongId();
	}
	
	/**
	 * <p>方法名称：nextLongId</p>
	 * <p>方法描述：下一个ID，返回的是long类型</p>
	 * @return long
	 * @author JimmyHong
	 * @since  Jun 6, 2015
	 * <p> history Jun 6, 2015 JimmyHong  创建   <p>
	 */
	@Override
	public synchronized long nextLongId(){
		// 获取当前时间戳
		long currentTimestamp = this.getTimestamp() ;
		
		if(currentTimestamp < this.lastTimestamp ){
			logger.error(String.format("clock is moving backwards.  Rejecting requests until %d .",  this.lastTimestamp ));
			throw new RuntimeException(String.format("clock is moving backwards.  Rejecting requests until %d .",  this.lastTimestamp ));
		}
		
		if(currentTimestamp == this.lastTimestamp ){
			this.sequence = (this.sequence + 1) & SEQUENCE_MASK ;
			if( 0 == this.sequence ) {
				currentTimestamp = waitForNextMillis(this.lastTimestamp) ;
			}
		} else {
			this.sequence = 0L ;
		}
		
		// 记录最后一次使用的时间戳的值
		this.lastTimestamp = currentTimestamp ;
		long result = ((currentTimestamp - TWEPOCH) << TIMESTAMP_SHIFT_BITS) |
				(this.dataCenterId << DATA_CENTER_ID_SHIFT_BITS) |
				(this.workerId << WORKER_ID_SHIFT_BITS ) | 
				this.sequence ;
		this.logger.info(String.format("IdWorker : generate id: %d, size : %d,sequence is: %d,binary is: %s .", result ,String.valueOf(result).length(),this.sequence,Long.toBinaryString(result)));
		return result ;
	}
	
	/**
	 * <p>方法名称：waitForNextMillis</p>
	 * <p>方法描述：等到下一个毫秒</p>
	 * @param lastTimestamp
	 * @return long
	 * @author JimmyHong
	 * @since  Jun 6, 2015
	 * <p> history Jun 6, 2015 JimmyHong  创建   <p>
	 */
	private long waitForNextMillis(long lastTimestamp) {
		long currentTimestamp = this.getTimestamp() ;
		if(currentTimestamp <= this.lastTimestamp){
			currentTimestamp = this.getTimestamp() ;
		}
		return currentTimestamp ;
	}

	public long getWorkerId(){
		return this.workerId  ;
	}
	
	public long getDataCenterId(){
		return this.dataCenterId ;
	}
	
	/**
	 * <p>方法名称：getTimestamp</p>
	 * <p>方法描述：获取系统时间： 返回毫秒数，集群下服务器时间尽量保持一致（非必须，关键由dataCenterId和workerId区分不同的服务器）</p>
	 * @return long     1970年后到当前的毫秒数
	 * @author JimmyHong
	 * @since  Jun 6, 2015
	 * <p> history Jun 6, 2015 JimmyHong  创建   <p>
	 */
	private long getTimestamp(){
		return System.currentTimeMillis() ;
	}

}
