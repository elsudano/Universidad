#!/bin/bash
systemctl status nginx.service
systemctl status gunicorn.service
systemctl status gunicorn.socket
cat /var/log/nginx/access.log
cat /var/log/nginx/error.log
cat /var/log/gunicorn/error.log
