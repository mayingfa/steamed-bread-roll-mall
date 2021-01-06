<!--登录表单组件-->
<template>
  <div class="login">
    <el-form class="demo-loginForm" :model="loadForm" :rules="rules" ref="loadForm">
      <el-form-item prop="username">
        <el-input class="el-input" v-model="loadForm.username" @keyup.enter.native="submitForm('loadForm')" name="account" auto-complete="on" autocomplete="on"  placeholder="请输入账号">
          <i slot="prefix" class="el-input__icon el-icon-mobile-phone"></i>
        </el-input>
      </el-form-item>
      <el-form-item prop="pass" style="margin-bottom: 35px;">
        <el-input class="el-input"  v-model="loadForm.pass" @keyup.enter.native="submitForm('loadForm')" type="password" placeholder="请输入密码">
          <i slot="prefix" class="el-input__icon el-icon-lock"></i>
        </el-input>
      </el-form-item>
      <router-link to="/resetPwdForm"><a class="resetPwd">找回密码</a></router-link>
      <router-link to="/registerForm"><a class="login-a">注册账户</a></router-link>
      <el-form-item>
        <el-button class="el-form-button" type="primary" plain @click="submitForm('loadForm')">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  export default {
    name: "Login_Form",
    data: function () {
      let checkNumber = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('请输入账号！'));
        } else {
          callback();
        }
      };
      let validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          callback();
        }
      };
      return {
        loadForm: {
          username: '',
          pass: ''
        },
        rules: {
          username: [
            {validator: checkNumber, trigger: 'blur'}
          ],
          pass: [
            {validator: validatePass, trigger: 'blur'}
          ]
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            let loading = this.$loading({lock: true, text: "登录中",background:"rgba(255,255,255,0.1)"});
            this.$http.post("/login?username="+this.loadForm.username+"&password="+this.loadForm.pass).then((res)=>{
              loading.close();
              if(res.data.code===200){
                let token = res.data.data.sessionId;
                let role = res.data.data.role;
                let user = res.data.data.user;
                let roleInfo = res.data.data.roleInfo;
                let isVip;
                this.$http.post('/vip/existsVip?accountNumber='+user.accountNumber).then((rep)=>{
                  if(rep.data.code === 200){
                    isVip = rep.data.data
                    user['isVip']=isVip;
                    this.$store.commit('setToken',token)
                    this.$store.commit('setRole',role)
                    this.$store.commit('setUser',user)
                    this.$store.commit('setRoleInfo',roleInfo)
                    localStorage.setItem("store",JSON.stringify(this.$store.state))
                    let hours = new Date().getHours();
                    let str;
                    if(hours>7 && hours<12){
                      str = '上午好!'
                    }else if(hours>=12 && hours<=13){
                      str = '中午好!'
                    }else if(hours>13 && hours<=18){
                      str = '下午好!'
                    }else if(hours>18 && hours<22){
                      str = '晚上好!'
                    }
                    let message = '欢迎登录花卷商城';
                    if(role.indexOf(this.$VAR.ordinaryRole)===-1){
                      message +='后台系统'
                    }
                    this.$notify({title: str, message: message, type: 'success',offset: 0});
                    if(role.indexOf(this.$VAR.ordinaryRole)!==-1){  // 不是-1  为普通用户，进入前端商城
                      this.$router.push('/MallHome')
                    }else{
                      this.$router.push('/HomePage')
                    }
                  }
                  }).catch((err)=>{
                    this.$msg.error(err)
                  })
              }else{
                this.$msg.error({message:res.data.message, showClose: true, duration:1500});
              }
            }).catch((err)=>{
              console.log(err);
              loading.close();
              this.$msg.error({message:'登录失败，'+err, showClose: true, duration:1500});
            })
          } else {
            return false;
          }
        });
      }
    }
  }
</script>

<style>
  .login{
    width: 80%;
    margin: auto;
    height: 100%;
  }

  .login .demo-loginForm{
    height: 50%;
    padding-top: 15%;
  }
  .el-form-button{
    display: block;
    margin: auto;
    width: 60%;
  }
  .login-a{
    cursor: pointer;
    position: absolute;
    right: 35px;
    top: 42%;
    font-size: 13px;
    color: rgb(147, 165, 255);
  }
  .login .resetPwd{
    cursor: pointer;
    position: absolute;
    right: 35px;
    top: 55%;
    font-size: 13px;
    color: rgb(147, 165, 255);
  }
</style>
