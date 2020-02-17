<template>
  <div v-if="requestors" class="row p-3">
    <div class="col-4" />
    <div class="col-4">
      <div class="row">
        <site-board-requestors
          v-for="r in requestors"
          :key="r.id"
          :requestor="r"
          class="col-12 mb-3 p-2 border border-success"
        />
      </div>
    </div>
    <div class="col-4" />
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import SiteBoardRequestors from "../components/board/siteBoardRequestors";

export default {
  name: "requestors",

  components: { SiteBoardRequestors },

  data() {
    return {
      requestors: []
    };
  },

  async created() {
    await this.$store.dispatch("fetchBoard", this.$route.params.boardid);
    this.requestors = this.boardInfo.requestors;
  },

  computed: {
    ...mapGetters(["boardInfo"])
  }
};
</script>

<style scoped>
.border {
  border-width: 2px !important;
}
</style>
