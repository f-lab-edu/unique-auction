#!/bin/sh

set -e

AGENT_OPTS=${AGENT_OPTS:="-javaagent:/scouter/agent.java/pinpoint-bootstrap-2.2.0.jar"}
CONF_OPTS=${CONF_OPTS:="-Dscouter.config=/scouter/agent.java/conf/scouter.conf"}
JAVA_OPTS=${JAVA_OPTS:=$AGENT_OPTS $CONF_OPTS }

echo "JAVA_OPTS=${JAVA_OPTS}"

exec java -jar ${JAVA_OPTS} app.jar $@