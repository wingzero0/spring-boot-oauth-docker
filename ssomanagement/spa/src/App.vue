<template>
  <div id="app">
    <div class="container">
      <nav id="nav" class="navbar navbar-expand-lg navbar-light bg-light">
        <router-link class="navbar-brand" href="#" v-bind:to="{ name: 'landing' }">SSO Management</router-link>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item">
              <router-link class="nav-link" href="#" v-bind:to="{ name: 'landing' }">#</router-link>
            </li>
          </ul>
          <div>
            <a href="logoutPage" class="btn btn-outline-success my-2 my-sm-0">登出</a>
          </div>
        </div>
      </nav>
      <hr/>
      <router-view/>
    </div>
  </div>
</template>

<style>
    .top-buffer{
        margin-top: 1.1em;
    }
    .ssonav{
        font-size: 2em;
    }
</style>
