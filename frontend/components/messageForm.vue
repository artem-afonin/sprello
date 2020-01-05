<template>
    <form autocomplete="off">
        <label>
            <input type="text" v-model="message" size="24" maxlength="128">
            <button @click="sendMessage">Отправить сообщение (текст)</button>
            <button @click="deleteMessage">Удалить сообщение (id)</button>
        </label>
    </form>
</template>

<script>
  export default {
    name: "messageForm",

    data: function () {
      return {
        message: ""
      }
    },

    methods: {
      sendMessage: function () {
        this.$http.post("/api/messages/", {text: this.message}).then(
            response => {
              console.log(response);
            },
            error => {
              console.error(error);
            }
        );
        this.message = "";
      },

      deleteMessage: function () {
        this.$http.delete(`/api/messages/${this.message}`).then(
            response => {
              console.log(response);
            },
            error => {
              console.error(error);
            });
            this.message = "";
      }
    }
  }
</script>

<style scoped>

</style>