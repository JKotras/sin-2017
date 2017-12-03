#!/bin/sh
wget "http://jade.tilab.com/dl.php?file=JADE-bin-4.5.0.zip" -O ./libs/jade.zip
wget "https://repo1.maven.org/maven2/com/google/code/gson/gson/2.8.2/gson-2.8.2.jar" -O ./libs/gson-2.8.2.jar
cd libs 
unzip jade.zip -d ./
cp ./jade/lib/jade.jar ./jade.jar


