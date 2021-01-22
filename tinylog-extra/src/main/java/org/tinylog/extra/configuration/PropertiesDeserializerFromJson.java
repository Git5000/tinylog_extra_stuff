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

import java.lang.reflect.Type;
import java.util.Map.Entry;
import java.util.Properties;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

/**
 * Special deserializer for properties.
 */
public class PropertiesDeserializerFromJson implements JsonDeserializer<Properties> {
	/** */
	public PropertiesDeserializerFromJson() {
	}
	
	@Override
	public Properties deserialize(final JsonElement jElement, 
			final Type typeOfT, 
			final JsonDeserializationContext context) throws JsonParseException {
		Properties properties = new Properties();
		resolveElement(properties, null, jElement);
		return properties;
	}

	/**
	 * Resolve a json element and store it to the given properties.
	 * @param properties The resulting properties
	 * @param parent The parent name or null for revusive fields
	 * @param jElement The current json element
	 */
	private void resolveElement(final Properties properties, final String parent, final JsonElement jElement) {
		JsonObject jObject = jElement.getAsJsonObject();
		for (Entry<String, JsonElement> entry : jObject.entrySet()) {
			String key = entry.getKey();
			String storeKey;
			if (parent != null) {
				storeKey = parent + "." + key;
			} else {
				storeKey = key;
			}
			JsonElement element = entry.getValue();
			if (element instanceof JsonPrimitive) {
				String value = ((JsonPrimitive) element).getAsString();
				// Catch the special entry for the writer type
				if (key.equals("type") && parent != null && !parent.contains(".")) {
					properties.put(parent, value); 
				} else {
					properties.put(storeKey, value);
				}
			} else {
				resolveElement(properties, storeKey, element);
			}
		}
	}
}
