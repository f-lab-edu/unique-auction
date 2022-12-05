cd /home/project/unique-auction

BUILD_PATH=$(ls build/libs/*-SNAPSHOT.jar)
JAR_NAME=$(ls $BUILD_PATH | grep 'uniqueauction' | tail -n 1 | xargs -0 -n 1 basename )
echo "> build 파일명: $JAR_NAME"




RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile)

echo ">$RESPONSE_CODE response code"

# 아무것도 구동중이지  않을 때 우리가 프로젝트에서 만든 api로 조회하면 당연히 오류가 난다. 이를 방지!
if [ ${RESPONSE_CODE} -ge 400 ]
then
        echo "> There is no running app lets set real2"
        CURRENT_PROFILE=real2
else
        CURRENT_PROFILE=$(curl -s http://localhost/profile)
fi


echo ">$CURRENT_PROFILE current profile"

echo "> build 파일 복사"
if [ $CURRENT_PROFILE == 'real1' ]
then
  IDLE_PROFILE=real2
  IDLE_PORT=8082

  DEPLOY_PATH=/home/project/unique-auction-2/jar/

elif [ $CURRENT_PROFILE == 'real2' ]
then
  IDLE_PROFILE=real1
  IDLE_PORT=8081
  DEPLOY_PATH=/home/project/unique-auction-1/jar/
else
  echo "> 일치하는 Profile이 없습니다. Profile: $CURRENT_PROFILE"
  echo "> real1을 할당합니다. IDLE_PROFILE: real1"
  IDLE_PROFILE=real1
  IDLE_PORT=8081
  DEPLOY_PATH=/home/project/unique-auction-2/jar/
fi

sudo cp $BUILD_PATH $DEPLOY_PATH


IDLE_APPLICATION=$IDLE_PROFILE-uniqueauction.jar

sudo ln -Tfs $DEPLOY_PATH$JAR_NAME $DEPLOY_PATH$IDLE_PROFILE-uniqueauction.jar


echo "> $IDLE_PROFILE 에서 구동중인 애플리케이션 pid 확인"
IDLE_PID=$(pgrep -f $IDLE_APPLICATION)

if [ -z $IDLE_PID ]
then
  echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
  echo "> kill -15 $IDLE_PID"
  sudo kill -15 $IDLE_PID
  sleep 10
fi

echo "> $IDLE_PROFILE 배포"
echo "> Change Directory to $DEPLOY_PATH "
cd $DEPLOY_PATH
echo "> $IDLE_APPLICATION Deploying "

nohup java -jar $IDLE_APPLICATION --spring.profiles.active=$IDLE_PROFILE --logging.file.path=/home/deploy/log/ --logging.level.org.hibernate.SQL=DEBUG >> /home/deploy/log/deploy.log 2>/home/deploy/log/deploy_err.log &


echo "> Profile Switching"
sleep 10
/home/project/unique-auction/scripts/switch.sh
