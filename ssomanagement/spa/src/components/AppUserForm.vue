<template>
    <div>
        <div class="row">
            <div class="col-12">
                <router-link class="ssonav" v-bind:to="{ 'name': 'appUserList' }">
                    <ArrowLeftBold class="icon-2x"></ArrowLeftBold>{{ appId }}
                </router-link>
            </div>
        </div>
        <form>
            <div class="form-group">
                <label for="username">帳號</label>
                <input type="text" class="form-control" id="username" v-model="appUser.username"/>
            </div>
            <div class="form-group">
                <label for="displayName">顯示名稱</label>
                <input type="text" class="form-control" id="displayName" v-model="appUser.displayName"/>
            </div>
            <div class="form-group">
                <label for="password">密碼</label>
                <input type="password" class="form-control" id="password" v-model="appUser.password"/>
            </div>
            <div class="form-group">
                <label for="password">密碼 (重複)</label>
                <input type="password" class="form-control" id="passwordAgain" v-model="appUser.passwordAgain"/>
            </div>
            <div class="form-group">
                <label for="email">email</label>
                <input type="email" class="form-control" id="email" v-model="appUser.email"/>
            </div>
            <div class="form-group">
                <label for="isActive">使用狀態</label>
                <select class="form-control" id="isActive" v-model="appUser.isActive">
                    <option value="Y">啟用</option>
                    <option value="N">停用</option>
                </select>
            </div>
            <div class="row">
                <div class="col-12">
                    <a class="btn btn-primary" href="#" role="button" v-on:click="save">Save</a>
                    <router-link class="btn btn-default" role="button"
                                 v-bind:to="{ 'name': 'appUserList' }">
                        Cancel
                    </router-link>
                </div>
            </div>
            <div class="row" v-bind:key="index" v-for="(error, index) in errors">
                <div class="col-12 text-danger">
                    {{ error }}
                </div>
            </div>
        </form>

    </div>
</template>
<style>
    .material-design-icon.icon-2x {
        height: 2em;
        width: 2em;
    }
    .material-design-icon.icon-2x > .material-design-icon__svg {
        height: 2em;
        width: 2em;
    }
</style>
<script>
    import axios from 'axios';
    import {getCSRFToken} from '@/utils/utilsFunction.js';
    import 'bootstrap/dist/css/bootstrap.css';
    import ArrowLeftBold from 'vue-material-design-icons/ArrowLeftBold.vue';

    export default {
        name: 'AppRoleForm',
        data: function(){
            return{
                appId : null,
                appUser:{
                    username:null,
                    displayName:null,
                    password:null,
                    passwordAgain:null,
                    email:null,
                },
                axiosConfig: {
                    headers : {
                    },
                    data:{
                    }
                },
                errors:[],
            }
        },
        mounted: function(){
            let self = this;
            getCSRFToken()
                .then(function (response) {
                    self.axiosConfig = response;
                    self.fetchRecord();
                });
        },
        methods: {
            fetchRecord: function () {
                let self = this;
                let id = this.$route.params.id;
                if (id !== 'new'){
                    axios.get('api/appUser/' + id, self.axiosConfig)
                        .then(function (response) {
                            console.log(response);
                            self.appUser = response.data;
                            self.appUser.password = null;
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
                }
            },
            save: function(event){
                event.preventDefault();
                let self = this;
                self.checkForm();
                if (self.errors.length > 0){
                    return;
                }
                axios.post('api/appUser', self.appUser, self.axiosConfig)
                    .then(function (response) {
                        console.log(response.data);
                        self.$router.push({ name: 'appUserList'});
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },
            checkForm : function(){
                this.errors = [];
                if (this.appUser.username === "" || this.appUser.username === null) {
                    this.errors.push('username could not empty');
                }
                if (this.appUser.displayName === "" || this.appUser.displayName === null) {
                    this.errors.push('displayName could not empty');
                }
                // if (this.appUser.email === "" || this.appUser.email === null) {
                //     this.errors.push('email could not empty');
                // }
                if (this.appUser.password !== null && this.appUser.password !== "") {
                    if (this.appUser.password !== this.appUser.passwordAgain){
                        this.errors.push('password not match');
                    }
                }
                let id = this.$route.params.id;
                if (id === 'new' && (this.appUser.password === null || this.appUser.password === "") ){
                    this.errors.push('password cannot be empty');
                }
            }
        },
        components: {
            ArrowLeftBold,
        },
        watch:{
            '$route' (){
                this.appUserRole = {
                    appId:null,
                    username:null,
                    appRole:null,
                };
                this.fetchRecord();
            }
        },
    }
</script>
