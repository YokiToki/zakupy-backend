FROM openjdk:11-jdk-slim

ARG JAR_FILE

WORKDIR /app

COPY ./build/${JAR_FILE} /app/app.jar
COPY ./heroku/entrypoint.sh /app/entrypoint.sh

RUN chmod +x /app/entrypoint.sh
CMD ["/app/entrypoint.sh"]
