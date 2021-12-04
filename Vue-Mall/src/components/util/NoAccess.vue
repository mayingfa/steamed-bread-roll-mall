<template>
  <div class="no-access">
    <div class="box">
      <el-image class="image"
        src="https://spring-oss.oss-cn-beijing.aliyuncs.com/images/img/401.475d4b80.png">
      </el-image>
      <div class="text">
        <h2>抱歉！</h2>
        <h3>您没有操作权限...</h3>
        <p>当前账号没有操作权限，请联系管理员。</p>
        <el-button type="primary" round style="padding: 12px 48px;" @click="ret">{{count}}s 返回</el-button>
      </div>
    </div>
  </div>
</template>

<script>
    export default {
        name: "NoAccess",
        data() {
            return{
              count:5,
              timer:'',
            }
        },
        methods:{
          ret(){
            this.$router.go(-1);
          },
          countRet(){
            const self = this;
            this.timer =  setInterval(() =>{
              self.count--
              if(self.count === 0){
                clearInterval(self.timer)
                self.count = 5
                self.ret();
              }
            },1000)
          },
        },
        created(){
          this.countRet();
        },
        beforeDestroy() {
         clearInterval(this.timer)
        }
    }
</script>

<style>
  .no-access{
    padding: 0;
    margin: 0;
    width: 100%;
    height: 100%;
    background-color: #FFF;
    display: flex;
    justify-content: center;
    align-items: center;
    user-select: none;
  }

  .no-access .box{
    position: relative;
    height: 300px;
    width: 650px;
  }

  .no-access .box .image{
    position: absolute;
    width: 500px;
    left: 0;
  }
  .no-access .box .text{
    position: absolute;
    text-align: left;
    right: 0;
  }

  .no-access .box .text h2{
    color: rgb(25, 144, 255);
    font-size: 38px;
    margin:0 0 20px;
  }
  .no-access .box .text h3{
    margin: 0 0 10px;
    font-size: 21px;
  }
  .no-access .box .text p{
    font-size: 14px;
    margin-bottom: 60px;
  }
</style>
