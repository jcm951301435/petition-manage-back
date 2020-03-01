(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-76ee2db1"],{48976:function(t,e,a){"use strict";var n=a("b775");function i(t){return Object(n["a"])({url:"/petitionContradiction/list",method:"post",params:t})}function r(t){return Object(n["a"])({url:"/petitionContradiction/create",method:"post",data:t})}function l(t){return Object(n["a"])({url:"/petitionContradiction/update",method:"post",data:t})}function s(t){return Object(n["a"])({url:"/petitionContradiction/delete/"+t,method:"post"})}e["a"]={fetchList:i,create:r,update:l,remove:s}},b7b3:function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("el-card",{staticClass:"filter-container",attrs:{shadow:"never"}},[a("div",[a("i",{staticClass:"el-icon-search"}),a("span",[t._v("查询条件")]),a("el-button",{staticStyle:{float:"right","margin-right":"15px"},attrs:{size:"small"},on:{click:function(e){return t.handleResetSearch()}}},[t._v("\n        重置\n      ")]),a("el-button",{staticStyle:{float:"right"},attrs:{disabled:!t.$checkMenuShow("contradiction:list"),type:"primary",size:"small"},on:{click:function(e){return t.handleSearchList()}}},[t._v("\n        确认查询\n      ")])],1),a("div",{staticStyle:{"margin-top":"15px"}},[a("el-form",{ref:"queryForm",attrs:{inline:!0,model:t.queryParams,size:"small","label-width":"140px"}},[a("el-form-item",{attrs:{label:"姓名："}},[a("el-input",{staticClass:"input-width",attrs:{placeholder:"姓名"},model:{value:t.queryParams.applyName,callback:function(e){t.$set(t.queryParams,"applyName",e)},expression:"queryParams.applyName"}})],1),a("el-form-item",{attrs:{label:"责任单位："}},[a("el-input",{staticClass:"input-width",attrs:{placeholder:"责任单位"},model:{value:t.queryParams.responsibleCompany,callback:function(e){t.$set(t.queryParams,"responsibleCompany",e)},expression:"queryParams.responsibleCompany"}})],1)],1)],1)]),a("el-card",{staticClass:"operate-container",attrs:{shadow:"never"}},[a("i",{staticClass:"el-icon-tickets"}),a("span",[t._v("信访列表")]),a("el-button",{staticClass:"btn-add",staticStyle:{"margin-left":"20px"},attrs:{disabled:!t.$checkMenuShow("contradiction:add"),type:"primary"},on:{click:function(e){return t.handleAdd()}}},[t._v("\n      添加\n    ")])],1),a("div",{staticClass:"table-container"},[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.listLoading,expression:"listLoading"}],ref:"contradictionTable",staticStyle:{width:"100%"},attrs:{data:t.list,border:"",stripe:"","highlight-current-row":"","row-key":"id"}},[a("el-table-column",{attrs:{type:"selection",width:"60",align:"center"}}),a("el-table-column",{attrs:{type:"index",width:"50"}}),a("el-table-column",{attrs:{label:"姓名",width:"150"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-link",{attrs:{type:"primary"},on:{click:function(a){return t.handleToDetail(e.$index,e.row)}}},[t._v("\n            "+t._s(e.row.applyName)+"\n          ")])]}}])}),a("el-table-column",{attrs:{prop:"companyName",label:"责任单位",width:"160"}}),a("el-table-column",{attrs:{prop:"resolveLevelName",label:"易化解程度",width:"160"}}),a("el-table-column",{attrs:{prop:"purposeName",label:"目的分类",width:"160"}}),a("el-table-column",{attrs:{prop:"insertOn",label:"创建日期",width:"160"}}),a("el-table-column",{attrs:{prop:"insertByName",label:"创建人",width:"160"}}),a("el-table-column",{attrs:{label:"操作",width:"250"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{type:"primary",size:"small"},on:{click:function(a){return t.handleToDetail(e.$index,e.row)}}},[t._v("\n            查看\n          ")]),a("el-button",{attrs:{disabled:!t.$checkMenuShow("contradiction:update"),type:"success",size:"small"},on:{click:function(a){return t.handleUpdate(e.$index,e.row)}}},[t._v("\n            修改\n          ")]),a("el-button",{attrs:{disabled:!t.$checkMenuShow("contradiction:delete"),type:"danger",size:"small"},on:{click:function(a){return t.handleDelete(e.$index,e.row)}}},[t._v("\n            删除\n          ")])]}}])})],1)],1),a("div",{staticClass:"pagination-container"},[a("el-pagination",{attrs:{background:"",layout:"total, prev, pager, next, jumper","page-size":t.queryParams.pageObj.pageSize,"current-page":t.queryParams.pageObj.pageNum,total:t.listTotal},on:{"update:currentPage":function(e){return t.$set(t.queryParams.pageObj,"pageNum",e)},"update:current-page":function(e){return t.$set(t.queryParams.pageObj,"pageNum",e)},"current-change":t.handlePageCurrentChange}})],1)],1)},i=[],r=a("48976"),l={applyName:"",responsibleCompany:"",pageObj:{pageNum:1,pageSize:10}},s={components:{},data:function(){return{queryParams:Object.assign({},l),listLoading:!1,list:[],listTotal:null}},computed:{routerKey:function(){return this.$route.path},queryParamsTrans:function(){var t={},e=this.queryParams,a=e.applyName,n=e.responsibleCompany,i=e.pageObj;return a&&(t.applyName=a),n&&(t.responsibleCompany=n),t.pageNum=i.pageNum,t.pageSize=i.pageSize,t}},created:function(){this.getList()},methods:{handleSearchList:function(){this.getList()},handleResetSearch:function(){this.queryParams=Object.assign({},l)},handleToDetail:function(t,e){this.$router.push({path:"/petition/contradictionDetail",query:{id:e.id}})},handleUpdate:function(t,e){this.$router.push({path:"/petition/contradictionUpdate",query:{id:e.id}})},handleDelete:function(t,e){var a=this;this.$confirm("是否要删除此人员?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){r["a"].remove(e.id).then((function(t){a.$message({type:"success",message:"删除成功!"}),a.getList()}))}))},handleAdd:function(){this.$router.push({path:"/petition/contradictionAdd"})},getList:function(){var t=this;this.listLoading=!0,r["a"].fetchList(this.queryParamsTrans).then((function(e){t.listLoading=!1;var a=e.data,n=a.list,i=a.total;t.list=n,t.listTotal=i})).catch((function(){t.listLoading=!1}))},handlePageCurrentChange:function(t){this.queryParams.pageNum=t,this.getList()}}},o=s,c=a("2877"),u=Object(c["a"])(o,n,i,!1,null,"ca2746d6",null);e["default"]=u.exports}}]);