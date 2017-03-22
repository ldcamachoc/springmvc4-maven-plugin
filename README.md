#Maven plugin

It was created a plugin-profiles maven plugin to copy dependencies after process-resources phase

spring-data/src/main/resources/dev/application.properties
spring-data/src/main/resources/test/application.properties

and will override

spring-data/src/main/resources/application.properties


In prepare-package phase will delete the following directories and files:

spring-data/src/main/resources/dev/
spring-data/src/main/resources/test/
spring-data/src/main/resources/dev/application.properties
spring-data/src/main/resources/test/application.properties

#Maven profiles

There are two profiles in maven:

-test: hsqldb
-dev : mysqldb

These profiles cause to take diferents dependencies for database for test and dev.

##Example Spring4 with Content Negotiation Resolver

**ContentNegotiationViewResolver** class implement ViewResolver. The ContentNegotiationViewResolver doesn't resolve the view by itself unlike the ViewResolver based on the request type or the Header type in fact this delegates the request to other ViewResolvers that are configured explicity.

This view resolver uses the request media type to select a suitable View for a request. The request media type is determinated through the ContentNegotiationManager. Once the requested media type has been determined, this resolver queries each delegate view resolver for a View and determines if the requested media type is compatible with view's content type. The most compatible view is returned.

##Template the Spring with annotations and the following technologies:

- Spring Data JPA
- Spring MVC
- Thymeleaf Engine
- Maven Technology


##Database

###The diagram of the dataBase is made in MySQL, you can find the schema and the script in the following directory:

- extra/Library_for_Students.mwb
- extra/Library_for_Students.sql

###The database diagram is the following:

![alt tag](extra/libraryDB.png)
