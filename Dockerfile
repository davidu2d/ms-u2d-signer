FROM adoptopenjdk/openjdk11:ubi
EXPOSE 8181
RUN mkdir /opt/app
ADD target/ms-u2d-signer.jar /opt/app
ENTRYPOINT ["java", "-jar", "/opt/app/ms-u2d-signer.jar"]