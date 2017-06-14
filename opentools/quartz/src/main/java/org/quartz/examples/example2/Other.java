package org.quartz.examples.example2;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Other {
	
	public void run() throws SchedulerException{
		Logger log = LoggerFactory.getLogger(SimpleTriggerExample.class);

		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		
		JobDetail job = newJob(SimpleJob.class).withIdentity("test").build();
		
		JobKey jobKey = JobKey.jobKey("test");
		
		sched.addJob(job, false);
		
		System.out.println(job.equals(jobKey));
		
		System.out.println(job.getKey());
		
		System.out.println(jobKey);
		
		System.out.println(sched.checkExists(jobKey));
		

		
		
	}
	
	
	public static void main(String[] args) throws SchedulerException {
		Other o = new Other();
		o.run();
	}

}
