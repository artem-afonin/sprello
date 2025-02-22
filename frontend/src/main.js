import Vue from "vue";
import store from "./store";
import router from "./router/router";
import App from "./App.vue";

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
