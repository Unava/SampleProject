#!/bin/sh

sampleProjectHome="/opt/sampleproject"

log "INFO" "Launching the SampleProject"
java $JAVA_OPTS -jar ./app.jar
