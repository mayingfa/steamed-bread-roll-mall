<template>
  <div class="change-password center">
    <el-card class="box-card change-pwd" style="width: 90%;height: 650px;min-width: 1000px" shadow="hover">
      <div slot="header">
        <h3>修改密码</h3>
      </div>
      <div style="text-align: center;width: 750px;height: 430px;margin:auto">
        <el-steps :active="active" finish-status="success" align-center style="padding:22px">
          <el-step title="验证身份"></el-step>
          <el-step title="设置密码"></el-step>
          <el-step title="改密成功"></el-step>
        </el-steps>

        <!--表单1-->
        <el-form v-show="active===0" :model="form_0" status-icon :rules="rule_0" ref="form_0" class="demo-ruleForm" style="margin:45px">
          <el-form-item label="" prop="account" style="margin:0 90px 35px">
            <el-input placeholder="请输入帐号" disabled v-model="form_0.account">
              <template slot="prepend">帐号</template>
            </el-input>
          </el-form-item>
          <el-form-item label="" prop="code" style="margin:0 90px 35px;text-align: left">
            <el-input v-model="form_0.code" placeholder="请输入邮箱验证码" style="width: 73%" auto-complete=“new-accounts”></el-input>
            <el-button plain @click="sendCode" :disabled="!show" style="width: 120px">
              <span v-show="show">获取验证码</span>
              <span v-show="!show" class="count">重新发送({{count}}s)</span>
            </el-button>
          </el-form-item>
        </el-form>
        <!--表单2-->
        <el-form v-show="active===1" :model="form_1" status-icon :rules="rule_1" ref="form_1" style="margin: 45px">
          <el-form-item label="" prop="pass" style="margin:0 90px 35px">
            <el-input type="password" v-model="form_1.pass" placeholder="字母开头，6~20位字符" autocomplete="off">
              <template slot="prepend">新密码</template>
            </el-input>
          </el-form-item>
          <el-form-item label="" prop="checkPass" style="margin:0 90px 35px">
            <el-input type="password" v-model="form_1.checkPass" placeholder="请输入确认密码" autocomplete="off">
              <template slot="prepend">确认密码</template>
            </el-input>
          </el-form-item>
        </el-form>

        <el-button style="margin-top: 12px;width: 200px"  @click="submitForm()">下一步</el-button>
      </div>
    </el-card>


  </div>
</template>

<script>
import router from "../../router";

export default {
  name: "ChangePassword",
  data() {
    let checkCode = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('请输入验证码！'));
      } else {
        callback();
      }
    };
    let validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        let reg = new RegExp("^[a-zA-Z][a-zA-Z0-9_-]{5,18}$");
        if (!reg.test(value)) {
          return callback(new Error("密码必须由数字和字母组合成  长度为6~20位"));
        } else {
          callback();
        }
        callback();
      }
    };
    let validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.form_1.pass) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      active: 0,
      show: true,  // 初始启用按钮
      count: '',   // 初始化次数
      timer: null,
      form_0: {
        account: this.$store.state.user['accountNumber'],
        code: ''
      },
      form_1:{
        pass: '',
        checkPass: '',
      },
      rule_0: {
        code: [
          {validator: checkCode, trigger: 'blur'}
        ],
      },
      rule_1: {
        pass: [
          { validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { validator: validatePass2, trigger: 'blur' }
        ]
      }
    };
  },

  methods: {
    next() {
      if (this.active++ > 2) this.active = 0;
      if(this.active===2){
        this.active = 3;
        this.$confirm('密码修改成功，身份已过期，请重新登录', '提示', {
          confirmButtonText: '确定',
          closeOnClickModal: false,
          showCancelButton: false,
          type: 'warning'
        }).then(() => {
          this.exit();
        })
      }
    },
    send(){
      const TIME_COUNT = 60; //更改倒计时时间
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
    sendCode(){
      let loading = this.$loading({lock: true, text: "验证码发送中",background:"rgba(255,255,255,0.1)"});
      this.$http.post("/allow/sendHtmlCode?email="+this.form_0.account).then((res)=>{
        loading.close()
        if(res.data.code===200){
          this.send();
          this.$msg.success(res.data.message);
        }else{
          this.$msg.warning(res.data.message);
        }
      }).catch((err)=>{
        loading.close();
        console.log('修改密码,发送验证码验证身份时，发送失败'+err);
        this.$msg.error("网络异常或其它原因，请联系工作人员"+err);
      })
    },
    submitForm() {
      let formName = 'form_'+this.active
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if(this.active===0){
            this.checkCodeInfo();
          }else if(this.active===1){
            this.updatePwd();
          }
        } else {
          return false;
        }
      });
    },
    checkCodeInfo(){
      this.$http.post("/allow/checkCode?key="+this.form_0.account+"&code="+this.form_0.code).then((res)=>{
        if(res.data.code === 200){
          if(res.data.data){
            this.next();
          }else{
            this.$msg.warning(res.data.message);
          }
        }else{
          this.$msg.warning(res.data.message);
        }
      }).catch((err)=>{
        console.log('修改密码，确认验证码时，发送失败'+err);
        this.$msg.error("网络异常或其它原因，请联系工作人员"+err);
      })
    },
    updatePwd(){
      let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.1)"});
      this.$http.post("/allow/resetpwd?account="+this.form_0.account+"&password="+this.form_1.pass).then((res)=>{
        loading.close();
        if(res.data.code === 200){
          this.next()
          this.$http.post("/allow/sendHtmlResetPwd?email="+this.form_0.account+"&password="+this.form_1.pass);
        }else{
          this.$msg.warning(res.data.message);
        }
      }).catch((err)=>{
        loading.close();
        console.log('修改密码时，数据库保存密码，发送失败'+err);
        this.$msg.error("网络异常或其它原因，请联系工作人员");
      })
    },
    clearInfo(){
      this.$message({showClose: true, message: '退出成功', duration:1000, type: 'success',});
      //调转到login界面
      router.replace({path:'/loginForm'})
      this.$store.commit('setToken',null)
      this.$store.commit('setRole',null)
      this.$store.commit('setUser',null)
      this.$store.commit('setRoleInfo',null)
      localStorage.clear()
    },
    exit(){
      let that =this;
      let loading = this.$loading({lock: true, text: "正在退出",background:"rgba(255,255,255,0.5)"});
      that.$http.post("/logout?key="+that.$store.state.user["accountNumber"]+"&session="+that.$store.state.token).then((res)=>{
        loading.close();
        if(res.status === 200) {
          if (res.data.code === 200) {
            that.clearInfo();
          }else{
            setTimeout(function(){
              that.$http.post("/logout?key="+that.$store.state.user["accountNumber"]+"&session="+that.$store.state.token).then((res)=>{
                loading.close();
                if(res.status === 200) {
                  if (res.data.code === 200) {
                    that.clearInfo();
                  }else{
                    that.$message({showClose: true, message: "退出失败,请重试", duration:1500, type: 'error',});
                  }
                }
              }).catch((err)=>{
                loading.close();
                console.log(err);
                that.$message.error('退出失败，'+err);
              })
            }, 1500);
          }
        }
      }).catch((err)=>{
        setTimeout(function(){
          that.$http.post("/logout?key="+that.$store.state.user["accountNumber"]+"&session="+that.$store.state.token).then((res)=>{
            loading.close();
            if(res.status === 200) {
              if (res.data.code === 200) {
                that.clearInfo();
              }else{
                that.$message({showClose: true, message: "退出失败,请重试", duration:1500, type: 'error',});
              }
            }
          }).catch((err)=>{
            loading.close();
            console.log(err);
            that.$message.error('退出失败，'+err);
          })
        }, 1500);
      })
    }
  }
}
</script>

<style>
  .change-password{
    margin: 0;
    padding: 0;
    min-height: 100%;
    background: #F5F5F5;
    user-select: none;
  }
  .center{
    display:flex;
    align-items: center;
    justify-content:center;
  }
  change-password .change-pwd .el-card__body{
    padding: 50px;
  }
</style>
