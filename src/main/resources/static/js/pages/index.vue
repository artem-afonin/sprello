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
                <site-sidebar v-bind:elements="sidebarElements"/>
                <!--SIDEBAR END-->
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
  import siteHeader from "components/siteHeader.vue";
  import siteNavigation from "components/siteNavigation.vue";
  import siteContent from "components/siteContent.vue";
  import siteSidebar from "components/siteSidebar.vue";
  import siteFooter from "components/siteFooter.vue";

  export default {
    name: "index",

    components: {
      siteHeader,
      siteNavigation,
      siteContent,
      siteSidebar,
      siteFooter
    },

    data: function () {
      return {
        navigationButtons: [
          {name: 'Главная', href: '#'},
          {name: 'Доски', href: '#'},
          {name: 'Группы', href: '#'},
          {name: 'Пользователи', href: '#'},
          {name: 'О Sprello', href: '#'}
        ],
        sidebarElements: [
            "Menu 1",
            "Menu 2",
            "Menu 3",
            "Menu 4",
            "Menu 5"
        ]
      }
    }
  }
</script>

<style scoped>
</style>