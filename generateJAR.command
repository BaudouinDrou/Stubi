#! /bin/sh

sudo javac src/game/*.java
sudo mv src/game/*.class META-INF/game/
sudo cp src/img/*.png META-INF/img/ 
sudo cp src/conf/ META-INF/conf/

sudo cd META-INF
sudo jar cvfm "../Stubi.jar" MANIFEST.MF .