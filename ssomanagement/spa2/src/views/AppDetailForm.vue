<template>
    <div>
        <el-row>
            <el-col :span="24">
                <router-link v-bind:to="{ name: 'AppManagement' }">
                    <i class="el-icon-caret-left"></i>{{ appDetail.displayName }}
                </router-link>
            </el-col>
        </el-row>
        <hr/>
        <el-form ref="appDetailForm" :model="appDetail" :rules="rules">
            <el-form-item label="clientId" prop="clientId" required>
                <el-input type="text" v-model="appDetail.clientId"></el-input>
            </el-form-item>
            <el-form-item label="displayName" prop="displayName">
                <el-input type="text" v-model="appDetail.displayName"></el-input>
            </el-form-item>
            <el-form-item label="resourceIds" prop="resourceIds">
                <el-input type="text" v-model="appDetail.resourceIds"></el-input>
                <small for="resourceIds">resourceIds (null or 'resource-server-rest-api')</small>
            </el-form-item>
            <el-form-item label="clientSecret" prop="clientSecret" required>
                <el-input type="password" v-model="appDetail.clientSecret" disabled></el-input>
                <a v-if="appDetail.clientSecret == null || appDetail.clientSecret == ''" href="#regen" v-on:click="(event) => {generatePassword(event, 32);}">
                    generate
                </a>
                <a v-else href="#regen" v-on:click="(event) => {generatePassword(event, 32);}">
                    reset
                </a>
                <div v-show="showClientSecret">
                    new clientSecret will be saved : {{appDetail.clientSecret}}
                </div>
            </el-form-item>
            <el-form-item label="scope" prop="scope" required>
                <el-input v-model="appDetail.scope"></el-input>
                <small>comma seperate, Supported value: read,full_user_list,user_management</small>
            </el-form-item>
            <el-form-item label="authorizedGrantTypes" prop="authorizedGrantTypes" required>
                <el-input v-model="appDetail.authorizedGrantTypes"></el-input>
                <small>comma seperate, Supported value: password,authorization_code,refresh_token,implicit,client_credentials</small>
            </el-form-item>
            <el-form-item label="webServerRedirectUri" prop="webServerRedirectUri">
                <el-input type="text" v-model="appDetail.webServerRedirectUri"></el-input>
                <small>comma seperate</small>
            </el-form-item>
            <el-form-item label="authorities" prop="authorities">
                <el-input type="text" v-model="appDetail.authorities"></el-input>
                <small>comma seperate</small>
            </el-form-item>
            <el-form-item label="accessTokenValidity" prop="accessTokenValidity" required>
                <el-input type="number" v-model="appDetail.accessTokenValidity" placeholder="10800"></el-input>
            </el-form-item>
            <el-form-item label="refreshTokenValidity" prop="refreshTokenValidity" required>
                <el-input type="number" v-model="appDetail.refreshTokenValidity" placeholder="2592000"></el-input>
            </el-form-item>
            <el-form-item label="additionalInformation" prop="additionalInformation">
                <el-input type="text" v-model="appDetail.additionalInformation"></el-input>
            </el-form-item>
            <el-button type="primary" v-on:click="save">Save</el-button>
            <el-button v-on:click="back">Cancel</el-button>
        </el-form>
    </div>
</template>
<style>
</style>
<script>
    export default {
        name: 'AppDetailForm',
        data: function(){
            return {
                appDetail:{
                    clientId: null,
                    resourceIds: null,
                    clientSecret: null,
                    scope: null,
                    authorizedGrantTypes: null,
                    webServerRedirectUri: null,
                    authorities: null,
                    accessTokenValidity: 10800,
                    refreshTokenValidity: 2592000,
                    additionalInformation: null,
                    autoapprove: null,
                    updateClientSecret: false,
                },
                showClientSecret:false,
                webApi: process.env.VUE_APP_WEB_ROOT,
                rules: {
                    clientId: [{required: true, message: 'value cannot be empty', trigger: 'blur'},],
                    clientSecret: [{required: true, message: 'value cannot be empty', trigger: 'blur'},],
                    scope: [{required: true, message: 'value cannot be empty', trigger: 'blur'},],
                    authorizedGrantTypes: [{required: true, message: 'value cannot be empty', trigger: 'blur'},],
                    accessTokenValidity: [{required: true, message: 'value cannot be empty', trigger: 'blur'},],
                    refreshTokenValidity: [{required: true, message: 'value cannot be empty', trigger: 'blur'},],
                },
                axiosConfig: {},
            };
        },
        computed: {
            appName: function(){
                return this.$route.params.appName;
            }
        },
        mounted: function(){
            this.axiosConfig = this.$store.state.axiosConfig;
            this.fetchRecord();
        },
        methods: {
            generatePassword(event, length){
                event.preventDefault();
                var result           = '';
                var characters       = 'ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjkmnopqrstuvwxyz23456789';
                var charactersLength = characters.length;
                for ( var i = 0; i < length; i++ ) {
                  result += characters.charAt(Math.floor(Math.random() * charactersLength));
                }
                this.appDetail.clientSecret = result;
                this.appDetail.updateClientSecret = true;
                this.showClientSecret = true;
            },
            fetchRecord: function(){
                let clientId = this.$route.params.clientId;
                let axiosConfig = this.$store.state.axiosConfig;
                if (clientId !== 'new'){
                    this.$http.get(this.webApi + 'api/app/' + clientId, axiosConfig)
                        .then((response) => {
                            this.appDetail = response.data;
                            console.log(response.data);
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
                }
            },
            save: function(event){
                event.preventDefault();
                this.$refs['appDetailForm'].validate((valid) => {
                    if (valid) {
                        this.$http.post(this.webApi + 'api/app', this.appDetail, this.axiosConfig)
                            .then((response) => {
                                console.log(response.data);
                                this.backPage();
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
                this.backPage();
            },
            backPage(){
                this.$router.go(-1);
            },
        },
        components: {
        },
        watch:{
            '$route' (){
                this.showClientSecret = false;
                this.fetchRecord();
            }
        },
    }
</script>
