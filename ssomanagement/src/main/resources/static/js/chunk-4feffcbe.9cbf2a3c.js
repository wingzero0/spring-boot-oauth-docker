(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4feffcbe"],{"2c8b":function(t,e,a){},"5c62":function(t,e,a){"use strict";var s=function(t,e){var a=e._c;return a("span",e._g(e._b({staticClass:"material-design-icon home-icon",class:[e.data.class,e.data.staticClass],attrs:{"aria-hidden":e.props.decorative,"aria-label":e.props.title,role:"img"}},"span",e.data.attrs,!1),e.listeners),[a("svg",{staticClass:"material-design-icon__svg",attrs:{fill:e.props.fillColor,width:e.props.size,height:e.props.size,viewBox:"0 0 24 24"}},[a("path",{attrs:{d:"M10,20V14H14V20H19V12H22L12,3L2,12H5V20H10Z"}},[a("title",[e._v(e._s(e.props.title))])])])])},o=[],l=(a("a9e3"),{name:"HomeIcon",props:{title:{type:String,default:"Home icon"},decorative:{type:Boolean,default:!1},fillColor:{type:String,default:"currentColor"},size:{type:Number,default:24}}}),i=l,n=a("2877"),r=Object(n["a"])(i,s,o,!0,null,null,null);e["a"]=r.exports},aa81:function(t,e,a){"use strict";a.r(e);var s=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("div",{staticClass:"row"},[a("div",{staticClass:"col-12"},[a("router-link",{staticClass:"ssonav",attrs:{to:{name:"appList"}}},[a("HomeIcon",{staticClass:"icon-2x"}),t._v(t._s(t.appId)+" ")],1)],1)]),a("hr"),a("router-link",{attrs:{to:{name:"appRoleForm",params:{appId:t.$route.params.appId,id:"new"}}}},[a("PlusBoxIcon"),t._v("Add ")],1),t._m(0),t._l(t.appUserRoleList,(function(e,s){return a("div",{key:s},[a("div",{staticClass:"row top-buffer"},[a("div",{staticClass:"col-md-2"},[a("router-link",{staticClass:"btn btn-primary",attrs:{to:{name:"appRoleForm",params:{appId:t.$route.params.appId,id:e.id}},role:"button"}},[t._v(" Edit ")]),a("a",{staticClass:"btn btn-danger",attrs:{href:"#"},on:{click:function(a){t.showPopup(a,e.username,e.appRole,e.id)}}},[t._v("Delete")])],1),a("div",{staticClass:"col-md-5"},[t._v(" "+t._s(e.username)+" ")]),a("div",{staticClass:"col-md-5"},[t._v(" "+t._s(e.appRole)+" ")])])])})),a("div",[a("b-modal",{attrs:{id:"modal-1",title:"BootstrapVue"},scopedSlots:t._u([{key:"modal-footer",fn:function(e){e.ok;var s=e.cancel;return[a("b-button",{attrs:{size:"sm",variant:"default"},on:{click:function(t){return s()}}},[t._v(" Cancel ")]),a("b-button",{attrs:{size:"sm",variant:"danger"},on:{click:t.deleteAppUserRole}},[t._v(" Delete ")])]}}])},[a("p",{staticClass:"my-4"},[t._v(t._s(t.deleteMsg))])])],1)],2)},o=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"row"},[a("div",{staticClass:"col-md-2 table-header"}),a("div",{staticClass:"col-md-5 table-header"},[t._v(" Username ")]),a("div",{staticClass:"col-md-5 table-header"},[t._v(" Role ")])])}],l=a("bc3a"),i=a.n(l),n=(a("f9e3"),a("5c62")),r=a("eb2a"),c={name:"AppRoleList",data:function(){return{appId:null,appUserRoleList:[],axiosConfig:{headers:{},data:{}},deleteMsg:null,deleteId:null}},mounted:function(){var t=this;t.axiosConfig.headers["Accept"]="application/json",t.axiosConfig.headers["Content-Type"]="application/json",t.appId=this.$route.params.appId,i.a.get("api/csrf-token",t.axiosConfig).then((function(e){console.log(e),t.axiosConfig.headers[e.data.csrf_header]=e.data.csrf_token,t.fetchRecord()})).catch((function(t){console.log(t)}))},methods:{fetchRecord:function(){var t=this;i.a.get("api/role?appId="+t.appId,t.axiosConfig).then((function(e){console.log(e),t.appUserRoleList=e.data.content})).catch((function(t){console.log(t)}))},deleteAppUserRole:function(){var t=this;i.a.delete("api/role/"+t.deleteId,t.axiosConfig).then((function(e){console.log("delete return"),console.log(e),t.fetchRecord(),t.$bvModal.hide("modal-1")})).catch((function(t){console.log(t)}))},showPopup:function(t,e,a,s){t.preventDefault(),console.log(t),this.deleteMsg="Are you sure to delete record:"+e+"-"+a,this.deleteId=s,this.$bvModal.show("modal-1")}},components:{HomeIcon:n["a"],PlusBoxIcon:r["a"]},watch:{$route:function(){this.fetchRecord()}}},d=c,p=(a("ecd1"),a("2877")),u=Object(p["a"])(d,s,o,!1,null,null,null);e["default"]=u.exports},eb2a:function(t,e,a){"use strict";var s=function(t,e){var a=e._c;return a("span",e._g(e._b({staticClass:"material-design-icon plus-box-icon",class:[e.data.class,e.data.staticClass],attrs:{"aria-hidden":e.props.decorative,"aria-label":e.props.title,role:"img"}},"span",e.data.attrs,!1),e.listeners),[a("svg",{staticClass:"material-design-icon__svg",attrs:{fill:e.props.fillColor,width:e.props.size,height:e.props.size,viewBox:"0 0 24 24"}},[a("path",{attrs:{d:"M17,13H13V17H11V13H7V11H11V7H13V11H17M19,3H5C3.89,3 3,3.89 3,5V19A2,2 0 0,0 5,21H19A2,2 0 0,0 21,19V5C21,3.89 20.1,3 19,3Z"}},[a("title",[e._v(e._s(e.props.title))])])])])},o=[],l=(a("a9e3"),{name:"PlusBoxIcon",props:{title:{type:String,default:"Plus Box icon"},decorative:{type:Boolean,default:!1},fillColor:{type:String,default:"currentColor"},size:{type:Number,default:24}}}),i=l,n=a("2877"),r=Object(n["a"])(i,s,o,!0,null,null,null);e["a"]=r.exports},ecd1:function(t,e,a){"use strict";var s=a("2c8b"),o=a.n(s);o.a}}]);
//# sourceMappingURL=chunk-4feffcbe.9cbf2a3c.js.map