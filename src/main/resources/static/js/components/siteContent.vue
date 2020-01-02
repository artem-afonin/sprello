<template>
    <div class="col-9">
        <div class="row no-gutters">
            <template v-if="serverResponded && !messagesEmpty">
                <div v-for="msg in messages" class="col-lg-3 col-sm-6">
                    <div class="panel border border-primary">
                        <b>{{ msg.id }}</b>) {{ msg.text }}
                        <hr/>
                        <slot/>
                    </div>
                </div>
            </template>
            <template v-else-if="serverResponded && messagesEmpty">
                <div class="col">
                    <h2>Нет сообщений</h2>
                </div>
            </template>
            <template v-else-if="!serverResponded && messagesEmpty">
                <div class="col">
                    <h2>Связываемся с сервером...</h2>
                </div>
            </template>
        </div>
    </div>
</template>

<script>
  export default {
    name: "siteContent",

    data: function () {
      return {
        messages: [],
        serverResponded: false
      }
    },

    created: function () {
      this.getAllMessages(this.messages);
    },

    methods: {
      getAllMessages: function (msgArray) {
        this.$http.get('/api/messages/').then(
            response => {
              if (response.ok) {
                response.body.forEach(msg => msgArray.push(msg));
                this.serverResponded = true;
              }
              else
                throw new Error("Server response: " + error.status);
            }
        ).catch((error) => {
          console.error(error.body);
        });
      }
    },

    computed: {
      messagesEmpty: function () {
        return this.messages.length === 0;
      }
    }
  }
</script>

<style scoped>

</style>