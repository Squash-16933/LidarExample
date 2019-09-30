#/!bin/bash

sudo raspivid -t $1  -w 1920 -h 1080 -fps 30 -o raw_pivideo.h264
