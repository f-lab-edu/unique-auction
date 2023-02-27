#!/bin/bash

ROOT=/home/project/unique-auction
# docker login & image pull
docker pull vymr1000/unique-auction

# Blue 를 기준으로 현재 떠있는 컨테이너를 체크한다.
EXIST_BLUE=$(docker-compose -p unique-auction-blue -f ${ROOT}/deploy/docker-compose.blue.yml ps | grep Up)

# 컨테이너 스위칭
if [ -z "$EXIST_BLUE" ]; then
  echo "blue up"
  docker-compose -p unique-auction-blue -f ${ROOT}/deploy/docker-compose.blue.yml up -d
  BEFORE_COMPOSE_COLOR="green"
  AFTER_COMPOSE_COLOR="blue"
else
  echo "green up"
  docker-compose -p unique-auction-green -f ${ROOT}/deploy/docker-compose.green.yml up -d
  BEFORE_COMPOSE_COLOR="blue"
  AFTER_COMPOSE_COLOR="green"
fi
 
sleep 10
 
# 새로운 컨테이너가 제대로 떴는지 확인
EXIST_AFTER=$(docker-compose -p unique-auction-${AFTER_COMPOSE_COLOR} -f ${ROOT}/deploy/docker-compose.${AFTER_COMPOSE_COLOR}.yml ps | grep Up)
if [ -n "$EXIST_AFTER" ]; then
  # nginx.config를 컨테이너에 맞게 변경해주고 reload 한다
  cp /etc/nginx/nginx.${AFTER_COMPOSE_COLOR}.conf /etc/nginx/nginx.conf
  nginx -s reload

  # 이전 컨테이너 종료
  docker-compose -p unique-auction-${BEFORE_COMPOSE_COLOR} -f ${ROOT}/deploy/docker-compose.${BEFORE_COMPOSE_COLOR}.yml down
  echo "$BEFORE_COMPOSE_COLOR down"
fi

echo "> Deployment Complete"
