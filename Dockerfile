FROM openjdk:8
EXPOSE 8282
ADD target/EmployeeCMSBackendMain-0.0.1-SNAPSHOT.jar EmployeeCMSBackendMain-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java","-jar","/EmployeeCMSBackendMain-0.0.1-SNAPSHOT.jar" ]