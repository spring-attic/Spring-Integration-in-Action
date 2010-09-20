package com.manning.siia.batch;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.Message;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Marius Bogoevici
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:batch-config.xml", "classpath:si-config.xml"})
public class BatchTest
{

   @Autowired
   private JobLauncher launcher;

   @Autowired
   private Job job;

   @Autowired
   private JdbcTemplate jdbcTemplate;

   @Autowired
   @Qualifier("statuses")
   private QueueChannel statusesChannel;

   @Test
   public void runBatch() throws Exception
   {
      //120 s should provide enough time for the poller to detect the file and process it
      JobExecution jobExecution = ((Message<JobExecution>) statusesChannel.receive(120000)).getPayload();

      jobExecution = ((Message<JobExecution>) statusesChannel.receive(120000)).getPayload();

      ExitStatus exitStatus = jobExecution.getExitStatus();

      Assert.assertEquals(exitStatus, ExitStatus.COMPLETED);

      int count = jdbcTemplate.queryForInt("select count(*) from payments");

      Assert.assertEquals(27, count);

      List<Map<String, Object>> accounts = jdbcTemplate.queryForList("select * from ACCOUNTS");
      for (Map<String, Object> account : accounts)
      {
         System.out.println("---------------");
         for (Map.Entry<String, Object> stringObjectEntry : account.entrySet())
         {
            System.out.print(stringObjectEntry.getKey() + ":" + stringObjectEntry.getValue() + " ");
         }
      }
   }

   @Ignore @Test
   public void runBatchStandalone() throws Exception
   {
      JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
      String filename = "/Users/marius/Personal/SpringIntegration/Book/examples/Spring-Integration-in-Action/siia-examples-parent/batch/src/test/resources/data/paymentImport/payment.input";
      jobParametersBuilder.addString("input.file.name", filename);
      JobExecution execution = launcher.run(job, jobParametersBuilder.toJobParameters());
   }
}
