<template>
  <div class="mall-container">
    <!--回到顶部-->
    <el-backtop target=".mall-container" style="height: 50px;width: 50px">
      <i class="el-icon-top"></i>
    </el-backtop>
    <!--导航栏-->
    <div class="mall-nav">
      <el-menu :default-active="activeIndex" class="el-menu-demo" :collapse-transition="false" active-text-color="#409EFF" mode="horizontal" @select="handleSelect">
        <el-menu-item index="1" style="width: 12%;margin-left:10%;position: relative">
          <router-link to="/MallHome" style="text-decoration: none;position: absolute;right: 0">
            <el-image class="animate__animated animate__fadeIn" style="width: 220px;height: 60px;" src="https://spring-oss.oss-cn-beijing.aliyuncs.com/images/img/bread-roll-logo.png"></el-image>
          </router-link>
        </el-menu-item>
        <el-menu-item index="2" style="width: 50%">
          <el-input placeholder="请输入内容" v-model="queryProductInfo" @keyup.enter.native="queryProduct(queryProductInfo)" class="input-with-select" style="height: 45px;">
            <el-select v-model="select"  slot="prepend" placeholder="请选择" style="width: 120px;user-select: none;">
              <el-option label="商品名称" value="1"></el-option>
              <el-option label="商品分类" value="2"></el-option>
              <el-option label="商品品牌" value="3"></el-option>
            </el-select>
            <el-button slot="append" icon="el-icon-search" @click="queryProduct(queryProductInfo)"></el-button>
          </el-input>
        </el-menu-item>
        <el-submenu index="3" style="width: 10%">
          <template slot="title">商品分类</template>
          <el-menu-item v-for="(item,i) in productType" :index="'3-'+i" :key="'3-'+i" @click="queryProduct(item,'type')">{{item}}</el-menu-item>
        </el-submenu>
        <el-submenu index="4" style="width: 10%">
          <template slot="title">商品品牌</template>
          <el-menu-item v-for="(item,i) in productBrand" :index="'4-'+i" :key="'4-'+i" @click="queryProduct(item,'brand')">{{item}}</el-menu-item>
        </el-submenu>
      </el-menu>
    </div>
    <!--轮播图-->
    <div class="slideshow" v-if="this.bannerInfo">
      <el-carousel height="480px">
        <el-carousel-item  v-for="banner in bannerInfo" :key="banner.bannerId">
          <router-link :to="banner.productUrl" style="width: 100%;height: 100%">
            <el-image :src="banner.bannerUrl" style="height: 100%;width: 100%"></el-image>
          </router-link>
        </el-carousel-item>
      </el-carousel>
    </div>
    <div class="slideshow" v-else>
      <el-carousel height="480px">
        <el-carousel-item  v-for="image in carouselImage" :key="image">
          <el-link href="#/MallHome" style="width: 100%;height: 100%">
            <el-image :src="image" style="height: 100%;width: 100%"></el-image>
          </el-link>
        </el-carousel-item>
      </el-carousel>
    </div>
    <!--新品专区-->
    <div class="new-product">
      <div class="box-hd">
        <h2 class="new-product-title" style="width: 98.5%;">
          新品专区
          <div style="position: absolute;top: 6px;right: 16px;">
            <el-button icon="el-icon-arrow-left" :disabled="butLFlag" size="mini" style="padding: 5px 12px;margin: 0" @click="newLeft()"></el-button>
            <el-button icon="el-icon-arrow-right" :disabled="butRFlag" size="mini" style="padding: 5px 12px;margin: 0" @click="newRight()"></el-button>
          </div>
        </h2>
      </div>
      <div style="overflow: hidden">
        <div class="new-sale">
          <div class="name">
            花卷买卖
          </div>
          <el-image class="animate__animated animate__flip" style="width: 100px;padding: 20px;" src="https://spring-oss.oss-cn-beijing.aliyuncs.com/images/img/nurse.png"></el-image>
          <h3>新品专区</h3>
        </div>
        <div class="new-product-info" ref="newInfo">
          <ul class="sale-list" ref="newUl">
            <li v-for="(product,index) in this.newProduct" :key="index" ref="newLi" @click="buy(product.productId)">
              <el-image class="img" :src="product.productUrl"></el-image>
              <h3 class="title">
                <el-tag type="warning" v-if="product.isNew" size="mini" style="margin-right: 3px">新品</el-tag>
                {{product.productName}}
              </h3>
              <p class="desc">{{product.productDescribe}}</p>
              <p class="price"><span>{{product.outPrice}}</span>元</p>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <!--商品信息-->
    <div class="product clearfix">
      <div class="product-info" v-for="(value, key) in this.productsInfo" :key="key">
        <el-image lazy scroll-container=".mall-container" style="height: 100px;margin: 30px 0 0;width: 98.5%;min-width:1325px" :src="value[0]['urlTop']"></el-image>
        <div class="box-hd">
          <h2 class="product-type-title">
            {{value[0]['typename']}}
          </h2>
          <div class="more" @click="queryProduct(value[0]['typename'],'type')">
            查看更多
            <i class="el-icon-video-play"></i>
          </div>
        </div>
        <ul class="product-type-promo">
          <li>
            <el-image lazy scroll-container=".mall-container" style="height: 100%;width: 100%" :src="value[0]['urlLeft']"
                      @click.native="queryProduct(value[0]['typename'],'type')"></el-image>
          </li>
        </ul>
        <ul class="product-item" v-if="value[1].length!==0">
          <li v-for="(product,index) in value[1]" :key="index" @click="buy(product.productId)">
            <a>
              <el-image lazy scroll-container=".mall-container" :src="product.productUrl"></el-image>
              <h3 class="title">
                <el-tag type="warning" v-if="product.isNew" size="mini" style="margin-right: 3px">新品</el-tag>
                {{product.productName}}</h3>
              <p class="desc">{{product.productDescribe}}</p>
              <p class="price">
                <span class="num">{{product.outPrice}}</span>元<span>起</span>
              </p>
            </a>
          </li>
        </ul>
        <div class="clearfix" v-else style="float:left;display:flex;justify-content:center;align-items: center; width:83%;height: 75%;background-color: #FFF">
          <el-image style="width: 410px" src="https://spring-oss.oss-cn-beijing.aliyuncs.com/images/img/noproduct.png"></el-image>
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
    name: "MallHome",
    components: {
      MallFooter
    },
    data() {
      return {
        carouselImage:[
          "https://spring-oss.oss-cn-beijing.aliyuncs.com/images/img/lb1.jpg",
          "https://spring-oss.oss-cn-beijing.aliyuncs.com/images/img/lb2.jpg",
          "https://spring-oss.oss-cn-beijing.aliyuncs.com/images/img/lb3.jpg",
          "https://spring-oss.oss-cn-beijing.aliyuncs.com/images/img/lb4.webp",
          "https://spring-oss.oss-cn-beijing.aliyuncs.com/images/img/lb5.jpg"
        ],
        bannerInfo:null,
        logoUrl:"https://spring-oss.oss-cn-beijing.aliyuncs.com/images/img/bread-roll-logo.png",
        activeIndex:'',  //当前激活菜单的 index
        queryProductInfo: '',
        select: "1",
        productType:[],
        productBrand:[],
        productTypeInfo:[],
        newProduct:[],
        productsInfo:null,
        productsTemp:null,
        currentX:0,
        butLFlag:true,  /*左按钮 是否可用*/
        butRFlag:false,/*右按钮 是否可用*/
        timer:null,  /*定时器*/
        product:{productId:'',productUrl:'',productName:'',productDescribe:'',outPrice:''}
      };
    },
    created(){
      this.$http.post('/banner/findAll').then((rep)=>{
        if(rep.data.code===200){
          this.bannerInfo=rep.data.data;
          for (let i = 0; i < this.bannerInfo.length; i++) {
            let url = this.bannerInfo[i].productUrl;
            this.bannerInfo[i].productUrl = url.substring(url.indexOf('#')+1);
          }
        }
      }).catch((err)=>{this.$msg.error(err)})

      this.$http.post('/productType/findAllName').then((rep)=>{
        if(rep.data.code===200){
          this.productType = rep.data.data;
        }
      })
      this.$http.post('/productBrand/findAllName').then((rep)=>{
        if(rep.data.code===200){
          this.productBrand = rep.data.data;
        }
      })

      let that = this;
      this.$axios.all([
        this.$http.post('/product/findAllByType'),
        this.$http.post('/product/findAllSale')
      ]).then(this.$axios.spread(function(typeResp, productResp){
        if(typeResp.data.code===200){
          that.productTypeInfo = typeResp.data.data;
        }
        if(productResp.data.code===200){
          that.productsTemp = productResp.data.data;
        }

        that.productsInfo = new Map();
        for(let i = 0,len=that.productTypeInfo.length; i < len; i++) {
          let list = [];
          let typeInfo = {};
          typeInfo['typename']=that.productTypeInfo[i]['product_type'];
          typeInfo['urlLeft']=that.productTypeInfo[i]['type_url_left'];
          typeInfo['urlTop']=that.productTypeInfo[i]['type_url_top'];
          for(let j = 0,len=that.productsTemp.length; j < len; j++){
            if(that.productsTemp[j]['productType']===typeInfo['typename']){
              list.push(that.productsTemp[j]);
            }
          }
          that.productsInfo.set(typeInfo,list);
        }

        for(let i=0,len=that.productsTemp.length;i<len;i++){
          if(i>15){
            break;
          }
          that.newProduct.push(that.productsTemp[i]);
        }
        that.$nextTick(() => {
          let info = that.$refs['newInfo'];
          let li = that.$refs['newLi'];
          let ul = that.$refs['newUl'];
          if(info) {
            let wid = info.offsetWidth * 0.19;
            let mar = info.offsetWidth * 0.005;
            let ulw = (wid + mar) * li.length;
            ul.style.width = ulw + "px";
            for (let i = 0, len = li.length; i < len; i++) {
              li[i].style.width = wid + "px";
              li[i].style.marginRight = mar + "px";
              li[i].style.marginLeft = mar + "px";
            }
            that.timer = setInterval(function () {
              that.newRight();
              if (that.currentX + ulw < 200) {
                that.currentX = 0;
                ul.style.transform = 'translate3d(' + that.currentX + 'px, 0px, 0px)';
                that.butLFlag = true;
                that.butRFlag = false;
              }
            }, 5000);
          }
        })
      })).catch((err)=>{this.$msg.error(err);});
    },
    methods:{
      handleSelect(key, keyPath) {
        // console.log(key, keyPath);
      },
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
      },
      queryProduct(productInfo,by){
        /* by 为 undefined，则代表从搜索框得到的值*/
        if(by===undefined){
          switch (this.select){
            case "1":
              by = "name";
              break;
            case "2":
              by = "type";
              break;
            case "3":
              by = "brand";
              break;
          }
        }
        this.$router.push({path:'/MallProduct',query:{keyword:productInfo,by:by}})
      },
      newLeft(){
       let ul =  this.$refs['newUl'];
       let li = this.$refs['newLi'];
        let wid = li[0].scrollWidth;
        let mar = parseInt(li[0].style.marginLeft)*2;
        let len =  (wid+mar)*5;
        this.currentX += len;
        switch (Math.abs(this.currentX)/len){
          case 0:
            this.butLFlag=true;
            this.butRFlag=false;
            break;
          case 1:
            this.butLFlag=false;
            this.butRFlag=false;
            break;
          case 2:
            this.butLFlag=false;
            this.butRFlag=true;
            break;
        }
        ul.style.transform='translate3d('+this.currentX+'px, 0px, 0px)';
      },
      newRight(){
        let ul =  this.$refs['newUl'];
        let li = this.$refs['newLi'];
        let wid = li[0].scrollWidth;
        let mar = parseInt(li[0].style.marginLeft)*2;
        let len =  (wid+mar)*5;
        this.currentX -= len;
        switch (Math.abs(this.currentX)/len){
          case 0:
            this.butLFlag=true;
            this.butRFlag=false;
            break;
          case 1:
            this.butLFlag=false;
            this.butRFlag=false;
            break;
          case 2:
            this.butLFlag=false;
            this.butRFlag=true;
            break;
        }
        ul.style.transform='translate3d('+this.currentX+'px, 0px, 0px)';
      },
    },
    destroyed: function () {
      window.clearInterval(this.timer)
    },
  }
</script>

<style>
  .mall-container{
    margin: 0;
    padding: 0;
    min-height: 100%;
    background: #F5F5F5;
    user-select: none;
    height: 85vh;
    overflow-x: hidden;
    overflow-y: scroll;
  }
  .mall-container::-webkit-scrollbar{
    width: 0;
  }


  .mall-nav .el-input__inner{
    height: 45px;
  }
  .mall-nav .el-menu-item{
    margin: 0 1%;
    padding:0;
  }

  .slideshow{
    margin: 0 auto;
    height: 480px;
    width: 90%;
    min-width: 1325px;
  }

  .slideshow .el-link--inner{
    width: 100%;
    height: 100%;
  }

  .product{
    width: 90%;
    min-width: 1325px;
    margin: 0 auto;
    background: #F5F5F5;
  }
  .product .product-type-title{
    margin: 0;
    text-align: left;
    font-size: 22px;
    font-weight: 200;
    line-height: 58px;
    color: #333;
  }
  .product .product-info{
    height: 820px;
    overflow: hidden;
    text-align: left;
    margin: 0 0 20px;
    padding: 0;
  }

  /*新品专区*/
  .new-product{
    width: 90%;
    min-width: 1325px;
    min-height: 400px;
    margin: 0 auto;
  }

  .new-product .box-hd{
    position: relative;
    height: 58px;
    -webkit-font-smoothing: antialiased;
  }
  .new-product .new-product-title{
    margin: 0;
    font-size: 22px;
    font-weight: 200;
    line-height: 58px;
    color: #333;
    text-align: left;
    position: relative;
  }

  .new-product .new-sale{
    float: left;
    width: 18%;
    height: 300px;
    padding-top: 39px;
    border-top: 1px solid #e53935;;
    background: #f1eded;
    text-align: center;
  }

  .new-product .name{
    font-size: 21px;
    color: #ef3a3b;
    padding-top: 15px;
  }
  .new-product .new-product-info{
    margin-left:16px;
    float: left;
    width: 80%;
    min-width: 1000px;
    height: 339px;
    overflow: hidden;
  }

  .new-product .sale-list{
    margin: 0;
    padding: 0;
    height: 100%;
    width: 6000px;
    background: #F5F5F5;
    transition-duration: 1000ms;
  }

  .new-product .sale-list li{
    width: 230px;
    height: 100%;
    margin:0 10px;
    border: none;
    border-top-width: 1px;
    border-top-style: solid;
    padding-top: 39px;
    text-align: center;
    background: #fff;
    -webkit-transition: all .6s;
    transition: all .6s;
    position: relative;
    list-style: none;
    float: left;
    cursor: pointer;
  }

  .new-product .sale-list li:nth-child(5n){
    border-top-color: #e53935;
  }
  .new-product .sale-list li:nth-child(5n+1){
    border-top-color: #2196f3;
  }
  .new-product .sale-list li:nth-child(5n+2){
    border-top-color: #83c44e;
  }
  .new-product .sale-list li:nth-child(5n+3){
    border-top-color: #ffac13;
  }
  .new-product .sale-list li:nth-child(5n+4){
    border-top-color: #747d8c;
  }

  .new-product .sale-list .img{
    display: block;
    width: 160px;
    height: 180px;
    margin: 0 auto 22px;
  }

  .new-product .sale-list .title{
    margin: 0 20px 5px;
    font-size: 14px;
    font-weight: 400;
    height: 18px;
    text-overflow: ellipsis;
    color: #212121;
    overflow: hidden;
    white-space: nowrap;
  }

  .new-product .sale-list .desc{
    height: 18px;
    margin: 0 20px 7px;
    font-size: 12px;
    text-overflow: ellipsis;
    color: #b0b0b0;
    overflow: hidden;
    white-space: nowrap;
  }
  .new-product .sale-list .price{
    margin: 0;
    color: #ff6709;
  }



  /*商品类别标头*/
  .product .box-hd{
    position: relative;
    height: 58px;
    -webkit-font-smoothing: antialiased;
  }
  .product .box-hd .more{
    position: absolute;
    top: 0;
    right: 70px;
    line-height: 58px;
    cursor: pointer;
  }
  .product .box-hd .more:hover{
    color: #EA875E;
  }
  /*商品类别广告*/
  .product .product-type-promo{
    margin: 0;
    padding: 0;
    max-width: 234px;
    min-width: 210px;
    width: 15%;
    height: 614px;
    float: left;
  }
  .product .product-type-promo li{
    list-style-type:none;
    height: 100%;
    cursor: pointer;
    transition: all .2s linear; /*过渡动画*/
  }
  /*商品信息*/
  .product .product-item{
    float: left;
    height: 614px;
    width: 84%;
    margin: 0;
    padding: 0;
    text-align: center;
  }

  .product .product-item li{
    list-style-type:none;
    height: 260px;
    padding: 20px 0;
    position: relative;
    z-index: 1;
    float: left;
    width: 17%;
    margin-left: 2.85%;
    margin-bottom: 14px;
    border-radius: 8px;
    background: #fff;
    -webkit-transition: all .2s linear;
    cursor: pointer;
    transition: all .2s linear; /*过渡动画*/
  }
  .product-type-promo li:hover,.product-item li:hover{
    z-index: 2;
    -webkit-box-shadow: 0 15px 30px rgba(0,0,0,.1);
    box-shadow: 0 15px 30px rgba(0,0,0,.1);
    -webkit-transform: translate3d(0,-2px,0);
    transform: translate3d(0,-2px,0);
  }
  .product-item .el-image{
    width: 160px;
    height: 160px;
    margin: 0 auto 18px;
  }
  .product-item .title {
    margin: 0 10px 5px;
    height: 18px;
    font-size: 14px;
    font-weight: 400;
    overflow: hidden;
    color: #333;
  }
  .product-item .desc {
    margin: 0 10px 5px;
    height: 18px;
    font-size: 12px;
    color: #b0b0b0;
    overflow: hidden;
  }
  .product-item .price {
    margin: 0 10px 10px;
    text-align: center;
    color: #ff6700;
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
