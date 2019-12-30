<template>
    <div id="wrapper">
        <div id="container">
            <header id="header">
                <div class="row">
                    <div class="col">
                        <h1>Sprello</h1>
                    </div>
                </div>
            </header>

            <div class="row" id="main">
                <div class="col" id="content">
                    <span v-for="msg in messages">
                        <b>id:{{ msg.id }}</b> -> <i>{{ msg.text }}</i>
                        <br/>
                    </span>
                </div>

                <aside class="col-3" id="sidebar">
                    <ul>
                        <li>1 menu</li>
                        <li>2 menu</li>
                        <li>3 menu</li>
                        <li>4 menu</li>
                    </ul>
                </aside>
            </div>

            <footer id="footer" class="row">
                <div>
                    <h3>Footer</h3>
                </div>
            </footer>
        </div>
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

    mounted: function () {
      this.addClasses('no-gutters', '.row');
      this.getAllMessages(this.messages);
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
    }
  }
</script>

<style>
    #container {
        border: 1px solid blue;
    }

    #header {
        background-color: yellow;
    }

    #content {
        background-color: lightgreen;
    }

    #sidebar {
        background-color: bisque;
    }

    #footer {
        background-color: lightcoral;
    }
</style>