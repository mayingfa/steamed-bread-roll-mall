<template>
  <div class="login-form">
    <el-header class="header" height="45px">
      <h2 class="title">登录</h2>
      <span class="pull-right">
        没有账号？
        <router-link class="link" to="/registerForm">点此注册</router-link>
      </span>
    </el-header>
    <div class="form">
      <el-form :model="loginForm" status-icon :rules="rules" ref="loginForm">
        <el-form-item prop="userAccount">
          <el-input v-model="loginForm.userAccount" autocomplete="on" placeholder="请输入帐号"
                    @keyup.enter.native="submitForm('loginForm')" prefix-icon="el-icon-user"></el-input>
        </el-form-item>
        <el-form-item prop="passWord">
          <el-input type="password" show-password v-model="loginForm.passWord" autocomplete="off" placeholder="请输入密码"
                    @keyup.enter.native="submitForm('loginForm')" prefix-icon="el-icon-lock"></el-input>
        </el-form-item>
        <el-form-item prop="loginMode" style="margin-bottom: 5px;">
          <el-radio-group v-model="loginForm.loginMode">
            <el-radio-button label="我是顾客"></el-radio-button>
            <el-radio-button label="我是管理员"></el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item style="margin-bottom: 10px;">
          <el-checkbox v-model="rememberMe">记住我</el-checkbox>
          <router-link to="/forgotPassword" class="retrieve-password">找回密码</router-link>
        </el-form-item>
        <el-form-item style="margin-bottom: 10px;">
          <el-button type="primary" class="submit" v-preventReClick @click="submitForm('loginForm')">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login-Form",
  data() {
    return {
      //checkCodeKey
      checkCodeKey: null,
      //验证码图片URL
      checkCodeUrl: null,
      //记住我
      rememberMe: true,
      //登录表单
      loginForm: {
        userAccount: '',
        passWord: '',
        loginMode:'我是顾客',
      },
      //表单的验证规则
      rules: {
        userAccount: [
          {required: true, message: '请输入帐号', trigger: 'blur'}
        ],
        passWord: [
          {required: true, message: '请输入密码', trigger: 'blur'}
        ],
      }
    }
  },
  methods: {
    //提交登录信息
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let loading = this.$loading({lock: true, text: "登录中", background: "rgba(255,255,255,0.1)"});
          this.$http.post("/login?username=" + this.loginForm.userAccount + "&password=" + this.loginForm.passWord).then((res) => {
            loading.close();
            if (res.data.code === 200) {
              let token = res.data.data.sessionId;
              let role = res.data.data.role;
              let user = res.data.data.user;
              let roleInfo = res.data.data.roleInfo;
              let isVip;
              this.$http.post('/vip/existsVip?accountNumber=' + user.accountNumber).then((rep) => {
                if (rep.data.code === 200) {
                  isVip = rep.data.data
                  user['isVip'] = isVip;
                  this.$store.commit('setToken', token)
                  this.$store.commit('setRole', role)
                  this.$store.commit('setUser', user)
                  this.$store.commit('setRoleInfo', roleInfo)
                  localStorage.setItem("store", JSON.stringify(this.$store.state))
                  let hours = new Date().getHours();
                  let str;
                  if (hours > 7 && hours < 12) {
                    str = '上午好!'
                  } else if (hours >= 12 && hours <= 13) {
                    str = '中午好!'
                  } else if (hours > 13 && hours <= 18) {
                    str = '下午好!'
                  } else if (hours > 18 && hours < 22) {
                    str = '晚上好!'
                  } else {
                    str = '晚上好!'
                  }
                  let message = '欢迎登录花卷商城';
                  if (role.indexOf(this.$VAR.ordinaryRole) === -1) {
                    message += '后台系统'
                  }
                  this.$notify({title: str, message: message, type: 'success', offset: 50});
                  if (role.indexOf(this.$VAR.ordinaryRole) !== -1) {  // 不是-1  为普通用户，进入前端商城
                    this.$router.push('/MallHome')
                  } else {
                    this.$router.push('/HomePage')
                  }
                }
              }).catch((err) => {
                this.$msg.error(err)
              })
            } else {
              this.$msg.error({message: res.data.message, showClose: true, duration: 1500});
            }
          }).catch((err) => {
            console.log(err);
            loading.close();
            this.$msg.error({message: '登录失败，' + err, showClose: true, duration: 1500});
          })
        } else {
          return false;
        }
      });
    }
  },
  created() {

  }
}
</script>

<style scoped>
.login-form .header {
  position: relative;
  text-align: left;
  border: none;
  padding: 0;
}

.login-form .header .title {
  margin: 0;
}

.login-form .header .pull-right {
  position: absolute;
  top: 15px;
  right: 0;
  font-size: 14px;
}

.login-form .header .pull-right .link {
  text-decoration: none;
  cursor: pointer;
  color: #005980;
}

.login-form .form {
  margin-top: 15px;
  text-align: left;
}

.login-form .form .check-code {
  position: absolute;
  right: 20px;
  cursor: pointer;
}

.login-form .form .phone-code {
  float: right;
  text-decoration: none;
  color: #005980;
}

.login-form .form .retrieve-password {
  text-decoration: none;
  float: right;
  color: #005980;
}

.login-form .form .submit {
  width: 100%;
}
</style>

<style>
.login-form .el-radio-group .el-radio-button__inner{
  width: 180px;
}
</style>
