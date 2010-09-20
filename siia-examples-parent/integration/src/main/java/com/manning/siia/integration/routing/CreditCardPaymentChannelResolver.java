package com.manning.siia.integration.routing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.support.channel.ChannelResolver;

/**
 * A simple example of a {@link ChannelResolver}
 *
 * @author Marius Bogoevici
 */
public class CreditCardPaymentChannelResolver implements ChannelResolver
{

   @Autowired
   private ApplicationContext applicationContext;

   @Override
   public MessageChannel resolveChannelName(String s)
   {
      return (MessageChannel)applicationContext.getBean(s + "-payments");
   }
}
