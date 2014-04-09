@ECHO ON

javac src/game/*.java
move Y src\game\*.class META-INF\game\
copy src\img\*.png META-INF\img\ 
copy src\conf\ META-INF\conf\

cd META-INF
jar cvfm "../Stubi.jar" MANIFEST.MF .

@PAUSE