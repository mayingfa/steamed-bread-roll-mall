<template>
  <div class="register-form">
    <el-header class="header" height="45px">
      <h2 class="title">找回密码</h2>
      <span class="pull-right">
        <router-link class="link" to="/loginForm">继续登录</router-link>
      </span>
    </el-header>
    <div class="form">
      <el-form :model="userForm" status-icon :rules="rules" ref="userForm">
        <el-form-item prop="userAccount">
          <el-input v-model="userForm.userAccount" autocomplete="off" placeholder="请输入邮箱"
                    prefix-icon="el-icon-message"></el-input>
        </el-form-item>
        <el-form-item prop="checkCode">
          <el-input v-model="userForm.checkCode" :disabled="codeRight" autocomplete="off" placeholder="邮箱验证码"
                    prefix-icon="el-icon-link"></el-input>
          <el-button plain style="position: absolute;right: 0; width: 40%;top: 0;" v-preventReClick
                     @click="sendEmailCode('userForm',$event)">发送验证码
          </el-button>
        </el-form-item>
        <el-form-item prop="passWord">
          <el-input type="password" auto-complete="new-password" show-password v-model="userForm.passWord"
                    autocomplete="off" placeholder="请输入密码" prefix-icon="el-icon-lock"></el-input>
        </el-form-item>
        <el-form-item prop="checkPassWord">
          <el-input type="password" auto-complete="new-password" show-password v-model="userForm.checkPassWord"
                    autocomplete="off" placeholder="请再次输入密码" prefix-icon="el-icon-lock"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" class="submit" v-preventReClick @click="submitForm('userForm')">找回密码</el-button>
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
    let CheckCode = async (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入验证码'));
      }
      if (this.checkCodeErrorMessage !== null) {
        callback(new Error(this.checkCodeErrorMessage));
      } else {
        callback()
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
    let CheckPass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.userForm.passWord) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      // 验证码错误信息
      checkCodeErrorMessage: null,
      //临时存放发送验证码按钮，方便后面清除按钮上倒计时的定时器
      codeButtonTemp: null,
      //验证码图片URL
      checkCodeUrl: null,
      //记住我
      rememberMe: false,
      //验证码是否正确
      codeRight: true,
      //登录表单
      userForm: {
        userAccount: '',
        passWord: '',
        checkCode: '',
        checkPassWord: '',
      },
      //表单的验证规则
      rules: {
        userAccount: [
          {validator: CheckEmail, trigger: 'blur'}
        ],
        checkCode: [
          {required: true, message: '请输入邮箱验证码', trigger: 'blur'}
        ],
        passWord: [
          {validator: CheckPass, trigger: 'blur'}
        ],
        checkPassWord: [
          {validator: CheckPass2, trigger: 'blur'}
        ],
      }
    }
  },
  methods: {
    //提交注册信息
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http.post("/allow/resetpwd?account=" + this.userForm.userAccount + "&password=" + this.userForm.passWord).then((res) => {
            if (res.data.code === 200) {
              debugger;
              //清除发送验证码按钮倒计时的定时器
              this.$countDown.removeItem(this.codeButtonTemp);
              this.$msg.success({message: res.data.message, showClose: true, duration: 1500});
              this.$http.post("/allow/sendHtmlResetPwd?email=" + this.userForm.userAccount + "&password=" + this.userForm.passWord);
              this.$router.push('/loginForm');
            } else {
              //验证码已过期，返回code 500
              this.$msg.warning({message: res.data.message, showClose: true, duration: 1500});
            }
          }).catch((err) => {  //网络等原因，导致发送失败
            this.$msg.error({message: '密码重置失败，' + err, showClose: true, duration: 1500});
          })
        } else {
          return false;
        }
      });
    },
    //发送邮件验证码
    sendEmailCode(formName, event) {
      this.codeButtonTemp = event.currentTarget;
      let va = true;
      this.$refs[formName].validateField('userAccount', (valid) => {
        va = valid === "";
      })
      if (va) {
        let loading = this.$loading({lock: true, text: "验证码发送中", background: "rgba(255,255,255,0.1)"});
        this.$http.post("/allow/sendHtmlCode?email=" + this.userForm.userAccount).then((res) => {
          loading.close();
          this.codeRight = false;
          this.$countDown.setItem(this.codeButtonTemp);
          this.$message.success("验证码发送成功");
        }).catch((err) => {
          this.checkCodeErrorMessage = err.message;
          this.checkCodeErrorMessage = null;
          loading.close();
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
