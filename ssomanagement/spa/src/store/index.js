import Vue from 'vue';
import Vuex from 'vuex';
import {getCSRFToken} from '@/utils/utilsFunction.js';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    axiosConfig : {
      headers : {},
      data:{},
    },
    fetched : false,
  },
  mutations: {
    udpateAxiosConfig(state, data){
      state.axiosConfig = data;
      state.fetched = true;
    },
  },
  actions: {
    getToken({commit}){
      return new Promise((resolve, reject) => {
          getCSRFToken()
            .then(function (data) {
              console.log('get csrf token from store');
              commit('udpateAxiosConfig', data);
              resolve();
            }).catch((data)=>{
              reject(data);
            });
        });
    },
  },
  getters: {

  },
});