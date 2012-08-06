package siia.channels;

/**
 * @author Marius Bogoevici
 */
public class AuditRecord {

    private Object auditedInstance;

    public AuditRecord(Object auditedInstance) {
        this.auditedInstance = auditedInstance;
    }

    public Object getAuditedInstance() {
        return auditedInstance;
    }

}
