import axios from "axios";
import {apiurl, devMode} from "../../globalDefines";

export default {
  actions: {
    async createTask(context, {boardId, name}) {
      try {
        const response = await axios.post(apiurl.task, null, {
          params: {
            boardId,
            name}
        })
        const newTask = response.data
        context.commit('addTask', newTask)
      } catch (e) {
        if (devMode) console.error(e)
      }
    },
    async patchTask(context, {taskId, name}) {
      try {
        const response = await axios.patch(apiurl.task, null, {
          params: {
            taskId,
            name
          }
        })
        const changedTask = response.data
        context.commit('changeTask', changedTask)
      } catch (e) {
        if (devMode) console.error(e)
      }
    },
    async deleteTask(context, taskId) {
      try {
        const response = await axios.delete(apiurl.task, {
          params: {taskId}
        })
        context.commit('removeTask', taskId)
      } catch (e) {
        if (devMode) console.error(e)
      }
    }
  },

  mutations: {
    clearTasks(state) {
      state.tasks.splice(0, state.tasks.length)
    },
    addTask(state, task) {
      state.tasks.push(task)
    },
    setTasks(state, tasks) {
      state.tasks = tasks
    },
    changeTask(state, task) {
      state.tasks = state.tasks.map(el => {
        return el.id === task.id ? task : el
      })
    },
    removeTask(state, taskId) {
      state.tasks = state.tasks.filter(el => el.id !== taskId)
    }
  },

  state: {
    tasks: []
  },

  getters: {
    getTasks(state) {
      return state.tasks.sort((t1, t2) => t1.id - t2.id)
    }
  }
}