import Vue from 'vue'
import Vuex from 'vuex'
import user from './modules/user'
import board from './modules/board'
import task from './modules/task'
import taskelement from './modules/taskelement'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    user,
    board,
    task,
    taskelement
  }
})
