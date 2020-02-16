<template>
  <div class="row no-gutters">
    <!-- СТРАНИЦА ЗАГРУЖАЕТСЯ -->
    <template v-if="loading">
      <div class="col-12 text-center">
        <h2 class="text-black-50 mx-auto my-5">
          Загрузка...
        </h2>
      </div>
    </template>

    <!-- ДОСТУП ЗАПРЕЩЁН -->
    <template v-if="isForbidden">
      <div class="col-12 text-center">
        <h2 class="text-danger mx-auto my-5">
          Доступ к доске запрещён
        </h2>
      </div>
    </template>

    <!-- НЕ СУЩЕСТВУЕТ -->
    <template v-else-if="isNotExist">
      <div class="col-12 text-center">
        <h2 class="text-danger mx-auto my-5">
          Такой доски не существует
        </h2>
      </div>
    </template>

    <!-- ОТОБРАЖАЕМ -->
    <template v-else-if="boardInfo">
      <div class="col-12">
        <div class="row no-gutters">
          <div class="col-9 border">
            <div class="row no-gutters justify-content-around">
              <site-task
                :task="task"
                v-for="task in getTasks"
                :key="task.id"
                class="col-12 col-md-5 mx-2 my-4"
              />
            </div>
          </div>
          <div class="col-3 border">
            <site-board-manage-component />
          </div>
        </div>
      </div>
    </template>

    <!-- ЗАГРУЗКА -->
    <template v-else>
      <div class="col-12 my-5 text-center">
        <div
          class="spinner-border text-warning mx-auto"
          style="width: 3rem; height: 3rem"
        />
      </div>
    </template>
  </div>
</template>

<script>
import { mapGetters, mapMutations } from "vuex";
import siteTask from "../components/board/task/siteTask";
import siteBoardManageComponent from "../components/board/siteBoardManageComponent";

export default {
  name: "boardpage",

  components: {
    siteTask,
    siteBoardManageComponent
  },

  data() {
    return {
      errorCode: "",
      loading: false
    };
  },

  async created() {
    this.clearTasks();
    await this.getBoard();
    this.setTasks(this.boardInfo.tasks);
  },

  methods: {
    ...mapMutations(["clearTasks", "setTasks"]),
    async getBoard() {
      this.errorCode = "";
      this.loading = true;
      try {
        await this.$store.dispatch("fetchBoard", this.$route.params.boardid);
      } catch (e) {
        this.errorCode = e.message;
      } finally {
        this.loading = false;
      }
    }
  },

  computed: {
    ...mapGetters(["boardInfo", "getTasks"]),
    isForbidden() {
      return this.errorCode === "403";
    },
    isNotExist() {
      return this.errorCode === "404";
    }
  }
};
</script>

<style scoped></style>
