# 封裝成 jar 檔
## 1. 編譯
* 執行目錄 : `PD2_Final_Project`
* 指令 : (windows)
```shell
javac .\project\*.java .\project\AlpacaAPICall\*.java .\project\GUI\*.java .\project\GUI\GUITools\*.java .\project\System\*.java .\project\trade\*.java -cp ".;{lib的絕對路徑}/lib/jfreechart.jar;{lib的絕對路徑}/lib/jcommon.jar" -d .\out
```
* 註 : `lib`要放在`PD2_Final_Project`下

<br>

## 2. 包成 jar 檔
* 執行目錄 : `PD2_Final_Project/out`
* 指令 : (windows)
```shell
jar -cvfm AutoTradingAgent.jar manifest.MF .\project
```
> * c : create 
> * v : 列出包了什麼東西 
> * f : 指定jar檔的文件名 
> * m : 用manifest檔 
> - 其他資訊 : https://www.twblogs.net/a/5b8d050d2b71771883395ce4

<br>

## 3. 執行
1. 在命令列執行
```shell
java -jar AutoTradingAgent.jar
```
2. 直接對 jar檔 點兩下