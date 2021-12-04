<!--导航栏组件-->
<template>
  <div class="header">
    <el-breadcrumb separator="/" class="el-breadcrumb">
      <el-breadcrumb-item to="/MallHome">首页</el-breadcrumb-item>
      <el-breadcrumb-item v-for="(item, index) in $route.meta.headerName" :key="index">
        <router-link v-if="item.url" :to="item.url">{{item.name}}</router-link>
        <span v-else>
        {{item.name}}
      </span>
      </el-breadcrumb-item>
      <watcher class="wat" :class="{watcher:this.$store.state.token!==null}"></watcher>
      <div class="avatar" :class="{'notLogin':this.$store.state.token===null}">
        <el-dropdown v-if="this.$store.state.token!==null" class="el-dropdown" placement="bottom-start" style="padding-top: 18px;">
          <div style="position: relative;height: 34px">
            <el-image
              v-if="getIsVip" class="animate__animated animate__rotateIn"
              src="http://img.alicdn.com/tfs/TB1felqRXXXXXa7XXXXXXXXXXXX-22-22.png"
              alt="vip" style="position: absolute;top: -12px;right: -7px;width: 19px;"></el-image>
            <el-avatar v-bind:src="getAvatarUrl" @error="errorHandler" class="el-avatar">
              <img src="https://spring-oss.oss-cn-beijing.aliyuncs.com/images/img/avat.gif" alt="默认头像"/>
            </el-avatar>
          </div>
          <el-dropdown-menu class="el-dropdown-menu" slot="dropdown" placement="bottom-end">
            <span style="border-top: 1px solid" @click="toHome()"><el-dropdown-item><i class="el-icon-house"/>首页</el-dropdown-item></span>
            <span style="border-top: 1px solid" @click="toPersonal()"><el-dropdown-item><i class="el-icon-user"/>个人中心</el-dropdown-item></span>
            <span style="border-top: 1px solid" @click="toChangePwd()"><el-dropdown-item><i class="el-icon-edit"/> 修改密码</el-dropdown-item></span>
            <span style="border-top: 1px solid" @click="toMyOrder()"
                  v-if="this.$store.state.role!==null && this.$store.state.role.indexOf(this.$VAR.ordinaryRole)!==-1">
              <el-dropdown-item><i class="el-icon-document"/>我的订单</el-dropdown-item>
            </span>
            <span style="border-top: 1px solid" @click="toMyCart()"
                  v-if="this.$store.state.role!==null && this.$store.state.role.indexOf(this.$VAR.ordinaryRole)!==-1">
              <el-dropdown-item><i class="el-icon-shopping-cart-full"/>购物车</el-dropdown-item>
            </span>
            <span style="border-top: 1px solid" @click="exit()"><el-dropdown-item><i class="el-icon-position"/>退出</el-dropdown-item></span>
          </el-dropdown-menu>
        </el-dropdown>
        <div v-else>
          <el-button round type="danger" @click="toLogin()">登录</el-button>
          <el-button  round @click="toRegister()">注册</el-button>
        </div>
      </div>
    </el-breadcrumb>
  </div>

</template>

<script>
import router from "../../router";
import watcher from "../util/weather"
export default {
  name: "Header",
  components:{watcher},
  data:function (){
    return{
      breadList: []
    }
  },
  computed:{
    //这里需要把store 动态的数据放到computed里面才会同步更新 视图
    getAvatarUrl(){
      if(this.$store.state.user!==null){
        return this.$store.state.user['avatarUrl']
      }else{
        return "https://spring-oss.oss-cn-beijing.aliyuncs.com/images/img/avat.gif"
      }
    },
    getIsVip(){
      if(this.$store.state.user!==null){
        return this.$store.state.user['isVip']
      }else{
        return false
      }
    }
  },
  methods: {
    errorHandler() {
      return true
    },
    toHome:function(){
      if(this.$store.state.role!==null && this.$store.state.role.indexOf(this.$VAR.ordinaryRole)===-1){
        if(this.$route.path!=='/HomePage'){
          this.$router.push({path:'/HomePage'});
        }
      }else{
        if(this.$route.path!=='/MallHome'){
          this.$router.push({path:'/MallHome'});
        }
      }
    },
    toLogin:function(){
      this.$router.push({path:'/loginForm'});
    },
    toRegister:function(){
      this.$router.push({path:'/registerForm'});
    },
    toPersonal:function(){
      this.$router.push({path:'/personalCenter'})
    },
    toChangePwd:function(){
      this.$router.push({path:'/changePassword'})
    },
    toMyOrder:function(){
      this.$router.push({path:'/myOrder'})
    },
    toMyCart:function(){
      this.$router.push({path:'/shoppingCart'})
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
    exit:function(){
      let that = this;
      this.$confirm('您确定要退出吗？', '温馨提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let loading = that.$loading({lock: true, text: "正在退出",background:"rgba(255,255,255,0.5)"});
        that.$http.post("/logout?key="+that.$store.state.user["accountNumber"]+"&session="+that.$store.state.token).then((res)=>{
          if(res.status === 200) {
            if (res.data.code === 200) {
              loading.close();
              that.clearInfo();
            }else{
              setTimeout(function(){
                that.$http.post("/logout?key="+that.$store.state.user["accountNumber"]+"&session="+that.$store.state.token).then((res)=>{
                  loading.close();
                  if(res.status === 200) {
                    if (res.data.code === 200) {
                      loading.close();
                      that.clearInfo();
                    }else{
                      loading.close();
                      that.$message({showClose: true, message: "退出失败,请重试", duration:1500, type: 'error',});
                    }
                  }
                }).catch((err)=>{
                  loading.close();
                  console.log(err);
                  that.$message.error('退出失败，'+err);
                })
              }, 1000);
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
      }).catch(() => {});
    }
  }
}
</script>

<style>
  .header .el-breadcrumb{
    height: 100%;
    background: #FFFFFF;
    font-size: 15px;
    line-height: 4;
    padding: 0 30px;
    user-select: none;
  }
  .header .avatar{
    position: absolute;
    right: 83px;
  }

  .header .notLogin{
    right: 50px!important;
  }

  .header .el-avatar{
    height: 35px;
    width: 35px;
    cursor:pointer;
  }
  .header .el-breadcrumb__item:last-of-type>:last-child{
    display: none!important;
  }
  .header .el-dropdown{
    display: flex;
  }
  .header .el-dropdown-menu{
    padding: 0!important;
    text-align: center;
    user-select: none;
    width: 100px;
  }

  .header .watcher{
    position: absolute;
    right: 140px!important;
    cursor: pointer;
  }
  .header .watcher .sw-card-slim-container{
    position: absolute;
    right: -150px!important;
  }

  .header .wat{
    position: absolute;
    right: 240px;
  }
  .header .wat .sw-card-slim-container{
    position: absolute;
    right: -150px;
  }
</style>
