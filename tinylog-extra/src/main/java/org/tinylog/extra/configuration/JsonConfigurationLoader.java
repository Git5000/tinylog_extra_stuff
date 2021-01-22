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
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

import org.tinylog.configuration.PropertiesConfigurationLoader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Load properties from a JSON file.
 */
public class JsonConfigurationLoader extends PropertiesConfigurationLoader {
	private static final String[] JSON_CONFIGURATION_FILES = new String[] {
		"tinylog-dev.json",
		"tinylog-test.json",
		"tinylog.json"	
	};

	/** */
	public JsonConfigurationLoader() {
		
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
		GsonBuilder builder = new GsonBuilder();
		builder.disableHtmlEscaping();
		builder.registerTypeAdapter(Properties.class, new PropertiesDeserializerFromJson());
		Gson gson = builder.create();
		Reader reader = null;
		try {
			reader = new InputStreamReader(stream, "utf-8");
			Properties prop = gson.fromJson(reader, Properties.class);
			properties.putAll(prop);
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}
	
	/**
	 * Retrieve an array of configuration files. An attempt is made to load the properties from these files in the 
	 * given sequence.
	 * 
	 *  @return
	 *          The configuration files
	 */
	protected String[] getConfigurationFiles() {
		return JSON_CONFIGURATION_FILES;
	}
	
}
