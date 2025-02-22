import axios from "axios";
import { apiurl } from "../../globalDefines";

export default {
  actions: {
    async fetchBoards(context, own = false) {
      try {
        context.commit("clearBoards");
        context.commit("setIsOwn", own);
        const response = await axios.get(apiurl.board, {
          params: { own }
        });
        const boards = response.data;
        boards.forEach(board => context.commit("pushBoard", board));
      } catch (e) {
        context.commit("setBoards", null);
      }
    },
    async fetchBoard(context, id) {
      try {
        const response = await axios.get(apiurl.boardId(id), {
          params: { boardId: id }
        });
        const board = response.data;
        context.commit("setCurrentBoard", board);
      } catch (e) {
        throw new Error(e.response.status.toString());
      }
    },
    async postBoard(context, { name }) {
      try {
        const response = await axios.post(apiurl.board, null, {
          params: { name: name }
        });
        const board = response.data;
        context.commit("pushBoard", board);
      } catch (e) {}
    },
    async createRequest(context, { boardId }) {
      await axios.post(apiurl.requestCreate, null, {
        params: { boardId: boardId }
      });
    },
    async acceptRequest(context, { boardId, requestorId }) {
      try {
        const response = await axios.post(apiurl.requestAccept, null, {
          params: {
            boardId: boardId,
            requestorId: requestorId
          }
        });
        const board = response.data;
        context.commit("setCurrentBoard", board);
      } catch (e) {}
    }
  },

  mutations: {
    clearBoards(state) {
      state.boards.splice(0, state.boards.length);
    },
    setBoards(state, boards) {
      state.clearBoards();
      state.boards = boards;
    },
    pushBoard(state, board) {
      state.boards.push(board);
    },
    setIsOwn(state, isOwn) {
      state.isOwn = isOwn;
    },
    setCurrentBoard(state, board) {
      state.currentBoard = board;
    }
  },

  state: {
    boards: [],
    isOwn: false,
    currentBoard: {}
  },

  getters: {
    getBoards(state) {
      return state.boards;
    },
    getIsOwn(state) {
      return state.isOwn;
    },
    boardInfo(state) {
      return Object.keys(state.currentBoard).length !== 0
        ? state.currentBoard
        : null;
    }
  }
};
