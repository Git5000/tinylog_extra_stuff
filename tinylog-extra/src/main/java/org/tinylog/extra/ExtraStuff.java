/*
 * Copyright 2020 Git5000
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package org.tinylog.extra;

import org.tinylog.provider.LoggingProvider;

/**
 * Registry for receiving the actual logging provider.
 *
 * <p>
 * As service registered logging providers will be loaded from {@code META-INF/services}. If there are multiple logging
 * providers, they will be combined to one.
 * </p>
 */
public final class ExtraStuff {

	private static final LoggingProvider loggingProvider = null;

	/** */
	private ExtraStuff() {
	}

	/**
	 * Returns the actual logging provider.
	 *
	 * <p>
	 * Multiple providers will be combined to one. If there are no logging providers, a stub implementation will be
	 * returned instead of {@code null}.
	 * </p>
	 *
	 * @return Actual logging provider
	 */
	public static LoggingProvider getLoggingProvider() {
		return loggingProvider;
	}
	
	public static String extraStuff() {
		return "Extra stuff for tinylog";
	}

}
