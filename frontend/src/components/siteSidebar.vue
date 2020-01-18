<template>
    <aside>
        <ul class="list-group">
            <li v-for="el in elements" class="list-group-item">{{ el }}</li>
        </ul>
    </aside>
</template>

<script>
  export default {
    name: "siteSidebar",

    props: ['elements']
  }
</script>

<style scoped>

</style>