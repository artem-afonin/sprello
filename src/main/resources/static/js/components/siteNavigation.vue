<template>
    <nav class="navbar navbar-light">
        <ul class="nav nav-pills nav-fill mr-auto">
            <li v-for="el in buttons" class="nav-item">
                <a v-bind:href="el.href" class="nav-link font-weight-bold">{{ el.name }}</a>
            </li>
        </ul>
    </nav>
</template>

<script>
  import $ from 'jquery';

  export default {
    name: "siteNavigation",
    props: ['buttons'],

    mounted: function () {
      const classes = 'active';
      $('a').hover(function () {
        $(this).addClass(classes);
      }, function () {
        $(this).removeClass(classes);
      });
    }
  }
</script>

<style scoped>
    nav {
        background-color: #e3f2fd;
    }
</style>