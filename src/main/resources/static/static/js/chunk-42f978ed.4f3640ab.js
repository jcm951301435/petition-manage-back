(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-42f978ed"],{"215e":function(t,e,o){"use strict";var i=o("5f44"),n=o.n(i);n.a},48976:function(t,e,o){"use strict";var i=o("b775");function n(t){return Object(i["a"])({url:"/petitionContradiction/list",method:"post",params:t})}function r(t){return Object(i["a"])({url:"/petitionContradiction/create",method:"post",data:t})}function a(t){return Object(i["a"])({url:"/petitionContradiction/update",method:"post",data:t})}function l(t){return Object(i["a"])({url:"/petitionContradiction/delete/"+t,method:"post"})}e["a"]={fetchList:n,create:r,update:a,remove:l}},"5f44":function(t,e,o){},"62e4":function(t,e,o){"use strict";var i=o("b775");function n(t){return Object(i["a"])({url:"/sysList/list",method:"post",params:t})}function r(t){return Object(i["a"])({url:"/sysList/create",method:"post",data:t})}function a(t){return Object(i["a"])({url:"/sysList/update",method:"post",data:t})}function l(t){return Object(i["a"])({url:"/sysList/delete/"+t,method:"post"})}e["a"]={fetchList:n,create:r,update:a,remove:l}},"73fc":function(t,e,o){"use strict";o.r(e);var i=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}]},[o("el-row",{attrs:{gutter:10}},[o("el-col",{attrs:{span:16}},[o("el-row",[o("el-form",{ref:"contradictionForm",attrs:{"label-position":"right",model:t.contradictionForm,rules:t.rules,size:"medium","label-width":"100px",disabled:t.isQuery}},[o("el-col",{attrs:{span:24}},[o("el-form-item",{attrs:{"label-width":"150px",label:"突出信访矛盾类别",prop:"contradictionTypes"}},[o("el-checkbox-group",{model:{value:t.contradictionForm.contradictionTypes,callback:function(e){t.$set(t.contradictionForm,"contradictionTypes",e)},expression:"contradictionForm.contradictionTypes"}},t._l(t.contradictionTypeOptions,(function(e){return o("el-checkbox",{key:e.listKey,attrs:{label:e.listKey}},[t._v("\n                  "+t._s(e.listValue)+"\n                ")])})),1)],1)],1),o("el-tag",[t._v("\n            基本信息\n          ")]),o("el-card",[o("el-col",{attrs:{span:8}},[o("el-form-item",{attrs:{label:"姓名",prop:"applyName"}},[o("el-input",{style:{width:"100%"},attrs:{placeholder:"请输入姓名",clearable:""},model:{value:t.contradictionForm.applyName,callback:function(e){t.$set(t.contradictionForm,"applyName",e)},expression:"contradictionForm.applyName"}})],1)],1),o("el-col",{attrs:{span:8}},[o("el-form-item",{attrs:{label:"性别",prop:"applySex"}},[o("el-radio-group",{model:{value:t.contradictionForm.applySex,callback:function(e){t.$set(t.contradictionForm,"applySex",e)},expression:"contradictionForm.applySex"}},[o("el-radio",{attrs:{label:"1"}},[t._v("\n                    男\n                  ")]),o("el-radio",{attrs:{label:"0"}},[t._v("\n                    女\n                  ")])],1)],1)],1),o("el-col",{attrs:{span:8}},[o("el-form-item",{attrs:{label:"出生年月",prop:"applyBirth"}},[o("el-date-picker",{attrs:{type:"month",placeholder:"请输入出生年月"},model:{value:t.contradictionForm.applyBirth,callback:function(e){t.$set(t.contradictionForm,"applyBirth",e)},expression:"contradictionForm.applyBirth"}})],1)],1),o("el-col",{attrs:{span:8}},[o("el-form-item",{attrs:{label:"信访类型",prop:"petitionTypes"}},[o("el-checkbox-group",{model:{value:t.contradictionForm.petitionTypes,callback:function(e){t.$set(t.contradictionForm,"petitionTypes",e)},expression:"contradictionForm.petitionTypes"}},t._l(t.petitionTypeOptions,(function(e){return o("el-checkbox",{key:e.listKey,attrs:{label:e.listKey}},[t._v("\n                    "+t._s(e.listValue)+"\n                  ")])})),1)],1)],1),o("el-col",{attrs:{span:8}},[o("el-form-item",{attrs:{label:"政治面貌",prop:"applyPoliticalStatus"}},[o("el-select",{attrs:{placeholder:"请选择"},model:{value:t.contradictionForm.applyPoliticalStatus,callback:function(e){t.$set(t.contradictionForm,"applyPoliticalStatus",e)},expression:"contradictionForm.applyPoliticalStatus"}},t._l(t.applyPoliticalStatusOptions,(function(t){return o("el-option",{key:t.id,attrs:{label:t.listValue,value:t.listKey}})})),1)],1)],1),o("el-col",{attrs:{span:8}},[o("el-form-item",{attrs:{label:"目的分类",prop:"purposeType"}},[o("el-select",{attrs:{placeholder:"请选择"},model:{value:t.contradictionForm.purposeType,callback:function(e){t.$set(t.contradictionForm,"purposeType",e)},expression:"contradictionForm.purposeType"}},t._l(t.purposeTypeOptions,(function(t){return o("el-option",{key:t.id,attrs:{label:t.listValue,value:t.listKey}})})),1)],1)],1),o("el-col",{attrs:{span:10}},[o("el-form-item",{attrs:{label:"身份证号",prop:"applyIdCard"}},[o("el-input",{style:{width:"100%"},attrs:{placeholder:"请输入身份证号",clearable:""},model:{value:t.contradictionForm.applyIdCard,callback:function(e){t.$set(t.contradictionForm,"applyIdCard",e)},expression:"contradictionForm.applyIdCard"}})],1)],1),o("el-col",{attrs:{span:12}},[o("el-form-item",{attrs:{label:"常住地址",prop:"applyAddress"}},[o("el-input",{style:{width:"100%"},attrs:{placeholder:"请输入常住地址",clearable:""},model:{value:t.contradictionForm.applyAddress,callback:function(e){t.$set(t.contradictionForm,"applyAddress",e)},expression:"contradictionForm.applyAddress"}})],1)],1),o("el-col",{attrs:{span:4}},[o("el-form-item",{attrs:{label:"核查终结",prop:"checkType"}},[o("el-switch",{model:{value:t.contradictionForm.checkType,callback:function(e){t.$set(t.contradictionForm,"checkType",e)},expression:"contradictionForm.checkType"}})],1)],1),o("el-col",{attrs:{span:8}},[o("el-form-item",{attrs:{label:"所属派出所",prop:"applyPoliceStation"}},[o("el-input",{style:{width:"100%"},attrs:{placeholder:"请输入所属派出所",clearable:""},model:{value:t.contradictionForm.applyPoliceStation,callback:function(e){t.$set(t.contradictionForm,"applyPoliceStation",e)},expression:"contradictionForm.applyPoliceStation"}})],1)],1),o("el-col",{attrs:{span:8}},[o("el-form-item",{attrs:{label:"所属街道",prop:"applyStreet"}},[o("el-input",{style:{width:"100%"},attrs:{placeholder:"请输入所属街道",clearable:""},model:{value:t.contradictionForm.applyStreet,callback:function(e){t.$set(t.contradictionForm,"applyStreet",e)},expression:"contradictionForm.applyStreet"}})],1)],1)],1),o("el-divider"),o("el-tag",{attrs:{type:"success"}},[t._v("\n            责任信息\n          ")]),o("el-card",[o("el-col",{attrs:{span:8}},[o("el-form-item",{attrs:{label:"责任单位",prop:"companyId"}},[o("el-select",{attrs:{filterable:"",placeholder:"请选择"},model:{value:t.contradictionForm.companyId,callback:function(e){t.$set(t.contradictionForm,"companyId",e)},expression:"contradictionForm.companyId"}},t._l(t.companyOptions,(function(t){return o("el-option",{key:t.id,attrs:{label:t.name,value:t.id}})})),1)],1)],1),o("el-col",{attrs:{span:8}},[o("el-form-item",{attrs:{label:"单位负责人",prop:"responsibleContactName"}},[o("el-input",{style:{width:"100%"},attrs:{placeholder:"请输入单位负责人",clearable:""},model:{value:t.contradictionForm.responsibleContactName,callback:function(e){t.$set(t.contradictionForm,"responsibleContactName",e)},expression:"contradictionForm.responsibleContactName"}})],1)],1),o("el-col",{attrs:{span:8}},[o("el-form-item",{attrs:{"label-width":"120px",label:"负责人手机号",prop:"responsibleContactTelephone"}},[o("el-input",{style:{width:"100%"},attrs:{placeholder:"请输入负责人手机号",clearable:""},model:{value:t.contradictionForm.responsibleContactTelephone,callback:function(e){t.$set(t.contradictionForm,"responsibleContactTelephone",e)},expression:"contradictionForm.responsibleContactTelephone"}})],1)],1),o("el-col",{attrs:{span:24}},[o("el-form-item",{attrs:{"label-width":"120px",label:"近年进京情况",prop:"enterBeijingState"}},[o("el-input",{style:{width:"100%"},attrs:{placeholder:"请输入近年进京情况",clearable:""},model:{value:t.contradictionForm.enterBeijingState,callback:function(e){t.$set(t.contradictionForm,"enterBeijingState",e)},expression:"contradictionForm.enterBeijingState"}})],1)],1),o("el-col",{attrs:{span:10}},[o("el-form-item",{attrs:{"label-width":"300px",label:"附件上传（表格、文字、视频影音等）",prop:"fileList"}},[o("el-upload",{ref:"uploadBtn",attrs:{"file-list":t.fileList,action:t.fileListAction,"before-upload":t.fileListBeforeUpload,data:{id:t.idComputed},"before-remove":t.handleFileListRemove,"on-error":t.handleFileUploadError,"on-success":t.handleFileUploadSuccess}},[o("el-button",{attrs:{disabled:!t.isEdit,size:"small",type:"primary",icon:"el-icon-upload"}},[t._v("\n                    "+t._s(t.isEdit?"点击上传":"保存后点击上传")+"\n                  ")])],1)],1)],1),o("el-col",{attrs:{span:6}},[o("el-form-item",{attrs:{"label-width":"160px",label:"是否集访、人数",prop:"teamPetitionState"}},[o("el-switch",{model:{value:t.contradictionForm.teamPetitionState,callback:function(e){t.$set(t.contradictionForm,"teamPetitionState",e)},expression:"contradictionForm.teamPetitionState"}})],1)],1),o("el-col",{attrs:{span:6}},[o("el-form-item",{attrs:{label:"",prop:"teamPetitionCount"}},[o("el-input-number",{model:{value:t.contradictionForm.teamPetitionCount,callback:function(e){t.$set(t.contradictionForm,"teamPetitionCount",e)},expression:"contradictionForm.teamPetitionCount"}})],1)],1)],1),o("el-divider"),o("el-tag",{attrs:{type:"warning"}},[t._v("\n            信访内容\n          ")]),o("el-card",[o("el-col",{attrs:{span:8}},[o("el-form-item",{attrs:{"label-width":"120px",label:"首次信访时间",prop:"firstPetitionTime"}},[o("el-date-picker",{attrs:{format:"yyyy-MM-dd HH:mm:ss","value-format":"yyyy-MM-dd HH:mm:ss",align:"right",type:"date",placeholder:"请选择首次信访时间"},model:{value:t.contradictionForm.firstPetitionTime,callback:function(e){t.$set(t.contradictionForm,"firstPetitionTime",e)},expression:"contradictionForm.firstPetitionTime"}})],1)],1),o("el-col",{attrs:{span:8}},[o("el-form-item",{attrs:{"label-width":"120px",label:"末次信访时间",prop:"lastPetitionTime"}},[o("el-date-picker",{attrs:{format:"yyyy-MM-dd HH:mm:ss","value-format":"yyyy-MM-dd HH:mm:ss",align:"right",type:"date",placeholder:"请选择末次信访时间"},model:{value:t.contradictionForm.lastPetitionTime,callback:function(e){t.$set(t.contradictionForm,"lastPetitionTime",e)},expression:"contradictionForm.lastPetitionTime"}})],1)],1),o("el-col",{attrs:{span:8}},[o("el-form-item",{attrs:{label:"易化解程度",prop:"resolveLevel"}},[o("el-select",{attrs:{placeholder:"请选择易化解程度"},model:{value:t.contradictionForm.resolveLevel,callback:function(e){t.$set(t.contradictionForm,"resolveLevel",e)},expression:"contradictionForm.resolveLevel"}},t._l(t.resolveLevelOptions,(function(t){return o("el-option",{key:t.id,attrs:{label:t.listValue,value:t.listKey}})})),1)],1)],1),o("el-col",{attrs:{span:18}},[o("el-button",{attrs:{type:"success"},on:{click:function(e){return e.preventDefault(),t.addContradictionContent()}}},[t._v("\n                新增诉求概述\n              ")])],1),o("el-col",{attrs:{span:18}},[t._l(t.contradictionForm.contradictionContent,(function(e,i){return o("el-form-item",{key:i,attrs:{"label-width":"120px",label:"信访人诉求概述"+(i<1?"":i)}},[o("el-input",{style:{width:"80%"},attrs:{type:"textarea",placeholder:"请输入信访人诉求概述",autosize:{minRows:4,maxRows:4}},model:{value:t.contradictionForm.contradictionContent[i].contradictionContent,callback:function(e){t.$set(t.contradictionForm.contradictionContent[i],"contradictionContent",e)},expression:"contradictionForm.contradictionContent[index].contradictionContent"}}),o("el-button",{directives:[{name:"show",rawName:"v-show",value:i>0,expression:"index > 0"}],attrs:{type:"danger"},on:{click:function(o){return o.preventDefault(),t.removeContradictionContent(e,i)}}},[t._v("\n                  删除\n                ")])],1)})),o("el-divider")],2),o("el-col",{attrs:{span:18}},[o("el-button",{attrs:{type:"success"},on:{click:function(e){return e.preventDefault(),t.addContradictionResolveProcess()}}},[t._v("\n                新增化解过程\n              ")])],1),o("el-col",{attrs:{span:18}},[t._l(t.contradictionForm.contradictionResolveProcess,(function(e,i){return o("el-form-item",{key:i,attrs:{"label-width":"120px",label:"化解过程简述"+(i<1?"":i)}},[o("el-input",{style:{width:"80%"},attrs:{type:"textarea",placeholder:"请输入化解过程简述",autosize:{minRows:4,maxRows:4}},model:{value:t.contradictionForm.contradictionResolveProcess[i].resolveContent,callback:function(e){t.$set(t.contradictionForm.contradictionResolveProcess[i],"resolveContent",e)},expression:"contradictionForm.contradictionResolveProcess[index].resolveContent"}}),o("el-button",{directives:[{name:"show",rawName:"v-show",value:i>0,expression:"index > 0"}],attrs:{type:"danger"},on:{click:function(o){return o.preventDefault(),t.removeContradictionResolveProcess(e,i)}}},[t._v("\n                  删除\n                ")])],1)})),o("el-divider")],2),o("el-col",{attrs:{span:23}},[o("el-form-item",{attrs:{"label-width":"120px",label:"化解推进方法",prop:"resolveMethodSelf"}},[o("el-checkbox",{model:{value:t.contradictionForm.resolveMethodSelf,callback:function(e){t.$set(t.contradictionForm,"resolveMethodSelf",e)},expression:"contradictionForm.resolveMethodSelf"}},[t._v("\n                  立足自身解决：领导包案，包案领导姓名\n                  "),o("el-input",{staticClass:"jc_checkbox_input",style:{width:"20%"},attrs:{placeholder:"包案领导姓名",clearable:""},model:{value:t.contradictionForm.resolveMethodSelfName,callback:function(e){t.$set(t.contradictionForm,"resolveMethodSelfName",e)},expression:"contradictionForm.resolveMethodSelfName"}}),t._v("\n                  职务\n                  "),o("el-input",{staticClass:"jc_checkbox_input",style:{width:"20%"},attrs:{placeholder:"包案领导职务",clearable:""},model:{value:t.contradictionForm.resolveMethodSelfPosition,callback:function(e){t.$set(t.contradictionForm,"resolveMethodSelfPosition",e)},expression:"contradictionForm.resolveMethodSelfPosition"}})],1),o("el-checkbox",{model:{value:t.contradictionForm.resolveMethodThird,callback:function(e){t.$set(t.contradictionForm,"resolveMethodThird",e)},expression:"contradictionForm.resolveMethodThird"}},[t._v("\n                  第三方参与化解：第三方单位\n                  "),o("el-input",{staticClass:"jc_checkbox_input",style:{width:"20%"},attrs:{placeholder:"第三方单位",clearable:""},model:{value:t.contradictionForm.resolveMethodThirdCompany,callback:function(e){t.$set(t.contradictionForm,"resolveMethodThirdCompany",e)},expression:"contradictionForm.resolveMethodThirdCompany"}}),t._v("\n                  姓名\n                  "),o("el-input",{staticClass:"jc_checkbox_input",style:{width:"20%"},attrs:{placeholder:"姓名",clearable:""},model:{value:t.contradictionForm.resolveMethodThirdName,callback:function(e){t.$set(t.contradictionForm,"resolveMethodThirdName",e)},expression:"contradictionForm.resolveMethodThirdName"}})],1),o("el-checkbox",{model:{value:t.contradictionForm.resolveMethodState,callback:function(e){t.$set(t.contradictionForm,"resolveMethodState",e)},expression:"contradictionForm.resolveMethodState"}},[t._v("\n                  市国资系统推进：项目制，项目负责人姓名\n                  "),o("el-input",{staticClass:"jc_checkbox_input",style:{width:"20%"},attrs:{placeholder:"姓名",clearable:""},model:{value:t.contradictionForm.resolveMethodStateName,callback:function(e){t.$set(t.contradictionForm,"resolveMethodStateName",e)},expression:"contradictionForm.resolveMethodStateName"}}),t._v("\n                  单位\n                  "),o("el-input",{staticClass:"jc_checkbox_input",style:{width:"20%"},attrs:{placeholder:"单位",clearable:""},model:{value:t.contradictionForm.resolveMethodStateCompany,callback:function(e){t.$set(t.contradictionForm,"resolveMethodStateCompany",e)},expression:"contradictionForm.resolveMethodStateCompany"}})],1),o("el-checkbox",{staticClass:"jc_checkbox",model:{value:t.contradictionForm.resolveMethodCity,callback:function(e){t.$set(t.contradictionForm,"resolveMethodCity",e)},expression:"contradictionForm.resolveMethodCity"}},[t._v("\n                  市级层面推进：\n                  "),o("el-checkbox",{staticStyle:{display:"block"},model:{value:t.contradictionForm.resolveMethodCityLeader,callback:function(e){t.$set(t.contradictionForm,"resolveMethodCityLeader",e)},expression:"contradictionForm.resolveMethodCityLeader"}},[t._v("\n                    市领导接访：领导姓名\n                    "),o("el-input",{staticClass:"jc_checkbox_input",style:{width:"20%"},attrs:{placeholder:"姓名",clearable:""},model:{value:t.contradictionForm.resolveMethodCityLeaderName,callback:function(e){t.$set(t.contradictionForm,"resolveMethodCityLeaderName",e)},expression:"contradictionForm.resolveMethodCityLeaderName"}}),t._v("\n                    职务\n                    "),o("el-input",{staticClass:"jc_checkbox_input",style:{width:"20%"},attrs:{placeholder:"职务",clearable:""},model:{value:t.contradictionForm.resolveMethodCityLeaderPosition,callback:function(e){t.$set(t.contradictionForm,"resolveMethodCityLeaderPosition",e)},expression:"contradictionForm.resolveMethodCityLeaderPosition"}})],1),o("el-checkbox",{staticStyle:{display:"block"},model:{value:t.contradictionForm.resolveMethodCityMeeting,callback:function(e){t.$set(t.contradictionForm,"resolveMethodCityMeeting",e)},expression:"contradictionForm.resolveMethodCityMeeting"}},[t._v("\n                    市信访稳定工作例会\n                  ")]),o("el-checkbox",{staticStyle:{display:"block"},model:{value:t.contradictionForm.resolveMethodCitySeparate,callback:function(e){t.$set(t.contradictionForm,"resolveMethodCitySeparate",e)},expression:"contradictionForm.resolveMethodCitySeparate"}},[t._v("\n                    市分级分责\n                  ")])],1),o("br"),o("el-checkbox",{model:{value:t.contradictionForm.resolveMethodCityOther,callback:function(e){t.$set(t.contradictionForm,"resolveMethodCityOther",e)},expression:"contradictionForm.resolveMethodCityOther"}},[t._v("\n                  其他\n                ")])],1),o("el-form-item",{directives:[{name:"show",rawName:"v-show",value:t.contradictionForm.resolveMethodCityOther,expression:"contradictionForm.resolveMethodCityOther"}],attrs:{"label-width":"120px",label:"其他推进方法",prop:"resolveMethodCityOtherContent"}},[o("el-input",{style:{width:"100%"},attrs:{type:"textarea",placeholder:"请输入其他推进方法",autosize:{minRows:4,maxRows:4}},model:{value:t.contradictionForm.resolveMethodCityOtherContent,callback:function(e){t.$set(t.contradictionForm,"resolveMethodCityOtherContent",e)},expression:"contradictionForm.resolveMethodCityOtherContent"}})],1)],1),o("el-col",{attrs:{span:23}},[o("el-form-item",{attrs:{"label-width":"120px",label:"化解形式",prop:"resolveForms"}},[o("el-checkbox-group",{model:{value:t.contradictionForm.resolveForms,callback:function(e){t.$set(t.contradictionForm,"resolveForms",e)},expression:"contradictionForm.resolveForms"}},t._l(t.resolveFormOptions,(function(e){return o("el-checkbox",{key:e.listKey,staticStyle:{display:"block"},attrs:{label:e.listKey}},[t._v("\n                    "+t._s(e.listValue)+"\n                  ")])})),1)],1),o("el-form-item",{directives:[{name:"show",rawName:"v-show",value:t.contradictionForm.resolveForms.indexOf("6")>=0,expression:"contradictionForm.resolveForms.indexOf('6') >= 0"}],attrs:{"label-width":"120px",label:"其他化解形式",prop:"resolveFormOtherContent"}},[o("el-input",{style:{width:"100%"},attrs:{type:"textarea",placeholder:"请输入其他化解形式",autosize:{minRows:4,maxRows:4}},model:{value:t.contradictionForm.resolveFormOtherContent,callback:function(e){t.$set(t.contradictionForm,"resolveFormOtherContent",e)},expression:"contradictionForm.resolveFormOtherContent"}})],1)],1)],1),o("el-divider"),o("el-col",{attrs:{span:24}},[o("el-form-item",{attrs:{size:"large"}},[o("el-button",{attrs:{type:"primary"},on:{click:t.submitForm}},[t._v("\n                提交\n              ")])],1)],1)],1)],1)],1)],1)],1)},n=[],r=(o("7f7f"),o("b775"));function a(t){return Object(r["a"])({url:"/sysFile/list",method:"post",params:t})}function l(t){return Object(r["a"])({url:"/sysFile/delete/"+t,method:"post"})}var c={remove:l,fetchList:a},s=o("62e4"),d=o("fcac"),p=o("48976"),m={contradictionTypes:[],petitionTypes:[],resolveForms:[],contradictionContent:[],contradictionResolveProcess:[]},u={components:{},props:[],data:function(){return{contradictionForm:Object.assign({},m),contradictionTypeOptions:[],petitionTypeOptions:[],purposeTypeOptions:[],applyPoliticalStatusOptions:[],companyOptions:[],resolveLevelOptions:[],resolveFormOptions:[],rules:{},loading:!1,fileListAction:"/sysFile/upload"}},computed:{routerKey:function(){return this.$route.path},formatForm:function(){return{}},idComputed:function(){return this.$route.query.id},isEdit:function(){return"contradictionUpdate"===this.$route.name},isQuery:function(){return"contradictionDetail"===this.$route.name},fileList:function(){var t=this.contradictionForm.fileList,e=[];for(var o in t)e.push({name:t[o].fileOldName,id:t[o].id});return e},contradictionContentComputed:function(){var t=this.contradictionForm.contradictionContent;return t&&t.length>0?t:[{id:"",contradictionContent:""}]},contradictionResolveProcessComputed:function(){var t=this.contradictionForm.contradictionResolveProcess;return t&&t.length>0?t:[{id:"",resolveContent:""}]}},watch:{},created:function(){var t=this;this.getListByType("contradictionType").then((function(e){t.contradictionTypeOptions=e.data.list||[]})),this.getListByType("petitionType").then((function(e){t.petitionTypeOptions=e.data.list||[]})),this.getListByType("purposeType").then((function(e){t.purposeTypeOptions=e.data.list||[]})),this.getListByType("politicalStatus").then((function(e){t.applyPoliticalStatusOptions=e.data.list||[]})),this.getListByType("resolveLevel").then((function(e){t.resolveLevelOptions=e.data.list||[]})),this.getListByType("resolveForm").then((function(e){t.resolveFormOptions=e.data.list||[]})),this.getCompanyList()},mounted:function(){if(this.isEdit||this.isQuery){var t=this.$route.query.id;this.getDetail(t)}},methods:{getDetail:function(t){var e=this;this.loading=!0,p["a"].fetchList({id:t}).then((function(t){e.loading=!1;var o=t.data.list;o&&o.length>0?e.contradictionForm=o[0]:e.$message({type:"error",message:"找不到此纪录，请联系管理员!"})}))},submitForm:function(){var t=this,e=this;this.$refs["contradictionForm"].validate((function(o){o&&(t.isEdit?p["a"].update(t.contradictionForm).then((function(e){t.$message({message:e.data,type:"success",duration:1e3,onClose:function(){location.reload()}})})):p["a"].create(t.contradictionForm).then((function(o){var i=o.data;t.$message({message:o.message,type:"success",duration:1e3,onClose:function(){e.$router.push({path:"/petition/contradictionUpdate",query:{id:i}})}})})))}))},resetForm:function(){this.$refs["elForm"].resetFields()},addContradictionContent:function(){this.contradictionForm.contradictionContent.push({id:"",contradictionContent:""})},removeContradictionContent:function(t,e){-1!==e&&this.contradictionForm.contradictionContent.splice(e,1)},addContradictionResolveProcess:function(){this.contradictionForm.contradictionResolveProcess.push({id:"",resolveContent:""})},removeContradictionResolveProcess:function(t,e){-1!==e&&this.contradictionForm.contradictionResolveProcess.splice(e,1)},fileListBeforeUpload:function(t){var e=t.size/1024/1024<1024;return e||this.$message.error("文件大小超过 1024MB"),e},handleFileListRemove:function(t,e){var o=this;return this.$confirm("是否要删除此文件?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){c.remove(t.id).then((function(){o.$message({type:"success",message:"删除成功!"}),o.getFileList()}))})),!1},handleFileUploadSuccess:function(t,e,o){200!==t.code&&this.$message({type:"error",message:t.message}),this.getFileList()},handleFileUploadError:function(t,e,o){console.log(t),console.log(e),console.log(o),this.$message({type:"error",message:"上传失败，请检查文件是否过大!"})},getListByType:function(t){return s["a"].fetchList({listType:t})},getCompanyList:function(t,e){var o=this;d["a"].fetchList({name:t}).then((function(t){o.companyOptions=t.data.list,e&&e(t.data.list)}))},getFileList:function(){var t=this;c.fetchList({id:this.idComputed}).then((function(e){t.contradictionForm.fileList=e.data}))}}},h=u,v=(o("215e"),o("2877")),y=Object(v["a"])(h,i,n,!1,null,null,null);e["default"]=y.exports},fcac:function(t,e,o){"use strict";var i=o("b775");function n(t){return Object(i["a"])({url:"/petitionCompany/list",method:"post",params:t})}function r(t){return Object(i["a"])({url:"/petitionCompany/create",method:"post",data:t})}function a(t){return Object(i["a"])({url:"/petitionCompany/update",method:"post",data:t})}function l(t){return Object(i["a"])({url:"/petitionCompany/delete/"+t,method:"post"})}e["a"]={fetchList:n,create:r,update:a,remove:l}}}]);