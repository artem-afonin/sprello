import Vue from 'vue';
import index from "pages/index.vue";

const app = new Vue({
  render: h => h(index)
}).$mount('#app');