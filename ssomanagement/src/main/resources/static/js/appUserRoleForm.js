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
            errors:[],
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
            save: function(event){
                event.preventDefault();
                var self = this;
                self.checkForm();
                if (self.errors.length > 0){
                    return;
                }
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
            checkForm : function(){
                this.errors = [];
                var letterNumber = /^[0-9a-zA-Z]+$/;
                if (!letterNumber.test(this.appUserRole.username)) {
                    this.errors.push('username should only contain letter and number');
                }
                if (!letterNumber.test(this.appUserRole.appRole)) {
                    this.errors.push('appRole should only contain letter and number');
                }
                if (this.appUserRole.username === "" || this.appUserRole.username === null) {
                    this.errors.push('username could not empty');
                }
                if (this.appUserRole.appRole === "" || this.appUserRole.appRole === null) {
                    this.errors.push('appRole could not empty');
                }
            }
        }
    });

    // todo fetch exiting record from db
})();
