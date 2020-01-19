<template>
    <header>
        <div class="row align-items-center no-gutters">
            <div class="col">
                <h4 class="display-4 text-center text-black-50">
                    <slot/>
                </h4>
            </div>

            <div class="col-3 bg-light mx-1 p-1 rounded">
                <div v-if="$root.user" class="card">
                    <div class="row no-gutters align-items-center">
                        <div class="d-none d-md-block col-md-3 pl-2">
                            <img class="card-img rounded-circle" :src="pic">
                        </div>
                        <div class="col-12 col-md-9">
                            <div class="card-body p-2 text-center">
                                <h6 class="card-text">{{ name }}</h6>
                                <button class="btn btn-outline-primary py-0 my-0 mx-auto">
                                    <a href="/logout" class="text-black-50">Выйти</a>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div v-else class="card">
                    <div class="card-body p-2 text-center">
                        <button class="btn btn-outline-primary py-0 my-0 mx-auto">
                            <a href="/login" class="text-black-50">Авторизоваться</a>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </header>
</template>

<script>
  import axios from 'axios'
  import {devMode, apiurl} from '../globalDefines'

  export default {
    name: 'siteHeader',

    created() {
      this.fetchUser()
    },

    methods: {
      fetchUser() {
        axios.get(`${apiurl}/user`).then(response => {
          this.$root.user = response.data
        }).catch(err => {
          if (devMode) console.error(err)
        })
      }
    },

    computed: {
      name() {
        return this.$root.user.name
      },
      pic() {
        return this.$root.user.userpic
      }
    },
  }
</script>

<style scoped>
</style>