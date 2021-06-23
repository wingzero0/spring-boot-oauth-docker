<template>
    <div>
        <el-dialog
            title="App User"
            :visible.sync="dialogVisible"
            :close-on-press-escape="true"
            :before-close="()=>{close(true);}"
            width="600px">
            <el-form ref="appUserForm" :model="appUser" :rules="rules">
                <el-form-item label="Username" prop="username" required>
                    <el-input v-model="appUser.username" :disabled="appUser.id != null">
                    </el-input>
                </el-form-item>
                <el-form-item label="Display Name" prop="displayName">
                    <el-input v-model="appUser.displayName">
                    </el-input>
                </el-form-item>
                <el-form-item label="Password" prop="password">
                    <el-input type="password" v-model="appUser.password">
                    </el-input>
                </el-form-item>
                <el-form-item label="Password Again" prop="passwordAgain">
                    <el-input type="password" v-model="passwordAgain">
                    </el-input>
                </el-form-item>
                <el-form-item label="Email" prop="email">
                    <el-input v-model="appUser.email">
                    </el-input>
                </el-form-item>
                <el-form-item label="Active Status" prop="isActive" required>
                    <el-select v-model="appUser.isActive" placeholder="select">
                        <el-option value="Y" label="Active">Active</el-option>
                        <el-option value="N" label="Inactive">Inactive</el-option>
                    </el-select>
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
        name: 'AppUserForm',
        props: {
            dialogVisible: Boolean,
            propAppUser: Object,
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
            var validatePassword = (rule, value, callback) => {
                if ((value === null || value === '') && this.appUser.id == null) {
                    callback(new Error('Password cannot be empty'));
                } else {
                    callback();
                }
            };
            var validatePassworAgain = (rule, value, callback) => {
                if (this.appUser.password != null && this.appUser.password != "" &&
                    (this.passwordAgain != this.appUser.password)) {
                    callback(new Error('Password not match!'));
                } else {
                    callback();
                }
            };
            return{
                appUser : {},
                rules: {
                    username: [{required: true, message: 'value cannot be empty', trigger: 'blur'},{validator: letterCheck, trigger: 'blur'}],
                    password: [{validator: validatePassword, trigger: 'blur' }],
                    passwordAgain: [{validator: validatePassworAgain, trigger: 'blur' }],
                    isActive: [{required: true, message: 'value cannot be empty', trigger: 'blur'}],
                },
                passwordAgain: null,
                filteredUsernameList:[],
                axiosConfig: {},
                webApi: process.env.VUE_APP_WEB_ROOT,
            }
        },
        computed: {
        },
        mounted(){
            this.appUser = Object.assign({}, this.propAppUser);
            this.axiosConfig = this.$store.state.axiosConfig;
        },
        methods: {
            close(refresh){
                this.$emit("closeAppUserForm", refresh);
            },
            save: function(event){
                event.preventDefault();
                this.$refs['appUserForm'].validate((valid) => {
                    if (valid) {
                        this.$http.post(this.webApi + 'api/appUser', this.appUser, this.axiosConfig)
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
        },
        components: {
        },
        watch:{
            'propAppUser'(newAppUser){
                this.appUser = Object.assign({}, newAppUser);
                this.passwordAgain = null;
            }
        },
    }
</script>
