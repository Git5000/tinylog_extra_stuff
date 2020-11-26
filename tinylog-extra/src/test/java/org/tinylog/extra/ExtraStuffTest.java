/*
 * Copyright 2016 Martin Winandy
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

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.tinylog.configuration.Configuration;
import org.tinylog.provider.NopLoggingProvider;
import org.tinylog.rules.SystemStreamCollector;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link ExtraStuff}.
 */
@RunWith(PowerMockRunner.class)
public final class ExtraStuffTest {

	/**
	 * Redirects and collects system output streams.
	 */
	@Rule
	public final SystemStreamCollector systemStream = new SystemStreamCollector(true);

	/**
	 * Initializes {@link ExtraStuff} properly.
	 */
	@Before
	public void init() {
		ExtraStuff.getLoggingProvider();
		systemStream.clear();
	}

	/**
	 * Deletes created service file from class path.
	 *
	 * @throws Exception
	 *             Failed deleting service file
	 */
	@After
	public void clear() throws Exception {
		//FileSystem.deleteServiceFile(LoggingProvider.class);
	}

	/**
	 * Verifies that {@link NopLoggingProvider} can be set explicitly if multiple  logging providers are available.
	 *
	 * @throws Exception
	 *             Failed creating service or invoking private method {@link ExtraStuff#loadLoggingProvider()}
	 */
	@Test
	@PrepareForTest(Configuration.class)
	public void nopProvider() throws Exception {
		assertThat(ExtraStuff.extraStuff()).isEqualTo("Extra stuff for tinylog");

	}

}
