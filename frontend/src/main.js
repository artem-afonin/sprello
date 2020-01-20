import Vue from 'vue'

import router from './router/router'
import App from './App.vue'

new Vue({
  data: {user: null},
  router,
  render: h => h(App)
}).$mount('#app')