<!--验证码组件-->
<template>
  <div class="randomised" style="user-select: none">
    <el-form inline class="login-form" status-icon :rules="loginRules" ref="codeForm" @submit.native.prevent :model="codeForm" label-width="50px">
      <!-- 随机验证码 输入框 -->
      <el-form-item prop="verifyCode" style="margin-right:0">
        <el-input v-model="codeForm.verifyCode" size="small" placeholder="请输入验证码"  style="user-select: text"></el-input>
      </el-form-item>
      <!-- 随机验证码 -->
      <el-form-item>
        <div class="identifyBox">
          <div @click="refreshCode">
            <s-identify :identifyCode="identifyCode"></s-identify>
          </div>
          <!-- 刷新验证码 -->
          <el-button @click="refreshCode" type='text'>看不清，换一张</el-button>
        </div>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import SIdentify from './SIdentify'
export default {
  name: 'AuthCode',
  data() {
    // 自定义验证规则：验证码验证规则
    const validateVerifyCode = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入验证码'))
        this.isCodeTrue=false;
      } else if (value !== this.identifyCode) {
        this.isCodeTrue=false;
        callback(new Error('验证码不正确'))
      } else {
        callback()
      }
    }
    return {
      isCodeTrue:false,
      codeForm: {
        verifyCode: ''
      },
      identifyCodes: '1234567890',
      identifyCode: '',
      loginRules: {
        verifyCode: [
          { required: true, trigger: 'blur', validator: validateVerifyCode },
        ]
      }
    }
  },
  components: {
    // 注册绘制随机验证码的组件
    SIdentify
  },
  created() {},
  mounted() {
    // 验证码初始化
    this.identifyCode = ''
    this.makeCode(this.identifyCodes, 4)
  },
  computed: {},
  methods: {
    //提交表单
    submitForm(codeForm) {
      this.$refs[codeForm].validate((valid) => {
        if (valid) {
          this.isCodeTrue=true;
        } else {
          return false;
        }
      });
      return this.isCodeTrue
    },
    resetForm(codeForm) {
      this.$refs[codeForm].resetFields();
    },
    // 生成随机数
    randomNum(min, max) {
      return Math.floor(Math.random() * (max - min) + min)
    },
    // 切换验证码
    refreshCode() {
      this.identifyCode = ''
      this.makeCode(this.identifyCodes, 4)
    },
    // 生成四位随机验证码
    makeCode(o, l) {
      for (let i = 0; i < l; i++) {
        this.identifyCode += this.identifyCodes[this.randomNum(0, this.identifyCodes.length)]
      }
    }
  }
}
</script>

<style>
  .randomised{
    width: 60%;
    margin: auto;
    display: flex;
    align-items: center;
  }
  .identifyBox {
    display: flex;
    justify-content: space-between;
  }
</style>
