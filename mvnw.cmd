@echo off
set WRAPPER_JAR=./.mvn/wrapper/maven-wrapper.jar
set WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain

if "%JAVA_HOME%"=="" (
  echo "JAVA_HOME is not set"
  exit /b 1
)

"%JAVA_HOME%\bin\java" -classpath "%WRAPPER_JAR%" %WRAPPER_LAUNCHER% %*