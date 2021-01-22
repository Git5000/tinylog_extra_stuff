# tinylog 2 **EXTRASTUFF**

This package provides some additional examples to the [tinylog v2](https://tinylog.org/v2/) framework.
In particular this is:

* YAML configuration properties loader (org.tinylog.extra.configuration.YamlConfigurationLoader)
* JSON configuration properties loader (org.tinylog.extra.configuration.JsonConfigurationLoader)

## Example YAML

```java
import org.tinylog.Logger;
    
public class Application {

    public static void main(String[] args) {
        System.setProperty("tinylog.configurationloader", "yaml");
        Logger.tag("TAG1").info("Loading file tinylog.yaml");
    }

}
```

YAML file:

```
  writerA: 
     type: "console"
     level: "INFO"
     tag: "TAG1"
     format: "{date}: [{level}]: YAML {message}"
     stream: "out"
  writerB: 
     type: "console"
     level: "WARN"
     tag: "TAG1"
     format: "{date}: [{level}]: YAML {message}"
     stream: "out"
     field:
        TIMESTAMP: date
        LEVEL: level
        MESSAGE: "{class}.{method}() {message}"
  extra: "test"
```

## Example JSON

```java
import org.tinylog.Logger;
    
public class Application {

    public static void main(String[] args) {
        System.setProperty("tinylog.configurationloader", "json");
        Logger.tag("TAG1").info("Loading file tinylog.json");
    }

}
```

JSON file:

```
{
  "writerA" : {
    "type": "console",
    "level": "INFO",
    "tag": "TAG1",
    "format": "{date}: [{level}]: JSON {message}",
    "stream": "out",
    "field" : {
      "TIMESTAMP": "date"
    }
  },
  "writerB" : {
    "type": "console",
    "tag": "TAG1",
    "format": "{date}: [{level}]: JSON {message}",
    "stream": "out",
    "level": "WARN"
  },
  "extra":"test"
}

```

Support
-------

More information about tinylog including a detailed user manual and the Javadoc documentation can be found on [tinylog v2](https://tinylog.org/v2/).


Build tinylog
-------------

tinylog requires Maven 3.5 and JDK 9 for building. Newer JDKs cannot compile legacy code for older Java versions, and older JDKs cannot compile new features for the latest Java versions. [OpenJDK 9](https://jdk.java.net/archive/) is still available on [java.net](https://jdk.java.net/archive/) and [Oracle JDK 9](https://www.oracle.com/java/technologies/javase/javase9-archive-downloads.html) on [oracle.com](https://www.oracle.com/java/technologies/javase/javase9-archive-downloads.html).

tinylog Extrastuff requires tinylog mnimum version of 2.3.

Build command:

	mvn clean install

License
-------

Tinylog *Extrastuff* is copyright 2020 by git5000.

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
