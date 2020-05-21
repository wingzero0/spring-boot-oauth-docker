<template>
    <div>
        <div class="row">
            <div class="col-12">
                <h1>App List</h1>
            </div>
        </div>
        <hr/>
        <div v-bind:key="index" v-for="(app,index) in appList">
            <div class="row top-buffer">
                <div class="col-md-1" v-if="isAdmin()">
                    <router-link :to='{name:"appDetailForm", params:{clientId : app.clientId, appName: app.displayName }}' class="btn btn-primary" role="button">Edit</router-link>
                </div>
                <div class="col-md-1">
                    <router-link :to='{name:"appRoleList", params:{appId : app.clientId, appName: app.displayName }}' class="btn btn-primary" role="button">Role</router-link>
                </div>
                <div class="col-md-8">
                    {{app.displayName}}
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import axios from 'axios';
import {isAdminCheck} from '@/utils/utilsFunction.js';

export default {
    name: 'AppList',
    data: function(){
        return{
            pageNumber: 0,
            appList: [],
            axiosConfig: {
                headers : {
                },
                data:{
                }
            },
            loginInfo:{
                grantedAuthorities: [],
            },
        }
    },
    mounted: function(){
        this.axiosConfig = this.$store.state.axiosConfig;
        this.loginInfo = this.$store.state.loginInfo;
        this.fetchRecord();
    },
    methods: {
        fetchRecord() {
            let self = this;
            axios.get('api/app?pageNumber=' + self.pageNumber, self.axiosConfig)
                .then(function (response) {
                    console.log(response);
                    self.appList = response.data.content;
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        isAdmin(){
            return isAdminCheck(this.loginInfo.grantedAuthorities);
        },
        updateAppName(appName){
            this.$store.commit('updateAppName', appName);
        },
    },
    components: {
    },
}
</script>

<style scoped>
    .material-design-icon.icon-2x {
        height: 2em;
        width: 2em;
    }
    .material-design-icon.icon-2x > .material-design-icon__svg {
        height: 2em;
        width: 2em;
    }
</style>