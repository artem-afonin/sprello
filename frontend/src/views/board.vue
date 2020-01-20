<template>
    <div>
        <div class="row no-gutters">
            <site-board-sidebar class="col-10 my-2 mx-auto"
                                :boards="boards"
                                v-on:updatePosts="this.getBoards"/>
        </div>
        <div class="row no-gutters">
            <div class="col-12">
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
    </div>
</template>

<script>
  import axios from 'axios'
  import {devMode, apiurl} from '../globalDefines'

  import siteBoardSidebar from '../components/siteBoardSidebar'

  export default {
    name: 'board',

    components: {
      siteBoardSidebar
    },

    data() {
      return {
        boards: []
      }
    },

    created() {
      this.getBoards(false)
    },

    methods: {
      getBoards(isTypeOwn) {
        this.boards.splice(0, this.boards.length)
        const typeStr = isTypeOwn ? 'own' : 'all'
        console.log(typeStr)
        axios.get(`${apiurl}/board`, {
          params: {type: typeStr}
        }).then(r => r.data).then(data => {
          if (devMode) console.log('Полученные борды:', data)
          data.forEach(el => {
            this.boards.push(el)
          })
        }).catch(err => {
          if (devMode) console.error('При получении бордов ошибка:', err)
        })
      }
    }
  }
</script>

<style scoped>
</style>