<template>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                App List
            </div>
        </div>
        <div v-bind:key="index" v-for="(app,index) in appList">
            <div class="row">
                <div class="col-md-2">
                    <router-link :to='{name:"appRoleList", params:{appId : app.clientId }}' class="btn btn-primary" role="button">Edit</router-link>
                </div>
                <div class="col-md-10">
                    <router-link :to='{name:"appRoleList", params:{appId : app.clientId }}' >{{app.clientId}}</router-link>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.css';
export default {
    name: 'AppList',
    data: function(){
        return{
            page: null,
            appList: [],
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

        axios.get('api/app/findAll?page=0', self.axiosConfig)
            .then(function (response) {
                console.log(response);
                self.appList = response.data.content;
            })
            .catch(function (error) {
                console.log(error);
            });

        // var url = new URL(window.location.href);
        // var page = url.searchParams.get('page');
        // if (page === null){
        //     page = 0;
        // }
        // self.page =  page;
        //
        // fetch("api/app/findAll?page=" + self.page, {
        //     method: 'GET',
        //     headers: self.headers,
        // })
        // .then(res => res.json())
        // .then((result) => {
        //         console.log('fetch success');
        //         console.log(result);
        //         self.appList = result.content;
        //     },
        //     (error) => {
        //         console.log(error);
        // });
    },
    methods: {
    },
}
</script>