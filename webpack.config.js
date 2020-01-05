const path = require('path');
const VueLoaderPlugin = require('vue-loader/lib/plugin');

module.exports = {
  //TODO создать две отдельные prod и dev конфигурации
  mode: 'development',
  devtool: 'eval',
  entry: {
    index: path.join(__dirname, 'frontend', 'index.js'),
    about: path.join(__dirname, 'frontend', 'about.js')
  },
  devServer: {
    contentBase: path.join(__dirname, 'dist'),
    compress: true,
    port: 8000,
    allowedHosts: [
      'localhost:8080'
    ]
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
  plugins: [
    new VueLoaderPlugin()
  ],
  resolve: {
    modules: [
      path.join(__dirname),
      path.join(__dirname, 'node_modules'),
    ],
    extensions: ['.js', '.vue']
  }
};