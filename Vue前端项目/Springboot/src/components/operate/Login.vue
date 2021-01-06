<!--登录页面组件-->
<template>
  <div style="perspective: 1000px;">
    <div class="login-wrap">
      <div id="login-vessel" parallaxstrength="4">
        <div class="login-box">
          <el-image src="https://spring-oss.oss-cn-beijing.aliyuncs.com/images/img/logo.png" class="nice-logo" alt="logo"></el-image>
          <router-view></router-view>
        </div>
      </div>
    </div>
    <div class="fly bg-fly-circle1"></div>
    <div class="fly bg-fly-circle2"></div>
    <div class="fly bg-fly-circle3"></div>
    <div class="fly bg-fly-circle4"></div>
  </div>
</template>

<script>
export default {
    name: "Login",
    data:function(){
      return{
        msg:"123",
        ele: undefined,
      }
    },
    methods:{
      beforeCreate () {
        document.querySelector('body').setAttribute('style', 'overflow:hidden;');
      },
      //销毁前清除
      beforeDestroy () {
        document.querySelector('body').removeAttribute('style')
      },
      mouseEffect(){
        let that = this;
        let i = 0;
        document.onmousemove = mouseMove;

        function mousePosition(ev){
          if(ev.pageX || ev.pageY){
            return {x:(ev.pageX-document.body.clientWidth/2), y:(ev.pageY-document.body.clientHeight/2)};
          }
          return {
            x:ev.clientX + document.body.scrollLeft - document.body.clientLeft,
            y:ev.clientY + document.body.scrollTop - document.body.clientTop
          };
        }

        function mouseMove(ev){
          if(i++ > 6){
            i=0;
            ev = ev || window.event;
            let mousePos = mousePosition(ev);
            let x = (mousePos.y/50).toFixed(2)*-1;
            let y = (mousePos.x/80).toFixed(2);
            that.ele.style.transform = "rotateX(" + x + "deg) rotateY(" + y + "deg)";
          }
        }
      }
    },
    mounted() {
      this.beforeCreate();
      this.ele=document.getElementById("login-vessel");
      this.mouseEffect();
    },
    destroyed:function(){
      this.beforeDestroy();
    }
  }
</script>

<style>
  .login-wrap{
    width: 100%;
    min-height: 100vh;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    align-items: center;
    padding: 15px;
    background: #5dd5c8 url(https://spring-oss.oss-cn-beijing.aliyuncs.com/images/img/bgimg.png) bottom no-repeat;
  }
  #login-vessel{
    transition-property: transform;
    transition-duration: 500ms;
    transform-origin: center center;
    transition-timing-function: cubic-bezier(0.23, 1, 0.32, 1);
    transform: rotateX(0deg) rotateY(0deg) translate3d(0px, 0px, 20px);
    user-select:none;
  }

  .login-wrap .login-box {
    overflow: hidden;
    height: 486px;
    position: relative;
    width: 350px;
    max-width: 350px;
    margin: 4em auto;
    border-radius: 8px;
    box-shadow: 1px 2px 15px rgba(0,0,0,.3);
    background: #fff url(https://spring-oss.oss-cn-beijing.aliyuncs.com/images/img/logbg.jpg) no-repeat bottom;
    text-align: center;
    -webkit-backface-visibility: hidden;
    transition-property: transform;
    transition-duration: .3s;
    z-index: 80;
  }

  .nice-logo{
    width: 200px;
    margin: 62px 0 0;
  }

  .fly {
    pointer-events: none;
    position: fixed;
    z-index: 100;
  }

  .bg-fly-circle1 {
    left: 40px;
    top: 100px;
    width: 100px;
    height: 100px;
    border-radius: 50%;
    background: linear-gradient(90deg,rgba(100,84,239,.07) 0,rgba(48,33,236,.04));
    -webkit-animation: move 2.5s linear infinite;
    animation: move 2.5s linear infinite;
  }

  .bg-fly-circle2 {
    left: 3%;
    top: 60%;
    width: 150px;
    height: 150px;
    border-radius: 50%;
    background: linear-gradient(90deg,rgba(100,84,239,.08) 0,rgba(48,33,236,.04));
    -webkit-animation: move 3s linear infinite;
    animation: move 3s linear infinite;
  }

  .bg-fly-circle3 {
    right: 2%;
    top: 140px;
    width: 145px;
    height: 145px;
    border-radius: 50%;
    background: linear-gradient(90deg,rgba(100,84,239,.1) 0,rgba(48,33,236,.04));
    -webkit-animation: move 2.5s linear infinite;
    animation: move 2.5s linear infinite;
  }

  .bg-fly-circle4 {
    right: 5%;
    top: 60%;
    width: 160px;
    height: 160px;
    border-radius: 50%;
    background: linear-gradient(90deg,rgba(100,84,239,.02) 0,rgba(48,33,236,.04));
    -webkit-animation: move 3.5s linear infinite;
    animation: move 3.5s linear infinite;
  }

  @keyframes move {
    0% {
      transform: translateY(0);
    }
    50% {
      transform: translateY(25px);
    }
    100% {
      transform: translateY(0);
    }
  }
</style>
