package com.capmkts.bulkupload.client;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.capmkts.msrprocess.constants.MsrConstants;

public class BulkUploadSchedulerTrigger {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			JobDetail job = JobBuilder.newJob(BulkUploadServiceClientJob.class)
					.withIdentity("bulkUploadServiceClientJob", "group1")
					.build();

			// configure the scheduler time
			Trigger trigger = TriggerBuilder
					.newTrigger()
					.withIdentity("dummyTriggerName", "group1")
					.withSchedule(
							SimpleScheduleBuilder.simpleSchedule().
							withIntervalInMinutes(MsrConstants.CAV_BULKUPLOAD_INTERVAL)
									.repeatForever())
					.build();

			// schedule it
			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.scheduleJob(job, trigger);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
