Older Config Clients stop working with new Hoxton based Config Server

```
java.lang.IllegalStateException: Failed to execute CommandLineRunner
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:707) ~[spring-boot-1.5.22.RELEASE.jar:1.5.22.RELEASE]
	at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:688) ~[spring-boot-1.5.22.RELEASE.jar:1.5.22.RELEASE]
	at org.springframework.boot.SpringApplication.afterRefresh(SpringApplication.java:675) ~[spring-boot-1.5.22.RELEASE.jar:1.5.22.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:301) ~[spring-boot-1.5.22.RELEASE.jar:1.5.22.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1082) ~[spring-boot-1.5.22.RELEASE.jar:1.5.22.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1071) ~[spring-boot-1.5.22.RELEASE.jar:1.5.22.RELEASE]
	at org.srempfer.config.client.ConfigClient15Application.main(ConfigClient15Application.java:13) ~[classes/:na]
Caused by: org.springframework.core.convert.ConverterNotFoundException: No converter found capable of converting from type [java.util.LinkedHashMap<?, ?>] to type [java.lang.String]
	at org.springframework.core.convert.support.GenericConversionService.handleConverterNotFound(GenericConversionService.java:324) ~[spring-core-4.3.25.RELEASE.jar:4.3.25.RELEASE]
	at org.springframework.core.convert.support.GenericConversionService.convert(GenericConversionService.java:206) ~[spring-core-4.3.25.RELEASE.jar:4.3.25.RELEASE]
	at org.springframework.core.convert.support.GenericConversionService.convert(GenericConversionService.java:187) ~[spring-core-4.3.25.RELEASE.jar:4.3.25.RELEASE]
	at org.springframework.core.env.AbstractPropertyResolver.convertValueIfNecessary(AbstractPropertyResolver.java:266) ~[spring-core-4.3.25.RELEASE.jar:4.3.25.RELEASE]
	at org.springframework.core.env.PropertySourcesPropertyResolver.getProperty(PropertySourcesPropertyResolver.java:87) ~[spring-core-4.3.25.RELEASE.jar:4.3.25.RELEASE]
	at org.springframework.core.env.PropertySourcesPropertyResolver.getProperty(PropertySourcesPropertyResolver.java:61) ~[spring-core-4.3.25.RELEASE.jar:4.3.25.RELEASE]
	at org.springframework.core.env.AbstractEnvironment.getProperty(AbstractEnvironment.java:531) ~[spring-core-4.3.25.RELEASE.jar:4.3.25.RELEASE]
	at org.srempfer.config.client.ConfigClient15Application.lambda$propertyPrinter$0(ConfigClient15Application.java:19) ~[classes/:na]
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:704) ~[spring-boot-1.5.22.RELEASE.jar:1.5.22.RELEASE]
	... 6 common frames omitted
```

There is a feature for "propery source origin" (https://github.com/spring-cloud/spring-cloud-config/issues/866) in the new Config Server.
It's designed with backward compatibility in mind but due to a missing content type definition in
`org.springframework.cloud.config.client.ConfigServicePropertySourceLocator.getRemoteEnvironment`
the default content type
`application/json, application/*+json` from `org.springframework.http.converter.json.AbstractJsonHttpMessageConverter` is used.

With the issue https://github.com/spring-cloud/spring-cloud-config/issues/1169 included since version 2.0.3.RELEASE (Finchley.SR3) the content type is defined by `Accept` header.

Here is a table with checked compatibility:

|  Config Client Version  | Config Server - SB 2.1 - Greenwich | Config Server - SB 2.2 - Hoxton |
| ------------------------| ------------- |-------------|
| SB 1.5.x - Edgware      | OK | ERROR |
| SB 2.0.x - Finchley.SR2 | OK | ERROR |
| SB 2.0.x - Finchley.SR3 | OK | OK |
| SB 2.0.x - Finchley.SR4 | OK | OK |
| SB 2.1.x - Greenwich    | OK | OK |
