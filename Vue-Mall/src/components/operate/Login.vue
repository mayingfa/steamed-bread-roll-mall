<template>
  <div class="login-page">
    <div class="login-header">
      <i class="el-icon-phone-outline"/>
      <span>客户服务热线: 400 065 5799</span>
    </div>
    <div class="login-box">
      <div class="left-half">
        <div class="logo">
          <el-image style="width: 300px; height: 70px"
                    src="https://spring-oss.oss-cn-beijing.aliyuncs.com/images/img/logo.png" fit="fill"></el-image>
        </div>
        <ul class="geek-slide">
          <li>
            <i class="iconfont iconzuhejiekou"></i>
            <span class="title">自由</span>
            <span class="description">想买就买，多种商品任你选择</span>
          </li>
          <li>
            <i class="iconfont iconyiduiyigoutong"></i>
            <span class="title">方便</span>
            <span class="description">足不出户，送货上门</span>
          </li>
          <li>
            <i class="iconfont icongaoxiao"></i>
            <span class="title">高效</span>
            <span class="description">24小时客服在线，为您提供最优质服务</span>
          </li>
        </ul>
      </div>
      <div class="right-half">
        <div class="form-box">
          <router-view/>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {}
  },
  methods: {},
  created() {

  }
}
</script>

<style scoped>
.login-page {
  width: 100%;
  height: 100vh;
  background: #5dd5c8 url(https://spring-oss.oss-cn-beijing.aliyuncs.com/images/img/bgimg.png) center bottom no-repeat;
}

.login-page .login-header {
  margin: 0;
  position: absolute;
  top: 50px;
  right: 75px;
  width: 250px;
  color: #FFFFFF;
  opacity: .8;
}

.login-page .login-header span {
  font-size: 16px;
}

.login-page .login-box {
  margin: 0;
  display: grid;
  grid-template-columns: 250px 1fr;
  position: absolute;
  width: 750px;
  height: 524px;
  padding: 0;
  background: #fff;
  box-shadow: 0 6px 13px 0 rgba(0, 0, 0, 0.1);
  border-radius: 10px;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.login-page .login-box .left-half {
  height: 100%;
}

.login-page .login-box .left-half .logo {
  position: absolute;
  top: 60px;
  left: 40px;
}

.login-page .left-half .logo p {
  margin: 3px 0 0;
  line-height: 20px;
  font-size: 14px;
  text-align: left;
}

.login-page .left-half .geek-slide {
  position: absolute;
  top: 160px;
  left: 60px;
  list-style: none;
  text-align: left;
  padding: 0;
  margin: 0;
}

.login-page .left-half .geek-slide li {
  margin-bottom: 32px;
  position: relative;
}

.login-page .left-half .geek-slide li i {
  position: absolute;
  top: 16px;
  font-size: 24px;
  color: #8c8c8c;
}

.login-page .left-half .geek-slide .title {
  display: block;
  margin-left: 50px;
  line-height: 22px;
  color: #8d92a1;
  font-size: 16px;
  font-weight: 700;
}

.login-page .left-half .geek-slide .description {
  display: block;
  margin-left: 50px;
  margin-top: 2px;
  line-height: 18px;
  color: #b0b4c1;
  font-size: 13px;
}

.login-page .login-box .right-half {
  padding: 60px 40px 0 100px;
}

/*右侧登录盒子*/
.login-page .login-box .right-half .form-box {
  width: 90%;
  height: 310px;
  margin: 0 auto;
}
</style>
