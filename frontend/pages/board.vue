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
            <div class="row justify-content-around no-gutters">
                <template v-for="el in boards">
                    <div class="col-10 col-md-5">
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
  import {navButtons, devMode} from "globalDefines"

  import siteHeader from "components/siteHeader.vue"
  import siteNavigation from "components/siteNavigation.vue"
  import siteFooter from "components/siteFooter.vue"

  export default {
    name: "board",

    components: {
      siteHeader,
      siteNavigation,
      siteFooter
    },

    data() {
      return {
        navButtons,
        boards: [ //FIXME убрать статичное заполнение сразу как появится API
          {id: 1, name: "First board"},
          {id: 2, name: "Second board"},
          {id: 3, name: "Third board"},
          {id: 4, name: "Fourth board"},
          {id: 5, name: "Fifth board"}
        ]
      }
    },

    created() {
      this.getBoards()
    },

    methods: {
      //TODO передавать в запросе количество досок для отображения (12?)
      getBoards() {
        this.$http.get('/api/board/').then(
            response => {
              response.body.forEach(this.boards.push)
            }
        ).catch(error => {
          if (devMode) console.log(error)
        })
      }
    }
  }
</script>

<style scoped>
</style>