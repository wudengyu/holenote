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