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

package org.tinylog.extra.examples;

import org.tinylog.Logger;

public final class ExampleConfigurationLoaderYaml {

	/** */
	private ExampleConfigurationLoaderYaml() {

	}

	/** 
	 * Start example. 
	 * 
	 * @param args
	 * 						Command arguments
	 */
	public static void main(final String[] args) {
		// Both statements do the same
		// System.setProperty("tinylog.configurationloader", YamlConfigurationLoader.class.getName());
		System.setProperty("tinylog.configurationloader", "yaml");

		Logger.tag("TAG1").info("Loading file tinylog.yaml");

		Logger.tag("TAG1").info("Log testing configurationloader INFO (1x)");
		Logger.tag("TAG1").warn("Log testing configurationloader WARN (2x)");
	}

}
