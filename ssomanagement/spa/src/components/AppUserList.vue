<template>
    <div>
        user list
        <div class="row">
            <div class="col-2">edit</div>
            <div class="col-2">Display Name</div>
            <div class="col-2">Username</div>
        </div>
        <div class="row" v-for="(appUser) in appUserList" v-bind:key="'appUser' + appUser.id">
            <div class="col-2">
                <router-link v-bind:to="{ 'name': 'appUserForm', params:{ id : appUser.id }}">
                    edit
                </router-link>
            </div>
            <div class="col-2">{{appUser.displayName}}</div>
            <div class="col-2">{{appUser.username}}</div>
        </div>
    </div>
</template>

<script>
    import axios from "axios";
    import {getCSRFToken} from '@/utils/utilsFunction.js';
    import 'bootstrap/dist/css/bootstrap.css';

    export default {
        name: "AppUserList",
        data:function (){
            return {
                pageNumber: 0,
                pageSize:10,
                appUserList: [],
            };
        },
        mounted: function(){
            let self = this;
            getCSRFToken()
                .then(function (response) {
                    self.axiosConfig = response;
                    self.fetchRecord();
                });
        },
        methods: {
            fetchRecord:function(){
                let self = this;
                axios.get("api/appUser?pageNumber=" + self.pageNumber + '&pageSize=' + self.pageSize, self.axiosConfig)
                    .then(function (response) {
                        console.log(response);
                        self.appUserList = response.data.content;
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },
        },
    }
</script>

<style scoped>

</style>