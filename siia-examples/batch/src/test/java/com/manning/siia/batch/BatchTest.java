/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
        Assert.assertEquals(ExitStatus.COMPLETED, exitStatus);
        int count = jdbcTemplate.queryForInt("select count(*) from payments");
        Assert.assertEquals(27, count);
    }
}
