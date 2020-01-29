<template>
  <div class="text-center">
    <h1 class="my-5 text-danger">404 NOT FOUND</h1>
    <h2 class="my-5 text-black-50">
      Запрашиваемая вами страница
      <b class="text-info">{{ pageUrl }}</b>
      не существует
      <i class="material-icons md-48">
        mood_bad
      </i>
    </h2>
  </div>
</template>

<script>
export default {
  name: "error404",

  computed: {
    pageUrl() {
      return this.$route.fullPath;
    }
  }
};
</script>

<style scoped></style>
