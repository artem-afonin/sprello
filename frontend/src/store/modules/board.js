import axios from "axios";
import {apiurl, devMode} from "../../globalDefines";

export default {
  actions: {
    async fetchBoards(context, own = false) {
      try {
        context.commit('clearBoards')
        const response = await axios.get(apiurl.board, {
          params: {own}
        })
        const boards = response.data
        if (devMode) console.log('Полученные доски:', boards)
        boards.forEach(board => context.commit('pushBoard', board))
      } catch (e) {
        if (devMode) console.error(e)
        context.commit('setBoards', null)
      }
    },
    async fetchBoard(context, id) {
      try {
        const response = await axios.get(apiurl.boardId(id), {
          params: {boardId: id}
        })
        const board = response.data
        if (devMode) console.log('Информация о доске: ', board)
        context.commit('setCurrentBoard', board)
      } catch (e) {
        if (devMode) console.log(e.response)
        throw new Error(e.response.status.toString())
      }
    },
    async postBoard(context, {name, isPrivate}) {
      try {
        const response = await axios.post(apiurl.board, null, {
          params: {name, isPrivate}
        })
        const board = response.data
        context.commit('pushBoard', board)
      } catch (e) {
        if (devMode) console.error(e)
      }
    }
  },

  mutations: {
    clearBoards(state) {
      state.boards.splice(0, state.boards.length)
    },
    setBoards(state, boards) {
      state.clearBoards()
      state.boards = boards
    },
    pushBoard(state, board) {
      state.boards.push(board)
    },
    setCurrentBoard(state, board) {
      state.currentBoard = board
    }
  },

  state: {
    boards: [],
    currentBoard: {}
  },

  getters: {
    getBoards(state) {
      return state.boards
    },
    boardInfo(state) {
      return Object.keys(state.currentBoard).length !== 0
          ? state.currentBoard
          : null
    }
  }
}