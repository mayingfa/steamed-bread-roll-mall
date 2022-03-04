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
        <el-form-item prop="passWord" style="margin-bottom: 10px;">
          <el-input type="password" show-password v-model="loginForm.passWord" autocomplete="off" placeholder="请输入密码"
                    @keyup.enter.native="submitForm('loginForm')" prefix-icon="el-icon-lock"></el-input>
        </el-form-item>
        <el-form-item style="margin-bottom: 5px;">
          <el-checkbox v-model="rememberMe">记住我</el-checkbox>
          <router-link to="/forgotPassword" class="retrieve-password">找回密码</router-link>
        </el-form-item>
        <el-form-item style="margin-bottom: 10px;">
          <el-button type="primary" class="submit" v-preventReClick @click="submitForm('loginForm')">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div style="width: 90%;margin: 0 auto;text-align: center">
      <el-divider>其他方式登录</el-divider>
      <div class="my-icon">
        <i class="iconfont iconqq qq"></i>
        <i class="iconfont iconweixin weixin"></i>
        <i class="iconfont icondingding01 dingding"></i>
      </div>
    </div>
  </div>
</template>

<script>
import store from "../../store";

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
              let role = res.data.data.role;
              localStorage.setItem("satoken",res.data.data.sessionId);
              this.$store.commit('setToken', res.data.data.sessionId);
              this.$store.commit('setRole', res.data.data.role);
              this.$store.commit('setUser', res.data.data.user);
              this.$store.commit('setRoleInfo', res.data.data.roleInfo);
              this.$http.post('/vip/existsVip?accountNumber=' + this.loginForm.userAccount).then((rep) => {
                if (rep.data.code === 200) {
                  let user = this.$store.state.user;
                  user['isVip'] = rep.data.data;
                  this.$store.commit('setUser', user);
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
                  if (user.status === 'ADMIN') {
                    message += '后台系统';
                  }
                  this.$notify({title: str, message: message, type: 'success', offset: 50});
                  if (user.status === 'ADMIN') {  // 管理员身份，进入前端页面
                    this.$router.push('/HomePage');
                  } else {
                    this.$router.push('/MallHome');
                  }
                } else {
                  this.$msg.error({message: res.data.message, showClose: true, duration: 1500});
                }
              }).catch((err) => {
                console.error(err)
                this.$msg.error(err)
              })
            } else {
              this.$msg.error({message: res.data.message, showClose: true, duration: 1500});
            }
          }).catch((err) => {
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


/*第三方登录图标*/
.login-form .my-icon {
  user-select: none;
}

.login-form .my-icon i {
  user-select: none;
  cursor: pointer;
  font-size: 32px;
  margin-left: 48px;
}

.login-form .el-divider--horizontal {
  margin: 40px 0 20px;
}

.login-form .qq {
  color: #00b0fb;
  margin-left: 0 !important;
}

.login-form .weixin {
  color: #46d800;
}

.login-form .dingding {
  color: #3795f9;
}
</style>
