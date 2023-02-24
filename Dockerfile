FROM openjdk:11
ENV ROOT /scouter/agent.java
ARG JAR_FILE=./build/libs/uniqueauction-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
COPY entrypoint.sh entrypoint.sh
COPY ${ROOT}/scouter.agent.jar ${ROOT}/scouter.agent.jar
COPY ${ROOT}/conf/scouter.conf ${ROOT}/conf/scouter.conf
RUN chmod +x /entrypoint.sh

ENTRYPOINT ["./entrypoint.sh"]