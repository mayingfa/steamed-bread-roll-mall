import Vue from 'vue'
import Vuex from 'vuex'
import router from "../router";

Vue.use(Vuex)
export default new Vuex.Store({
  //变量存储库
  state: {
    token:null,
    role:null,
    roleInfo:null,
    user:null,
    vipSerialNumber:null,
  },
  //通过同步的方法存储
  mutations: {
    //设置vuex的token
    setToken(state,token){
      state.token=token
    },
    //设置角色
    setRole(state,role){
      state.role=role
    },
    //设置角色描述
    setRoleInfo(state,roleInfo){
      state.roleInfo=roleInfo
    },
    //设置当前用户信息
    setUser(state,user){
      state.user=user
    },
    setVipSerialNumber(state,vipSerialNumber){
      state.vipSerialNumber=vipSerialNumber
    }
  },
  //异步的方法存储，需要警告mutations
  actions: {

  }
})

