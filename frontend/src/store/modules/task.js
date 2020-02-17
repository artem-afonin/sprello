import axios from "axios";
import { apiurl } from "../../globalDefines";

export default {
  actions: {
    async createTask(context, { boardId, name }) {
      try {
        const response = await axios.post(apiurl.task, null, {
          params: {
            boardId: boardId,
            name: name
          }
        });
        const newTask = response.data;
        context.commit("addTask", newTask);
      } catch (e) {}
    },
    async patchTask(context, { taskId, name }) {
      try {
        const response = await axios.patch(apiurl.task, null, {
          params: {
            taskId: taskId,
            name: name
          }
        });
        const changedTask = response.data;
        context.commit("changeTask", changedTask);
      } catch (e) {}
    },
    async deleteTask(context, taskId) {
      try {
        await axios.delete(apiurl.task, {
          params: { taskId: taskId }
        });
        context.commit("removeTask", taskId);
      } catch (e) {}
    },
    async createElement(context, { taskId, text, color }) {
      try {
        const response = await axios.post(apiurl.taskElement, null, {
          params: {
            taskId: taskId,
            text: text,
            color: color
          }
        });
        const newElement = response.data;
        context.commit("addElement", newElement);
      } catch (e) {}
    },
    async patchElement(context, { taskElementId, text, color }) {
      try {
        const response = await axios.patch(apiurl.taskElement, null, {
          params: {
            taskElementId: taskElementId,
            text: text,
            color: color
          }
        });
        const changedElement = response.data;
        context.commit("changeElement", changedElement);
      } catch (e) {}
    },
    async deleteElement(context, taskElementId) {
      try {
        await axios.delete(apiurl.taskElement, {
          params: { taskElementId: taskElementId }
        });
        context.commit("removeElement", taskElementId);
      } catch (e) {}
    }
  },

  mutations: {
    clearTasks(state) {
      state.tasks.splice(0, state.tasks.length);
    },
    addTask(state, task) {
      state.tasks.push(task);
    },
    setTasks(state, tasks) {
      state.tasks = tasks;
    },
    changeTask(state, task) {
      state.tasks = state.tasks.map(el => {
        return el.id === task.id ? task : el;
      });
    },
    removeTask(state, taskId) {
      state.tasks = state.tasks.filter(el => el.id !== taskId);
    },
    addElement(state, element) {
      const parent = element.parent;
      state.tasks.forEach(task => {
        if (task.id === parent.id) task.elements.push(element);
      });
    },
    changeElement(state, element) {
      state.tasks.forEach(task => {
        const index = task.elements.findIndex(el => el.id === element.id);
        if (index > -1) task.elements.splice(index, 1, element);
      });
    },
    removeElement(state, elementId) {
      state.tasks.forEach(task => {
        task.elements = task.elements.filter(el => el.id !== elementId);
      });
    }
  },

  state: {
    tasks: []
  },

  getters: {
    getTasks(state) {
      return state.tasks.sort((t1, t2) => t1.id - t2.id);
    }
  }
};
