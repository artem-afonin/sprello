import Vue from 'vue'
import VueResource from 'vue-resource'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'

import index from '@pages/index.vue'

Vue.use(VueResource)
Vue.use(BootstrapVue)
Vue.use(IconsPlugin)

new Vue({
  render: h => h(index)
}).$mount('#app')