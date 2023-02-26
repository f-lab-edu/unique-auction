FROM gradle:8-jdk11-alpine as builder
ENV ROOT /scouter/agent.java
RUN gradle build -x test --parallel
COPY --from=builder app.jar .
COPY entrypoint.sh entrypoint.sh
COPY ${ROOT}/scouter.agent.jar ${ROOT}/scouter.agent.jar
COPY ${ROOT}/conf/scouter.conf ${ROOT}/conf/scouter.conf
RUN chmod +x /entrypoint.sh

ENTRYPOINT ["./entrypoint.sh"]