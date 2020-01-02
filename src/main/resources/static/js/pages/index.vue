<template>
    <div class="wrapper bg-light text-black-50">
        <!--CONTAINER START-->
        <div class="container border p-0 bg-light">
            <!--HEADER START-->
            <site-header v-bind:class="'border-bottom bg-primary'">Sprello</site-header>
            <!--HEADER END-->

            <!--NAVIGATION START-->
            <site-navigation v-bind:buttons="navigationButtons"/>
            <!--NAVIGATION END-->

            <!--CENTRAL BLOCK START-->
            <div class="row no-gutters">
                <!--CONTENT START-->
                <site-content>
                    Amors sunt fortiss de regius clabulare.
                    Sunt gemnaes experientia regius, domesticus guttuses.
                    A falsis, fortis salvus tabes.
                    Est barbatus victrix, cesaris.
                    Resistentia velums, tanquam festus guttus.
                </site-content>
                <!--CONTENT END-->

                <!--SIDEBAR START-->
                <aside class="col-3 border-left">
                    <ul class="list-group">
                        <li class="list-group-item">1 menu</li>
                        <li class="list-group-item">2 menu</li>
                        <li class="list-group-item">3 menu</li>
                        <li class="list-group-item">4 menu</li>
                    </ul>
                </aside>
                <!--SIDEBAR END-->
            </div>
            <!--CENTRAL BLOCK END-->

            <!--FOOTER START-->
            <footer class="row no-gutters bg-primary border-top">
                <div class="col">
                    <h3>Footer</h3>
                </div>
            </footer>
            <!--FOOTER END-->
        </div>
        <!--CONTAINER END-->
    </div>
</template>

<script>
  import siteHeader from "components/siteHeader.vue";
  import siteNavigation from "components/siteNavigation.vue";
  import siteContent from "components/siteContent.vue";

  export default {
    name: "index",

    components: {
      siteHeader,
      siteNavigation,
      siteContent
    },

    data: function () {
      return {
        navigationButtons: [
          {name: 'Главная', href: '#'},
          {name: 'Доски', href: '#'},
          {name: 'Группы', href: '#'},
          {name: 'Пользователи', href: '#'},
          {name: 'О Sprello', href: '#'}
        ]
      }
    }
  }
</script>

<style scoped>
</style>