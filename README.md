#Sprello
![Spring Boot](https://olaralex.com/assets/images/spring-boot.jpg)
![Vue.js](https://tuhub.ru/sites/default/files/2018-02/vuejs-logo_0.jpg)
---

### Sprello (Spring + Trello)
Sprello - это веб-сервис, являющийся идейным клоном [Trello](https://trello.com/).

#### Стек проекта:
- Spring Boot
  - Spring Boot Data
  - Spring Boot Security (OAuth2)
- PostgreSQL
- Vue.js (SPA)
  - Vue Router
  - Vuex

Сборка и запуск
---
######Для сборки необходимо иметь следующие пакеты:
- JDK 11
- NPM
- Gradle (не обязательно)
---
###### Настройка переменных окружения
Перед сборкой предварительно необходимо получить OAuth2 данные в Google OAuth2 и
объявить следующие переменные окружения:
- **psql_url** - URL PostgreSQL
  - Пример: _jdbc:postgresql://127.0.0.1:5432/database_name_
- **psql_login** - Логин PostgreSQL пользователя
- **psql_password**  - Пароль PostgreSQL пользователя
- **client_id** - Google OAuth2 cliend-id
- **client_code** - Google OAuth2 client-secret
---
###### Сборка
Вместо `./gradlew <command>` можно использовать
`gradle <command>`, если Gradle установлен в системе
```
$ git clone https://gitlab.com/sprello-developers/sprello.git
$ cd sprello
$ ./gradlew bootJar
```
###### Запуск
```
$ ./gradlew bootRun
```
_или после сборки_
```
$ java -jar ./build/libs/sprello.jar
```
