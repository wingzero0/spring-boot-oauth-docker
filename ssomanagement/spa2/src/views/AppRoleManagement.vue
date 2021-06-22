<template>
    <div>
        <el-row>
            <el-col :span="24">
                <router-link v-bind:to="{ name: 'AppManagement' }">
                    <i class="el-icon-caret-left"></i>{{appName}}
                </router-link>
            </el-col>
        </el-row>
        <hr/>
        <el-row>
            <el-col :span="24">
                <h1>App Role List</h1>
            </el-col>
        </el-row>
        <hr/>
        <router-link v-bind:to="{ name: 'appRoleForm', params: {appId: $route.params.appId, appName: appName, appRoleId:'new'} } ">
            <i class="el-icon-circle-plus"></i>Add
        </router-link>
        <hr/>
        <el-table :data="appUserRoleList">
            <el-table-column
                fixed="left"
                label="Action"
                width="150">
                <template slot-scope="scope">
                    <el-button @click="edit(scope.row)" type="text" size="small">
                        <i class="el-icon-edit"></i> Edit
                    </el-button>
                    <el-button @click="deleteConfirm(scope.row)" type="text" size="small">
                        <i class="el-icon-s-custom"></i> Delete
                    </el-button>
                </template>
            </el-table-column>
            <el-table-column
                prop="username"
                label="Username"
                width="200"
            >
                <template slot="header" slot-scope="scope">
                    <div v-if="false">{{scope.column}} work around that make el-input work</div>
                    <span>Username</span>
                    <el-input v-model="filter.username" 
                        v-on:change="search" size="mini" placeholder="filter"
                    ></el-input>
                </template>
            </el-table-column>
            <el-table-column
                prop="displayName"
                label="User Display Name"
                width="200"
            >
            </el-table-column>
            <el-table-column
                prop="appRole"
                label="App Role"
                width="200"
            >
                <template slot="header" slot-scope="scope">
                    <div v-if="false">{{scope.column}} work around that make el-input work</div>
                    <span>Role</span>
                    <el-input v-model="filter.appRole"
                        v-on:change="search" size="mini" placeholder="filter"
                    ></el-input>
                </template>
            </el-table-column>
        </el-table>
        <hr/>
        <div class="block">
            <el-pagination
                layout="prev, pager, next"
                :current-page.sync="pageNumber"
                :count="totalElements"
                :page-count="totalPages"
                @current-change="currentChange"
                >
            </el-pagination>
        </div>
        <div>
            <el-dialog
                title="Delete Confirm"
                :visible.sync="dialogVisible"
                width="30%">
                <span>{{this.deleteMsg}}</span>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="dialogVisible = false">Cancel</el-button>
                    <el-button type="primary" @click="deleteAppUserRole">Delete</el-button>
                </span>
            </el-dialog>
        </div>
    </div>
</template>
<style>
</style>
<script>
    export default {
        name: 'AppRoleManagement',
        props:{
            // appId: String,
            appName: String,
        },
        data: function(){
            return{
                appId : null,
                appUserRoleList : [],
                appUserMap : {},
                axiosConfig: {
                    headers : {
                    },
                    data:{
                    }
                },
                loginInfo: {},
                deleteMsg: null,
                deleteId: null,
                dialogVisible: false,
                pageNumber: 1,
                pageSize: 10,
                totalPages : 1,
                totalElements: 10,
                filter:{
                    username:"",
                    appRole:"",
                },
                // app : {
                //     displayName: null,
                // },
                webApi: process.env.VUE_APP_WEB_ROOT,
            }
        },
        mounted: function(){
            this.axiosConfig = this.$store.state.axiosConfig;
            this.loginInfo = this.$store.state.loginInfo;
            this.appId = this.$route.params.appId;
            this.fetchRecord(this.pageNumber - 1);
        },
        computed: {
        },
        methods: {
            search(){
                this.pageNumber = 1;
                this.fetchRecord(this.pageNumber - 1);
            },
            currentChange(pageNumber){
                console.log("event: "+ pageNumber);
                this.fetchRecord(pageNumber - 1);
            },
            fetchRecord(pageNumber){
                this.$http.get(this.webApi + "api/role?appId=" + this.appId + '&pageNumber=' + pageNumber + '&pageSize=' + this.pageSize 
                    + "&username=" + this.filter.username + "&appRole=" + this.filter.appRole, this.axiosConfig)
                    .then((response) => {
                        console.log(response);
                        this.appUserRoleList = response.data.content;
                        this.totalPages = response.data.totalPages;
                        this.totalElements = response.data.totalElements;
                        let usernameRequests = this.appUserRoleList.map((appUserRole)=>{
                            this.appUserMap[appUserRole.username] = { displayName : null };
                            appUserRole.displayName = "loading";
                            return this.$http.get(this.webApi + "api/appUserByUsername/" + appUserRole.username, this.axiosConfig)
                                .then((response) => {
                                    this.appUserMap[appUserRole.username] = response.data;
                                    appUserRole.displayName = response.data.displayName;
                                })
                                .catch((error) => {
                                    console.log(error);
                                });
                        });
                        return Promise.all(usernameRequests).then(()=>{
                            this.appUserRoleList = this.appUserRoleList.map(x=>x); // force update
                        });
                    })
                    .catch((error) => {
                        console.log(error);
                    });
            },
            deleteAppUserRole(event){
                event.preventDefault();
                this.$http.delete(this.webApi + "api/role/" + this.deleteId, this.axiosConfig)
                    .then(() => {
                        this.fetchRecord();
                        this.dialogVisible = false;
                    })
                    .catch((error) => {
                        console.log(error);
                    });
            },
            deleteConfirm(appUserRole){
                this.showPopup(appUserRole.username,appUserRole.appRole, appUserRole.id);
            },
            edit(appUserRole){
                console.log(appUserRole);
                this.$router.push({name:"appRoleForm", params: {appId: this.$route.params.appId, appName: this.appName, appRoleId:appUserRole.id} });
            },
            showPopup: function(username, role, id){
                this.deleteMsg = 'Are you sure to delete record:' + username + ' ' + role;
                this.deleteId = id;
                this.dialogVisible = true;
            },
        },
        components: {
        },
        watch:{
            '$route' (){
                this.fetchRecord();
            }
        },
    }
</script>
