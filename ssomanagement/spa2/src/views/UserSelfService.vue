<template>
    <div>
        <el-row>
            <el-col :span="24">
                <h1>User Self Service</h1>
            </el-col>
        </el-row>
        <hr/>
        <el-row>
            <el-col :span="24">
                <el-button @click="edit">Change Password</el-button>
            </el-col>
        </el-row>
        <div>
            <app-user-form
                :dialogVisible="appUserFormShow"
                @closeAppUserForm="closeAppUserFormShow"
                :propAppUser="appUser"
            >
            </app-user-form>
        </div>
    </div>
</template>

<script>
    import AppUserForm from "@/components/AppUserForm";
    import AppUser from "@/model/AppUser";
    export default {
        name: "UserManagement",
        data() {
            return {
                appUserList: [],
                appUserFilteredList: [],
                appUser:{},
                appUserFormShow: false,
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
            fetchRecord(){
                return this.$http.get(this.webApi + 'selfServiceApi/getAppUser', this.axiosConfig)
                    .then((response) => {
                        this.appUser = response.data;
                        this.appUser.password = null;
                    })
                    .catch((error) => {
                        console.log(error);
                    });
            },
            edit(){
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
        },
    }
</script>

<style scoped>
</style>