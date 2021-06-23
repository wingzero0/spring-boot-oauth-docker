<template>
    <div>
        <el-dialog
            title="App Role"
            :visible.sync="dialogVisible"
            :close-on-press-escape="true"
            :before-close="()=>{close(true);}"
            width="600px">
            <el-form ref="appRoleForm" :model="appUserRole" :rules="rules">
                <el-form-item label="Username" prop="username" required>
                    <el-select v-model="appUserRole.username" 
                        filterable placeholder="username"
                        :filter-method="filterUsername"
                        >
                        <el-option
                            v-for="item in filteredUsernameList"
                            :key="item.username"
                            :label="item.username + ' ' + item.displayName + ' ' + item.email"
                            :value="item.username">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="App Role" prop="appRole" required>
                    <el-input v-model="appUserRole.appRole"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="()=>{close(true);}">Cancel</el-button>
                <el-button type="primary" @click="save">Save</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<style>
</style>
<script>
    export default {
        name: 'AppRoleForm',
        props: {
            dialogVisible: Boolean,
            propAppUserRole: Object,
        },
        data: function(){
            var letterCheck = (rule, value, callback) => {
                let letterNumber = /^[0-9a-zA-Z]+$/;
                if (!letterNumber.test(value)) {
                    callback(new Error('should only contain letter and number'));
                } else {
                    callback();
                }
            };
            return{
                appUserRole : {},
                rules: {
                    username: [{required: true, message: 'value cannot be empty', trigger: 'blur'},{validator: letterCheck, trigger: 'blur'}],
                    appRole: [{required: true, message: 'value cannot be empty', trigger: 'blur'},{validator: letterCheck, trigger: 'blur'}],
                },
                usernameList:[],
                filteredUsernameList:[],
                axiosConfig: {},
                webApi: process.env.VUE_APP_WEB_ROOT,
            }
        },
        computed: {
        },
        mounted(){
            this.appUserRole = Object.assign({}, this.propAppUserRole);
            this.usernameList = this.$store.state.usernameList;
            this.filteredUsernameList = this.usernameList;
            this.axiosConfig = this.$store.state.axiosConfig;
        },
        methods: {
            close(refresh){
                this.$emit("closeAppRoleForm", refresh);
            },
            save: function(event){
                event.preventDefault();
                this.$refs['appRoleForm'].validate((valid) => {
                    if (valid) {
                        this.$http.post(this.webApi + 'api/role/', this.appUserRole, this.axiosConfig)
                            .then((response) => {
                                console.log(response.data);
                                this.close(true);
                            })
                            .catch((error) => {
                                console.log(error);
                            });
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            back(event){
                event.preventDefault();
                this.close(false);
            },
            filterUsername(value){
                console.log("filter username:" + value);
                if (value != ""){
                    let lowerCase = value.toLowerCase();
                    this.filteredUsernameList = this.usernameList.filter(item => {
                        if (item.displayName != null && item.displayName.toLowerCase().includes(lowerCase)){
                            return true;
                        } if (item.username != null && item.username.toLowerCase().includes(lowerCase)){
                            return true;
                        } if (item.email != null && item.email.toLowerCase().includes(lowerCase)){
                            return true;
                        } else {
                            return false;
                        }
                    });
                }
            },
        },
        components: {
        },
        watch:{
            'propAppUserRole'(newAppUserRole){
                this.appUserRole = Object.assign({}, newAppUserRole);
            }
        },
    }
</script>
