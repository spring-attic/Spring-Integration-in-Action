package siia.monitoring.controlbus;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

/**
 * @author Mark Fisher
 */
@ManagedResource
public class NumberHolder {

	private final AtomicInteger number = new AtomicInteger();

	@ManagedAttribute
	public int getNumber() {
		return this.number.get();
	}

	@ManagedAttribute
	public void setNumber(int value) {
		this.number.set(value);
	}

	@ManagedOperation
	public void increment() {
		this.number.incrementAndGet();
	}

}
