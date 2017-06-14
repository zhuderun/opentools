package org.quartz.examples.deleteexample;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;

public class SimpleJob implements Job{

	@Override
	public void execute(JobExecutionContext paramJobExecutionContext) throws JobExecutionException {
		JobDetail jobDetail = paramJobExecutionContext.getJobDetail();
		Trigger trigger = paramJobExecutionContext.getTrigger();
		System.out.println(trigger.getKey() + "===" + jobDetail.getKey());
	}

}
