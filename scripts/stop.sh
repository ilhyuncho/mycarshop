#!/usr/bin/env bash

PROJECT_ROOT="/home/ubuntu/app"
JAR_FILE="$PROJECT_ROOT/mycarshop.jar"
LOG_DIR="$PROJECT_ROOT/logs"
SERVER_PORT=8080

DEPLOY_LOG="$LOG_DIR/deploy.log"
mkdir -p "$LOG_DIR"
if [ -f "$DEPLOY_LOG" ] && [ ! -w "$DEPLOY_LOG" ]; then
  DEPLOY_LOG="/home/ubuntu/logs/deploy.log"
fi
touch "$DEPLOY_LOG"

TIME_NOW=$(date +%c)

log() {
  echo "$TIME_NOW > $1" >> "$DEPLOY_LOG"
}

is_app_running() {
  pgrep -f "$JAR_FILE" >/dev/null 2>&1
}

is_port_in_use() {
  ss -lntp 2>/dev/null | grep -q ":${SERVER_PORT} "
}

stop_app_processes() {
  local pids
  pids=$(pgrep -f "$JAR_FILE" || true)

  if [ -z "$pids" ]; then
    log "현재 실행중인 애플리케이션이 없습니다"
    return 0
  fi

  log "실행중인 애플리케이션 종료 시도 (PID: $pids)"
  kill -15 $pids 2>/dev/null || true

  local i
  for i in $(seq 1 30); do
    if ! is_app_running && ! is_port_in_use; then
      log "애플리케이션 종료 완료 (${i}초)"
      return 0
    fi
    sleep 1
  done

  pids=$(pgrep -f "$JAR_FILE" || true)
  if [ -n "$pids" ]; then
    log "SIGTERM 후에도 남아 있어 SIGKILL (PID: $pids)"
    kill -9 $pids 2>/dev/null || true
    sleep 2
  fi

  if is_port_in_use; then
    pids=$(ss -lntp 2>/dev/null | grep ":${SERVER_PORT} " | grep -o 'pid=[0-9]*' | cut -d= -f2 | sort -u || true)
    if [ -n "$pids" ]; then
      log "8080 포트 점유 프로세스 강제 종료 (PID: $pids)"
      kill -9 $pids 2>/dev/null || true
      sleep 2
    fi
  fi
}

stop_app_processes

if is_port_in_use; then
  log "경고: 8080 포트가 아직 사용 중입니다"
  exit 1
fi

log "stop.sh 완료"
