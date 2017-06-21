package org.wang.tools.util;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldQuartz implements Job {

	private static Logger log = LoggerFactory.getLogger(HelloWorldQuartz.class);
	private static int count = 0 ;
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		CountPlus();
		System.out.println(count +" time Hello World! - " + new Date());
	}
	
	private static void CountPlus(){
		count++;
	}

}
