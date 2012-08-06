package siia.channels;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marius Bogoevici
 */
public class AuditService {

  private List<AuditRecord> auditRecords = new ArrayList<AuditRecord>();

  public void audit(Object auditedPayload) {
      auditRecords.add(new AuditRecord(auditedPayload));
  }

    public List<AuditRecord> getAuditRecords() {
        return auditRecords;
    }
}
