<template>
    <div class="wrapper py-1 bg-light text-black-50">
        <!--CONTAINER START-->
        <div class="container border p-0 bg-light">
            <!--HEADER START-->
            <site-header v-bind:class="'border-bottom bg-primary'">Sprello</site-header>
            <!--HEADER END-->

            <!--NAVIGATION START-->
            <site-navigation v-bind:buttons="navButtons"/>
            <!--NAVIGATION END-->

            <!--CENTRAL BLOCK START-->
            <div class="row no-gutters">
                <div class="col text-justify">
                    <h4 class="text-center text-danger">
                        Сюда добавить информацию об проекте Sprello
                    </h4>
                    <p>
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent sit amet sem porta erat porta
                        tempus. Mauris fermentum ex non urna condimentum tincidunt. Curabitur sollicitudin malesuada
                        semper. Suspendisse sed posuere massa. Integer laoreet purus in tortor scelerisque, ac accumsan
                        massa ultrices. Donec eget malesuada urna, sit amet sagittis tortor. Vestibulum non odio felis.
                        Sed vitae turpis nisi. Nulla id dictum tellus. Duis varius, dolor ut imperdiet mollis, erat erat
                        iaculis elit, non elementum felis nibh ultricies velit. Praesent consectetur mi vitae nisi
                        faucibus viverra. Donec vel auctor orci, non lacinia nulla. Suspendisse vel arcu dictum, sodales
                        turpis sit amet, tempor dui. Quisque in lectus id lacus varius ultrices sed vel neque.
                        Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.
                        Fusce et arcu eget tellus efficitur maximus.
                    </p>
                    <p>
                        Nullam ultrices tristique tortor sed eleifend. Maecenas convallis mi id ex dapibus sodales.
                        Mauris mi magna, pulvinar at tincidunt sed, vestibulum sed tortor. Sed blandit tempor lectus
                        suscipit mattis. Aenean feugiat feugiat neque non tristique. Ut scelerisque mattis elit. Donec
                        et nisi egestas mauris euismod pellentesque. Ut eleifend pretium viverra. Quisque mattis sapien
                        at laoreet luctus. Cras leo augue, eleifend sed iaculis efficitur, pellentesque vulputate
                        turpis. Proin rhoncus consequat sem, at facilisis purus ornare eu.
                    </p>
                    <p>
                        Curabitur bibendum sapien dui, sit amet eleifend eros ultrices non. Nullam aliquet erat et dolor
                        accumsan, sed scelerisque ante posuere. Nunc ac dolor sit amet diam pretium sollicitudin.
                        Vivamus sit amet velit vel dui pulvinar volutpat. Proin cursus ultrices velit. Duis imperdiet a
                        nulla in hendrerit. In elementum risus lorem, aliquet aliquam enim dapibus ac. Suspendisse
                        porttitor risus et ipsum tincidunt condimentum. Vivamus tempus maximus semper. Sed non nulla
                        dui. Duis ac justo mattis, facilisis massa sed, pharetra lectus. Maecenas nec massa lobortis,
                        malesuada purus et, elementum orci. Fusce dignissim mauris in mi lacinia, sed fringilla lorem
                        mollis. Suspendisse potenti. Phasellus a vehicula velit, quis pellentesque dui.
                    </p>
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
  import siteFooter from "components/siteFooter.vue"

  export default {
    name: "about",

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

<style scoped>
</style>