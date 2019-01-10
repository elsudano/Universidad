#!/bin/bash
systemctl stop gunicorn.service
systemctl restart gunicorn.socket
systemctl restart nginx.service
