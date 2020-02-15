<template>
  <div>
    <!-- Добавление задания -->
    <div class="card mt-4 text-center">
      <div class="card-header">
        <span class="h4">Добавить задание</span>
      </div>
      <div class="form-group px-1">
        <input
          class="form-control mt-3"
          v-model="newTask.name"
          type="text"
          maxlength="48"
          placeholder="Введите название задания"
        />
        <button
          type="button"
          @click="create"
          class="btn btn-outline-primary mt-2"
        >
          Создать
        </button>
      </div>
      <hr />
      <router-link
        :to="{
          name: 'requestors',
          params: {
            boardid: this.$route.params.boardid
          }
        }"
      >
        <button class="btn btn-outline-dark">
          Посмотреть запросы на вступление
        </button>
      </router-link>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "siteBoardManageComponent",

  data() {
    return {
      newTask: {
        name: ""
      }
    };
  },

  methods: {
    ...mapActions(["createTask", "deleteTask"]),
    create() {
      if (this.newTask.name !== "")
        this.createTask({
          boardId: this.$store.getters.boardInfo.id,
          name: this.newTask.name
        });
      this.newTask.name = "";
    }
  }
};
</script>

<style scoped></style>
