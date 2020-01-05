import Vue from 'vue'
import VueResource from 'vue-resource'

import about from "pages/about.vue"

Vue.use(VueResource)

new Vue({
  render: h => h(about)
}).$mount('#app')