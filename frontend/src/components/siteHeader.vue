<template>
  <header>
    <div class="row align-items-center no-gutters">
      <div class="col">
        <h4 class="display-4 text-center text-black-50">
          <slot />
        </h4>
      </div>

      <div class="col-3 bg-light mx-1 p-1 rounded">
        <div v-if="myUserInfo" class="card">
          <div class="row no-gutters align-items-center">
            <div class="d-none d-md-block col-md-3 pl-2">
              <router-link
                :to="{
                  name: 'userpage',
                  params: {
                    userid: myUserInfo.id
                  }
                }"
              >
                <img
                  class="card-img rounded-circle"
                  :src="myUserInfo.userpic"
                />
              </router-link>
            </div>
            <div class="col-12 col-md-9">
              <div class="card-body p-2 text-center">
                <router-link
                  :to="{
                    name: 'userpage',
                    params: {
                      userid: myUserInfo.id
                    }
                  }"
                >
                  <h6 class="card-text">{{ myUserInfo.name }}</h6>
                </router-link>
                <button class="btn btn-outline-primary py-0 mt-1 mb-0 mx-auto">
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
import { mapActions, mapGetters } from "vuex";

export default {
  name: "siteHeader",

  created() {
    this.fetchMyUser();
  },

  methods: mapActions(["fetchMyUser"]),

  computed: mapGetters(["myUserInfo"])
};
</script>

<style scoped></style>
