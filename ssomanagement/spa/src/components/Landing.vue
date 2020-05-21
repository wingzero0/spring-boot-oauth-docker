<template>
    <div>
        <div>
            <router-link class="ssonav" v-bind:to="{ name: 'appList' }">
                App Management
            </router-link>
        </div>
        <div v-if="isAdmin()">
            <router-link class="ssonav" v-bind:to="{ name: 'appUserList' }">
                User Management
            </router-link>
        </div>
    </div>
</template>

<script>
    import {isAdminCheck} from '@/utils/utilsFunction.js';
    export default {
        name: "Landing",
        data: function(){
            return {
                axiosConfig: {},
                loginInfo: {
                    name:null,
                    grantedAuthorities:[],
                },
            };
        },
        mounted: function(){
            this.init();
        },
        methods: {
            init(){
                this.axiosConfig = this.$store.state.axiosConfig;
                this.loginInfo = this.$store.state.loginInfo;
            },
            isAdmin(){
                return isAdminCheck(this.loginInfo.grantedAuthorities);
            },
        },
    }
</script>

<style scoped>

</style>