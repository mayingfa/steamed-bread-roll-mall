<template>
  <!--底部-->
  <div class="mall-footer">
    <div class="container">
      <div class="footer-service">
        <ul class="list-service clearfix">
          <li><i class="el-icon-bell"></i>预约维修服务</li>
          <li><i class="el-icon-time"></i>7天无理由退货</li>
          <li><i class="el-icon-position"></i>15天免费换货</li>
          <li><i class="el-icon-present"></i>满99元包邮</li>
          <li><i class="el-icon-location-outline"></i>520余家售后网点</li>
        </ul>
      </div>
      <div class="footer-copyright">
        <div class="clearfix" style="width: 32%;min-width: 456px;margin: 0 auto;padding: 10px 0">
          <el-image src="https://spring-oss.oss-cn-beijing.aliyuncs.com/images/img/bread-roll-logo.png"></el-image>
          <div class="copyright">花卷买卖公司版权所有 ©2020-{{new Date().getFullYear()}}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "MallFooter"
  }
</script>

<style>
  .mall-footer{
    background: #FFF;
    height: 150px;
  }

  .mall-footer .container{
    width: 80%;
    height: 100%;
    margin: 0 auto;
  }

  .mall-footer .container .footer-service{
    padding: 27px 0!important;
    min-width: 754px!important;
    border-bottom: 1px solid #e0e0e0!important;
  }

  .mall-footer .container .footer-service .list-service{
    margin: 0;
    padding: 0;
    list-style-type: none;
  }
  .mall-footer .list-service li{
    float: left;
    width: 19.8%;
    height: 25px;
    border-left: 1px solid #e0e0e0;
    font-size: 16px;
    line-height: 25px;
    text-align: center;
    cursor: pointer;
  }

  .mall-footer .list-service li:first-child{
    border-left: none;
  }

  .mall-footer .list-service li:hover{
    color: #FF7634;
  }
  .mall-footer .list-service i{
    font-size: 20px;
    padding-right: 4px;
  }

  .mall-footer .copyright{

  }
  .mall-footer .footer-copyright .el-image{
    width: 180px;
    float: left;

  }

  .mall-footer .footer-copyright .copyright{
    float: left;
    height: 50px;
    line-height: 50px;
  }

  .clearfix:after{/*伪元素是行内元素 正常浏览器清除浮动方法*/
    content: "";
    display: block;
    height: 0;
    clear:both;
    visibility: hidden;
  }
  .clearfix{
    *zoom: 1;/*ie6清除浮动的方式 *号只有IE6-IE7执行，其他浏览器不执行*/
  }
</style>
