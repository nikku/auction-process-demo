auction-app
===========

A sample process supported auction application built with camunda fox and Java EE technologies.


Required environment
--------------------

* (IDE)
* maven


Used technologies
-----------------

* JPA2, EJB 3.1, JSF, JAX-RS, CDI, (JAX-WS), ...


How to start
------------

Deploy to a running Java EE application server with camunda fox platform installed.

E.g, to deploy to a running JBoss AS 7.1 via maven use `mvn -DskipTests clean package org.jboss.as.plugins:jboss-as-maven-plugin:7.1.0.Final:deploy`.