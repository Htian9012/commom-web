package com.lzh.util.KeyFactory;

public class IdWorkerFactory {
	
	private static final IdGenerator idworker = new IdWorker(1,1);
	
	public static IdGenerator getIdWorker(){
		return idworker ;
	}

}
