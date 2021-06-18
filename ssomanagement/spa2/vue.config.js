module.exports = {
    devServer: {
        proxy: 'http://compiler:8082/ssomanagement/',
    },
    outputDir: '../src/main/resources/static/',
    publicPath: process.env.NODE_ENV === 'production' ? '/ssomanagement/' : '/'
}
