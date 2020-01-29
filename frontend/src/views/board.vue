<template>
  <div>
    <template v-if="getBoards !== null">
      <div class="row no-gutters">
        <site-board-sidebar
          class="col-10 my-2 mx-auto"
          :boards="getBoards"
          v-on:updatePosts="fetchBoards"
        />
      </div>
      <div class="row no-gutters">
        <div class="col-12">
          <div class="row justify-content-around">
            <div v-for="el in getBoards" :key="el.id">
              <div class="col-9 col-md-5">
                <div class="card my-1">
                  <router-link
                    :to="{
                      name: 'boardpage',
                      params: {
                        boardid: el.id
                      }
                    }"
                  >
                    <div class="card-body text-center">
                      <h5 class="card-title text-dark">
                        {{ el.id }} ->
                        <b>
                          {{ el.name }}
                        </b>
                      </h5>
                      <h6
                        v-if="el.isPrivate"
                        class="card-subtitle text-muted mb-1"
                      >
                        <i class="material-icons md-24">
                          lock
                        </i>
                        приватная
                      </h6>
                      <p class="card-text">
                        Возможно здесь в будущем будет описание. <br />
                        Calcarias accelerare in infernum! A falsis, particula
                        germanus ausus.
                      </p>
                      <button class="btn btn-info">Подписаться</button>
                    </div>
                  </router-link>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </template>
    <template v-else>
      <div class="mx-auto my-5">
        <h2 class="text-danger text-center">Не удалось получить доски</h2>
        <h5 class="text-center text-black-50">
          Возможно, Ваш сеанс истёк, попробуйте
          <a href="/login">
            заново войти в аккаунт
          </a>
        </h5>
      </div>
    </template>
  </div>
</template>

<script>
import siteBoardSidebar from "../components/board/siteBoardSidebar";
import { mapActions, mapGetters } from "vuex";

export default {
  name: "board",

  components: {
    siteBoardSidebar
  },

  created() {
    this.fetchBoards(false);
  },

  methods: mapActions(["fetchBoards"]),

  computed: mapGetters(["getBoards"])
};
</script>

<style scoped>
a {
  text-decoration: none !important;
}
</style>
