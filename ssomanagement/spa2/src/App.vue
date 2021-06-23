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
          <el-menu-item :index="menuLink.routerObj.name" v-for="(menuLink, menuIndex) in menuLinks" v-bind:key="menuIndex">
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
          {displayName: 'Home Page', iconClass:"el-icon-s-home", routerObj: { name: 'Home' }},
          {displayName: 'App Management', iconClass:"el-icon-mobile-phone", routerObj: { name: 'AppManagement' }},
          {displayName: 'User Management', iconClass:"el-icon-user-solid", routerObj: { name: 'userManagement' }},
        ],
        menuLinkMap: [],
      };
    },
    mounted(){
      this.menuLinks.forEach(element => {
        this.menuLinkMap[element.routerObj.name] = element;
      });
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
