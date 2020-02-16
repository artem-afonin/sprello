import axios from "axios";
import { apiurl } from "../../globalDefines";

export default {
  actions: {
    async fetchMyUser(context) {
      try {
        const response = await axios.get(apiurl.user);
        const user = response.data;
        context.commit("updateMyUser", user);
      } catch (e) {}
    },
    async fetchUser(context, id) {
      const response = await axios.get(apiurl.userId(id));
      const user = response.data;
      context.commit("updateOtherUser", user);
    }
  },

  mutations: {
    updateMyUser(state, user) {
      state.myUser = user;
    },

    updateOtherUser(state, user) {
      state.otherUser = user;
    }
  },

  state: {
    myUser: {},
    otherUser: {}
  },

  getters: {
    myUserInfo(state) {
      return Object.keys(state.myUser).length !== 0 ? state.myUser : null;
    },
    userInfo(state) {
      return Object.keys(state.otherUser).length !== 0 ? state.otherUser : null;
    }
  }
};
