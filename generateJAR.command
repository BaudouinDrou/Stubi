javac "src/game/*.java"
mv src/game/*.class META-INF/game/
cp src/img/*.png META-INF/img/ 
cp src/conf/ META-INF/conf/

cd META-INF
jar cvfm "../Stubi.jar" MANIFEST.MF .
