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
          <p class="card-text">
            Возможно здесь в будущем будет описание. <br />
            Calcarias accelerare in infernum! A falsis, particula germanus
            ausus.
          </p>
        </router-link>
        <button class="btn btn-info" @click="createJoinRequest">
          Отправить запрос на вступление
        </button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "siteBoardCard",

  props: ["board"],

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
