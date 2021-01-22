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
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.tinylog.configuration.PropertiesConfigurationLoader;
import org.yaml.snakeyaml.Yaml;

/**
 * Load properties from YAML.
 */
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
		// Note: The recursive parsing could be done a bit neater and shorter if done like the JSON reader 
		for (Entry<String, Object> yamlEntry : yamlObject.entrySet()) {
			if (yamlEntry.getKey().startsWith("writer")) {
				@SuppressWarnings("unchecked")
				Map<String, Object> values = (Map<String, Object>) yamlEntry.getValue();
				String writer = yamlEntry.getKey();
				for (Entry<String, Object> writerEntry : values.entrySet()) {
					if (writerEntry.getKey().equals("type")) {
						properties.put(writer, writerEntry.getValue());
					} else {
						resolveYamlMap(properties, writer, writerEntry.getKey(), writerEntry.getValue());
					}
				}
			} else {
				properties.put(yamlEntry.getKey(), yamlEntry.getValue());
			}
		}	
	}
	
	/**
	 * Resolve nested YAML entries to a dot separated string (e.g. writer.FIELD.date) and
	 * writes them to the properties.
	 * @param properties
	 *            The properties to use
	 * @param parent 
	 *            The parent tag (e.g. writerA)
	 * @param key 
	 *            The current key (e.g. field)
	 * @param value 
	 *            The value (e.g. the new hashmap or a string)
	 */
	@SuppressWarnings("unchecked")
	protected void resolveYamlMap(final Properties properties, final String parent, final String key, final Object value) {
		if (value instanceof HashMap) {
			for (Entry<String, Object> entry: ((HashMap<String, Object>) value).entrySet()) {
				resolveYamlMap(properties, parent + "." + key, entry.getKey(), entry.getValue());
			}
		} else {
			String resultKey = parent + "." + key;
			properties.put(resultKey, value);
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
