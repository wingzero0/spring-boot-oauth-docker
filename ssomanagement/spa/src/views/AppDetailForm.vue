<template>
    <div>
        <form>
            <div class="form-group">
                <label for="clientId">clientId</label>
                <input type="text" class="form-control" id="clientId" v-model="appDetail.clientId"/>
            </div>
            <div class="form-group">
                <label for="resourceIds">resourceIds</label>
                <input type="text" class="form-control" id="resourceIds" v-model="appDetail.resourceIds"/>
            </div>
            <div class="form-group">
                <label for="clientSecret">clientSecret</label>
                <input type="password" class="form-control" id="clientSecret" v-model="appDetail.clientSecret" disabled/>
                <a href="#regen" v-on:click="(event) => {generatePassword(event, 32);}">generate</a>
            </div>
            <div class="form-group" v-show="showClientSecret">
                <div>
                    generated: {{appDetail.clientSecret}}
                </div>
            </div>
            <div class="form-group">
                <label for="scope">scope</label>
                <input type="text" class="form-control" id="scope" v-model="appDetail.scope"/>
            </div>
            <div class="form-group">
                <label for="authorizedGrantTypes">authorizedGrantTypes</label>
                <input type="text" class="form-control" id="authorizedGrantTypes" v-model="appDetail.authorizedGrantTypes"/>
            </div>
            <div class="form-group">
                <label for="webServerRedirectUri">webServerRedirectUri</label>
                <input type="text" class="form-control" id="webServerRedirectUri" v-model="appDetail.webServerRedirectUri"/>
            </div>
            <div class="form-group">
                <label for="authorities">authorities</label>
                <input type="text" class="form-control" id="authorities" v-model="appDetail.authorities"/>
            </div>
            <div class="form-group">
                <label for="accessTokenValidity">accessTokenValidity</label>
                <input type="number" class="form-control" id="accessTokenValidity" v-model="appDetail.accessTokenValidity"/>
            </div>
            <div class="form-group">
                <label for="refreshTokenValidity">refreshTokenValidity</label>
                <input type="number" class="form-control" id="refreshTokenValidity" v-model="appDetail.refreshTokenValidity"/>
            </div>
            <div class="form-group">
                <label for="additionalInformation">additionalInformation</label>
                <input type="text" class="form-control" id="additionalInformation" v-model="appDetail.additionalInformation"/>
            </div>
            <div class="form-group">
                <label for="autoapprove">autoapprove</label>
                <input type="text" class="form-control" id="autoapprove" v-model="appDetail.autoapprove"/>
            </div>
            <a href="#save" v-on:click="save">save</a>
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
    import 'bootstrap/dist/css/bootstrap.css';
    import axios from 'axios';

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
                    accessTokenValidity: null,
                    refreshTokenValidity: null,
                    additionalInformation: null,
                    autoapprove: null,
                },
                showClientSecret:false,
            };
        },
        mounted: function(){
            this.fetchRecord();
        },
        methods: {
            getToken: function () {
                return this.$store.state.axiosConfig.headers['X-CSRF-TOKEN'];
            },
            checkForm : function(){
                this.errors = [];
            },
            generatePassword(event, length){
                event.preventDefault();
                var result           = '';
                var characters       = 'ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjkmnopqrstuvwxyz23456789';
                var charactersLength = characters.length;
                for ( var i = 0; i < length; i++ ) {
                  result += characters.charAt(Math.floor(Math.random() * charactersLength));
                }
                this.appDetail.clientSecret = result;
                this.showClientSecret = true;
            },
            fetchRecord: function(){
                let clientId = this.$route.params.clientId;
                let axiosConfig = this.$store.state.axiosConfig;
                if (clientId !== 'new'){
                    axios.get('api/app/' + clientId, axiosConfig)
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
                let self = this;
                self.checkForm();
                if (self.errors.length > 0){
                    return;
                }
                let axiosConfig = self.$store.state.axiosConfig;
                axios.post('api/app', self.appDetail, axiosConfig)
                    .then(function (response) {
                        console.log(response.data);
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },
        },
        watch:{
            '$route' (){
                this.showClientSecret = false;
                this.fetchRecord();
            }
        },
    }
</script>
