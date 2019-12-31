<template>
    <div class="wrapper">
        <!--CONTAINER START-->
        <div class="container">
            <!--HEADER START-->
            <header id="header">
                <div class="row">
                    <div class="col">
                        <h1>Sprello</h1>
                    </div>
                </div>
            </header>
            <!--HEADER END-->

            <!--CENTRAL BLOCK START-->
            <div class="center row">
                <!--CONTENT START-->
                <div class="col-9">
                    <div v-if="!messagesEmpty" class="row">
                        <div v-for="msg in messages" class="col-3">
                            <div class="panel" style="border: red solid 2px">
                                <b>{{ msg.id }}</b>) {{ msg.text }}
                                <hr/>
                                Amors sunt fortiss de regius clabulare.
                                Sunt gemnaes experientia regius, domesticus guttuses.
                                A falsis, fortis salvus tabes.
                                Est barbatus victrix, cesaris.
                                Resistentia velums, tanquam festus guttus.
                            </div>
                        </div>
                    </div>
                    <div v-else class="row">
                        <div class="col">
                            <h2>Не удалось связаться с сервером!</h2>
                        </div>
                    </div>
                </div>
                <!--CONTENT END-->

                <!--SIDEBAR START-->
                <aside class="col-3">
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
            <footer id="footer" class="row">
                <div>
                    <h3>Footer</h3>
                </div>
            </footer>
            <!--FOOTER END-->
        </div>
        <!--CONTAINER END-->
    </div>
</template>

<script>
  export default {
    name: "index",

    data: function () {
      return {
        messages: []
      }
    },

    created: function () {
      this.getAllMessages(this.messages);
    },

    mounted: function () {
      this.addClasses('no-gutters', '.row');
    },

    updated: function () {
      this.addClasses('no-gutters', '.row');
    },

    methods: {
      addClasses: function (newClass, searchClass) {
        document.querySelectorAll(searchClass).forEach((el) => {
          el.classList.add(newClass);
        })
      },

      getAllMessages: function (msgArray) {
        this.$http.get('/messages/').then(
            response => {
              if (response.ok)
                response.body.forEach(msg => msgArray.push(msg));
              else
                console.log(response.status);
            },
            error => console.log(error));
      }
    },

    computed: {
      messagesEmpty: function () {
        return this.messages.length === 0;
      }
    }
  }
</script>

<style>
    .wrapper {
        background-color: #eeeeee;
    }

    .container {
        border: 1px solid blue;
        padding: 0;
    }

    header {
        border: inherit;
        background-color: yellow;
    }

    .center {
        border: inherit;
    }

    aside {
        border-left: inherit;
    }

    footer {
        border: inherit;
        background-color: lightcoral;
    }
</style>