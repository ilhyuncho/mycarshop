#!/usr/bin/env bash

PROJECT_ROOT="/home/ubuntu/app"
JAR_FILE="$PROJECT_ROOT/mycarshop.jar"
LOG_DIR="$PROJECT_ROOT/logs"

APP_LOG="$LOG_DIR/application.log"
ERROR_LOG="$LOG_DIR/error.log"
DEPLOY_LOG="$LOG_DIR/deploy.log"

# 앱 실행에 필요한 디렉터리 (파일 업로드, 로그)
mkdir -p "$PROJECT_ROOT/file_repo" "$LOG_DIR"

if [ -f "$DEPLOY_LOG" ] && [ ! -w "$DEPLOY_LOG" ]; then
  DEPLOY_LOG="/home/ubuntu/logs/deploy.log"
fi
if [ -f "$APP_LOG" ] && [ ! -w "$APP_LOG" ]; then
  APP_LOG="/home/ubuntu/logs/application.log"
fi
if [ -f "$ERROR_LOG" ] && [ ! -w "$ERROR_LOG" ]; then
  ERROR_LOG="/home/ubuntu/logs/error.log"
fi
touch "$DEPLOY_LOG" "$APP_LOG" "$ERROR_LOG"

TIME_NOW=$(date +%c)

# build 파일 복사
echo "$TIME_NOW > $JAR_FILE 파일 복사" >> $DEPLOY_LOG
cp $PROJECT_ROOT/build/libs/*.jar $JAR_FILE

# 재기동 시 이전 로그 백업 (ANSI 섞인 옛 application.log 와 구분)
if [ -f "$APP_LOG" ] && [ -s "$APP_LOG" ]; then
  mv "$APP_LOG" "${APP_LOG}.bak.$(date +%Y%m%d_%H%M%S)"
fi
if [ -f "$ERROR_LOG" ] && [ -s "$ERROR_LOG" ]; then
  mv "$ERROR_LOG" "${ERROR_LOG}.bak.$(date +%Y%m%d_%H%M%S)"
fi
touch "$APP_LOG" "$ERROR_LOG"

# jar 파일 실행
echo "$TIME_NOW > $JAR_FILE 파일 실행" >> $DEPLOY_LOG
nohup java -jar -Duser.timezone=Asia/Seoul -Dspring.profiles.active=aws $JAR_FILE > $APP_LOG 2> $ERROR_LOG &

CURRENT_PID=$(pgrep -f $JAR_FILE)
echo "$TIME_NOW > 실행된 프로세스 아이디 $CURRENT_PID 입니다." >> $DEPLOY_LOG
