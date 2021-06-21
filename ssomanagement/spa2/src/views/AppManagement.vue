<template>
    <div>
        <el-row>
            <el-col :span="24">
                <h1>App List</h1>
            </el-col>
        </el-row>
        <hr/>
        <router-link :to='{name:"appDetailForm", params:{clientId : "new", appName: "draft" }}'>
            <i class="el-icon-circle-plus"></i>Add
        </router-link>
        <hr/>
        <el-table :data="appList">
            <el-table-column
                fixed="left"
                label="Action"
                width="150">
                <template slot-scope="scope">
                    <el-button v-if="isAdmin()" @click="edit(scope.row)" type="text" size="small">
                        <i class="el-icon-edit"></i> Edit
                    </el-button>
                    <el-button @click="role(scope.row)" type="text" size="small">
                        <i class="el-icon-s-custom"></i> Role
                    </el-button>
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
        <div class="block">
            <el-pagination
                layout="prev, pager, next"
                :current-page="pageNumber"
                :count="totalElements"
                :page-count="totalPages"
                @current-change="currentChange"
                >
            </el-pagination>
        </div>
    </div>
</template>
<script>
import {isAdminCheck} from '@/utils/utilsFunction.js';

export default {
    name: 'AppList',
    data: function(){
        return{
            pageNumber: 1,
            totalPages: 1,
            totalElements: 10,
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
            webApi: process.env.VUE_APP_WEB_ROOT,
        }
    },
    mounted: function(){
        this.axiosConfig = this.$store.state.axiosConfig;
        this.loginInfo = this.$store.state.loginInfo;
        if (this.$route.query.pageNumber != null){
            this.pageNumber = parseInt(this.$route.query.pageNumber);
        }
        this.fetchRecord(this.pageNumber - 1);
    },
    methods: {
        currentChange(pageNumber){
            console.log("event: "+ pageNumber);
            this.fetchRecord(pageNumber - 1);
        },
        fetchRecord(pageNumber) {
            this.$http.get(this.webApi + 'api/app?pageNumber=' + pageNumber, this.axiosConfig)
                .then((response) => {
                    console.log(response);
                    this.appList = response.data.content;
                    this.totalPages = response.data.totalPages;
                    this.totalElements = response.data.totalElements;
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