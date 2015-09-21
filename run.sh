#!/bin/bash

readonly APP_NAME="target/spring-boot-thymeleaf-1.0-SNAPSHOT.jar"

readonly JAVA_PATH="/usr/lib/jvm/java-8-oracle/bin/java"
readonly JAVA_OPTS="-Xms128m -Xmx256m"

set -x
exec ${JAVA_PATH} ${JAVA_OPTS} -jar ${APP_NAME}