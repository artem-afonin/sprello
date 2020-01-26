<template>
    <div class="row no-gutters">
        <template v-if="isForbidden">
            <div class="col-12 text-center">
                <h2 class="text-danger mx-auto my-5">
                    Доступ к доске запрещён
                </h2>
            </div>
        </template>
        <template v-else-if="isNotExist">
            <div class="col-12 text-center">
                <h2 class="text-danger mx-auto my-5">
                    Такой доски не существует
                </h2>
            </div>
        </template>
        <template v-else-if="boardInfo">
        </template>
        <template v-else>
            <div class="col-12 my-5 text-center">
                <div class="spinner-border text-warning mx-auto"
                     style="width: 3rem; height: 3rem"
                />
            </div>
        </template>
    </div>
</template>

<script>
  import {mapGetters} from 'vuex'

  export default {
    name: 'boardpage',

    data() {
      return {
        errorCode: ''
      }
    },

    async created() {
      await this.getBoard()
    },

    methods: {
      async getBoard() {
        this.errorCode = ''
        try {
          await this.$store.dispatch('fetchBoard', this.$route.params.boardid)
        } catch (e) {
          this.errorCode = e.message
        }
      }
    },

    computed: {
      ...mapGetters(['boardInfo']),
      isForbidden() {
        return this.errorCode === '403'
      },
      isNotExist() {
        return this.errorCode === '404'
      }
    }
  }
</script>

<style scoped>

</style>