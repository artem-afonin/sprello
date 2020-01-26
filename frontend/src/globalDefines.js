module.exports = {
  devMode: process.env.NODE_ENV === 'development',
  navButtons: [
    {name: 'Главная', href: '/', authRequired: false},
    {name: 'Доски', href: '/board', authRequired: true},
    {name: 'Пользователи', href: '/users', authRequired: true},
    {name: 'О Sprello', href: '/about', authRequired: false}
  ],
  apiurl: {
    board: 'api/v1/board',
    task: 'api/v1/board/task',
    taskElement: 'api/v1/board/task/element',
    user: 'api/v1/user',
    boardId: id => `api/v1/board/${id}`,
    userId: id => `api/v1/user/${id}`
  }
}