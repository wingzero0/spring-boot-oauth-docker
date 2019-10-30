(function(){
    var app = new Vue({
        el: '#app',
        data: {
            appUserRole : {
                id: null,
                username: null,
                appId: null,
                appRole: null,
            },
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
            var id = url.searchParams.get('id');
            self.appUserRole.appId = url.searchParams.get('appId');

            if (id !== null){
                fetch("api/role/?id=" + id, {
                    method: 'GET',
                    headers: self.headers,
//                    body : JSON.stringify(submitJson),
                })
                .then(res => res.json())
                .then((result) => {
                    console.log('fetch success');
                    console.log(result);
                    self.appUserRole = result;
                    },
                    (error) => {
                        console.log(error);
                });
            }
        },
        methods: {
            save: function(){
                var self = this;
                fetch("api/role/", {
                    method: 'POST',
                    headers: self.headers,
                    body : JSON.stringify(self.appUserRole),
                })
                .then(res => res.json())
                .then(
                    (result) => {
                        console.log('fetch success');
                        console.log(result);
                        self.appUserRole = result;
                        window.location = 'appUserRoleList.html?appId=' + self.appUserRole.appId;
                    },
                    (error) => {
                    console.log(error);
                    }
                );
            },
        }
    });

    // todo fetch exiting record from db
})();
