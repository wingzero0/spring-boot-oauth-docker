<template>
    <div>
        <el-row>
            <el-col :span="24">
                <h1>App List</h1>
            </el-col>
        </el-row>
        <hr/>
        <router-link :to='{name:"appDetailForm", params:{clientId : "new", appName: "draft" }}'>
            Add
        </router-link>
        <hr/>
        <el-table :data="appList">
            <el-table-column
                fixed="left"
                label="Action"
                width="100">
                <template slot-scope="scope">
                    <el-button v-if="isAdmin()" @click="edit(scope.row)" type="text" size="small">Edit</el-button>
                    <el-button @click="role(scope.row)" type="text" size="small">Role</el-button>
                </template>
            </el-table-column>
            <el-table-column
                prop="clientId"
                label="App Client Id"
                width="300">
            </el-table-column>
            <el-table-column
                prop="displayName"
                label="App Display Name"
                width="180">
            </el-table-column>
        </el-table>
        <hr/>
        <PagingComp :pageNumber="pageNumber"
            :getPreviousPage="getPreviousPage"
            :getNextPage="getNextPage"
        >

        </PagingComp>
    </div>
</template>
<script>
import {isAdminCheck} from '@/utils/utilsFunction.js';

export default {
    name: 'AppList',
    data: function(){
        return{
            pageNumber: 0,
            totalPages: 1,
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
        if (this.$route.query.pageNumber != null){
            this.pageNumber = parseInt(this.$route.query.pageNumber);
        }
        this.fetchRecord();
    },
    methods: {
        fetchRecord() {
            this.$http.get('api/app?pageNumber=' + this.pageNumber, this.axiosConfig)
                .then((response) => {
                    console.log(response);
                    this.appList = response.data.content;
                    this.totalPages = response.data.totalPages;
                })
                .catch((error) => {
                    console.log(error);
                });
        },
        isAdmin(){
            return isAdminCheck(this.loginInfo.grantedAuthorities);
        },
        updateAppName(appName){
            this.$store.commit('updateAppName', appName);
        },
        getPreviousPage(event){
            event.preventDefault();
            if (this.pageNumber > 0){
                this.pageNumber = this.pageNumber - 1;
                this.$router.push({ name: 'appList', query: { pageNumber: this.pageNumber, } });
            }
        },
        getNextPage(event){
            event.preventDefault();
            if (this.pageNumber < (this.totalPages -1)){
                this.pageNumber = this.pageNumber + 1;
                this.$router.push({ name: 'appList', query: { pageNumber: this.pageNumber, } });
            }
        },
        edit(app){
            this.$router.push({name:"appDetailForm", params:{clientId : app.clientId, appName: app.displayName }});
        },
        role(app){
            this.$router.push({name:"appRoleList", params:{appId : app.clientId, appName: app.displayName }});
        },
    },
    watch:{
        '$route' (){
            this.fetchRecord();
        }
    },
}
</script>

<style scoped>
</style>