module.exports = {
    devServer: {
        proxy: {
            '^/api': {
                target: 'http://compiler:8082/ssomanagement/',
            },
            '^/selfServiceApi': {
                target: 'http://compiler:8082/ssomanagement/',
            }
        }
        
    },
    outputDir: '../src/main/resources/static/',
    publicPath: process.env.NODE_ENV === 'production' ? '/ssomanagement/' : '/'
}
