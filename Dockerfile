FROM java:8
LABEL maintainer="douglasmacbrito@gmail.com"
VOLUME /tmp
EXPOSE 8092
ARG JAR_FILE=target/livraria-1.0.jar
ADD ${JAR_FILE} livraria-1.0.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/livraria-1.0.jar"]