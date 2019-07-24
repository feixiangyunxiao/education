package com.bootdo.common.utils;


public class MybatilsLogImp implements org.apache.ibatis.logging.Log{
	
	
	public static void main(String [] s) throws ClassNotFoundException {
		 Class z =  Class.forName("com.boxin.framework.base.ibatis.SqlMapClientFactoryBean");
		 System.out.println(z.getName());
	}
	
	
	public MybatilsLogImp() {
		
	}
	
	public MybatilsLogImp(String str) {
		//System.out.println("MybatilsLogImp 构造方法  =  " + str);
	}
	 
	@Override
	public boolean isDebugEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isTraceEnabled() {
		return true;
	}

	@Override
	public void error(String s, Throwable e) {

	}
	
	@Override
	public void error(String s) {

	}

	@Override
	public void debug(String s) {

	}

	@Override
	public void trace(String s) {

		//HogTransaction.createEvent("sqltrace", s );
	}

	@Override
	public void warn(String s) {

	}

}
