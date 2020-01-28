<template>
    <div class="wrapper py-1 bg-light text-black-50">
        <!--CONTAINER START-->
        <div class="container-fluid border py-0 px-4 bg-light">
            <!--HEADER START-->
            <site-header class="bg-primary">Sprello</site-header>
            <!--HEADER END-->

            <!--NAVIGATION START-->
            <site-navigation :buttons="navButtons"/>
            <!--NAVIGATION END-->

            <!--CENTRAL BLOCK START-->
            <router-view/>
            <!--CENTRAL BLOCK END-->

            <!--FOOTER START-->
            <site-footer>&copy; Artem and Vasiliy</site-footer>
            <!--FOOTER END-->
        </div>
        <!--CONTAINER END-->
    </div>
</template>

<script>
  import 'bootswatch/dist/minty/bootstrap.min.css'
  import siteHeader from './components/siteHeader'
  import siteNavigation from './components/siteNavigation'
  import siteFooter from './components/siteFooter'
  import {navButtons} from './globalDefines'

  export default {
    name: 'App',

    components: {
      siteHeader,
      siteNavigation,
      siteFooter
    },

    data() {
      return {
        navButtons
      }
    }
  }
</script>

<style>
    .material-icons.md-18 {
        font-size: 18px;
    }
    .material-icons.md-24 {
        font-size: 24px;
    }
    .material-icons.md-36 {
        font-size: 36px;
    }
    .material-icons.md-48 {
        font-size: 48px;
    }
</style>