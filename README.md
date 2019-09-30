# VL53L0X_bundle

This git project contains source codes for VL53L0X Time of Flight sensor. Sensor will measure the distance to some object and if the distance is between 60 and 200mm it will record a 10s long video clip using Raspberry Pi's camera module. It will also light up a LED light while it is recording. Once the recording is done MP4Box will pack the video to MP4 format and upload it to shared disk using ftp (This is just for demoing so security wasn't the priority).
