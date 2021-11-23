@echo off

set CD=%~dp0

SET mainPath=%CD%java\src\edu\csf\oop\java\durak
set logPath=%CD%lib\log

javac --upgrade-module-path "%logPath%" --add-modules ALL-MODULE-PATH -d bin %mainPath%\*.java -d bin %mainPath%\play\*.java -d bin %mainPath%\baseDescriptions\*.java -d bin %mainPath%\cardDeckDescriptions\*.java
