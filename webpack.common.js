const path = require('path')
const VueLoaderPlugin = require('vue-loader/lib/plugin')

module.exports = {
  entry: {
    index: path.join(__dirname, 'frontend', 'index.js'),
    board: path.join(__dirname, 'frontend', 'board.js'),
    about: path.join(__dirname, 'frontend', 'about.js')
  },

  module: {
    rules: [
      {
        test: /\.js$/,
        exclude: /(node_modules|bower_components)/,
        use: {
          loader: 'babel-loader',
          options: {
            presets: ['@babel/preset-env']
          }
        }
      },
      {
        test: /\.vue$/,
        use: 'vue-loader'
      },
      {
        test: /\.css$/,
        use: ['vue-style-loader', 'css-loader']
      }
    ]
  },

  output: {
    path: path.join(__dirname, 'build', 'frontend')
  },

  plugins: [
    new VueLoaderPlugin()
  ],

  resolve: {
    modules: [
      path.join(__dirname),
      path.join(__dirname, 'frontend'),
      path.join(__dirname, 'node_modules'),
    ],
    extensions: ['.js', '.vue']
  }
}
