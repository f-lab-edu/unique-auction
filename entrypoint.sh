#!/bin/sh

AGENT_OPTS=${AGENT_OPTS:="-javaagent:/scouter/agent.java/scouter.agent.jar"}
CONF_OPTS=${CONF_OPTS:="-Dscouter.config=/scouter/agent.java/conf/scouter.conf"}
JAVA_OPTS=${JAVA_OPTS:=$AGENT_OPTS $CONF_OPTS }

echo "JAVA_OPTS=${JAVA_OPTS}"
exec java -jar ${JAVA_OPTS} app.jar $@