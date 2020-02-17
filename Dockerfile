FROM alpine

RUN apk add --update --no-cache npm

RUN apk add --no-cache openjdk11

EXPOSE 8080

VOLUME /home/sprello

WORKDIR /home/sprello

COPY . /home/sprello/

CMD ./gradlew bootJar && java -jar ./build/libs/sprello.jar

#TODO: прокинуть переменные окружения
