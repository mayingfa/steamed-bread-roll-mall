<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>

import router from "./router";

export default {
  name: 'App',
  methods:{
    clearInfo(){
      this.$store.commit('setToken',null)
      this.$store.commit('setRole',null)
      this.$store.commit('setUser',null)
      this.$store.commit('setRoleInfo',null)
      localStorage.clear()
      //调转到login界面
      router.replace({path:'/loginForm'})
    },
    log(){
      setTimeout(function(){
        console.clear();
        console.log('%c 花卷买卖  %c  您的帐号存在异常行为，已被封停（请联系工作人员）  ','font-size:12px;color:#FADFA3;background-color:#030307;line-height:25px;padding:0 5px;margin:8px 0','font-size:12px;color:#030307;background-color:#FADFA3;line-height:25px;padding:0 3px');
        console.log('%c 联系方式  %c 4002-106-2335 ','font-size:12px;color:white;background-color:#FF4081;line-height:25px;padding:0 5px;margin:7px 0','font-size:12px;color:white;background-color:#424242;line-height:25px;padding:0 3px');
      }, 2000);
    },
    exit(accountNumber,token){
      let that = this;
      that.$http.post("/logout?key="+accountNumber+"&session="+token).then((res)=>{
        if(res.status === 200) {
          if (res.data.code !== 200) {
            setTimeout(function(){
              that.$http.post("/logout?key="+accountNumber+"&session="+token)
            }, 1000);
          }
        }
      }).catch(()=>{
        setTimeout(function(){
          that.$http.post("/logout?key="+accountNumber+"&session="+token)
        }, 1000);
      })
    }
  },
  created () {
    //在页面加载时读取localStorage里的状态信息
    if (localStorage.getItem("store")) {
      this.$store.replaceState(Object.assign({}, this.$store.state,JSON.parse(localStorage.getItem("store"))))
      if(this.$store.state.user!==null){
      let accountNumber = this.$store.state.user.accountNumber;
        this.$http.post('/user/existKey?key='+accountNumber).then((res)=>{
          if(res.data.code===200){
            if(!res.data.data){
              const confirmText = [`帐号：${accountNumber}` , `已被注销（有疑问请联系工作人员）`];
              const newData = []
              const h = this.$createElement
              let i =0;
              for ( i in confirmText) {
                if (confirmText.hasOwnProperty(i)) {
                  newData.push(h('p', null, confirmText[i]))
                }
              }
              this.$notify({
                title: '警告',
                message: h('div', null, newData),
                type: 'warning'
              });
              this.clearInfo();
            }else{
              this.$http.post('/user/userState?accountNumber='+accountNumber).then((res)=>{
                if(res.data.code===200){
                  if(!res.data.data){
                    this.exit(accountNumber,this.$store.state.token);
                    this.log();
                    this.clearInfo();
                    this.$msg.error("您的帐号存在异常行为，已被封停（请联系工作人员）")
                  }else{
                    this.$http.post('/vip/existsVip?accountNumber='+accountNumber).then((rep)=>{
                      if(rep.data.code === 200) {
                        let user = this.$store.state.user;
                        user['isVip'] = rep.data.data;
                        this.$store.commit('setUser', user)
                      }
                    }).catch((err)=>{this.$msg.error(err)})
                  }
                }
              }).catch((err)=>{this.$msg.error(err)})
            }
          }
        }).catch((err)=>{this.$msg.error(err)})
      }
    }

    //在页面刷新时将vuex里的信息保存到localStorage里
    window.addEventListener("beforeunload",()=>{
      if(localStorage.length!==0){
        localStorage.setItem("store",JSON.stringify(this.$store.state))
      }else{
        this.$router.push("/loginForm")
      }
    })
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  height: 100vh;
}

.el-dropdown-menu{
  min-width: 137px;
}

</style>
