package com.manning.siia.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;

/**
 * @author Marius Bogoevici
 */
public class JobRestart
{
   @Autowired
   JobLauncher jobLauncher;

    @Autowired
   Job job;

   @ServiceActivator
   public void restartIfPossible(JobExecution execution) throws JobInstanceAlreadyCompleteException, JobParametersInvalidException, JobRestartException, JobExecutionAlreadyRunningException
   {
      jobLauncher.run(job, execution.getJobInstance().getJobParameters());
   }
}
