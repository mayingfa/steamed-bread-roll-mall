<template>
  <div class="mall-product">
    <!--回到顶部-->
    <el-backtop target=".mall-product" style="height: 50px;width: 50px">
      <i class="el-icon-top"></i>
    </el-backtop>
    <!--商品信息-->
    <div class="mall-info clearfix">
      <div class="product-hd">
        <div class="product-box">
          <div style="position:absolute;padding: 23px;">
            <span>{{this.byC}}</span>
            <span style="padding-right: 12px;">:</span>
            <span style="color:#FF7918;cursor: pointer">{{this.keyword}}</span>
          </div>
        </div>
      </div>
      <div class="mall-container clearfix" v-if="productInfo">
        <ul v-if="productInfo.length!==0">
          <li v-for="(product,index) in this.productInfo" :key="index" @click="buy(product.productId)">
            <div style="float: left;width: 44%;height: 100%">
              <el-image :src="product.productUrl" style="width: 100%"></el-image>
            </div>
            <div class="pro-con">
              <span :title="product.productName" class="name">{{product.productName}}</span>
              <span class="desc">{{product.productDescribe}}</span>
              <span class="price">{{product.outPrice}}元</span>
              <div style="position: absolute;bottom: 30px;">
                <el-button size="small" type="danger" style="padding: 9px 29px;">
                  <span v-if="$store.state.user!=null">立即抢购</span>
                  <span v-else>登录后抢购</span>
                </el-button>
              </div>
            </div>
          </li>
        </ul>
        <div v-else class="no-product">
          <el-image style="width:400px;height:388px" src="https://spring-oss.oss-cn-beijing.aliyuncs.com/images/img/noproduct.png"></el-image>
        </div>
      </div>
    </div>
    <!--底部-->
    <MallFooter></MallFooter>
  </div>
</template>

<script>
import MallFooter from "./MallFooter";
  export default {
    name: "MallProduct",
    components: {
      MallFooter
    },
    data(){
      return{
        keyword:null,
        by:null,   /*通过（商品名称、类型、品牌）查询*/
        byC:null,
        productInfo:null
      }
    },
    methods:{
      buy(productId){
        if(this.$store.state.user===null){
          this.$notify({
            title: '警告',
            message: '请先登录',
            type: 'warning'
          });
          this.$router.push({path:'/loginForm'});
        }else{
          this.$router.push({path:'/MallPurchase',query:{id:productId}})
        }
      }
    },
    created() {
        if(this.$route.query.keyword!=null){
          this.keyword = this.$route.query.keyword
        }
        if(this.$route.query.by!=null){
          this.by = this.$route.query.by
        }
        if(this.keyword!==null && this.by!=null){
          if(this.by==='name'){
            this.byC = '商品名称'
            this.$http.post('/product/findAllLikeName?keyWord='+this.keyword).then((res=>{
              if(res.data.code===200){
                this.productInfo = res.data.data;
              }
            })).catch((err)=>{console.log(err)})
          }else if(this.by==='type'){
            this.byC = '商品类型'
            this.$http.post('/product/findAllLikeType?keyWord='+this.keyword).then((res=>{
              if(res.data.code===200){
                this.productInfo = res.data.data;
              }
            })).catch((err)=>{console.log(err)})
          }else if(this.by==='brand'){
            this.byC = '商品品牌'
            this.$http.post('/product/findAllLikeBrand?keyWord='+this.keyword).then((res=>{
              if(res.data.code===200){
                this.productInfo = res.data.data;
              }
            })).catch((err)=>{console.log(err)})
          }
        }
    }
  }
</script>

<style>
  .mall-product{
    margin: 0;
    padding: 0;
    background: #F5F5F5;
    user-select: none;
    min-height: 100%;
    overflow-x: hidden;
  }

  .mall-product .mall-info{
    width: 80%;
    min-width: 1325px;
    margin: 0 auto;
    border: 1px;
    min-height: 800px;
  }

  .mall-product .product-hd{
    background: #f5f5f5 url(https://spring-oss.oss-cn-beijing.aliyuncs.com/images/img/title.png) 50% 0 no-repeat;
    height: 170px;
    position: relative;
  }

  .mall-product .product-hd .product-box{
    position: absolute;
    bottom: 0;
    height: 40%;
    width: 100%;
    text-align: left;
    background: #414141;
    color: white;
  }

  .mall-product .mall-container{
    width: 100%;
  }

  .mall-product .mall-container ul{
    width: 100%;
    margin: 0;
    padding: 0.5%;
  }

  .mall-container ul:after, .mall-container ul:before{
    content: " ";
    display: table;
  }

  .mall-product .mall-container li{
    float: left;
    list-style: none;
    background: #fff;
    width: 31%;
    height: 190px;
    margin: 16px 1% 17px;
    -webkit-transition: all .2s linear;
    transition: all .2s linear;
    position: relative;
    z-index: 2;
    cursor: pointer;
    overflow: hidden;
  }

  .mall-product .mall-container li:hover{
    z-index: 2;
    -webkit-box-shadow: 0 15px 30px rgba(0,0,0,.1);
    box-shadow: 0 15px 30px rgba(0,0,0,.1);
    -webkit-transform: translate3d(0,-2px,0);
    transform: translate3d(0,-2px,0);
  }


  .mall-product .mall-container .pro-con{
    float: left;
    padding-top: 30px;
    padding-left: 6%;
    width: 50%;
    text-align: left;
  }

  .mall-product .mall-container .pro-con .name{
    font-size: 16px;
    color: #333;
    height: 16px;
    overflow: hidden;
    _zoom: 1;
    text-overflow: ellipsis;
    white-space: nowrap;
    display: block;
    line-height: 16px;
  }
  .mall-product .mall-container .pro-con .desc{
    color: rgba(0,0,0,.54);
    margin-top: 7px;
    font-size: 12px;
    line-height: 16px;
    height: 32px;
    width: 100%;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2;
    overflow: hidden;
  }
  .mall-product .mall-container .pro-con .price{
    font-size: 20px;
    color: #f1393a;
    line-height: 1;
    margin-top: 14px;
  }


  .mall-container .no-product{
    min-height: 630px;
    line-height: 630px;
    display: flex;
    justify-content: center;
    align-items: center;
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

  .mall-product .mall-container{
    margin: 0;
    padding: 0;
    min-height: 600px;
    height: auto;
    background: #F5F5F5;
    user-select: none;
  }

</style>
