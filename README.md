Maven构建
```
mvn archetype:generate -DarchetypeArtifactId=maven-archetype-webapp -DarchetypeVersion=1.4
```
```
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
```
这段错误是因为某个依赖引入了slf4j-api，但是slf4j-api并不包含实现，所以应该找到导入slf4j-api的包，排除依赖，再导入slf4j-nop.jar slf4j-simple.jar, slf4j-log4j12.jar, slf4j-jdk14.jar or logback-classic.jar。

添加Gradle

由于目前主要只是用到依赖管理，所以对照pom.xml写一个build.gradle就行了，但是，由于使用了BOM控制Spring模块版本，Gradle 4.6以下的版本不支持，5.0以上才默认使能这个功能，所以需要手工安装Gradle的高版本，并且需要配置好环境变量GRADLE_HOME，或者配置集成开发环境使用Gradle的安装目录，否则即使用命令行可以正常构建，环境仍会提示无法解析的依赖。