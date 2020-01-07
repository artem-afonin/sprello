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
            <div class="row text-center my-4 justify-content-center no-gutters">
                <h3 class="col-12">Главная страница, добавить материал</h3>
                <div class="col-8 alert alert-warning text-black-50">
                    Sunt capioes examinare barbatus, ferox castores. Secula, solitudo, et assimilatio. Hercle,
                    nuptia raptus!, rumor! Tabes, particula, et demolitione. Pol, a bene heuretes.
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
  import {navButtons} from "globalDefines"

  import siteHeader from "components/siteHeader.vue"
  import siteNavigation from "components/siteNavigation.vue"
  import siteMessages from "components/siteMessages.vue"
  import siteSidebar from "components/siteSidebar.vue"
  import siteFooter from "components/siteFooter.vue"
  import messageForm from "components/messageForm.vue"

  export default {
    name: "index",

    components: {
      siteHeader,
      siteNavigation,
      siteMessages,
      siteSidebar,
      siteFooter,
      messageForm
    },

    data() {
      return {
        navButtons
      }
    }
  }
</script>

<style scoped>
</style>