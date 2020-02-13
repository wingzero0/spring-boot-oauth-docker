module.exports = {
    devServer: {
        port: 3000,
        proxy: 'http://compiler:8082',
    },
    outputDir: '../src/main/resources/static/',
}
