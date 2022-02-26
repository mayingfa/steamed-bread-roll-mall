<template>
  <div class="register-form">
    <el-header class="header" height="45px">
      <h2 class="title">注册</h2>
      <span class="pull-right">
      已有账号？
      <router-link class="link" to="/loginForm">点此登录</router-link>
    </span>
    </el-header>
    <div class="form">
      <el-form :model="registerForm" status-icon :rules="rules" ref="registerForm">
        <el-form-item prop="userName">
          <el-input v-model="registerForm.userName" autocomplete="on" placeholder="请输入昵称"
                    prefix-icon="el-icon-user"></el-input>
        </el-form-item>
        <el-form-item prop="userAccount">
          <el-input v-model="registerForm.userAccount" autocomplete="off" placeholder="请输入邮箱"
                    prefix-icon="el-icon-message"></el-input>
        </el-form-item>
        <el-form-item prop="checkCode">
          <el-input v-model="registerForm.checkCode" :disabled="codeRight" autocomplete="off" placeholder="邮箱验证码"
                    prefix-icon="el-icon-link"></el-input>
          <el-button plain style="position: absolute;right: 0; width: 40%;top: 0;" v-preventReClick
                     @click="sendEmailCode('registerForm',$event)">发送验证码
          </el-button>
        </el-form-item>
        <el-form-item prop="passWord">
          <el-input type="password" auto-complete="new-password" show-password v-model="registerForm.passWord"
                    autocomplete="off" placeholder="请输入密码" prefix-icon="el-icon-lock"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" class="submit" v-preventReClick @click="submitForm('registerForm')">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Register_Form",
  data() {
    let CheckEmail = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入邮箱'));
      } else {
        if (this.$tools.checkEmail(value)) {
          callback();
        }
        return callback(new Error("请输入正确的邮箱"));
      }
    };
    let CheckPass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.$tools.checkPass(value)) {
          callback();
        }
        return callback(new Error("以字母开头，长度在6~18之间，只能包含字母、数字和下划线"));
      }
    };
    return {
      // 验证码错误信息
      checkCodeErrorMessage: null,
      //临时存放发送验证码按钮，方便后面清除按钮上倒计时的定时器
      codeButtonTemp: null,
      //记住我
      rememberMe: false,
      //验证码是否正确
      codeRight: true,
      //登录表单
      registerForm: {
        userAccount: '',
        userName: '',
        passWord: '',
        checkCode: '',
      },
      //表单的验证规则
      rules: {
        userAccount: [
          {validator: CheckEmail, trigger: 'blur'}
        ],
        userName: [
          {required: true, message: '请输入姓名', trigger: 'blur'}
        ],
        checkCode: [
          {required: true, message: '请输入邮箱验证码', trigger: 'blur'}
        ],
        passWord: [
          {validator: CheckPass, trigger: 'blur'}
        ],
      }
    }
  },
  methods: {
    //提交注册信息
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http.post("/allow/checkCode?key=" + this.registerForm.userAccount + "&code=" + this.registerForm.checkCode).then((res) => {
            if (res.data.code === 200) {//验证码存在，返回code 200
              if (res.data.data) {    //邮箱验证码验证成功
                let loading = this.$loading({lock: true, text: "数据提交中", background: "rgba(255,255,255,0.1)"});
                this.$http.post("/allow/add?accountNumber=" + this.registerForm.userAccount + "&password=" + this.registerForm.passWord + "&userName=" + this.registerForm.userName).then((res) => {
                  loading.close();
                  if (res.data.code === 200) {  //注册成功
                    this.$msg.success({message: res.data.message, duration: 1500});
                    this.$http.post("/allow/sendHtmlRegister?email=" + this.registerForm.userAccount + "&name=" + this.registerForm.userName + "&password=" + this.registerForm.passWord);
                    this.$router.push('/loginForm')
                  } else {  //注册失败
                    this.$msg.error({message: res.data.message, showClose: true, duration: 1500});
                  }
                }).catch(() => {
                  loading.close();
                  this.$msg.error({message: '注册失败，请稍后再试~', showClose: true, duration: 1500});
                })
              } else {    //邮箱验证码输入错误
                this.$msg.warning({message: res.data.message, duration: 1500});
              }
            } else {  //验证码已过期，返回code 500
              this.$msg.error({message: res.data.message, showClose: true, duration: 1500});
            }
          }).catch(() => {  //网络等原因，导致发送失败
            this.$msg.error({message: '注册失败，请稍后再试~', showClose: true, duration: 1500});
          })
        } else {
          return false;
        }
      });
    },
    //发送邮箱验证码
    sendEmailCode(formName, event) {
      this.codeButtonTemp = event.currentTarget;
      let va = true;
      this.$refs[formName].validateField('userAccount', (valid) => {
        va = valid === "";
      })
      this.$refs[formName].validateField('userName', (valid) => {
        va = va && valid === "";
      })
      if (va) {
        this.$http.post("/allow/sendHtmlCode?email=" + this.registerForm.userAccount).then((res) => {
          if (res.data.code === 200) {
            this.codeRight = false;
            this.$countDown.setItem(this.codeButtonTemp);
            this.$message.success("验证码发送成功");
          } else if (res.data.code === 500) {
            this.$msg.warning(res.data.message)
          }
        }).catch((err) => {
          this.checkCodeErrorMessage = err.message;
          this.$msg.error("验证码发送失败，请稍后重试！")
          this.checkCodeErrorMessage = null;
        })
      }
    }
  },
  created() {
  }
}
</script>


<style>
.register-form .header {
  position: relative;
  text-align: left;
  border: none;
  padding: 0;
}

.register-form .header .title {
  margin: 0;
}

.register-form .header .pull-right {
  position: absolute;
  top: 15px;
  right: 20px;
  font-size: 14px;
}

.register-form .header .pull-right .link {
  text-decoration: none;
  cursor: pointer;
  color: #005980;
}

.register-form .form {
  text-align: left;
}

.register-form .form .check-code {
  position: absolute;
  right: 20px;
  cursor: pointer;
}


.register-form .form .submit {
  width: 100%;
}

.register-form .el-form-item {
  margin-bottom: 19px;
}
</style>
