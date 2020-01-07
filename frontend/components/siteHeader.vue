<template>
    <header>
        <div class="row align-items-center no-gutters">
            <div class="col">
                <h4 class="display-4 text-center text-black-50">
                    <slot/>
                </h4>
            </div>

            <div class="col-3 bg-light mx-1 p-1 rounded border-bottom">

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