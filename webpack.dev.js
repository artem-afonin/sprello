const path = require('path')
const merge = require('webpack-merge')
const common = require('./webpack.common.js')

module.exports = merge(common, {
  mode: 'development',
  devtool: 'source-map',

  devServer: {
    contentBase: path.join(__dirname, 'frontend', 'dist'),
    compress: true,
    port: 8000,
    allowedHosts: [
      'localhost:8080'
    ],
    stats: 'errors-only'
  }
})