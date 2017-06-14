package org.quartz.examples.deleteexample;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class DeleteExmaple {
	
	public void run() throws SchedulerException{
		SchedulerFactory scheduleFactory = new StdSchedulerFactory();
		Scheduler sche = scheduleFactory.getScheduler();
		
		JobDetail job = JobBuilder.newJob(SimpleJob.class).withIdentity("job1", "group1").build();
		
		SimpleTrigger trigger = TriggerBuilder.newTrigger().startNow().withIdentity("t1").withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(10)).build();
		SimpleTrigger trigger2 = TriggerBuilder.newTrigger().startNow().withIdentity("t2").forJob(job).withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(1)).build();
		
		sche.scheduleJob(job,trigger);
		
		sche.scheduleJob(trigger2);
		
		sche.start();
		
//		job = JobBuilder.newJob(SimpleJob.class).withIdentity("job2", "group1").build();
		
		try {
			Thread.sleep(5L*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sche.unscheduleJob(trigger2.getKey());
		
		
		
//		sche.scheduleJob(job,trigger2);
		
//		sche.deleteJob(job.getKey());
		
	}
	
	public static void main(String[] args) throws SchedulerException {
		DeleteExmaple de = new DeleteExmaple();
		de.run();
	}

}
