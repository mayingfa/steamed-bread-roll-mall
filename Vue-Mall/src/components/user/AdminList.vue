<template>
  <div class="user-list">
    <el-card class="user-hd" shadow="never">
      <div slot="header" style="text-align: left;">
        <i class="el-icon-search"/>
        <span>筛选搜索</span>
        <el-button class="el-button-search" style="right: 111px" type="primary" @click="queryUser()">查询</el-button>
        <el-button class="el-button-search" style="right: 20px" @click="resetForm('userInfoForm')">重置</el-button>
      </div>
      <el-form :model="userInfoForm" ref="userInfoForm" inline class="query-form">
        <el-form-item label="输入搜索" prop="key">
          <el-input v-model="userInfoForm.key" @keyup.enter.native="queryUser()" clearable
                    placeholder="帐号/姓名"></el-input>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card class="user-box" shadow="never">
      <div slot="header" style="text-align: left;position: relative">
        <i class="el-icon-coin"/>
        <span>数据列表</span>
      </div>
      <el-table :data="userInfo" class="type-table" height="425" border style="width: 100%">
        <el-table-column prop="userId" label="编号" width="100"></el-table-column>
        <el-table-column prop="accountNumber" label="帐号" width="200"></el-table-column>
        <el-table-column prop="userName" label="姓名" width="150"></el-table-column>
        <el-table-column prop="telephone" label="手机号" width="200"></el-table-column>
        <el-table-column prop="creatTime" label="注册时间" width="200"></el-table-column>
        <el-table-column prop="loginTime" label="最后登录" width="200"></el-table-column>
        <el-table-column label="是否启用" width="100">
          <template slot-scope="scope">
            <el-switch :disabled="operateId===scope.row.userId||scope.row.userName===VAR.adminRole"
                       @change="isUserState(scope.$index)" v-model="userInfo[scope.$index].userState"/>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" min-width="200">
          <template slot-scope="scope">
            <el-button size="mini" style="margin: 0 2%" @click="allotRole(scope.$index)">分配角色</el-button>
            <el-button size="mini" type="danger" title="超级管理员才有删除权限"
                       :disabled="((operateId===scope.row.userId) || (operateRole!==null && operateRole.indexOf(VAR.adminRole)===-1))"
                       style="margin: 0 2%" @click="deleteUser(scope.$index, userInfo)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-dialog title="分配角色" class="user-dialog" :visible.sync="userDialogVisible" width="30%" distinguishCancelAndClose>
      <div style="text-align: left;width: 80%;margin: 0 auto">
        <h3 style="margin-top: 0" v-if="userInfo.length!==0">姓名:
          <el-tooltip effect="dark" :content="currentRoleName" placement="right">
            <el-tag v-if="currentRoleName.indexOf(VAR.adminRole)!==-1" type="warning">
              {{ userInfo[handleIndex]['userName'] }}
            </el-tag>
            <el-tag v-else-if="currentRoleName.indexOf(VAR.ordinaryRole)!==-1" type="success">
              {{ userInfo[handleIndex]['userName'] }}
            </el-tag>
            <el-tag v-else-if="currentRoleName.indexOf('暂无角色信息')!==-1" type="info">
              {{ userInfo[handleIndex]['userName'] }}
            </el-tag>
            <el-tag v-else type="danger">{{ userInfo[handleIndex]['userName'] }}</el-tag>
          </el-tooltip>
        </h3>
        <el-select v-model="allotRoles" multiple placeholder="请选择角色" style="width: 100%;">
          <el-option v-for="item in roleInfo" :key="item.roleId" :label="item.roleName"
                     :value="item.roleId"></el-option>
        </el-select>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button size="small" @click="userDialogVisible = false">取 消</el-button>
        <el-button size="small" type="primary" @click="submitRole">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "UserList",
  data: function () {
    return {
      userInfo: [],
      VAR: this.$VAR,
      operateRole: this.$store.state.role,
      operateId: this.$store.state.user.userId,
      userInfoResource: [],
      userDialogVisible: false,
      roleInfo: [],
      handleIndex: 0,
      currentRole: [],
      currentRoleName: '',
      allotRoles: [],  //被分配的角色
      userInfoForm: {
        key: ''
      }
    }
  },
  methods: {
    queryUser() {
      if (this.userInfoForm.key.length !== 0) {
        this.userInfo = [];
        for (let i = 0, len = this.userInfoResource.length; i < len; i++) {
          if (this.userInfoResource[i].userName.indexOf(this.userInfoForm.key) !== -1) {
            this.userInfo.push(this.userInfoResource[i]);
          } else if (this.userInfoResource[i].accountNumber.indexOf(this.userInfoForm.key) !== -1) {
            this.userInfo.push(this.userInfoResource[i]);
          }
        }
      } else {
        this.userInfo = this.userInfoResource;
      }
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    isUserState(index) {
      let loading = this.$loading({lock: true, text: "数据提交中", background: "rgba(255,255,255,0.1)"});
      this.$http.post('/allow/update?userId=' + this.userInfo[index].userId + '&userState=' + this.userInfo[index].userState).then((rep) => {
        loading.close();
        if (rep.data.code === 200) {
          if (this.userInfo[index].userState) {
            this.$notify({title: '成功', message: this.userInfo[index].userName + '\t已启用', type: 'success'});
          } else {
            this.$notify({title: '成功', message: this.userInfo[index].userName + '\t已禁用', type: 'success'});
          }
        }
      }).catch((err) => {
        loading.close();
        this.$msg.error(err)
      })
    },
    allotRole: function (index) {
      this.handleIndex = index;
      let userId = this.userInfo[index].userId;
      this.$http.post('/userRole/findRoleById?userId=' + userId).then((rep) => {
        if (rep.data.code === 200) {
          let userRole = rep.data.data;
          this.currentRole = rep.data.data;
          this.currentRoleName = ''; //数组清空
          for (let i = 0; i < this.currentRole.length; i++) {
            this.currentRoleName += this.currentRole[i].roleName + '\t';
          }
          if (this.currentRoleName.length === 0) {
            this.currentRoleName = '暂无角色信息'
          }
          let roleId = [];
          for (let i = 0; i < userRole.length; i++) {
            if (userRole[i].roleId !== 5 && userRole[i].roleId !== 1) {
              roleId.push(userRole[i].roleId)
            }
          }
          this.allotRoles = roleId;
        }
      }).catch((err) => {
        this.$msg.error(err);
      })
      this.userDialogVisible = true;
    },
    deleteUser(index, rows) {
      this.$confirm('此操作将永久删除该用户信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let loading = this.$loading({lock: true, text: "数据提交中", background: "rgba(255,255,255,0.1)"});
        this.$http.post('/user/delete?userId=' + this.userInfo[index].userId).then((rep) => {
          loading.close();
          if (rep.data.code === 200) {
            this.$notify({title: '成功', message: '用户（' + this.userInfo[index].userName + '）\t删除成功', type: 'success'});
            rows.splice(index, 1);//删掉该行
          } else {
            this.$notify({title: '错误', message: rep.data.message, type: 'error'});
          }
        }).catch((err) => {
          loading.close();
          this.$msg.error(err);
        })
      }).catch(() => {
        this.$notify({title: '操作取消', message: '', type: 'success'});
      });

    },
    reqInfo() {
      this.$http.post('/user/findAll/admin').then((rep) => {
        if (rep.data.code === 200) {
          this.userInfo = this.userInfoResource = rep.data.data;
        }
      }).catch((err) => {
        this.$msg.error(err)
      })
      this.$http.post('/role/findAllUsable').then((rep) => {
        if (rep.data.code === 200) {
          this.roleInfo = rep.data.data.filter((item) => {
            return item.roleName !== VAR.adminRole && item.roleName !== VAR.ordinaryRole;
          });
        }
      }).catch((err) => {
        this.$msg.error(err)
      })
    },
    submitRole() {
      if (this.allotRoles.length === 0) {
        this.$alert('至少选择一个角色', '提示', {
          confirmButtonText: '确定',
          type: "warning"
        });
      } else {
        this.$confirm('是否要分配该角色?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let roleInfo = '';
          for (let i = 0; i < this.currentRole.length; i++) {
            if (this.currentRole[i].roleName.indexOf(this.$VAR.adminRole) !== -1) {
              this.allotRoles.push(this.currentRole[i].roleId);
              roleInfo += this.currentRole[i].roleName + '、 ';
            }
          }

          for (let i = 0; i < this.allotRoles.length; i++) {
            for (let j = 0; j < this.roleInfo.length; j++) {
              if (this.allotRoles[i] === this.roleInfo[j].roleId) {
                roleInfo += this.roleInfo[j].roleName + '、 ';
              }
            }
          }
          roleInfo = roleInfo.substring(0, roleInfo.length - 2);
          console.log(roleInfo)
          let loading = this.$loading({lock: true, text: "数据提交中", background: "rgba(255,255,255,0.1)"});
          this.$http.post('/user/author?userId=' + this.userInfo[this.handleIndex].userId + '&roleId=' + this.allotRoles).then((rep) => {
            loading.close();
            if (rep.data.code === 200) {
              this.$http.post('/allow/sendHtmlRole?email=' + this.userInfo[this.handleIndex].accountNumber +
                '&userName=' + this.userInfo[this.handleIndex].userName + '&roleInfo=' + roleInfo);
              this.userDialogVisible = false;
              this.$notify({title: '成功', message: '\t角色授权成功', type: 'success'});
            } else {
              this.$notify({title: '错误', message: rep.data.message, type: 'error'});
            }
          }).catch((err) => {
            loading.close();
            this.$msg.error(err)
          })
        }).catch(() => {
        });
      }
    }
  },
  created() {
    this.reqInfo();
  }
}
</script>

<style>
.user-list {
  margin: 0;
  padding: 0;
  min-height: 94%;
  user-select: none;
  min-width: 1226px;
}

.user-list .user-hd {
  height: 138px;
  margin: 30px auto 10px;
  width: 95%;
}

.user-list .user-hd .el-card__header {
  position: relative;
}


.user-list .info-box {
  height: 600px;
  border: 1px solid #EBEEF5;
}

.user-list .el-card__body {
  user-select: text;
}

.user-list .user-hd .el-button-search {
  position: absolute;
  top: 14px;
  padding: 3px 0;
  width: 70px;
  height: 30px;
}

.user-list .query-form {
  text-align: left;
}

.user-list .user-box {
  height: 530px;
  margin: 20px auto 10px;
  width: 95%;
}

.user-list .user-box th, .user-list .user-box td {
  text-align: center;
}

.user-list .el-table th.gutter {
  display: table-cell !important;
}

.user-list .info-form {
  position: relative;
}

.user-list .user-dialog {
  min-width: 200px;

}

.user-list .user-dialog .el-dialog__header {
  text-align: left;
}

.user-list .user-dialog .el-dialog {
  margin-top: 28vh !important;
}

.user-list .user-dialog .el-tag {
  height: 28px;
  line-height: 28px;
  font-weight: 200;
  padding: 0 4px;
  cursor: pointer;
}

</style>
