import Vue from 'vue'
import VueRouter from 'vue-router'
import index from '../views/index.vue'
import about from '../views/about.vue'
import board from '../views/board.vue'
import error404 from '../views/error404.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: index
  },
  {
    path: '/about',
    component: about
  },
  {
    path: '/board',
    component: board
  },
  {
    path: '/404',
    component: error404
  },
  {
    path: '*',
    redirect: '/404'
  }
]

export default new VueRouter({
  mode: 'history',
  routes
})