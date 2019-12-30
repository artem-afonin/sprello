import Vue from 'vue';
import index from 'pages/index.vue';
import VueResource from 'vue-resource';

Vue.use(VueResource);

const app = new Vue({
  render: h => h(index)
}).$mount('#app');