<template>
    <div class="wrapper bg-light text-black-50">
        <!--CONTAINER START-->
        <div class="container border p-0 bg-light">
            <!--HEADER START-->
            <site-header v-bind:class="'border-bottom bg-primary'">Sprello</site-header>
            <!--HEADER END-->

            <!--NAVIGATION START-->
            <site-navigation v-bind:buttons="navButtons"/>
            <!--NAVIGATION END-->

            <!--CENTRAL BLOCK START-->
            <div class="row justify-content-around">
                <template v-for="el in boards">
                    <div class="col-10 col-md-5 py-1 my-1  border border-secondary text-center">
                        <h5>{{ el.id }} -> {{ el.name }}</h5>
                        <hr/>
                        <p>
                            Возможно здесь в будущем будет описание. <br/>
                            Calcarias accelerare in infernum! A falsis, particula germanus ausus.
                        </p>
                        <hr/>
                        <button class="btn btn-info">Подписаться</button>
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
    .border {
        border-width: 2px !important;
    }
</style>