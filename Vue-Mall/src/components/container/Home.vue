<!--中间主界面组件-->
<template>
  <div class="home">
    <el-container class="el-container">
      <!--侧边栏-->
      <el-aside class="el-aside" v-if="this.$store.state.user!==null && (this.$store.state.role!==null && this.$store.state.role.indexOf(this.$VAR.ordinaryRole)===-1)">
        <Aside></Aside>
      </el-aside>
      <!--导航栏-->
      <el-container class="el-container">
        <el-header class="el-header">
          <Header></Header>
        </el-header>
        <!--主面板-->
        <el-main class="el-main">
          <router-view></router-view>
        </el-main>
        <!--底部-->
        <el-footer class="el-footer" v-if="this.$store.state.role!==null && this.$store.state.role.indexOf(this.$VAR.ordinaryRole)===-1">
          <div class="footer"><span>Copyright&nbsp;©&nbsp;2020&nbsp;-&nbsp;{{new Date().getFullYear()}}&nbsp;&nbsp;花卷买卖公司&nbsp;版权所有</span></div>
        </el-footer>
      </el-container>
    </el-container>
  </div>
</template>

<script>
  import Aside from "./Aside";
  import Header from "./Header";
  import ProductList from "../product/ProductList";
  import ProductAdd from "../product/ProductAdd";
  export default {
    name: "Home",
    components: {Aside,Header,ProductList,ProductAdd}
  }
</script>

<style>
  .home{
    height: 100%;
  }

  .home .el-container{
    height: 100%;
  }

  .home .el-header{
    padding: 0;
    background-color: #FFF;
    color: #333;
    text-align: center;
    border-bottom: 1px solid #C1C1C1;
  }
  .home .el-footer{
    padding: 0;
    color: #333;
    height: 50px!important;
    background: #FFFFFF;
    line-height: 50px;
  }

  .home .el-aside {
    width:180px!important;
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
  }

  .home .el-main {
    background-color: #FFF;
    border-top: 1px solid #C1C1C1;
    color: #333;
    text-align: center;
    padding: 0;
    margin: 0;
  }

  .home .el-main::-webkit-scrollbar {/*滚动条整体样式*/
    width: 0;     /*高宽分别对应横竖滚动条的尺寸*/
  }

  .home .footer{
    width: auto;
    height: 100%;
    line-height: 50px;
    font-size: 10px;
    user-select: none;
  }
</style>
