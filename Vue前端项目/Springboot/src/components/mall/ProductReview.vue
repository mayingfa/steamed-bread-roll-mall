<template>
  <div class="product-review">
    <div class="product-box">
      <div class="nav-bar">
        <div v-if="productInfo" class="container">
          <router-link to="/MallHome" style="text-decoration: none" title="花卷官网">
            <el-image class="logo" style="width: 65px;height: 65px;margin-top: 10px;margin-right: 15px;" :src="productInfo.productUrl"></el-image>
          </router-link>
          <div style="display: inline-block;position:absolute;top: 35px">
            <span style="font-size: 19px;font-weight: bolder">{{productInfo.productName}}</span>
            <el-divider direction="vertical"></el-divider>
            <span style="font-size: 18px;font-weight: bolder">{{productInfo.productType}}</span>
          </div>
          <el-button  round  plain style="position: absolute;right: 0;top: 28px" @click="toBuy">立即购买</el-button>
        </div>
      </div>
    </div>
    <div class="page-main">
      <div class="review-box">
        <h2 class="title">热门评价</h2>
        <div class="review-content" v-if="productReview && productReview.length!==0">
          <div class="review-item" v-for="review in productReview" :key="review.reviewId">
            <div class="user-info">
              <el-avatar class="user-avatar" :size="50" :src="review.avatarUrl" @error="errorHandler">
                <img src="https://spring-oss.oss-cn-beijing.aliyuncs.com/images/img/avat.gif" alt="默认头像"/>
              </el-avatar>
              <span class="user-name">
                {{review.userName}}
              </span>
              <div class="info">
                <span class="review-time">
                 {{review.reviewTime}}
                </span>
                <span v-if="review.productSpecs">
                  <el-divider direction="vertical"/>
                  <span class="product-specs">
                    商品规格：{{review.productSpecs}}
                  </span>
                </span>
              </div>
              <div class="star-level">
                <el-rate v-model="review.starLevel" show-text disabled  text-color="#ff9900"></el-rate>
              </div>
            </div>
            <div class="review-info">
              <p>
                {{review.productReview}}
              </p>
            </div>
          </div>
        </div>
        <div class="empty-review" v-else>
          <h2>
            该商品还没有人评价哦！
          </h2>
          <el-button type="success" @click="toBuy">返回购买</el-button>
        </div>
      </div>
    </div>

    <!--底部-->
    <MallFooter></MallFooter>
  </div>
</template>

<script>
import MallFooter from "../mall/MallFooter";

  export default {
    name: "ProductReview",
    components: {
      MallFooter
    },
    data() {
      return {
        keyword:'',
        productInfo:null,
        productReview:null,
      }
    },
    methods:{
      errorHandler() {
        return true
      },
      toBuy(){
        this.$router.push({path:'/MallPurchase',query:{id:this.productInfo.productId}})
      },
      reqInfo(){
        let loading = this.$loading({lock: true, text: "数据加载中",background:"rgba(255,255,255,0.1)"});
        this.$http.post('/product/findByKey?productNo='+this.keyword).then((rep)=>{
          loading.close();
          if(rep.data.code===200){
            this.productInfo = rep.data.data;
          }
        }).catch((err)=>{loading.close();this.$msg.error(err)})
        this.$http.post('/productReview/findAll?productNo='+this.keyword).then((rep)=>{
          loading.close();
          if(rep.data.code===200){
            this.productReview = rep.data.data;
          }
        }).catch((err)=>{loading.close();this.$msg.error(err)})
      }
    },
    created() {
      this.keyword = this.$route.query.keyword;
      this.reqInfo();
    }
  }
</script>

<style>
.product-review{
  margin: 0;
  padding: 0;
  background: #F5F5F5;
  user-select: none;
  min-height: 100%;
  min-width: 1226px;
}
.product-review .product-box{
  position: relative;
  z-index: 4;
  background: #fff;
  border-top: 1px solid #e0e0e0;
  border-bottom: 1px solid #e0e0e0;
  -webkit-box-shadow: 0 5px 5px rgba(0,0,0,.07);
  box-shadow: 0 5px 5px rgba(0,0,0,.07);
}

.product-review .product-box .nav-bar{
  position: relative;
  height: 80px;
  width: 100%;
  margin-top: 0;
  color: #616161;
  border-bottom: 1px solid #ff6700;
}

.product-review .nav-bar .container{
  position: relative;
  width: 80%;
  min-width: 1226px;
  margin-right: auto;
  margin-left: auto;
  text-align: left;
}

.product-review .nav-bar .container .logo:hover{
  animation: myAnimation 8s;
}

@keyframes myAnimation
{
  0%   {transform:rotateY(0deg) scale(.9,.9);}
  50%  {transform:rotateY(180deg) scale(.9,.9);}
  100% {transform:rotateY(360deg) scale(.9,.9);}
}

.product-review .nav-bar .el-button span{
  font-size: 12px;
}

.product-review .page-main{
  min-height: 65vh;
  padding: 30px 0;
}

.product-review .el-divider{
  margin-top: -3px;
  width: 2px;
  height: 25px;
}

.product-review .page-main .review-box{
  width: 80%;
  margin: 0 auto;
  min-width: 1100px;
  height: 300px;
}

.product-review .page-main .review-box .title{
  margin-top: 0;
  text-align: left;
}

.product-review .page-main .review-box .review-content{
  height: 100%;
}

.product-review .page-main .review-box .review-content .review-item{
  background-color: #FFF;
  min-height: 140px;
  margin-bottom: 20px;
  padding-top: 15px;
  padding-bottom: 10px;
}

.product-review .page-main .review-item .user-info{
  position: relative;
  height: 90px;
}

.product-review .page-main .review-item .user-info .user-avatar{
  position: absolute;
  top: 10px;
  left: 20px;
}

.product-review .page-main .review-item .user-info .user-name{
  position: absolute;
  top: 15px;
  left: 90px;
  height: 18px;
  line-height: 18px;
  font-size: 18px;
  color: #8d665a;
  display: block;
}

.product-review .page-main .review-item .user-info .info{
  position: absolute;
  top: 40px;
  left: 90px;
  text-align: left;
  font-size: 14px;
  color: #b0b0b0;
}

.product-review .page-main .user-info .info .el-divider{
  margin: 0 4px;
  width: 1px;
  height: 14px;
}

.product-review .page-main .user-info .star-level{
  position: absolute;
  top: 30px;
  right: 30px;
}

.product-review .page-main .review-info{
  padding-left: 90px;
  text-align: left;
  padding-right: 32px;
}

.product-review .page-main .review-info p{
  margin-top: 0;
}

.product-review .page-main .empty-review{
  height: 273px;
  padding-left: 47%;
  margin: 65px 0 130px;
  background: url(https://spring-oss.oss-cn-beijing.aliyuncs.com/images/img/review-empty.png) no-repeat 30% 0;
  color: #b0b0b0;
  overflow: hidden;
  text-align: left;
}

.product-review .page-main .empty-review h2{
  margin: 50px 0 0 0;
  font-size: 36px;
}

.product-review .page-main .empty-review .el-button{
  margin: 35px 0 0;
  width: 170px;
  height: 46px;
  font-size: 18px;
}

</style>
