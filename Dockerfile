FROM maven:3.6.3-jdk-11-slim AS build
COPY src /tmp/statsagg/src
COPY pom.xml /tmp/statsagg/pom.xml
RUN mkdir /tmp/statsagg/logs
RUN mvn -f /tmp/statsagg/pom.xml clean package

FROM adoptopenjdk/openjdk11:debianslim-jre

COPY --from=build /tmp/statsagg/target/*.jar /opt/StatsAgg/StatsAgg.jar
COPY --from=build /tmp/statsagg/target/lib /opt/StatsAgg/lib
COPY --from=build /tmp/statsagg/src/webapp /opt/StatsAgg/webapp
COPY conf/example-application.ini /opt/StatsAgg/conf/application.ini
COPY conf/logback-config.xml /opt/StatsAgg/conf/logback-config.xml
COPY conf/example-flyway.properties /opt/StatsAgg/conf/flyway.properties
COPY conf/example-database.properties /opt/StatsAgg/conf/database.properties
RUN mkdir /opt/StatsAgg/logs
RUN adduser -system statsagg
WORKDIR /opt/StatsAgg
RUN chown -R statsagg:adm .

EXPOSE 8080
EXPOSE 8125
EXPOSE 22003
EXPOSE 2003
EXPOSE 4242
EXPOSE 8086

CMD ["java","-XX:InitialRAMPercentage=40","-XX:MaxRAMPercentage=60","-XX:MinRAMPercentage=25","-XX:MaxGCPauseMillis=5000","-XX:+UseStringDeduplication","-XX:-UsePerfData","-Djava.net.preferIPv4Stack=true","-Djava.net.preferIPv4Addresses=true","-jar","StatsAgg.jar"]

# docker build -t statsagg .
# docker volume create statsagg-data

# Run with default configurations
# docker run -d \
#     --name statsagg \
#     -p 8080:8080 \
#     -p 8125:8125 \
#     -p 22003:22003 \
#     -p 2003:2003 \
#     -p 4242:4242 \
#     -p 8086:8086 \
#     -v statsagg-data:/data \
#     statsagg

# Run with conf directory overwritten
# docker run -it \
#   --name statsagg \
#   -p 8080:8080 \
#   -p 8125:8125 \
#   -p 22003:22003 \
#   -p 2003:2003 \
#   -p 4242:4242 \
#   -p 8086:8086 \
#   --mount type=bind,src=/Users/uthiech/desktop/Forked_StatsAgg/StatsAgg/test_conf,target=/opt/StatsAgg/conf \
#   -v statsagg-data:/data \
#   statsagg