#!/bin/bash
systemctl stop gunicorn.socket
systemctl stop gunicorn.service
systemctl start gunicorn.socket
systemctl restart nginx.service
