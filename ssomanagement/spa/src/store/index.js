import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios';

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    axiosConfig : {
      headers : {},
      data:{},
    },
    loginInfo: {
      name: null,
      grantedAuthorities: [],
    },
    fetched : false,
  },
  mutations: {
    updateAxiosConfig(state, data){
      console.log("in store updateAxiosConfig");
      console.log(data);
      state.axiosConfig = data;
    },
    updateLoginInfo(state, data){
      console.log("in store updateLoginInfo");
      console.log(data);
      state.loginInfo = data;
      state.fetched = true;
    },
  },
  actions: {
    getToken({commit}){
      return new Promise((resolve, reject) => {
        let initConfig = {
          headers : {},
          data:{},
        };
        initConfig.headers['Accept'] = 'application/json';
        initConfig.headers['Content-Type'] = 'application/json';
        axios.get('api/csrf-token', initConfig)
          .then(function (response) {
            console.log(response);
            initConfig.headers[response.data.csrf_header] = response.data.csrf_token;
            commit('updateAxiosConfig', initConfig);
            axios.get('api/loginInfo', initConfig)
              .then(function (response){
                console.log(response);
                commit('updateLoginInfo', response.data);
                resolve();
              }).catch(function (error) {
                console.log(error);
                reject(error);
              });
          })
          .catch(function (error) {
              console.log(error);
              reject(error);
          });
        });
    },
  },
  modules: {
  }
})