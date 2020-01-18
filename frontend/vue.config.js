const path = require('path')

module.exports = {
  pages: {
    index: {
      // точка входа для страницы
      entry: 'src/index.js',
      template: 'public/index.html',
      filename: 'index.html',
      title: 'Index Page',
    },
    about: {
      entry: 'src/about.js',
      template: 'public/index.html',
      filename: 'about.html',
      title: 'About Page'
    },
    board: {
      entry: 'src/board.js',
      template: 'public/index.html',
      filename: 'board.html',
      title: 'Boards Page'
    }
  },
  devServer: {
    contentBase: path.join(__dirname, 'dist'),
    compress: true,
    port: 8000,
    allowedHosts: [
      'localhost:8080'
    ],
    stats: 'errors-only'
  }
}