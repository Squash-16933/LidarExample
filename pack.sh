#/!bin/bash
now=$(date +"%m_%d_%Y_%H_%M_%S")

MP4Box -add raw_pivideo.h264 /home/jari/video/mp4/pivideo_$now.mp4
sudo rm raw_pivideo*
./upload.sh mp4/pivideo_$now.mp4
rm mp4/*
