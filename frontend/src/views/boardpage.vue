<template>
    <div class="row no-gutters">
        <template v-if="board">
        </template>
        <template v-else-if="isForbidden">
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
  import axios from 'axios'
  import {apiurl, devMode} from '../globalDefines'

  export default {
    name: 'boardpage',

    data() {
      return {
        board: null,
        isForbidden: false,
        isNotExist: false
      }
    },

    created() {
      this.getBoard()
    },

    methods: {
      getBoard() {
        const boardId = this.$route.params.boardid
        axios.get(`${apiurl}/board/${boardId}`, {
          params: {
            boardId
          }
        }).then(response => {
          if (response.statusText.toLowerCase() === 'ok') {
            this.board = response.data
            if (devMode) console.log('Информация о доске: ', this.board)
          }
        }).catch(err => {
          if (devMode) console.log(err.response)
          if (err.response.status === 403)
            this.isForbidden = true
          else if (err.response.status === 404)
            this.isNotExist = true
        })
      }
    }
  }
</script>

<style scoped>

</style>