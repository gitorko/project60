FROM openjdk:11
WORKDIR /var/app
COPY ./build/libs/project60-1.0.0.jar /var/app/project60-1.0.0.jar
ENV _JAVA_OPTIONS '-Dspring.data.mongodb.host=mongodb'
CMD [ "java", "-jar", "/var/app/project60-1.0.0.jar" ]
EXPOSE 8080
