FROM eclipse-temurin:17-jre

MAINTAINER UNAVA <lukas.rejha@gmail.com>

# add non root user
RUN addgroup --system --gid 10001 sampleproject && adduser --system --home /opt/sampleproject --uid 10001 --ingroup sampleproject sampleproject

COPY data/docker /
COPY data/target/*.jar /opt/sampleproject/app.jar

WORKDIR /opt/sampleproject

ENV JDBC_URL=
ENV JDBC_USERNAME=
ENV JDBC_PASSWORD=
ENV RABBITMQ_URL=
ENV RABBITMQ_USERNAME=
ENV RABBITMQ_PASSWORD=
ENV JAVA_OPTS=

USER 10001

ENTRYPOINT ["/opt/sampleproject/entry.sh"]