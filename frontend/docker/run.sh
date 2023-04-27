#!/usr/bin/env bash
if [ -n "$API_BASE_URL" ]; then
  sed -i "s/localhost:8080/$API_BASE_URL/g" /usr/share/nginx/html/main.*.js
fi

nginx -g 'daemon off;'
