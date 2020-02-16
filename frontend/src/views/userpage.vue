<template>
  <div>
    <div class="row no-gutters p-3">
      <template v-if="userNotExist">
        <h2 class="mx-auto my-5 text-danger">
          Такого пользователя не существует
        </h2>
      </template>
      <template v-else-if="userLoading">
        <h2 class="mx-auto my-5 text-black-50">Идёт загрузка, подождите...</h2>
      </template>
      <template v-else>
        <div class="col-4 text-center text-black-50">
          <img :src="userInfo.userpic" class="w-50 rounded-circle" />
          <h3 class="mt-3">{{ userInfo.name }}</h3>
        </div>
        <div class="col-8 p-4">
          <ul class="h5 list-group">
            <li class="list-group-item">
              <template v-if="userBoardsCount !== 0">
                Cостоит в {{ userBoardsCount }} досках
              </template>
              <template v-else>
                Ещё не присоединился ни к одной доске
              </template>
            </li>
            <li class="list-group-item">
              Последний раз был в сети {{ userLastVisit }}
            </li>
          </ul>
        </div>
      </template>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import moment from "moment";

export default {
  name: "userpage",

  data() {
    return {
      userLoading: false,
      userNotExist: false
    };
  },

  created() {
    moment.locale("ru");
    this.fetchUser(this.$route.params.userid);
  },

  methods: {
    async fetchUser() {
      try {
        this.userLoading = true;
        await this.$store.dispatch("fetchUser", this.$route.params.userid);
      } catch (e) {
        this.userNotExist = true;
      } finally {
        this.userLoading = false;
      }
    }
  },

  computed: {
    ...mapGetters(["userInfo"]),
    userBoardsCount() {
      return this.userInfo.boards.length;
    },
    userLastVisit() {
      const lastDate = new Date(this.userInfo.lastVisit);
      const lastVisit = moment(lastDate);
      const time = lastVisit.fromNow();
      return time;
    }
  }
};
</script>

<style scoped></style>
