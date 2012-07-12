package com.manning.siia.batch;

import org.springframework.batch.core.JobExecution;
import org.springframework.integration.Message;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.mail.MailHeaders;
import org.springframework.integration.support.MessageBuilder;

/**
 * @author Marius Bogoevici
 */
public class ExecutionsToMailTransformer {

   @Transformer
   public Message<String> transformExecutionsToMail(JobExecution jobExecution) {
      String result = "Execution has ended " + jobExecution.getStatus().toString();
      return MessageBuilder.withPayload(result).setHeader(MailHeaders.TO, "siia.test@yahoo.ca").setHeader(MailHeaders.FROM, "siia.test@yahoo.ca").build();
   }
}
