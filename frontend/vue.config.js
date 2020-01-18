const clientPort = 8081
const serverPort = 8080
const serverUrl = `http://localhost:${serverPort}`

module.exports = {
  outputDir: 'dist/public',
  assetsDir: 'static',
  filenameHashing: false,

  devServer: {
    contentBase: './dist/public',
    compress: true,
    port: clientPort,
    stats: 'errors-only',
    clientLogLevel: 'error',
    proxy: {
      '/*': {
        target: serverUrl,
        secure: false,
        prependPath: false
      }
    },
    historyApiFallback: true
  }
}