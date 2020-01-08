module.exports = {
  devMode: process.env.NODE_ENV === 'development',
  navButtons: [
    {name: 'Главная', href: '/', authRequired: false},
    {name: 'Доски', href: '/board', authRequired: true},
    {name: 'Пользователи', href: '/users', authRequired: true},
    {name: 'О Sprello', href: '/about', authRequired: false}
  ]
}