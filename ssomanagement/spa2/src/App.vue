<template>
  <div id="app">
    <el-container>
      <el-aside v-bind:width="asideWidth">
        <el-menu default-active="1" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" :collapse="isCollapse"  v-on:select="goto">
          <el-menu-item index="MENU"
            class="top-menu-item"
          >
            <i class="el-icon-menu"></i>
            <span slot="title">
              MENU
            </span>
          </el-menu-item>
          <el-menu-item :index="menuLink.routerObj.name"
            v-for="(menuLink, menuIndex) in menuFitleredLinks"
            v-bind:key="menuIndex"
          >
            <i :class="menuLink.iconClass"></i>
            <span slot="title">
              {{ menuLink.displayName }}
            </span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-container>
        <div class="right-side">
          <router-view/>
        </div>
      </el-container>
    </el-container>
  </div>
</template>

<style>
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 800px;
  }
  .el-menu-vertical-demo.el-menu--collapse {
    /* width: 200px; */
    min-height: 800px;
  }
  .right-side{
    padding-left: 10px;
  }
            
</style>

<script>
  export default {
    data() {
      return {
        isCollapse: false,
        menuLinks: [
          {displayName: 'App Management', iconClass:"el-icon-mobile-phone", routerObj: { name: 'AppManagement' }, permission: "ROLE_USER",},
          {displayName: 'User Management', iconClass:"el-icon-user-solid", routerObj: { name: 'userManagement' }, permission: "ROLE_ADMIN",},
          {displayName: 'User Self Service', iconClass:"el-icon-user-solid", routerObj: { name: 'userSelfService' }, permission: null,},
        ],
        menuFitleredLinks: [],
        menuLinkMap: [],
        loginInfo:{
          grantedAuthorities: [],
        },
      };
    },
    mounted(){
      if (this.$store.state.fetched === false){
        this.$store.dispatch('getToken', { 'http': this.$http }).then(() => {
          this.loginInfo = this.$store.state.loginInfo;
          this.menuLinks.forEach(element => {
            this.menuLinkMap[element.routerObj.name] = element;
          });
          this.menuFitleredLinks = this.menuLinks.filter((menuLink)=>{
            if (menuLink.permission == null) {
              return true;
            } else {
              let matchedAuthority = this.loginInfo.grantedAuthorities.filter(grantedAuthority=>{
                return grantedAuthority.authority == menuLink.permission;
              });
              return matchedAuthority.length > 0;
            }
          });
        });
      }
    },
    computed: {
      asideWidth: function () {
        return this.isCollapse ? '65px' : '201px';
      }
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      toggleMenu(){
        this.isCollapse = !this.isCollapse;
      },
      goto(index){
        console.log("path: " + index);
        if (index == "MENU"){
          this.toggleMenu();
        } else {
          this.$router.push(this.menuLinkMap[index].routerObj).catch(err => {
            if (
              err.name !== 'NavigationDuplicated' &&
              !err.message.includes('Avoided redundant navigation to current location')
            ) {
              throw err;
            }
          });
        }
      },
    }
  }
</script>
