(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-779c4200"],{"0756":function(e,t,a){},5899:function(e,t){e.exports="\t\n\v\f\r                　\u2028\u2029\ufeff"},"58a8":function(e,t,a){var r=a("1d80"),s=a("5899"),o="["+s+"]",n=RegExp("^"+o+o+"*"),i=RegExp(o+o+"*$"),l=function(e){return function(t){var a=String(r(t));return 1&e&&(a=a.replace(n,"")),2&e&&(a=a.replace(i,"")),a}};e.exports={start:l(1),end:l(2),trim:l(3)}},7156:function(e,t,a){var r=a("861d"),s=a("d2bb");e.exports=function(e,t,a){var o,n;return s&&"function"==typeof(o=t.constructor)&&o!==a&&r(n=o.prototype)&&n!==a.prototype&&s(e,n),e}},"97f0":function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("div",{staticClass:"row"},[a("div",{staticClass:"col-12"},[a("a",{staticClass:"ssonav",attrs:{href:"#"},on:{click:e.back}},[a("ArrowLeftBold",{staticClass:"icon-2x"}),e._v(e._s(e.appName)+" ")],1)])]),a("hr"),e._m(0),a("hr"),a("form",[a("div",{staticClass:"form-group"},[a("label",{attrs:{for:"username"}},[e._v("Username")]),a("input",{directives:[{name:"model",rawName:"v-model",value:e.appUserRole.username,expression:"appUserRole.username"}],staticClass:"form-control",attrs:{type:"text",id:"username"},domProps:{value:e.appUserRole.username},on:{input:[function(t){t.target.composing||e.$set(e.appUserRole,"username",t.target.value)},e.search]}})]),e._l(e.appUserList,(function(t){return a("div",{key:"appUser"+t.id},[a("a",{attrs:{href:"#"},on:{click:function(a){e.selectAppUser(a,t)}}},[e._v(e._s(t.username+" "+t.displayName))])])})),a("div",{staticClass:"form-group"},[a("label",{attrs:{for:"appRole"}},[e._v("AppRole")]),a("input",{directives:[{name:"model",rawName:"v-model",value:e.appUserRole.appRole,expression:"appUserRole.appRole"}],staticClass:"form-control",attrs:{type:"text",id:"appRole"},domProps:{value:e.appUserRole.appRole},on:{input:function(t){t.target.composing||e.$set(e.appUserRole,"appRole",t.target.value)}}})]),a("div",{staticClass:"row"},[a("div",{staticClass:"col-12"},[a("a",{staticClass:"btn btn-primary",attrs:{href:"#",role:"button"},on:{click:e.save}},[e._v("Save")]),a("a",{staticClass:"btn btn-default",attrs:{href:"#",role:"button"},on:{click:e.back}},[e._v("Cancel")])])]),e._l(e.errors,(function(t,r){return a("div",{key:r,staticClass:"row"},[a("div",{staticClass:"col-12 text-danger"},[e._v(" "+e._s(t)+" ")])])}))],2)])},s=[function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"row"},[a("div",{staticClass:"col-12"},[a("h1",[e._v("App Role")])])])}],o=a("bc3a"),n=a.n(o),i=(a("f9e3"),a("e85b")),l={name:"AppRoleForm",data:function(){return{appId:null,appUserRole:{appId:null,username:null,appRole:null},axiosConfig:{headers:{},data:{}},appUserList:[],errors:[]}},computed:{appName:function(){return this.$route.params.appName}},mounted:function(){var e=this;e.axiosConfig.headers["Accept"]="application/json",e.axiosConfig.headers["Content-Type"]="application/json",e.appId=this.$route.params.appId,e.appUserRole.appId=this.$route.params.appId,n.a.get("api/csrf-token",e.axiosConfig).then((function(t){console.log(t),e.axiosConfig.headers[t.data.csrf_header]=t.data.csrf_token,e.fetchRecord()})).catch((function(e){console.log(e)}))},methods:{fetchRecord:function(){var e=this,t=this.$route.params.id;"new"!==t&&n.a.get("api/role/"+t,e.axiosConfig).then((function(t){console.log(t),e.appUserRole=t.data})).catch((function(e){console.log(e)}))},selectAppUser:function(e,t){e.preventDefault(),this.appUserRole.username=t.username,this.appUserList=[]},search:function(){var e=this;n.a.get("api/appUserSearch?name="+e.appUserRole.username,e.axiosConfig).then((function(t){console.log(t),e.appUserList=t.data.content})).catch((function(e){console.log(e)}))},save:function(e){e.preventDefault();var t=this;t.checkForm(),t.errors.length>0||n.a.post("api/role/",t.appUserRole,t.axiosConfig).then((function(e){console.log(e.data),t.backPage()})).catch((function(e){console.log(e)}))},checkForm:function(){this.errors=[];var e=/^[0-9a-zA-Z]+$/;e.test(this.appUserRole.username)||this.errors.push("username should only contain letter and number"),e.test(this.appUserRole.appRole)||this.errors.push("appRole should only contain letter and number"),""!==this.appUserRole.username&&null!==this.appUserRole.username||this.errors.push("username could not empty"),""!==this.appUserRole.appRole&&null!==this.appUserRole.appRole||this.errors.push("appRole could not empty")},back:function(e){e.preventDefault(),this.backPage()},backPage:function(){this.$router.go(-1)}},components:{ArrowLeftBold:i["a"]},watch:{$route:function(){this.appUserRole={appId:null,username:null,appRole:null},this.fetchRecord()}}},p=l,c=(a("bd24"),a("2877")),u=Object(c["a"])(p,r,s,!1,null,null,null);t["default"]=u.exports},a9e3:function(e,t,a){"use strict";var r=a("83ab"),s=a("da84"),o=a("94ca"),n=a("6eeb"),i=a("5135"),l=a("c6b6"),p=a("7156"),c=a("c04e"),u=a("d039"),f=a("7c73"),d=a("241c").f,h=a("06cf").f,m=a("9bf2").f,v=a("58a8").trim,g="Number",R=s[g],b=R.prototype,C=l(f(b))==g,_=function(e){var t,a,r,s,o,n,i,l,p=c(e,!1);if("string"==typeof p&&p.length>2)if(p=v(p),t=p.charCodeAt(0),43===t||45===t){if(a=p.charCodeAt(2),88===a||120===a)return NaN}else if(48===t){switch(p.charCodeAt(1)){case 66:case 98:r=2,s=49;break;case 79:case 111:r=8,s=55;break;default:return+p}for(o=p.slice(2),n=o.length,i=0;i<n;i++)if(l=o.charCodeAt(i),l<48||l>s)return NaN;return parseInt(o,r)}return+p};if(o(g,!R(" 0o1")||!R("0b1")||R("+0x1"))){for(var U,I=function(e){var t=arguments.length<1?0:e,a=this;return a instanceof I&&(C?u((function(){b.valueOf.call(a)})):l(a)!=g)?p(new R(_(t)),a,I):_(t)},N=r?d(R):"MAX_VALUE,MIN_VALUE,NaN,NEGATIVE_INFINITY,POSITIVE_INFINITY,EPSILON,isFinite,isInteger,isNaN,isSafeInteger,MAX_SAFE_INTEGER,MIN_SAFE_INTEGER,parseFloat,parseInt,isInteger".split(","),w=0;N.length>w;w++)i(R,U=N[w])&&!i(I,U)&&m(I,U,h(R,U));I.prototype=b,b.constructor=I,n(s,g,I)}},bd24:function(e,t,a){"use strict";var r=a("0756"),s=a.n(r);s.a},e85b:function(e,t,a){"use strict";var r=function(e,t){var a=t._c;return a("span",t._g(t._b({staticClass:"material-design-icon arrow-left-bold-icon",class:[t.data.class,t.data.staticClass],attrs:{"aria-hidden":t.props.decorative,"aria-label":t.props.title,role:"img"}},"span",t.data.attrs,!1),t.listeners),[a("svg",{staticClass:"material-design-icon__svg",attrs:{fill:t.props.fillColor,width:t.props.size,height:t.props.size,viewBox:"0 0 24 24"}},[a("path",{attrs:{d:"M20,9V15H12V19.84L4.16,12L12,4.16V9H20Z"}},[a("title",[t._v(t._s(t.props.title))])])])])},s=[],o=(a("a9e3"),{name:"ArrowLeftBoldIcon",props:{title:{type:String,default:"Arrow Left Bold icon"},decorative:{type:Boolean,default:!1},fillColor:{type:String,default:"currentColor"},size:{type:Number,default:24}}}),n=o,i=a("2877"),l=Object(i["a"])(n,r,s,!0,null,null,null);t["a"]=l.exports}}]);
//# sourceMappingURL=chunk-779c4200.3d080737.js.map