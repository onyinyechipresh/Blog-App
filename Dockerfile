FROM openjdk:17
EXPOSE 8080
ADD target/blog-wk10.jar blog-wk10.jar
ENTRYPOINT ["java" , "-jar" , "blog-wk10.jar"]