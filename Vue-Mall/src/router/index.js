import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)
export default new Router({
  routes: [
    //path: '/', redirect: '/MallHome'
    {path: '/',
      redirect: () => {
        try{
          let store= JSON.parse(localStorage.getItem("store"));
          let role = store['role'];
          if(role.indexOf('顾客')!==-1){
            return '/MallHome'
          }else{
            return '/HomePage'
          }
        }catch(e){
          return '/MallHome'
        }
      }
    },
    {path: '/login',
      name: 'Login',
      component: () => import("../components/operate/Login"),
      children: [
        {
          path:'/loginForm',
          name:'登录',
          component: () => import("../components/operate/Login_Form")
        },
        {
          path:'/registerForm',
          name:'注册',
          component: () => import("../components/operate/Register_Form")
        },
        {
          path:'/forgotPassword',
          name:'找回密码',
          component: () => import("../components/operate/Forgot_Password")
        }
      ]
    },
    {path: '/home',
      name: 'Home',
      component: () => import("../components/container/Home"),
      children: [
        {
          path: '/HomePage',
          name: '首页',
          component: () => import("../components/operate/HomePage"),
          meta:{requiresAuth:true,
          }
        },
        {
          path: '/MallHome',
          name: '商城首页',
          component: () => import("../components/mall/MallHome")
        },
        {
          path: '/MallProduct',
          name: '商城商品',
          component: () => import("../components/mall/MallProduct")
        },
        {
          path: '/MallPurchase',
          name: '购买商品',
          component: () => import("../components/mall/MallPurchase"),
          meta:{requiresAuth:true,
            headerName:[{ name: '购买商品' }]
          }
        },
        {
          path: '/MyOrder',
          name: '我的订单',
          component: () => import("../components/user/MyOrder"),
          meta:{requiresAuth:true,
            headerName:[{ name: '我的订单' }]
          }
        },
        {    //商品列表
          path: '/ProductList',
          name: '商品列表',
          component: () => import("../components/product/ProductList"),
          meta:{requiresAuth:true,
                productRole:true,
                headerName:[
                  { name: '商品', url: '/ProductList'},
                  { name: '商品列表' }]
          }
        },{ //添加商品
          path: '/ProductAdd',
          name: '添加商品',
          component: () => import("../components/product/ProductAdd"),
          meta:{requiresAuth:true,
                productRole:true,
                headerName:[
                  { name: '商品', url: '/ProductList'},
                  { name: '添加商品' }]
          }
        },{ //商品类别
          path: '/ProductType',
          name: '商品类别',
          component: () => import("../components/product/ProductType"),
          meta:{requiresAuth:true,
                productRole:true,
                headerName:[
                  { name: '商品', url: '/ProductList'},
                  { name: '商品类别' }]
          }
        },{ //商品品牌
          path: '/ProductBrand',
          name: '商品品牌',
          component: () => import("../components/product/ProductBrand"),
          meta:{requiresAuth:true,
                productRole:true,
                headerName:[
                  { name: '商品', url: '/ProductList'},
                  { name: '商品品牌' }]
          }
        },{ //商品规格
          path: '/ProductSpecs',
          name: '商品规格',
          component: () => import("../components/product/ProductSpecs"),
          meta:{requiresAuth:true,
                productRole:true,
                headerName:[
                  { name: '商品', url: '/ProductList'},
                  { name: '商品规格' }]
          }
        },{ //商品采购
          path: '/Purchase',
          name: '商品采购',
          component: () => import("../components/purchase/Purchase"),
          meta:{requiresAuth:true,
                productRole:true,
                headerName:[
                  { name: '商品', url: '/ProductList'},
                  { name: '商品采购' }]
          }
        },{ //供应商
          path: '/Supplier',
          name: '供应商管理',
          component: () => import("../components/purchase/Supplier"),
          meta:{requiresAuth:true,
                productRole:true,
                headerName:[
                  { name: '商品', url: '/ProductList'},
                  { name: '供应商管理' }]
          }
        },{ //订单列表列表
          path: '/OrderList',
          name: '订单列表',
          component: () => import("../components/order/OrderList"),
          meta:{requiresAuth:true,
                orderRole:true,
                headerName:[
                  { name: '订单', url: '/OrderList'},
                  { name: '订单列表' }]
          }
        },{ //订单详情
          path: '/OrderDetail',
          name: '订单详情',
          component: () => import("../components/order/OrderDetail"),
          meta:{requiresAuth:true,
                orderRole:true,
                headerName:[
                  { name: '订单', url: '/OrderList'},
                  { name: '订单详情' }]
          }
        },{ //退货原因
          path: '/ReturnReason',
          name: '退货原因设置',
          component: () => import("../components/return/ReturnReason"),
          meta:{requiresAuth:true,
                orderRole:true,
                headerName:[
                  { name: '订单', url: '/OrderList'},
                  { name: '退货原因设置' }]
          }
        },{ //商品退货
          path: '/ReturnGoods',
          name: '退货申请处理',
          component: () => import("../components/return/ReturnGoods"),
          meta:{requiresAuth:true,
                orderRole:true,
                headerName:[
                  { name: '订单', url: '/OrderList'},
                  { name: '退货申请处理' }]
          }
        },{ //商品轮播图
          path: '/SlideShow',
          name: '商品轮播图',
          component: () => import("../components/marketing/SlideShow"),
          meta:{requiresAuth:true,
            orderRole:true,
            headerName:[
              { name: '营销', url: '/SlideShow'},
              { name: '商品轮播图' }]
          }
        }, { //管理员列表
          path: '/AdminList',
          name: '管理员列表',
          component: () => import("../components/user/AdminList"),
          meta: {
            requiresAuth: true,
            userRole: true,
            headerName: [
              {name: '权限', url: '/AdminList'},
              {name: '用户列表'}]
          }
        },{ //顾客列表
          path: '/UserList',
          name: '用户列表',
          component: () => import("../components/user/UserList"),
          meta:{requiresAuth:true,
                userRole:true,
                headerName:[
                  { name: '权限', url: '/UserList'},
                  { name: '用户列表' }]
          }
        },{ //角色列表
          path: '/RoleList',
          name: '角色列表',
          component: () => import("../components/user/RoleList"),
          meta:{requiresAuth:true,
                userRole:true,
                headerName:[
                  { name: '权限', url: '/UserList'},
                  { name: '角色列表' }]
          }
        },{ //个人中心
          path: '/personalCenter',
          name: '个人中心',
          component: () => import("../components/user/PersonalCenter"),
          meta:{requiresAuth:true,
            headerName:[{ name: '个人中心' }]
          }
        },{ //修改密码
          path: '/changePassword',
          name: '修改密码',
          component: () => import("../components/user/ChangePassword"),
          meta:{requiresAuth:true,
            headerName:[{ name: '修改密码' }]
          }
        },{ //修改密码
          path: '/shoppingCart',
          name: '购物车',
          component: () => import("../components/user/ShoppingCart"),
          meta:{requiresAuth:true,
            headerName:[{ name: '购物车' }]
          }
        },{ //商品评价
          path: '/productReview',
          name: '商品评价',
          component: () => import("../components/mall/ProductReview"),
          meta:{
            headerName:[{ name: '商品评价' }]
          }
        },{
          path:'/NoAccess',
          name:'暂无权限访问',
          component: () => import("../components/util/NoAccess")
        }
      ],
    },

  ]
})

let originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

