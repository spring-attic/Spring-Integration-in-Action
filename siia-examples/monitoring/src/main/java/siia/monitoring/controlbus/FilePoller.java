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
