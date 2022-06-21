
get the gluon graal version:

https://github.com/gluonhq/graal/releases/tag/gluon-22.0.0.3-Final

set JAVA_HOME to that JDK downloaded above

edit pom.xml to also point to the JDK above (not sure if both are needed, but better safe than sorry)

for android you want to do:

./mvnw -Pandroid gluonfx:build gluonfx:package


if you add code that usse any form of reflection, that will need an updated `reflect-config.json`, you can generate the config automatically by running the application with `./mvnw gluonfx:runagent` however, that will generate too much configuration and you will have to delete everything that sounds like it is related to graphics.

See commit 3c57e25e2d83f5ace4d8779f055a0ba4176ebc9b for things I removed manually


auto generated readme below with more possible options.



# My Gluon Application

This Gluon sample was generated from https://start.gluon.io

## Basic Requirements

A list of the basic requirements can be found online in the [Gluon documentation](https://docs.gluonhq.com/#_requirements).

## Quick instructions

### Run the sample on JVM/HotSpot:

    mvn gluonfx:run

### Run the sample as a native image:

    mvn gluonfx:build gluonfx:nativerun

### Run the sample as a native android image:

    mvn -Pandroid gluonfx:build gluonfx:package gluonfx:install gluonfx:nativerun

### Run the sample as a native iOS image:

    mvn -Pios gluonfx:build gluonfx:package gluonfx:install gluonfx:nativerun

## Selected features

This is a list of all the features that were selected when creating the sample:

### JavaFX 18 Modules

 - javafx-base
 - javafx-graphics
 - javafx-controls

### Gluon Features

 - Glisten: build platform independent user interfaces
 - Attach ble
 - Attach connectivity
 - Attach display
 - Attach lifecycle
 - Attach pictures
 - Attach position
 - Attach push notifications
 - Attach statusbar
 - Attach storage
