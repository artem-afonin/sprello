<template>
    <header>
        <div class="row no-gutters">
            <div class="col">
                <h1 class="display-3 text-center text-black-50">
                    <slot/>
                </h1>
            </div>
        </div>
    </header>
</template>

<script>
  export default {
    name: "siteHeader"
  }
</script>

<style scoped>
</style>