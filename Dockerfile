FROM swr.cn-north-4.myhuaweicloud.com/ddn-k8s/docker.io/eclipse-temurin:17-jdk

WORKDIR /app

COPY build/libs/*.jar app.jar

EXPOSE 5033

ENTRYPOINT ["java","-jar","app.jar"]