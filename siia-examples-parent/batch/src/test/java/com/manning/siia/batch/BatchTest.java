package com.manning.siia.batch;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
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
public class BatchTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired @Qualifier("statuses")
    private QueueChannel statusesChannel;

    @Test
    @SuppressWarnings("unchecked")
    public void runBatch() throws Exception {
        //120 s should provide enough time for the poller to detect the file and process it
        JobExecution jobExecution = ((Message<JobExecution>) statusesChannel.receive(120000)).getPayload();

        ExitStatus exitStatus = jobExecution.getExitStatus();

        Assert.assertEquals(exitStatus, ExitStatus.COMPLETED);

        int count = jdbcTemplate.queryForInt("select count(*) from payments");

        Assert.assertEquals(27, count);
    }
}
