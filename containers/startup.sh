#!/bin/bash

export WILDFLY_HOME=/opt/jboss/wildfly

export JAVA_OPTS="$JAVA_OPTS -agentlib:jdwp=transport=dt_socket,address=8787,server=y,suspend=n"

eth0addr=$(/sbin/ifconfig eth0 | grep 'inet ' | tr -s ' ' | cut -d' ' -f3)
echo "Wildfly listening on ${eth0addr}"

# This will boot WildFly in the standalone mode and bind to Docker bridged interface
$WILDFLY_HOME/bin/standalone.sh -b $eth0addr -bmanagement $eth0addr
