#!/bin/bash

STARTTIME=$(date +%s)

outputPath="../lib/"
# 把相对路径转化成绝对路径
outputPath=`cd "${outputPath}";pwd`

scriptPath="./universal-framework"
projectName="RCTMaxSocialShare.xcodeproj"

buildCommand='sh '$scriptPath' -project '${projectName}' -scheme RCTMaxSocialShare -productName RCTMaxSocialShare -output '"${outputPath}"
$buildCommand
#
# open "${outputPath}"

ENDTIME=$(date +%s)

echo "It takes $(($ENDTIME - $STARTTIME)) seconds to complete this task..."
