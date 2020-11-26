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

package org.tinylog.extra.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.tinylog.configuration.PropertiesConfigurationLoader;
import org.yaml.snakeyaml.Yaml;

public class YamlConfigurationLoader extends PropertiesConfigurationLoader {
	private static final String[] YAML_CONFIGURATION_FILES = new String[] {
		"tinylog-dev.yaml",
		"tinylog-test.yaml",
		"tinylog.yaml"	
	};

	/** */
	public YamlConfigurationLoader() {
		
	}
	
	/**
	 * Puts all properties from a stream to an existing properties object. Already existing properties will be
	 * overridden.
	 *
	 * @param properties
	 *            Read properties will be put to this properties object
	 * @param stream
	 *            Input stream with a properties file
	 * @throws IOException
	 *             Failed reading properties from input stream
	 */
	protected void load(final Properties properties, final InputStream stream) throws IOException {
		Yaml yaml = new Yaml();
		Map<String, Object> yamlObject = yaml.load(stream);
		for (Entry<String, Object> yamlEntry : yamlObject.entrySet()) {
			if (yamlEntry.getKey().startsWith("writer")) {
				@SuppressWarnings("unchecked")
				Map<String, Object> values = (Map<String, Object>) yamlEntry.getValue();
				String writer = yamlEntry.getKey();
				for (Entry<String, Object> writerEntry : values.entrySet()) {
					if (writerEntry.getKey().equals("type")) {
						properties.put(writer, writerEntry.getValue());
					} else {
						properties.put(writer + "." + writerEntry.getKey(), writerEntry.getValue());
					}
				}
			} else {
				properties.put(yamlEntry.getKey(), yamlEntry.getValue());
			}
		}	
	}
	
	/**
	 * Retrieve an array of configuration files. An attempt is made to load the properties from these files in the 
	 * given sequence.
	 * 
	 *  @return The configuration files
	 */
	protected String[] getConfigurationFiles() {
		return YAML_CONFIGURATION_FILES;
	}
	
}
