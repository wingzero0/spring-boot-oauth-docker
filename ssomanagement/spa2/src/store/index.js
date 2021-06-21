import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
const webRoot = process.env.VUE_APP_WEB_ROOT;

export default new Vuex.Store({
  state: {
    axiosConfig: {
      headers: {},
      data: {},
    },
    loginInfo: {
      name: null,
      grantedAuthorities: [],
    },
    fetched: false,
  },
  mutations: {
    updateAxiosConfig(state, data) {
      console.log("in store updateAxiosConfig");
      console.log(data);
      state.axiosConfig = data;
    },
    updateLoginInfo(state, data) {
      console.log("in store updateLoginInfo");
      console.log(data);
      state.loginInfo = data;
      state.fetched = true;
    },
  },
  actions: {
    getToken({ commit }, payload) {
      return new Promise((resolve, reject) => {
        let initConfig = {
          headers: {},
          data: {},
        };
        initConfig.headers['Accept'] = 'application/json';
        initConfig.headers['Content-Type'] = 'application/json';
        payload.http.get(webRoot + 'api/csrf-token', initConfig)
          .then(function (response) {
            console.log(response);
            initConfig.headers[response.data.csrf_header] = response.data.csrf_token;
            commit('updateAxiosConfig', initConfig);
            payload.http.get(webRoot + 'api/loginInfo', initConfig)
              .then(function (response) {
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