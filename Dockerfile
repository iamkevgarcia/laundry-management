FROM java:8-alpine

COPY target/uberjar/laundry-management.jar /laundry-management/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/laundry-management/app.jar"]
