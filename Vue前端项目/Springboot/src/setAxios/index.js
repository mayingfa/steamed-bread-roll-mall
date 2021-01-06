import axios from 'axios'
import store from '../../src/store'
import router from '../../src/router'
import {Notification} from "element-ui";


//http全局拦截
//token要放在我们请求的header上面带回给后端
export default function index(){
  //请求拦截
  axios.interceptors.request.use(
    config=>{
      if(store.state.token){
        config.headers.token=store.state.token
      }
      return config
    }
  )
  //每次请求有返回的，都是先警告拦截器最先的
  axios.interceptors.response.use(
    response=>{
      if(response.status===200){
        const data=response.data
        switch (data.code) {
          case 401:
            //登录过期 需求重新登录 情况vuex的token和localStorage的token
            store.commit('setToken',null)
            store.commit('setRole',null)
            store.commit('setUser',null)
            store.commit('setRoleInfo',null)
            localStorage.clear()
            Notification({title: '警告', message:data.message, type: 'warning'});
            //调转到login界面
            router.replace({path:'/loginForm'})
            break
          case 403:
            //暂无权限
            this.$msg.warning({message: data.message, showClose: true, duration:2000});
            break
        }
      }
      return response
    }
  )
}
