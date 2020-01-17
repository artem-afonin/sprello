<template>
    <!--    <b-nav pills>-->
    <!--        <b-nav-item v-for="el in buttons" @click="active">-->
    <!--            <a v-if="!user && el.authRequired" href="#"-->
    <!--               title="Сначала необходимо авторизоваться">-->
    <!--                {{ el.name }}-->
    <!--            </a>-->
    <!--            <a v-else :href="el.href">-->
    <!--                {{ el.name }}-->
    <!--            </a>-->
    <!--        </b-nav-item>-->
    <!--    </b-nav>-->
    <nav class="navbar navbar-light">
        <ul class="nav nav-pills nav-fill mx-auto">
            <li v-for="el in buttons" class="nav-item px-4">
                <a v-if="!user && el.authRequired" href="#"
                   class="nav-link font-weight-bold bg-light text-black-50"
                   title="Сначала необходимо авторизоваться">
                    {{ el.name }}
                </a>
                <a v-else :href="el.href" class="nav-link font-weight-bold">
                    {{ el.name }}
                </a>
            </li>
        </ul>
    </nav>
</template>

<script>
  import $ from 'jquery'

  export default {
    name: "siteNavigation",
    props: ['buttons'],

    data() {
      return {
        user: userInfo
      }
    },

    mounted: function () {
      this.addClasses('active')
    },

    methods: {
      addClasses(classes) {
        classes.split(' ').forEach((el) => {
          $('a').hover(function () {
            $(this).addClass(el)
          }, function () {
            $(this).removeClass(el)
          })
        })
      }
    }
  }
</script>

<style scoped>
    nav {
        background-color: #e3f2fd;
        font-size: 14pt;
    }
</style>