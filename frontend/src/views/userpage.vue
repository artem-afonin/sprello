<template>
    <div>
        <div class="row no-gutters">
            <template v-if="user">
                <div class="col-4 text-center border border-success text-black-50">
                    <img :src="user.userpic" class="mt-4 w-50 rounded-circle">
                    <h3 class="mt-3">{{ user.name }}</h3>
                </div>
                <div class="col-8 border border-success">
                    Cum orexis cantare, omnes fraticinidaes locus barbatus, gratis glutenes.
                    <!-- TODO добавить больше информации о пользователе -->
                </div>
            </template>
            <template v-else-if="userNotExist">
                <h2 class="mx-auto my-5 text-black-50">Такого пользователя не существует</h2>
            </template>
            <template v-else>
                <h2 class="mx-auto my-5 text-black-50">Идёт загрузка, подождите...</h2>
            </template>
        </div>
    </div>
</template>

<script>
  import axios from 'axios'
  import {apiurl, devMode} from '../globalDefines'

  export default {
    name: 'userpage',

    data() {
      return {
        user: null,
        userNotExist: false
      }
    },

    created() {
      this.fetchUserData()
    },

    methods: {
      fetchUserData() {
        const userid = this.$route.params.userid
        axios.get(`${apiurl}/user/${userid}`).then(response => {
          this.user = response.data
        }).catch(err => {
          if (devMode) console.error(err)
          this.userNotExist = true
        })
      }
    },

    computed: {
      name() {
        return this.user.name
      },
      userpic() {
        return this.user.userpic
      }
    }
  }
</script>

<style scoped>

</style>