#!/bin/sh

AGENT_OPTS=${AGENT_OPTS:="-javaagent:/scouter/agent.java/scouter.agent.jar"}
CONF_OPTS=${CONF_OPTS:="-Dscouter.config=/scouter/agent.java/conf/scouter.conf"}
ACTIVE_PROFILE=${ACTIVE_PROFILE:="-Dspring.profiles.active=prod-main"}
JAVA_OPTS=${JAVA_OPTS:=$AGENT_OPTS $CONF_OPTS $ACTIVE_PROFILE}

echo "JAVA_OPTS=${JAVA_OPTS}"
exec java -jar ${JAVA_OPTS} app.jar $@