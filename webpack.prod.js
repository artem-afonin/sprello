const path = require('path')
const merge = require('webpack-merge')
const common = require('./webpack.common.js')

module.exports = merge(common, {
  mode: 'production',

  output: {
    //TODO направить компиляцию в другую директорию
    path: path.join(__dirname, 'src', 'main', 'resources', 'static', 'js')
  }
})