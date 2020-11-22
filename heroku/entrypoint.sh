#!/usr/bin/env sh

java \
  -XX:+UnlockExperimentalVMOptions \
  -XX:+UseContainerSupport \
  -XX:MetaspaceSize=100m \
  -Xmx256m \
  -Xss512k \
  -Denv=$ENV \
  -jar /app/app.jar
