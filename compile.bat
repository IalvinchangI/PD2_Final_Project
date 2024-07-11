@REM 1. 編譯
javac .\project\*.java .\project\AlpacaAPICall\*.java .\project\GUI\*.java .\project\GUI\GUITools\*.java .\project\System\*.java .\project\trade\*.java -cp "./project;./lib/jfreechart.jar;./lib/jcommon.jar" -d .\out

@REM 2. 包成 jar 檔
cd .\out
jar -cvfm AutoTradingAgent.jar manifest.MF .\project
cd ..