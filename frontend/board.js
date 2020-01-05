import Vue from 'vue'
import VueResource from 'vue-resource'

import board from "pages/board.vue"

Vue.use(VueResource)

new Vue({
  render: h => h(board)
}).$mount('#app')