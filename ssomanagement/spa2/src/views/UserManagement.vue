<template>
    <div>
        <el-row>
            <el-col :span="24">
                <h1>User List</h1>
            </el-col>
        </el-row>
        <hr/>
        <el-button type="primary" icon="el-icon-plus" v-on:click="addAppUser">
            Add
        </el-button>
        <hr/>
        <el-input v-model="searchWords"
            v-on:change="search" v-on:keyup.enter="search" size="mini" placeholder="filter"
        ></el-input>
        <el-table :data="appUserFilteredList" :default-sort="{prop:'username', order: 'descending'}">
            <el-table-column
                fixed="left"
                label="Action"
                width="100">
                <template slot-scope="scope">
                    <el-button @click="edit(scope.row)" type="text" size="small">
                        <i class="el-icon-edit"></i> Edit
                    </el-button>
                </template>
            </el-table-column>
            <el-table-column
                prop="username"
                label="Username"
                width="200"
                sortable
            ></el-table-column>
            <el-table-column
                prop="displayName"
                label="User Display Name"
                width="200"
                sortable
            ></el-table-column>
            <el-table-column
                prop="email"
                label="Email"
                width="200"
                sortable
            ></el-table-column>
            <el-table-column
                label="Active Status"
                width="200"
            >
                <template slot-scope="scope">
                    {{(scope.row.isActive == 'Y') ? 'Active' : 'Inactive'}}
                </template>
            </el-table-column>
        </el-table>
        <div>
            <app-user-form
                :dialogVisible="appUserFormShow"
                @closeAppUserForm="closeAppUserFormShow"
                :propAppUser="appUser"
            >
            </app-user-form>
        </div>
        <delete-confirm-dialog
            :propDialogVisible="deleteConfirmShow"
            :propDeleteMsg="deleteMsg"
            @cancelDelete="cancelDeleteAppUser"
            @confirmDelete="confirmDeleteAppUser"
        ></delete-confirm-dialog>
    </div>
</template>

<script>
    import AppUserForm from "@/components/AppUserForm";
    import DeleteConfirmDialog from "@/components/DeleteConfirmDialog";
    import AppUser from "@/model/AppUser";
    export default {
        name: "UserManagement",
        data() {
            return {
                appUserList: [],
                appUserFilteredList: [],
                appUser:{},
                appUserFormShow: false,
                deleteConfirmShow: false,
                deleteMsg: "",
                searchWords: "",
                axiosConfig: {},
                webApi: process.env.VUE_APP_WEB_ROOT,
            };
        },
        mounted() {
            this.axiosConfig = this.$store.state.axiosConfig;
            this.appUser = new AppUser();
            this.fetchRecord();
        },
        methods: {
            search(){
                this.appUserFilteredList = this.appUserList.filter(appUser => {
                    if (this.searchWords != ""){
                        return (appUser.username != null && appUser.username.includes(this.searchWords)) || 
                            (appUser.displayName != null && appUser.displayName.includes(this.searchWords)) || 
                            (appUser.email != null && appUser.email.includes(this.searchWords));
                    } else {
                        return true;
                    }
                });
            },
            fetchRecord(){
                return this.$http.get(this.webApi + "api/appUsers", this.axiosConfig)
                    .then((response) => {
                        console.log(response);
                        this.appUserList = response.data;
                        this.search();
                    })
                    .catch((error) => {
                        console.log(error);
                    });
            },
            edit(appUser){
                this.appUser = appUser;
                this.appUser.password = null;
                this.appUserFormShow = true;
            },
            closeAppUserFormShow(refresh){
                if (refresh === true){
                    this.fetchRecord();
                }
                this.appUserFormShow = false;
            },
            addAppUser(){
                this.appUser = new AppUser();
                this.appUserFormShow = true;
            },
            showDeleteAppUserDialog(appUser){
                this.deleteConfirmShow = true;
                this.deleteMsg = 'Are you sure to delete record:' + appUser.username;
            },
            cancelDeleteAppUser(){
                this.deleteConfirmShow = false;
            },
            confirmDeleteAppUser(){
                console.log("not implemented from backend");
                this.deleteConfirmShow = false;
            },
        },
        components: {
            "app-user-form" : AppUserForm,
            "delete-confirm-dialog": DeleteConfirmDialog
        },
    }
</script>

<style scoped>
</style>