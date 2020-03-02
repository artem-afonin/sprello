<template>
  <div>
    <div class="card my-1">
      <div class="card-body text-center">
        <router-link
          :to="{
            name: 'boardpage',
            params: {
              boardid: board.id
            }
          }"
        >
          <h3 class="card-title text-dark">
            <b>
              {{ board.name }}
            </b>
          </h3>
          <template class="card-text">
            <h5 v-if="board.isMember" class="text-primary">
              Вы состоите в этой доске
            </h5>
            <h5 v-else-if="board.isRequestor" class="text-secondary">
              Вы подали заявку на вступление
            </h5>
          </template>
        </router-link>
        <button
          v-if="!board.isMember && !board.isRequestor"
          @click="createJoinRequest"
          class="btn btn-info"
        >
          Отправить запрос на вступление
        </button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "siteBoardCard",

  props: ["board", "isOwnBoard"],

  methods: {
    async createJoinRequest(event) {
      try {
        await this.$store.dispatch("createRequest", { boardId: this.board.id });
        const button = event.target;
        button.classList.remove("btn-info");
        button.classList.add("btn-success");
        button.innerText = "Заявка отправлена!";
      } catch (e) {
        const button = event.target;
        button.classList.remove("btn-info");
        button.classList.add("btn-danger");
        button.innerText = "Ошибка!";
      } finally {
        event.target.disabled = true;
      }
    }
  }
};
</script>

<style scoped>
a {
  text-decoration: none !important;
}
</style>
