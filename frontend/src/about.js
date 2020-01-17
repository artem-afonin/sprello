import Vue from 'vue'
import VueResource from 'vue-resource'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'

import about from "@pages/about.vue"

Vue.use(VueResource)
Vue.use(BootstrapVue)
Vue.use(IconsPlugin)

new Vue({
  render: h => h(about)
}).$mount('#app')