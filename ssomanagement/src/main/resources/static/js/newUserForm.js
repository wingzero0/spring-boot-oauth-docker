(function(){
    var app = new Vue({
        el: '#app',
        data: {
            message: 'Hello Vue!',
            id: 1,
            username: 'john',
            appId: 'spring-security-oauth2-read-write-client',
            appRole: 'readwriteclient',
        },
        mounted: function(){
            var self = this;
        },
    });

    // todo fetch exiting record from db
})();
