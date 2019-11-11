(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-035ef5c0"],{5899:function(e,t){e.exports="\t\n\v\f\r                　\u2028\u2029\ufeff"},"58a8":function(e,t,a){var r=a("1d80"),o=a("5899"),s="["+o+"]",n=RegExp("^"+s+s+"*"),p=RegExp(s+s+"*$"),l=function(e){return function(t){var a=String(r(t));return 1&e&&(a=a.replace(n,"")),2&e&&(a=a.replace(p,"")),a}};e.exports={start:l(1),end:l(2),trim:l(3)}},7156:function(e,t,a){var r=a("861d"),o=a("d2bb");e.exports=function(e,t,a){var s,n;return o&&"function"==typeof(s=t.constructor)&&s!==a&&r(n=s.prototype)&&n!==a.prototype&&o(e,n),e}},"97f0":function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"container"},[a("div",{staticClass:"row"},[a("div",{staticClass:"col-12"},[a("router-link",{attrs:{to:{name:"appRoleList",params:{appId:e.$route.params.appId}}}},[a("ArrowLeft")],1),e._v(" "+e._s(e.appId)+" ")],1)]),a("form",[a("div",{staticClass:"form-group"},[a("label",{attrs:{for:"username"}},[e._v("Username")]),a("input",{directives:[{name:"model",rawName:"v-model",value:e.appUserRole.username,expression:"appUserRole.username"}],staticClass:"form-control",attrs:{type:"text",id:"username"},domProps:{value:e.appUserRole.username},on:{input:function(t){t.target.composing||e.$set(e.appUserRole,"username",t.target.value)}}})]),a("div",{staticClass:"form-group"},[a("label",{attrs:{for:"appRole"}},[e._v("AppRole")]),a("input",{directives:[{name:"model",rawName:"v-model",value:e.appUserRole.appRole,expression:"appUserRole.appRole"}],staticClass:"form-control",attrs:{type:"text",id:"appRole"},domProps:{value:e.appUserRole.appRole},on:{input:function(t){t.target.composing||e.$set(e.appUserRole,"appRole",t.target.value)}}})]),a("div",{staticClass:"row"},[a("div",{staticClass:"col-12"},[a("a",{staticClass:"btn btn-primary",attrs:{href:"#",role:"button"},on:{click:e.save}},[e._v("Save")]),a("router-link",{staticClass:"btn btn-default",attrs:{role:"button",to:{name:"appRoleList",params:{appId:e.$route.params.appId}}}},[e._v(" Cancel ")])],1)]),e._l(e.errors,(function(t,r){return a("div",{key:r,staticClass:"row"},[a("div",{staticClass:"col-12 text-danger"},[e._v(" "+e._s(t)+" ")])])}))],2)])},o=[],s=a("bc3a"),n=a.n(s),p=(a("f9e3"),function(e,t){var a=t._c;return a("span",t._g({staticClass:"material-design-icon arrow-left-icon",class:[t.data.class,t.data.staticClass],attrs:{"aria-hidden":t.props.decorative,"aria-label":t.props.title,role:"img"}},t.listeners),[a("svg",{staticClass:"material-design-icon__svg",attrs:{fill:t.props.fillColor,width:t.props.size,height:t.props.size,viewBox:"0 0 24 24"}},[a("path",{attrs:{d:"M20,11V13H8L13.5,18.5L12.08,19.92L4.16,12L12.08,4.08L13.5,5.5L8,11H20Z"}},[a("title",[t._v(t._s(t.props.title))])])])])}),l=[],i=(a("a9e3"),{name:"ArrowLeftIcon",props:{title:{type:String,default:"Arrow Left icon"},decorative:{type:Boolean,default:!1},fillColor:{type:String,default:"currentColor"},size:{type:Number,default:24}}}),c=i,u=a("2877"),f=Object(u["a"])(c,p,l,!0,null,null,null),d=f.exports,h={name:"AppRoleForm",data:function(){return{appId:null,appUserRole:{appId:null,username:null,appRole:null},axiosConfig:{headers:{},data:{}},errors:[]}},mounted:function(){var e=this;e.axiosConfig.headers["Accept"]="application/json",e.axiosConfig.headers["Content-Type"]="application/json",e.appId=this.$route.params.appId,e.appUserRole.appId=this.$route.params.appId,n.a.get("api/csrf-token",e.axiosConfig).then((function(t){console.log(t),e.axiosConfig.headers[t.data.csrf_header]=t.data.csrf_token})).catch((function(e){console.log(e)})),e.fetchRecord()},methods:{fetchRecord:function(){var e=this,t=this.$route.params.id;"new"!==t&&n.a.get("api/role/?id="+t,e.axiosConfig).then((function(t){console.log(t),e.appUserRole=t.data})).catch((function(e){console.log(e)}))},save:function(e){e.preventDefault();var t=this;t.checkForm(),t.errors.length>0||n.a.post("api/role/",t.appUserRole,t.axiosConfig).then((function(e){console.log(e.data),t.$router.push({name:"appRoleList",params:{appId:t.appId}})})).catch((function(e){console.log(e)}))},checkForm:function(){this.errors=[];var e=/^[0-9a-zA-Z]+$/;e.test(this.appUserRole.username)||this.errors.push("username should only contain letter and number"),e.test(this.appUserRole.appRole)||this.errors.push("appRole should only contain letter and number"),""!==this.appUserRole.username&&null!==this.appUserRole.username||this.errors.push("username could not empty"),""!==this.appUserRole.appRole&&null!==this.appUserRole.appRole||this.errors.push("appRole could not empty")}},components:{ArrowLeft:d},watch:{$route:function(){this.appUserRole={appId:null,username:null,appRole:null},this.fetchRecord()}}},m=h,v=Object(u["a"])(m,r,o,!1,null,null,null);t["default"]=v.exports},a9e3:function(e,t,a){"use strict";var r=a("83ab"),o=a("da84"),s=a("94ca"),n=a("6eeb"),p=a("5135"),l=a("c6b6"),i=a("7156"),c=a("c04e"),u=a("d039"),f=a("7c73"),d=a("241c").f,h=a("06cf").f,m=a("9bf2").f,v=a("58a8").trim,g="Number",R=o[g],I=R.prototype,C=l(f(I))==g,b=function(e){var t,a,r,o,s,n,p,l,i=c(e,!1);if("string"==typeof i&&i.length>2)if(i=v(i),t=i.charCodeAt(0),43===t||45===t){if(a=i.charCodeAt(2),88===a||120===a)return NaN}else if(48===t){switch(i.charCodeAt(1)){case 66:case 98:r=2,o=49;break;case 79:case 111:r=8,o=55;break;default:return+i}for(s=i.slice(2),n=s.length,p=0;p<n;p++)if(l=s.charCodeAt(p),l<48||l>o)return NaN;return parseInt(s,r)}return+i};if(s(g,!R(" 0o1")||!R("0b1")||R("+0x1"))){for(var _,w=function(e){var t=arguments.length<1?0:e,a=this;return a instanceof w&&(C?u((function(){I.valueOf.call(a)})):l(a)!=g)?i(new R(b(t)),a,w):b(t)},N=r?d(R):"MAX_VALUE,MIN_VALUE,NaN,NEGATIVE_INFINITY,POSITIVE_INFINITY,EPSILON,isFinite,isInteger,isNaN,isSafeInteger,MAX_SAFE_INTEGER,MIN_SAFE_INTEGER,parseFloat,parseInt,isInteger".split(","),U=0;N.length>U;U++)p(R,_=N[U])&&!p(w,_)&&m(w,_,h(R,_));w.prototype=I,I.constructor=w,n(o,g,w)}}}]);
//# sourceMappingURL=chunk-035ef5c0.4c7129f2.js.map