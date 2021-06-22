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
    usernameMap: {},
    usernameList: [],
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
    updateUsernameMap(state, data) {
      state.usernameList = data;
      state.usernameMap = [],
      data.forEach(e => {
        state.usernameMap[e.username] = e;
      });
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
            let loginInfoRequest = payload.http.get(webRoot + 'api/loginInfo', initConfig)
              .then(function (response) {
                console.log(response);
                commit('updateLoginInfo', response.data);
              }).catch(function (error) {
                console.log(error);
              });
            let allUsersRequest = payload.http.get(webRoot + 'api/appUsers', initConfig)
              .then(response=>{
                commit('updateUsernameMap', response.data);
              });
            Promise.all([loginInfoRequest, allUsersRequest]).then(()=>{
              resolve();
            }).catch(error=>{
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