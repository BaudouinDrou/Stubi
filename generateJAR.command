cd "/src/game/"
javac -d "../../META-INF/game/" *.java 

cd ../img
cp *.png "../../META-INF/img/"
cd ../conf 
cp * "../../META-INF/conf/"

cd "../../META-INF"
jar cvfm "../Stubi.jar" MANIFEST.MF .