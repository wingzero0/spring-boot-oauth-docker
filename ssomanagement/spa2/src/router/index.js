import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
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
    path: '/appRoleForm/:appId/:appName/:appRoleId',
    name: 'appRoleForm',
    component: () => import('../views/AppRoleForm.vue'),
    props: true,
  },
  {
    path: '/element',
    name: 'Element',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/Element.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
