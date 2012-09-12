package siia.monitoring.controlbus;

import org.springframework.context.Lifecycle;

/**
 * This does not poll files. It simply demonstrates that any
 * Lifecycle implementation can be managed via the control bus.
 *
 * @author Mark Fisher
 */
public class FilePoller implements Lifecycle {

	private volatile boolean running = false;

	public void start() {
		this.running = true;
	}

	public void stop() {
		this.running = false;
	}

	public boolean isRunning() {
		return this.running;
	}

}
