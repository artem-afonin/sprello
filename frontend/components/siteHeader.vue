<template>
    <header>
        <div class="row align-items-center no-gutters">
            <div class="col">
                <h4 class="display-4 text-center text-black-50">
                    <slot/>
                </h4>
            </div>

            <div v-if="user" class="col-3 bg-light mx-1 p-1 rounded border-bottom">
                <div class="row">
                    <div class="col">
                        <img class="rounded-circle" width="20%" :src="pic">
                        <strong class="ml-2">{{ name }}</strong>
                    </div>
                    <div class="w-100"></div>
                    <div class="col">
                        <button class="btn btn-block py-0 my-0"><a href="/logout" style="font-size: 0.8rem">Выйти</a></button>
                    </div>
                </div>
            </div>
        </div>
    </header>
</template>

<script>
  export default {
    name: "siteHeader",

    data() {
      return {
        user: userInfo
      }
    },

    computed: {
      name() {
        return this.user.name
      },
      pic() {
        return this.user.userpic
      }
    }
  }
</script>

<style scoped>
</style>