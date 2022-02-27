<template>
  <div class="role-list">
    <el-card class="user-hd" shadow="never">
      <div slot="header" style="text-align: left;">
        <i class="el-icon-search"/>
        <span>筛选搜索</span>
        <el-button class="el-button-search" style="right: 111px" type="primary" @click="queryRoleInfo()">查询</el-button>
        <el-button class="el-button-search" style="right: 20px" @click="resetForm('roleInfoForm')">重置</el-button>
      </div>
      <el-form :model="roleInfoForm" ref="roleInfoForm" inline class="query-form">
        <el-form-item label="输入搜索：" prop="key">
          <el-input v-model="roleInfoForm.key" @keyup.enter.native="queryRoleInfo" clearable placeholder="角色名称/描述"></el-input>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card class="role-box" shadow="never">
      <div slot="header" style="text-align: left;position: relative">
        <i class="el-icon-coin"/>
        <span>数据列表</span>
        <el-button class="el-button-add" @click="addRole">添加</el-button>
      </div>
      <el-table :data="roleInfo" class="type-table" height="425"  border style="width: 100%">
        <el-table-column prop="roleId" label="编号" width="150"></el-table-column>
        <el-table-column prop="roleName" label="角色" width="300"></el-table-column>
        <el-table-column prop="roleDescribe" label="描述" width="500"></el-table-column>
        <el-table-column label="是否启用" width="150">
          <template slot-scope="scope">
            <el-switch :disabled="roleInfo[scope.$index].roleName.indexOf(VAR.adminRole)!==-1||
                        roleInfo[scope.$index].roleName.indexOf(VAR.ordinaryRole)!==-1"
                       @change="isRoleState(scope.$index)" v-model="roleInfo[scope.$index].roleState"/>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" min-width="200">
          <template slot-scope="scope">
            <el-button size="mini" :disabled="roleInfo[scope.$index].roleName.indexOf(VAR.adminRole)!==-1||
                        roleInfo[scope.$index].roleName.indexOf(VAR.ordinaryRole)!==-1"
                       style="margin: 0 2%" @click="openChange(scope.$index)">编辑</el-button>
            <el-button size="mini" :disabled="roleInfo[scope.$index].roleName.indexOf(VAR.adminRole)!==-1||
                        roleInfo[scope.$index].roleName.indexOf(VAR.ordinaryRole)!==-1"
                       style="margin: 0 2%" type="danger" slot="reference" @click="deleteRole(scope.$index, roleInfo)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-dialog title="编辑角色信息" class="user-dialog" :close-on-click-modal="false" :visible.sync="roleDialogVisible" center>
      <el-form class="info-form" inline :rules="alterRules" :model="alterForm" ref='changeFrom'>
        <el-form-item label="角色名称：" prop="roleName" style="width: 100%">
          <el-input size="small" v-model="alterForm.roleName" placeholder="角色名称"></el-input>
        </el-form-item>
        <el-form-item label="角色描述：" prop="roleDescribe" style="width: 100%">
          <el-input size="small" type="textarea" autosize v-model="alterForm.roleDescribe" placeholder="角色描述"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" style="padding: 12px 54px;margin: 0 55px;" @click="submitChange('changeFrom')">确 定</el-button>
        <el-button style="padding: 12px 54px;margin: 0 55px;" @click="cancelChange('changeFrom')">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    name: "RoleList",
    data(){
      return{
        roleInfo:[],
        VAR : this.$VAR,
        roleInfoResource:[],
        roleDialogVisible:false,
        handleIndex:0,
        insert:false,
        roleInfoForm:{
          key:''
        },
        alterForm:{
          roleId:'',
          roleName:'',
          roleDescribe:'',
          roleState:1,
        },
        alterRules:{
          roleName: [{ required: true, message: '请输入角色名称', trigger: 'blur' },
            { min: 1, max: 30, message: '名称长度不允许超过 30 个字符', trigger: 'blur' }],
          roleDescribe: [{ required: true, message: '请输入角色描述', trigger: 'blur' },
            { min: 1, max: 100, message: '描述信息长度不允许超过 100 个字符', trigger: 'blur' }]
        }
      }
    },
    methods:{
      queryRoleInfo(){
        if(this.roleInfoForm.key.length!==0){
          this.roleInfo=[];
          for(let i=0,len=this.roleInfoResource.length;i<len;i++){
            if(this.roleInfoResource[i].roleName.indexOf(this.roleInfoForm.key)!==-1){
              this.roleInfo.push(this.roleInfoResource[i]);
            }else if(this.roleInfoResource[i].roleDescribe.indexOf(this.roleInfoForm.key)!==-1){
              this.roleInfo.push(this.roleInfoResource[i]);
            }
          }
        }else{
          this.roleInfo = this.roleInfoResource;
        }
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      openChange(index){
        this.handleIndex=index;
        this.insert=false;
        this.alterForm = this.roleInfo[index];
        this.roleDialogVisible=true;
      },
      submitChange(formName){
        this.$refs[formName].validate((valid) => {
          if (valid) {
            if(this.insert){
              this.$http.post('/role/existRoleName?roleId=0&roleName='+this.alterForm.roleName).then((rep)=>{
                if(rep.data.code===200){
                  if(rep.data.data){
                    this.$alert('角色('+this.alterForm.roleName+')\t已存在，不能重复添加', '警告', {confirmButtonText: '确定', type:"warning"});
                  }else{
                    let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.1)"});
                    this.$http.post('/role/add',this.$qs.stringify(this.alterForm)).then((rep)=>{
                      loading.close();
                      if(rep.data.code===200){
                        this.roleDialogVisible=false;
                        this.reqInfo();
                        this.$notify({title: '成功', message: '角色('+this.alterForm.roleName+')\t添加成功', type: 'success'});
                      }else{
                        this.$notify({title: '错误', message: rep.data.message, type: 'error'});
                      }
                    }).catch((err)=>{loading.close();this.$msg.error(err)})
                  }
                }
              }).catch((err)=>{this.$msg.error(err)})
            }else{
              this.$http.post('/role/existRoleName?roleId='+this.roleInfo[this.handleIndex].roleId+'&roleName='+this.alterForm.roleName).then((rep)=> {
                if (rep.data.code === 200) {
                  if (rep.data.data) {
                    this.$alert('角色('+this.alterForm.roleName + ')\t已存在，无法完成修改操作', '警告', {
                      confirmButtonText: '确定',
                      type: "warning"
                    });
                  } else {
                    let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.1)"});
                    this.$http.post('/role/update', this.$qs.stringify(this.alterForm)).then((rep) => {
                      loading.close();
                      if (rep.data.code === 200) {
                        this.roleDialogVisible = false;
                        this.roleInfo[this.handleIndex] = this.alterForm;
                        this.$notify({title: '成功', message: '角色('+this.roleInfo[this.handleIndex].roleName+')\t信息更新成功', type: 'success'});

                      } else {
                        this.$notify({title: '错误', message: rep.data.message, type: 'error'});
                      }
                    }).catch((err) => {
                      loading.close();
                      this.$msg.error(err)
                    })
                  }
                }
              }).catch((err)=>{this.$msg.error(err)})
            }
          } else {
            return false;
          }
        });
      },
      addRole(){
        delete this.alterForm.roleId;
        this.alterForm.roleName='';
        this.alterForm.roleDescribe='';
        this.insert=true;
        this.roleDialogVisible=true;
      },
      cancelChange(formName){
        this.roleDialogVisible=false;
        this.$refs[formName].resetFields();
        this.$notify({title: '操作取消', message: '', type: 'success'});
      },
      deleteRole(index, rows){
        let name = this.roleInfo[index].roleName;
        this.$http.post('/userRole/existsRole?roleId='+this.roleInfo[index].roleId).then((rep)=>{
          if(rep.data.code===200){
            if(rep.data.data){
              this.$alert('角色('+name+')\t下存在用户，无法删除', '警告', {confirmButtonText: '确定', type:"warning"});
            }else{
              this.$confirm('此操作将永久删除该角色信息, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(() => {
                let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.1)"});
                this.$http.post('/role/delete?roleId='+this.roleInfo[index].roleId).then((rep)=>{
                  loading.close();
                  if(rep.data.code === 200){
                    let name = this.roleInfo[index].roleName;
                    rows.splice(index, 1);//删掉该行
                    this.$notify({title: '成功', message: '角色('+name+')\t移除成功', type: 'success'});
                  }else{
                    this.$notify({title: '错误', message: rep.data.message, type: 'error'});
                  }
                }).catch((err)=>{
                  loading.close();
                  this.$msg.error(err);
                })
              }).catch(() => {
                this.$notify({title: '成功', message: '操作取消', type: 'success'});
              });
            }
          }
        }).catch((err)=>{this.$msg.error(err)})
      },
      isRoleState(index){
        let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.1)"});
        this.$http.post('/role/update?roleId=' + this.roleInfo[index].roleId + '&roleState=' + this.roleInfo[index].roleState).then((rep) => {
          loading.close();
          if (rep.data.code === 200) {
            if (this.roleInfo[index].roleState) {
              this.$notify({title: '成功', message: this.roleInfo[index].roleName+'\t已启用', type: 'success'});
            } else {
              this.$notify({title: '成功', message: this.roleInfo[index].roleName+'\t已禁用', type: 'success'});
            }
          }
        }).catch((err) => {
          loading.close();
          this.$msg.error(err);
        })
      },
      reqInfo(){
        this.$http.post('/role/findAll').then((rep)=>{
          if(rep.data.code===200){
            this.roleInfoResource = this.roleInfo = rep.data.data.filter((item)=> {
                 return item.roleName !== VAR.ordinaryRole;
            });
          }
        }).catch((err)=>{this.$msg.error(err)})
      }
    },
    created() {
      this.reqInfo();
    }
  }
</script>

<style>
  .role-list{
    margin: 0;
    padding: 0;
    min-height: 94%;
    user-select: none;
    min-width: 1226px;
  }
  .role-list .user-hd{
    height: 138px;
    margin: 30px auto 10px;
    width: 95%;
  }

  .role-list .user-hd .el-card__header{
    position: relative;
  }


  .role-list .info-box{
    height: 600px;
    border:1px solid #EBEEF5;
  }

  .role-list .user-hd .el-button-search{
    position: absolute;
    top: 14px;
    padding: 3px 0;
    width: 70px;
    height: 30px;
  }

  .role-list .query-form{
    text-align: left;
  }

  .role-list .role-box{
    height: 530px;
    margin: 20px auto 10px;
    width: 95%;
  }

  .role-list .role-box th,.role-list .role-box td{
    text-align: center;
  }

  .role-list .el-table th.gutter{
    display: table-cell!important;
  }

  .role-list .info-form{
    position: relative;
  }

  .role-list .user-dialog{
    min-width: 200px;
  }

  .role-list .el-card__body{
    user-select: text;
  }

  .role-list .user-dialog .el-dialog__header{
    text-align: left;
  }

  .role-list .user-dialog .el-dialog{
    margin-top: 28vh!important;
  }

  .role-list .role-box .el-button-add{
    position: absolute;
    right: 32px;
    top: -5px;
    padding: 3px 0;
    width: 80px;
    height: 32px;
  }

  .role-list .el-dialog .el-input__inner{
    width: 500px!important;
  }

  .role-list .el-dialog .el-textarea__inner{
    height: 180px!important;
    width: 500px!important;
  }

</style>
