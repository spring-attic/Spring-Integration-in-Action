package com.manning.siia.batch;

import java.util.List;

import org.springframework.batch.core.listener.ItemListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.support.MessageBuilder;

/**
 * @author Marius Bogoevici
 */
public class PaymentChunkListener extends ItemListenerSupport<Payment, Payment>
{

   @Autowired
   @Qualifier("chunkExecutions")
   MessageChannel chunkNotificationsChannel;


   @Override
   public void onReadError(Exception ex)
   {
      chunkNotificationsChannel.send(MessageBuilder.withPayload(new Notification(ex.getMessage(),true)).build());
   }

   @Override
   public void onWriteError(Exception ex, List<? extends Payment> item)
   {

   }
}
