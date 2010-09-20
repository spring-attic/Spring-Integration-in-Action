package com.manning.siia.batch;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author Marius Bogoevici
 */
public class PaymentJobExecutionListener extends JobExecutionListenerSupport
{
   @Autowired @Qualifier("notificationExecutionsListener")
   JobExecutionListener jobExecutionListenerGateway;

   @Override
   public void afterJob(JobExecution jobExecution)
   {
      System.out.println("received ...");
      jobExecutionListenerGateway.afterJob(jobExecution);
   }
}
