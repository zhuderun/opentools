package org.quartz.examples.example2;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import org.quartz.DateBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleTriggerExample2 {

	public void run() throws SchedulerException {
		Logger log = LoggerFactory.getLogger(SimpleTriggerExample.class);

		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();

		Date startTime = DateBuilder.nextGivenSecondDate(null, 15);

		// job1 will only fire once at date/time "ts"
		JobDetail job = newJob(SimpleJob.class).withIdentity("job1", "group1").build();

		SimpleTrigger trigger = (SimpleTrigger) newTrigger().withIdentity("trigger1", "group1").startAt(startTime)
				.build();

		Date ft = sched.scheduleJob(job, trigger);
		log.info(job.getKey() + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every "
				+ trigger.getRepeatInterval() / 1000 + " seconds");

		job = newJob(SimpleJob.class).withIdentity("job2", "group1").build();

		trigger = (SimpleTrigger) newTrigger().withIdentity("trigger2", "group1").startAt(startTime).build();

		ft = sched.scheduleJob(job, trigger);
		log.info(job.getKey() + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every "
				+ trigger.getRepeatInterval() / 1000 + " seconds");

		// repeat trigger
		job = newJob(SimpleJob.class).withIdentity("job3", "group1").build();
		trigger = newTrigger().withIdentity("trigger3", "group1").startAt(startTime)
				.withSchedule(simpleSchedule().withIntervalInSeconds(10).withRepeatCount(10)).build();
		ft = sched.scheduleJob(job, trigger);
		log.info(job.getKey() + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every "
				+ trigger.getRepeatInterval() / 1000 + " seconds");

		// construct trigger with job and start it
		trigger = newTrigger().withIdentity("trigger3", "group2").startAt(startTime)
				.withSchedule(simpleSchedule().withIntervalInSeconds(10).withRepeatCount(2)).forJob(job).build();
		ft = sched.scheduleJob(trigger);
		log.info(job.getKey() + " will [also] run at: " + ft + " and repeat: " + trigger.getRepeatCount()
				+ " times, every " + trigger.getRepeatInterval() / 1000 + " seconds");

		
		
		job = newJob(SimpleJob.class).withIdentity("job4", "group1").build();
		trigger = newTrigger().withIdentity("trigger4", "group1").startAt(startTime)
				.withSchedule(simpleSchedule().withIntervalInSeconds(10).withRepeatCount(5)).build();
		ft = sched.scheduleJob(job, trigger);
		log.info(job.getKey() + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every "
				+ trigger.getRepeatInterval() / 1000 + " seconds");
		

		sched.start();

	}

	public static void main(String[] args) throws SchedulerException {
		SimpleTriggerExample2 ste2 = new SimpleTriggerExample2();

		ste2.run();

	}

}
