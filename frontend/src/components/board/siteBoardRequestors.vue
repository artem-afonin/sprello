<template>
  <div>
    <div class="row px-2">
      <div class="col-4">
        <img :src="requestor.userpic" class="w-75 rounded-circle" />
      </div>
      <div class="col-8 py-3">
        <h4 class="w-100">
          {{ requestor.name }}
        </h4>
        <button @click="applyNewUser" class="btn btn-success">
          Добавить пользователя
        </button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "siteBoardRequestors",

  props: ["requestor"],

  methods: {
    async applyNewUser(event) {
      try {
        await this.$store.dispatch("acceptRequest", {
          boardId: this.$route.params.boardid,
          requestorId: this.requestor.id
        });
        const button = event.target;
        button.innerText = "Успех!";
      } catch (e) {
        const button = event.target;
        button.classList.remove("btn-success");
        button.classList.add("btn-danger");
        button.innerText = "Ошибка!";
      } finally {
        event.target.disabled = true;
      }
    }
  }
};
</script>

<style scoped></style>
