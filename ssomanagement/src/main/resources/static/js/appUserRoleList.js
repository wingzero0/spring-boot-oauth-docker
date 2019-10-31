(function(){
    var app = new Vue({
        el: '#app',
        data: {
            appId : null,
            appUserRoleList : [],
            headers : {

            },
        },
        mounted: function(){
            var self = this;
            self.headers['Accept'] = 'application/json';
            self.headers['Content-Type'] = 'application/json';
            fetch('api/csrf-token', {
                method: 'GET',
                headers: self.headers,
            })
            .then(res => res.json())
            .then(
                (result) => {
                    console.log(result);
                    self.headers[result.csrf_header] = result.csrf_token;
                    console.log(self.headers);
                },
                (error) => {
                    console.log(error);
                }
            );

            var url = new URL(window.location.href);
            self.appId = url.searchParams.get('appId');


            fetch("api/role/filerByAppId?appId=" + self.appId, {
                method: 'GET',
                headers: self.headers,
            })
            .then(res => res.json())
            .then((result) => {
                console.log('fetch success');
                console.log(result);
                self.appUserRoleList = result.content;
                },
                (error) => {
                    console.log(error);
            });
        },
        methods: {
            deleteAppUserRole: function(id){
                var self = this;
                fetch("api/role/?id=" + id, {
                    method: 'DELETE',
                    headers: self.headers,
                })
                .then(res => res.json())
                .then(
                    (result) => {
                        console.log('delete success');
                        console.log(result);
                        window.location.reload();
                    },
                    (error) => {
                    console.log(error);
                    }
                );
            }
        }
    });

    // todo fetch exiting record from db
})();
