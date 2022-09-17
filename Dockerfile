FROM openjdk:11
VOLUME /tmp
ADD ./out/artifacts/br_bank_data_jar/br-bank-data.jar br-bank-data.jar
ENTRYPOINT ["java","-jar","/br-bank-data.jar"]