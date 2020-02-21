# Sprello
![Spring Boot](https://olaralex.com/assets/images/spring-boot.jpg)
![Vue.js](https://tuhub.ru/sites/default/files/2018-02/vuejs-logo_0.jpg)
---

## Sprello (Spring + Trello)
Sprello - это веб-сервис, являющийся идейным клоном [Trello](https://trello.com/).

## Стек проекта:
- [Spring Boot](https://spring.io/projects/spring-boot)
  - [Spring Data](https://spring.io/projects/spring-data)
  - [Spring Security](https://spring.io/projects/spring-security) (OAuth2)
- [PostgreSQL](https://www.postgresql.org/)
- [Vue.js](https://vuejs.org/) (SPA)
  - [Vue Router](https://router.vuejs.org/)
  - [Vuex](https://vuex.vuejs.org/)

## Сборка и запуск
##### Для сборки необходимо иметь следующие пакеты:
- [JDK 11](https://www.oracle.com/java/technologies/javase/jdk11-readme.html)
- [Gradle](https://gradle.org/) (опционально)
- [npm](https://www.npmjs.com/)

##### Настройка переменных окружения
Перед сборкой предварительно необходимо получить OAuth2 данные в Google OAuth2 и
объявить следующие переменные окружения:
- **psql_url** - URL PostgreSQL
  - Пример: _jdbc:postgresql://127.0.0.1:5432/database_name_
- **psql_login** - Логин PostgreSQL пользователя
- **psql_password**  - Пароль PostgreSQL пользователя
- **client_id** - Google OAuth2 cliend-id
- **client_code** - Google OAuth2 client-secret

##### Сборка
Вместо `./gradlew <command>` можно использовать
`gradle <command>`, если Gradle установлен в системе
```
$ git clone https://gitlab.com/sprello-developers/sprello.git
$ cd sprello
$ ./gradlew bootJar
```
##### Запуск
```
$ ./gradlew bootRun
```
_или после сборки_
```
$ java -jar ./build/libs/sprello.jar
```

## Docker
Для сборка приложения в контейнере требуется пробросить
в контейнер вышеописанные environment variables.

Для удобства предлагается создать файл **.env** с переменными окружения,
(_пример оформления файла .env можно посмотреть в **.env.example**_)

Предлагаются следующие параметры для запуска веб-приложения в Docker контейнере:
```
$ docker run -it --name container_name -p 8080:8080 --env-file=./.env image_name
```