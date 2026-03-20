@echo off
set DIRNAME=%~dp0
set APP_HOME=%DIRNAME%

set DEFAULT_JVM_OPTS=

set CLASSPATH=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar

java %DEFAULT_JVM_OPTS% -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*
