package org.wang.tools.controller;

import static org.quartz.DateBuilder.futureDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.JobBuilder.newJob;

import org.wang.tools.util.HelloWorldQuartz;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.DateBuilder.IntervalUnit;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/quartz")
public class QuartzController implements ApplicationContextAware {
	private static Logger logger = Logger.getLogger(QuartzController.class);
    private static ApplicationContext applicationContext;
    
	@RequestMapping(value="add" , method=RequestMethod.POST)
	@ResponseBody
	public void add() throws SchedulerException{
		SchedulerFactory sf = (SchedulerFactory)applicationContext.getBean("scheduler");
		Scheduler sched = sf.getScheduler();
	    sched.clear();

	      String schedId = sched.getSchedulerInstanceId();

	      int count = 1;

	      JobDetail job = newJob(HelloWorldQuartz.class).withIdentity("job_" + count, schedId) // put triggers in group
	                                                                                            // named after the cluster
	                                                                                            // node instance just to
	                                                                                            // distinguish (in logging)
	                                                                                            // what was scheduled from
	                                                                                            // where
	          .requestRecovery() // ask scheduler to re-execute this job if it was in progress when the scheduler went
	                             // down...
	          .build();

	      SimpleTrigger trigger = newTrigger().withIdentity("triger_" + count, schedId)
	          .startAt(futureDate(1, IntervalUnit.SECOND))
	          .withSchedule(simpleSchedule().withIntervalInSeconds(2).repeatForever()).build();

	      
	      Job jobTest = new Job() {
			
			@Override
			public void execute(JobExecutionContext cxt) throws JobExecutionException {
				
			}
		}; 
	      
	      System.out.println(job.getKey() + " will run at: " + trigger.getNextFireTime() + " and repeat: "
	                + trigger.getRepeatCount() + " times, every " + trigger.getRepeatInterval() / 1000 + " seconds");
	      sched.scheduleJob(job, trigger);
	      
	      sched.start();
	      
	      try {
	          Thread.sleep(100L * 1000L);
	        } catch (Exception e) {
	          //
	        }

	      System.out.println("------- Shutting Down --------------------");
	        sched.shutdown(true);	      

	}

	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		applicationContext = ctx;
	}
	
}
