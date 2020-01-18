const path = require('path')
const VueLoaderPlugin = require('vue-loader/lib/plugin')
const {CleanWebpackPlugin} = require('clean-webpack-plugin')

module.exports = {
  context: path.resolve(__dirname, 'src'),

  entry: {
    index: path.resolve(__dirname, 'src/index.js'),
    board: path.resolve(__dirname, 'src/board.js'),
    about: path.resolve(__dirname, 'src/about.js')
  },

  module: {
    rules: [
      {
        test: /\.js$/,
        exclude: /node_modules/,
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

  /*optimization: {
    splitChunks: {
      chunks: 'all'
    }
  },*/

  plugins: [
    new CleanWebpackPlugin(),
    new VueLoaderPlugin()
  ],

  resolve: {
    modules: [
      path.join(__dirname),
      path.join(__dirname, '../node_modules'),
    ],
    extensions: ['.js'],
    alias: {
      '@': path.resolve(__dirname, 'src'),
      '@pages': path.resolve(__dirname, 'src/pages'),
      '@components': path.resolve(__dirname, 'src/components')
    }
  }
}
