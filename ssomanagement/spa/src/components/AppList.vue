<template>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                App List
            </div>
        </div>
        <div v-bind:key="index" v-for="(app,index) in appList">
            <div class="row">
                <div class="col-md-12">
                    <a v-bind:href="'appUserRoleList.html?appId=' + app.clientId">{{ app.clientId }}</a>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
// import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.css';
export default {
    name: 'AppList',
    data: function(){
        return{
            page: null,
            appList: [],
            headers : {

            },
        }
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
        var page = url.searchParams.get('page');
        if (page === null){
            page = 0;
        }
        self.page =  page;

        fetch("api/app/findAll?page=" + self.page, {
            method: 'GET',
            headers: self.headers,
        })
        .then(res => res.json())
        .then((result) => {
                console.log('fetch success');
                console.log(result);
                self.appList = result.content;
            },
            (error) => {
                console.log(error);
        });
    },
    methods: {
    },
}
</script>