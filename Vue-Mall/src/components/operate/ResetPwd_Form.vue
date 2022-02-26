<!--找回密码-->
<template>
  <div class="reset-pwd">
    <el-form class="demo-registerForm" :model="loadForm" :rules="rules" ref="loadForm">
      <el-form-item prop="mailbox">
        <el-input class="el-input" v-model="loadForm.mailbox" @keyup.enter.native="submitForm('loadForm')" autocomplete="on" placeholder="请输入邮箱帐号">
          <i slot="prefix" class="el-input__icon el-icon-message"></i>
        </el-input>
      </el-form-item>

      <el-form-item prop="authcode" style="text-align: left;">
        <el-input class="el-input" v-model="loadForm.authcode" @keyup.enter.native="submitForm('loadForm')" auto-complete=“new-accounts” style="width: 66%;"  maxlength="6" autocomplete="off" placeholder="请输入邮箱验证码">
          <i slot="prefix" class="el-input__icon el-icon-connection"></i>
        </el-input>
        <el-button type="success" plain size="small" style="position: absolute;margin: 4px 6px;width: 88px;" :disabled="!sendCodeIsUsed" @click="dialogVisible = true">
          <span v-show="show">获取验证码</span>
          <span v-show="!show" class="count">重发({{count}}s)</span>
        </el-button>
      </el-form-item>
      <el-dialog title="请输入验证码" :visible.sync="dialogVisible" width="90%" top="0" :modal="false">
        <span>
          <AuthCode  ref="child"></AuthCode>
        </span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="cancelCode">取 消</el-button>
          <el-button type="primary" @click="submitCode">确 定</el-button>
        </span>
      </el-dialog>

      <el-form-item prop="password">
        <el-input class="el-input"  v-model="loadForm.password" @keyup.enter.native="submitForm('loadForm')" autocomplete="off" type="password" placeholder="新密码：字母开头，6~20位字符">
          <i slot="prefix" class="el-input__icon el-icon-lock"></i>
        </el-input>
      </el-form-item>
      <el-form-item prop="repeatPassword" style="margin-bottom: 24px;">
        <el-input class="el-input"  v-model="loadForm.repeatPassword" @keyup.enter.native="submitForm('loadForm')" autocomplete="off" type="password" placeholder="请输入确认密码">
          <i slot="prefix" class="el-input__icon el-icon-lock"></i>
        </el-input>
      </el-form-item>
      <router-link to="/loginForm"><a class="returnLogin">返回登录</a></router-link>
      <el-form-item>
        <el-button class="el-form-button" type="primary" plain @click="submitForm('loadForm')">重置密码</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import AuthCode from "../util/AuthCode";
const TIME_COUNT = 60; //更改倒计时时间
export default {
  name: "Register_Form",
  components: {AuthCode},
  data(){
    let checkMail = (rule, value, callback) => {
      if (value.length===0) {
        this.sendCodeIsUsed=false;
        callback(new Error('请输入邮箱'));
      } else {
        let reg = new RegExp("^[A-Za-z0-9.\u4e00-\u9fa5_]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
        if (!reg.test(value)) {
          this.sendCodeIsUsed=false;
          return callback(new Error("邮箱格式错误"));
        } else {
          this.$http.post("/allow/existUser?accountNumber="+value).then((res)=>{
            if(res.data.code===200){
              if(!res.data.data){
                this.sendCodeIsUsed=false;
                callback(new Error('帐号不存在'));
              }else{
                this.sendCodeIsUsed=true;
                callback();
              }
            }
          }).catch((err)=>{console.log(err)})
        }
      }
    };
    let checkCode = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入验证码'));
      } else {
        callback();
      }
    };
    let checkPass = (rule, value, callback) => {
      if (value.length===0) {
        callback(new Error('请输入密码'));
      } else {
        let reg = new RegExp("^[a-zA-Z][a-zA-Z0-9_-]{5,18}$");
        if (!reg.test(value)) {
          return callback(new Error("密码必须由数字和字母组合成,长度为6-18"));
        } else {
          callback();
        }
        callback();
      }
    };
    let checkRepPass = (rule, value, callback) => {
      if (value.length===0) {
        callback(new Error('请输入确认密码'));
      } else {
        if (this.loadForm.password!==this.loadForm.repeatPassword) {
          return callback(new Error("两次密码输入不一致"));
        } else {
          callback();
        }
        callback();
      }
    };
    return {
      dialogVisible: false, //验证码框
      sendCodeIsUsed:false, // 是否禁用发送验证码按钮
      show: true,  // 初始启用按钮
      count: '',   // 初始化次数
      timer: null,
      loadForm: {
        mailbox:'',
        authcode:'',
        password: '',
        repeatPassword:''
      },

      rules: {
        mailbox:[
          {validator: checkMail, trigger: 'blur'}
        ],
        authcode:[
          {validator: checkCode, trigger: 'blur'}
        ],
        password: [
          {validator: checkPass, trigger: 'blur'}
        ],
        repeatPassword:[
          {validator: checkRepPass, trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    send(){
      if (!this.timer) {
        this.count = TIME_COUNT;
        this.show = false;
        this.timer = setInterval(() => {
          if (this.count > 0 && this.count <= TIME_COUNT) {
            this.count--;
          } else {
            this.show = true;
            clearInterval(this.timer);  // 清除定时器
            this.timer = null;
          }
        }, 1000)
      }
    },
    submitCode(){
      if(this.$refs.child.submitForm("codeForm")){
        this.dialogVisible = false
        this.$refs.child.resetForm("codeForm")
        let loading = this.$loading({lock: true, text: "验证码发送中",background:"rgba(255,255,255,0.1)"});
        this.$http.post("/allow/sendHtmlCode?email="+this.loadForm.mailbox).then((res)=>{
          loading.close();
          if(res.data.code===200){
            this.send();
            this.sendCodeIsUsed=false;
            this.$msg.success(res.data.message)
          }else if(res.data.code===500){
            this.$msg.warning(res.data.message)
          }
        }).catch((err)=>{
          console.log(err);
          loading.close();
          this.$msg.error("验证码发送失败，"+err)
        })
      }
    },
    cancelCode(){
      this.$refs.child.resetForm("codeForm")
      this.dialogVisible = false
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http.post("/allow/resetpwd?accountNumber="+this.loadForm.mailbox+"&password="+this.loadForm.password).then((res)=>{
            if(res.data.code===200){//验证码存在，返回code 200
              this.$msg.success({message:res.data.message, showClose: true, duration:1500});
              this.$http.post("/allow/sendHtmlResetPwd?email="+this.loadForm.mailbox+"&password="+this.loadForm.password);
              this.$router.push('/loginForm');
            }else{  //验证码已过期，返回code 500
              this.$msg.warning({message:res.data.message, showClose: true, duration:1500});
            }
          }).catch((err)=>{  //网络等原因，导致发送失败
            this.$msg.error({message:'密码重置失败，'+err, showClose: true, duration:1500});
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
  .reset-pwd{
    width: 80%;
    margin: auto;
    height: 100%
  }

  .reset-pwd .demo-registerForm{
  height: 50%;
  padding-top: 2%;
  }
  .reset-pwd .el-form-button{
    display: block;
    margin: auto;
    width: 60%;
  }
  .reset-pwd .el-form-item{
    margin-bottom: 20px;
  }

  .reset-pwd .returnLogin{
    cursor: pointer;
    position: absolute;
    right: 35px;
    top: 73%;
    font-size: 13px;
    color: rgb(147, 165, 255);
  }
  .reset-pwd .el-dialog{
    position: absolute;
    top: 31%;
    left: 5%;
    height: 40%;
    border-radius: 25px;
    margin: 0 auto;
  }
  .reset-pwd .el-dialog__body{
    padding: 10px 20px 0;
  }
  .reset-pwd .el-dialog__footer{
    padding: 0 20px 20px;
  }
</style>
