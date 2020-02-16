<template>
  <div v-if="users" class="row p-3">
    <div class="col-2" />
    <div class="col-8">
      <div class="row justify-content-around">
        <site-board-user-component
          v-for="usr of users"
          :key="usr.id"
          :user="usr"
          class="col-4"
        />
      </div>
    </div>
    <div class="col-2" />
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import SiteBoardUserComponent from "../components/board/siteBoardUserComponent";

export default {
  name: "boardusers",

  components: { SiteBoardUserComponent },

  data() {
    return {
      users: []
    };
  },

  async created() {
    await this.$store.dispatch("fetchBoard", this.$route.params.boardid);
    this.users = this.boardInfo.users;
  },

  computed: {
    ...mapGetters(["boardInfo"])
  }
};
</script>

<style scoped>
.border {
  border-width: 2px;
}
</style>
