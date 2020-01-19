<template>
    <nav class="navbar navbar-light">
        <ul class="nav nav-pills nav-fill mx-auto">
            <li v-for="el in buttons" class="nav-item px-4">
                <a :href="el.href" class="nav-link font-weight-bold">
                    {{ el.name }}
                </a>
            </li>
        </ul>
    </nav>
</template>

<script>
  import $ from 'jquery'

  export default {
    name: 'siteNavigation',
    props: ['buttons'],

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