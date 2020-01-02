<template>
    <div class="wrapper bg-light text-black-50">
        <!--CONTAINER START-->
        <div class="container border p-0 bg-light">
            <!--HEADER START-->
            <site-header v-bind:class="'border-bottom bg-primary'">Sprello</site-header>
            <!--HEADER END-->

            <!--NAVIGATION START-->
            <site-navigation v-bind:buttons="navigationButtons"/>
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
            <footer class="row no-gutters bg-primary border-top">
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
  import siteHeader from "../components/siteHeader.vue";
  import siteNavigation from "../components/siteNavigation.vue";

  export default {
    name: "index",

    components: {
      siteHeader,
      siteNavigation
    },

    data: function () {
      return {
        messages: [],
        serverResponded: false,
        navigationButtons: [
          {name: 'Главная', href: '#'},
          {name: 'Доски', href: '#'},
          {name: 'Группы', href: '#'},
          {name: 'Пользователи', href: '#'},
          {name: 'О Sprello', href: '#'}
        ]
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

<style scoped>
</style>