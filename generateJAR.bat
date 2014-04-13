@ECHO ON

javac src/game/*.java
move Y src\game\*.class tmp\game\
copy src\img\*.png tmp\img\ 
copy src\conf\ tmp\conf\

cd tmp
jar cvfm "../Stubi.jar" MANIFEST.MF .

@PAUSE