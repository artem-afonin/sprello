import Vue from 'vue'

import about from "./views/about.vue"

new Vue({
  render: h => h(about)
}).$mount('#app')