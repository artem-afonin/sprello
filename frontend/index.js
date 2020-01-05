import Vue from 'vue';
import VueResource from 'vue-resource';

import index from 'frontend/pages/index.vue';

Vue.use(VueResource);

new Vue({
  render: h => h(index)
}).$mount('#app');