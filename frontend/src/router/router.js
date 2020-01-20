import Vue from 'vue'
import VueRouter from 'vue-router'
import index from '../views/index'
import about from '../views/about'
import board from '../views/board'
import error404 from '../views/error404'

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
    path: '/login',
    redirect: '/login'
  },
  {
    path: '/logout',
    redirect: '/logout'
  },
  {
    path: '/board',
    component: board
  },
  {
    path: '*',
    component: error404
  }
]

export default new VueRouter({
  mode: 'history',
  routes
})