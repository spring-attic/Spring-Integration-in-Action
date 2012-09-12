package siia.channels;

import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.channel.interceptor.ChannelInterceptorAdapter;

/**
 * @author Marius Bogoevici
 */
public class ChannelAuditor extends ChannelInterceptorAdapter {

    private AuditService auditService;

    public void setAuditService(AuditService auditService) {
        this.auditService = auditService;
    }

    public Message<?> preSend(Message<?> message,
                              MessageChannel channel) {
        this.auditService.audit(message.getPayload());
        return message;
    }
}
