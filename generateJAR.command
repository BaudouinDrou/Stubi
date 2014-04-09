cd META-INF
echo src/game/*.java > fileList.txt
javac -d . @fileList.txt 

cp /src/img/*.png /img/
cp /src/conf/* /conf/

jar cvfm "../Stubi.jar" MANIFEST.MF .