<template>
    <div>
        <div class="row justify-content-around no-gutters">
            <template v-if="serverResponded && !messagesEmpty">
                <div v-for="msg in messages" class="col-10 col-md-5 py-1 my-1">
                    <div class="border border-primary px-1">
                        <b>{{ msg.id }}</b>) {{ msg.text }}
                        <hr/>
                        <slot/>
                    </div>
                </div>
            </template>
            <template v-else-if="serverResponded && messagesEmpty">
                <div class="col text-center">
                    <h2 class="alert alert-primary my-5">
                        Нет сообщений
                    </h2>
                </div>
            </template>
            <template v-else-if="!serverResponded && messagesEmpty">
                <div class="col text-center">
                    <div class="spinner-border text-primary  my-5" role="status">
                        <span class="sr-only">Loading...</span>
                    </div>
                </div>
            </template>
        </div>
    </div>
</template>

<script>
  export default {
    name: "siteMessages",

    data: function () {
      return {
        messages: [],
        serverResponded: false
      }
    },

    created: function () {
      this.getAllMessages(this.messages)
    },

    methods: {
      getAllMessages: function (msgArray) {
        this.$http.get('/api/messages/').then(
            response => {
              if (response.ok) {
                response.body.forEach(msg => msgArray.push(msg))
                this.serverResponded = true
              } else
                throw new Error("Server response: " + error.status)
            }
        ).catch((error) => {
          console.error(error.body)
        });
      }
    },

    computed: {
      messagesEmpty: function () {
        return this.messages.length === 0
      }
    }
  }
</script>

<style scoped>

</style>