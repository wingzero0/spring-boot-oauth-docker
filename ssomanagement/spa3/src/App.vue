<template>
  <div id="app">
    <el-container>
      <el-aside width="200px">
        <el-radio-group v-model="isCollapse" style="margin-bottom: 20px;">
          <el-radio-button :label="false">展开</el-radio-button>
          <el-radio-button :label="true">收起</el-radio-button>
        </el-radio-group>
        <el-menu default-active="1" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" :collapse="isCollapse"  v-on:select="goto">
          <el-menu-item :index="menuLink.routerObj.name" v-for="(menuLink, menuIndex) in menuLinks" v-bind:key="menuIndex">
            <i class="el-icon-setting"></i>
            <span slot="title">
              {{ menuLink.displayName }}
            </span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-container>
        <router-view/>
      </el-container>
    </el-container>
  </div>
</template>

<style>
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
  }
</style>

<script>
  export default {
    data() {
      return {
        isCollapse: true,
        menuLinks: [
          {displayName: 'SSO Management', routerObj: { name: 'Home' }},
          {displayName: 'App Management', routerObj: { name: 'About' }},
          {displayName: 'User Management', routerObj: { name: 'Element' }},
        ],
        menuLinkMap: [],
      };
    },
    mounted(){
      this.menuLinks.forEach(element => {
        this.menuLinkMap[element.routerObj.name] = element;
      });
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      goto(index){
        console.log("path: " + index);
        this.$router.push(this.menuLinkMap[index].routerObj);
      },
    }
  }
</script>
