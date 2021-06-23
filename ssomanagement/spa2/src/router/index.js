import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/appManagement',
    name: 'AppManagement',
    component: () => import(/* webpackChunkName: "about" */ '../views/AppManagement.vue')
  },
  {
    path: '/appDetailForm/:clientId',
    name: 'appDetailForm',
    component: () => import(/* webpackChunkName: "about" */ '../views/AppDetailForm.vue'),
  },
  {
    path: '/appRoleManagement/:appId/:appName',
    name: 'appRoleManagement',
    component: () => import('../views/AppRoleManagement.vue'),
    props: true,
  },
  {
    path: '/userManagement',
    name: 'userManagement',
    component: () => import(/* webpackChunkName: "about" */ '../views/UserManagement.vue')
  },
  {
    path: '/userSelfService',
    alias: '/',
    name: 'userSelfService',
    component: () => import('../views/UserSelfService.vue')
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
