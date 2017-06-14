package test;

import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.snailxr.base.task.domain.ScheduleJob;
import com.snailxr.base.task.service.JobTaskService;

public class App {
	public static void main(String[] args) throws SchedulerException {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
		System.out.println(ac);
		JobTaskService jts = (JobTaskService) ac.getBean("jobTaskService");
		
		ScheduleJob sj = new ScheduleJob();
		sj.setBeanClass("com.snailxr.base.task.TaskTest");
		sj.setCronExpression("*/5 * * * * ?");
		sj.setJobGroup("ga");
		sj.setJobName("test");
		sj.setMethodName("run1");
		
		
		jts.addJob(sj);
		
	}

}
