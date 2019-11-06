<template>
    <div class="container">
        <div class="row">
            <div class="col-12">
                <router-link v-bind:to="{ name: 'appList' }">
                    <HomeIcon></HomeIcon>
                </router-link>
                appId : {{ appId }}
            </div>
        </div>
        <a v-bind:href="'appUserRoleForm.html?appId=' + appId">
            <PlusBoxIcon></PlusBoxIcon>
        </a>
        <div class="row">
            <div class="col-md-2 table-header">

            </div>
            <div class="col-md-5 table-header">
                Username
            </div>
            <div class="col-md-5 table-header">
                Role
            </div>
        </div>
        <div v-bind:key="index" v-for="(appUserRole, index) in appUserRoleList">
            <div class="row top-buffer">
                <div class="col-md-2">
                    <a class="btn btn-primary" v-bind:href="'appUserRoleForm.html?id=' + appUserRole.id + '&appId=' + appId">Edit</a>
                    <a class="btn btn-danger" href="#" v-on:click="deleteAppUserRole(appUserRole.id)">Delete</a>
                </div>
                <div class="col-md-5">
                    {{ appUserRole.username }}
                </div>
                <div class="col-md-5">
                    {{ appUserRole.appRole }}
                </div>
            </div>
        </div>

    </div>
</template>
<script>
    import axios from 'axios';
    import 'bootstrap/dist/css/bootstrap.css';
    import HomeIcon from 'vue-material-design-icons/Home.vue';
    import PlusBoxIcon from 'vue-material-design-icons/PlusBox.vue';



    export default {
        name: 'AppRoleList',
        data: function(){
            return{
                appId : null,
                appUserRoleList : [],
                axiosConfig: {
                    headers : {
                    },
                    data:{
                    }
                },
            }
        },
        mounted: function(){
            let self = this;
            self.axiosConfig.headers['Accept'] = 'application/json';
            self.axiosConfig.headers['Content-Type'] = 'application/json';

            axios.get('api/csrf-token', self.axiosConfig)
                .then(function (response) {
                    console.log(response);
                    self.axiosConfig.headers[response.data.csrf_header] = response.data.csrf_token;
                })
                .catch(function (error) {
                    console.log(error);
                });
            self.appId = this.$route.params.appId;
            axios.get("api/role/filerByAppId?appId=" + self.appId, self.axiosConfig)
                .then(function (response) {
                    console.log(response);
                    self.appUserRoleList = response.data.content;
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        methods: {
            deleteAppUserRole: function(id){
                let self = this;
                axios.delete("api/role/?id=" + id, self.axiosConfig)
                    .then(function (response){
                        console.log("delete return");
                        console.log(response);
                        // window.location.reload();
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }
        },
        components: {
            HomeIcon,
            PlusBoxIcon,
        }
    }
</script>