import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import axios from 'axios'

Vue.prototype.$http = axios;
Vue.prototype.$http.defaults.withCredentials = true;
Vue.prototype.$http.interceptors.response.use(
  response => {
    return response;
  },
  error => {
    if (typeof error.response == "undefined") {
      alert("session timeout, the request not perform, please retry after re-login");
      window.location = process.env.BASE_URL;
    }
    return Promise.reject(error);
  }
);


router.beforeEach((to, from, next) => {
  if (store.state.fetched === false) {
    store.dispatch('getToken', { 'http': axios }).then(() => {
      next();
    });
  } else {
    next();
  }
});

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
