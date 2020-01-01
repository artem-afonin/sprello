<template>
    <div class="wrapper bg-light text-black-50">
        <!--CONTAINER START-->
        <div class="container border p-0 bg-light">
            <!--HEADER START-->
            <header class="border-bottom bg-primary">
                <div class="row no-gutters">
                    <div class="col">
                        <h1 class="text-center text-black-50">Sprello</h1>
                    </div>
                </div>
            </header>
            <!--HEADER END-->

            <!--NAVIGATION START-->
            <nav class="navbar navbar-light">
                <ul class="nav nav-pills nav-fill mr-auto">
                    <li class="nav-item">
                        <a href="#" class="nav-link font-weight-bold">Главная</a>
                    </li>
                    <li class="nav-item">
                        <a href="#" class="nav-link font-weight-bold">Доски</a>
                    </li>
                    <li class="nav-item">
                        <a href="#" class="nav-link font-weight-bold">Группы</a>
                    </li>
                    <li class="nav-item">
                        <a href="#" class="nav-link font-weight-bold">Пользователи</a>
                    </li>
                    <li class="nav-item">
                        <a href="#" class="nav-link font-weight-bold">О Sprello</a>
                    </li>
                </ul>
            </nav>
            <!--NAVIGATION END-->

            <!--CENTRAL BLOCK START-->
            <div class="row no-gutters">
                <!--CONTENT START-->
                <div class="col-9">
                    <div class="row no-gutters">
                        <template v-if="!messagesEmpty">
                            <div v-for="msg in messages" class="col-lg-3 col-sm-6">
                                <div class="panel border border-primary">
                                    <b>{{ msg.id }}</b>) {{ msg.text }}
                                    <hr/>
                                    Amors sunt fortiss de regius clabulare.
                                    Sunt gemnaes experientia regius, domesticus guttuses.
                                    A falsis, fortis salvus tabes.
                                    Est barbatus victrix, cesaris.
                                    Resistentia velums, tanquam festus guttus.
                                </div>
                            </div>
                        </template>
                        <template v-else-if="messagesEmpty && serverResponded">
                            <div class="col">
                                <h2>Нет сообщений</h2>
                            </div>
                        </template>
                        <template v-else-if="messagesEmpty">
                            <div class="col">
                                <h2>Связываемся с сервером...</h2>
                            </div>
                        </template>
                        <template v-if="messagesNull">
                            <div class="col">
                                <h2>Не удалось получить данные с сервера!</h2>
                            </div>
                        </template>
                    </div>
                </div>
                <!--CONTENT END-->

                <!--SIDEBAR START-->
                <aside class="col-3 border-left">
                    <ul class="list-group">
                        <li class="list-group-item">1 menu</li>
                        <li class="list-group-item">2 menu</li>
                        <li class="list-group-item">3 menu</li>
                        <li class="list-group-item">4 menu</li>
                    </ul>
                </aside>
                <!--SIDEBAR END-->
            </div>
            <!--CENTRAL BLOCK END-->

            <!--FOOTER START-->
            <footer class="row no-gutters border-top">
                <div class="col">
                    <h3>Footer</h3>
                </div>
            </footer>
            <!--FOOTER END-->
        </div>
        <!--CONTAINER END-->
    </div>
</template>

<script>
  import $ from 'jquery';

  export default {
    name: "index",

    data: function () {
      return {
        serverResponded: false,
        messages: []
      }
    },

    created: function () {
      try {
        this.getAllMessages(this.messages);
        this.serverResponded = true;
      } catch (error) {
        this.messages = null;
        console.error(error);
      }
    },

    mounted: function () {
      const classes = 'active';
      $('nav>ul>li>a').hover(function () {
        $(this).addClass(classes);
      }, function () {
        $(this).removeClass(classes);
      });
    },

    methods: {
      getAllMessages: function (msgArray) {
        this.$http.get('/messages/').then(
            response => {
              if (response.ok)
                response.body.forEach(msg => msgArray.push(msg));
              else
                console.log("Server response: " + response.status);
            },
            error => {
              console.log(error);
              throw new Error("Server ERROR response: " + error.status);
            }
        );
      }
    },

    computed: {
      messagesEmpty: function () {
        return this.messages.length === 0;
      },
      messagesNull: function () {
        return this.messages === null;
      }
    }
  }
</script>

<style>
    nav {
        background-color: #e3f2fd;
    }
</style>