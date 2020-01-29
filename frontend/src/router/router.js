import Vue from "vue";
import VueRouter from "vue-router";
import index from "../views/index";
import about from "../views/about";
import board from "../views/board";
import boardpage from "../views/boardpage";
import userpage from "../views/userpage";
import error404 from "../views/error404";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "index",
    component: index
  },
  {
    path: "/about",
    name: "about",
    component: about
  },
  {
    path: "/login",
    name: "login",
    redirect: "/login"
  },
  {
    path: "/logout",
    name: "logout",
    redirect: "/logout"
  },
  {
    path: "/board",
    name: "board",
    component: board
  },
  {
    path: "/board/:boardid",
    name: "boardpage",
    component: boardpage
  },
  {
    path: "/users/:userid",
    name: "userpage",
    component: userpage
  },
  {
    path: "*",
    name: "error404",
    component: error404
  }
];

export default new VueRouter({
  mode: "history",
  routes
});
