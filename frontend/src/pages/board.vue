<template>
    <div class="wrapper py-1 bg-light text-black-50">
        <!--CONTAINER START-->
        <div class="container border p-0 bg-light">
            <!--HEADER START-->
            <site-header class="bg-primary">Sprello</site-header>
            <!--HEADER END-->

            <!--NAVIGATION START-->
            <site-navigation :buttons="navButtons"/>
            <!--NAVIGATION END-->

            <!--CENTRAL BLOCK START-->

            <div class="row no-gutters">
                <site-board-sidebar class="col-12 col-md-4 mb-2 mx-auto"/>
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
            <!--CENTRAL BLOCK END-->

            <!--FOOTER START-->
            <site-footer>&copy; Artem and Vasiliy</site-footer>
            <!--FOOTER END-->
        </div>
        <!--CONTAINER END-->
    </div>
</template>

<script>
  import "bootswatch/dist/minty/bootstrap.min.css"
  import axios from 'axios'
  import {navButtons, devMode} from "@/globalDefines"

  import siteHeader from "@components/siteHeader.vue"
  import siteNavigation from "@components/siteNavigation.vue"
  import siteBoardSidebar from "@components/siteBoardSidebar.vue"
  import siteFooter from "@components/siteFooter.vue"

  export default {
    name: "board",

    components: {
      siteHeader,
      siteNavigation,
      siteBoardSidebar,
      siteFooter
    },

    data() {
      return {
        navButtons,
        boards: []
      }
    },

    created() {
      this.getBoards()
    },

    methods: {
      //TODO передавать в запросе количество досок для отображения (12?)
      getBoards() {
        axios.get('api/v1/board').then(r => r.data).then(data => {
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