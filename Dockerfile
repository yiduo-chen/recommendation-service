FROM eclipse-temurin:17-jre

WORKDIR /app

# 复制打包产物
COPY target/*.jar app.jar

# 应用端口
EXPOSE 8085

# 容器启动命令
ENTRYPOINT ["java","-jar","/app/app.jar"]
