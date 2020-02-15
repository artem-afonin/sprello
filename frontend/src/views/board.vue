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
          <div class="row no-gutters">
            <div class="col-2" />
            <div class="col">
              <div class="row justify-content-between">
                <site-board-card
                  class="col-12 col-md-5"
                  v-for="el in getBoards"
                  :key="el.id"
                  :board="el"
                />
              </div>
            </div>
            <div class="col-2" />
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
import SiteBoardCard from "../components/board/siteBoardCard";

export default {
  name: "board",

  components: {
    SiteBoardCard,
    siteBoardSidebar
  },

  created() {
    this.fetchBoards(false);
  },

  methods: mapActions(["fetchBoards"]),

  computed: mapGetters(["getBoards"])
};
</script>

<style scoped></style>
