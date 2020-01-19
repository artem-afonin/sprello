<template>
    <div class="row no-gutters">
        <site-board-sidebar class="col-12 col-md-4 mb-2 mx-auto" :boards="boards"/>
        <div class="col-12 col-md-8">
            <div class="row justify-content-around">
                <template v-for="el in boards">
                    <div class="col-9 col-md-5">
                        <div class="card my-1">
                            <div class="card-body text-center">
                                <h5 class="card-title text-dark">{{ el.id }} -> <b>{{ el.name }}</b></h5>
                                <p class="card-text">
                                    Возможно здесь в будущем будет описание. <br/>
                                    Calcarias accelerare in infernum! A falsis, particula germanus ausus.
                                </p>
                                <button class="btn btn-info">Подписаться</button>
                            </div>
                        </div>
                    </div>
                </template>
            </div>
        </div>
    </div>
</template>

<script>
  import axios from 'axios'
  import {devMode, apiurl} from "../globalDefines"

  import siteBoardSidebar from "../components/siteBoardSidebar.vue"

  export default {
    name: "board",

    components: {
      siteBoardSidebar
    },

    data() {
      return {
        boards: []
      }
    },

    created() {
      this.getBoards()
    },

    methods: {
      //TODO передавать в запросе количество досок для отображения (12?)
      getBoards() {
        axios.get(`${apiurl}/board`).then(r => r.data).then(data => {
          if (devMode) console.log(data)
          data.forEach(el => {
            this.boards.push(el)
          })
        }).catch(err => {
          if (devMode) console.error(err)
        })
      }
    }
  }
</script>

<style scoped>
</style>