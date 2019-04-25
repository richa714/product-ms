FROM openjdk:8
COPY ./target/product-ms-0.0.1-SNAPSHOT.jar product-ms-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","product-ms-0.0.1-SNAPSHOT.jar"]