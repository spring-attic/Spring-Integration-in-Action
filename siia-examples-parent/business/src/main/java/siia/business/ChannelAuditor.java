package siia.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.channel.interceptor.ChannelInterceptorAdapter;

public class ChannelAuditor extends ChannelInterceptorAdapter {

  @Autowired
  private AuditService auditService;

  public Message<?> preSend(Message<?> message,
                                MessageChannel channel) {
        this.auditService.audit(message.getPayload());
        return message;
    }
}
