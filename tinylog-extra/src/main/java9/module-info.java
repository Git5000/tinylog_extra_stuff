module org.tinylog.api {

	requires static java.sql;
	requires java.management;

	exports org.tinylog.extra;
  exports org.tinylog.extra.configuration;
  exports org.tinylog.extra.examples;
}
