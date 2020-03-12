<template>
    <div>
        {{getToken()}}
    </div>
</template>
<style>
  .material-design-icon.icon-2x {
    height: 2em;
    width: 2em;
  }
  .material-design-icon.icon-2x > .material-design-icon__svg {
    height: 2em;
    width: 2em;
  }
</style>
<script>
    import 'bootstrap/dist/css/bootstrap.css';

    export default {
        name: 'AppRoleForm',
        data: function(){
            return {
                appId : null,
            }
        },
        methods: {
            getToken: function () {
                return this.$store.state.axiosConfig.headers['X-CSRF-TOKEN'];
            }
        },
    }
</script>
