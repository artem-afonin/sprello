<template>
  <div>
    <div class="row no-gutters">
      <template v-if="userNotExist">
        <h2 class="mx-auto my-5 text-black-50">
          Такого пользователя не существует
        </h2>
      </template>
      <template v-else-if="userLoading">
        <h2 class="mx-auto my-5 text-black-50">Идёт загрузка, подождите...</h2>
      </template>
      <template v-else>
        <div class="col-4 text-center border border-success text-black-50">
          <img :src="userInfo.userpic" class="mt-4 w-50 rounded-circle" />
          <h3 class="mt-3">{{ userInfo.name }}</h3>
        </div>
        <div class="col-8 border border-success">
          Cum orexis cantare, omnes fraticinidaes locus barbatus, gratis
          glutenes.
          <!-- TODO добавить больше информации о пользователе -->
        </div>
      </template>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

export default {
  name: "userpage",

  created() {
    this.fetchUser(this.$route.params.userid);
  },

  methods: mapActions(["fetchUser"]),

  computed: {
    ...mapGetters(["userInfo"]),
    userNotExist() {
      return this.userInfo === null;
    },
    userLoading() {
      return Object.keys(this.userInfo).length === 0;
    }
  }
};
</script>

<style scoped></style>
