import Vue from 'vue';
import VueResource from 'vue-resource';

import index from 'pages/index.vue';

Vue.use(VueResource);

new Vue({
  render: h => h(index)
}).$mount('#app');