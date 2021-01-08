<template>
  <div class="mall-purchase">
    <div class="product-box">
      <div class="nav-bar">
        <div class="container">
          <h2>{{productInfo.productName}}</h2>
          <div>
            <span class="separator">|</span>
            <span>{{productInfo.productType}}</span>
          </div>
          <el-link type="danger" @click="toReview">商品评价</el-link>
        </div>
      </div>
    </div>
    <div class="product-info">
      <div class="product-info-content container">
        <div class="img">
          <el-image :src="productInfo.productUrl" style="height: 85%;width: 70%;margin: 15%"></el-image>
        </div>
        <div class="product-con">
          <h2>{{productInfo.productName}}</h2>
          <p class="sale-desc">{{productInfo.productDescribe}}</p>
          <div class="price-info">
            <span>{{productInfo.outPrice}} 元</span>
          </div>
          <div class="line"></div>
          <div class="address-box container">
            <i class="el-icon-location-outline"></i>
            <div class="address-con">
              <div class="address-info" v-if="order.acceptAddress">{{order.acceptAddress}}</div>
              <div class="address-info" v-else>未设置</div>
            </div>
            <div class="address-alter" @click="addressFormVisible=true">修改</div>
          </div>
          <div class="option-box" v-if="this.productSpec!==null && this.productSpec.length!==0">
            <div class="title">选择规格</div>
            <el-radio-group class="option-list" v-for="(option,index) in this.productSpec" fill="#ff6700" v-model="order.productSpecs" :key="index">
              <el-radio-button :label="option"></el-radio-button>
            </el-radio-group>
          </div>
          <div class="select-amount">
            <div style="font-size: 18px;">选择数量</div>
            <el-input-number :min="1" @change="calculationPrice" :max="productInfo.productStock" v-model="order.payAmount" style="margin: 16px;"></el-input-number>
          </div>
          <div class="selected-list container">
            <span>{{productInfo.productName}}</span>
            <span style="padding-left: 10px">{{order.productSpecs}}</span>
            <span style="padding-left: 10px">{{productInfo.outPrice}}元</span>
            <div>
              <div class="total-price" v-model="totalPrice">总计：{{totalPrice}}元</div>
              <el-button type="success" plain style="float: left;margin: 20px 40px" @click="joinCart">加入购物车</el-button>
              <el-button type="danger" plain style="float: left;margin: 20px 0" @click="buy">立即购买</el-button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <el-dialog title="收货地址" class="address-dialog" width="450px"  :visible.sync="addressFormVisible">
      <el-form :model="addressForm" :rules="addressRules" ref="addressForm">
        <el-form-item label="所在地区" prop="selectedOptions">
          <el-cascader size="large" :options="options" separator=" "
                       v-model="addressForm.selectedOptions" @change="handleChange">
          </el-cascader>
        </el-form-item>
        <el-form-item label="详细地址" prop="detailAddress">
          <el-input v-model="addressForm.detailAddress" autocomplete="off" style="width: auto">
            <i class="el-icon-edit el-input__icon" slot="suffix"/>
          </el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addressFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm('addressForm')">确 定</el-button>
      </div>
    </el-dialog>
    <!--产生订单-->
    <el-dialog title="订单信息" :visible.sync="orderFormVisible" width="600px">
      <el-form :model="order" class="order-form" :rules="orderRules" ref="order">
        <el-form-item label="商品名称">
          <el-input v-model="this.productInfo['productName']" disabled></el-input>
        </el-form-item>
        <el-form-item v-if="order.productSpecs" label="商品规格">
          <el-input v-model="order.productSpecs" disabled></el-input>
        </el-form-item>
        <el-form-item label="购买数量">
          <el-input v-model="order.payAmount" disabled></el-input>
        </el-form-item>
        <el-form-item label="支付金额">
          <el-input v-if="isVip" v-model="order.payPrice" class="delete-line" disabled></el-input>
          <el-input v-else v-model="order.payPrice" disabled></el-input>
        </el-form-item>
        <el-form-item v-if="isVip" label="会员折扣" style="position: relative">
          <el-input v-model="order.discountPrice" class="discount-price" disabled></el-input>
          <el-button plain class="vip-info">花卷VIP {{discountStrength}}折</el-button>
        </el-form-item>
        <el-form-item label="收货人" prop="userName">
          <el-input v-model="order.userName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="联系方式" prop="contactWay">
          <el-input v-model="order.contactWay" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="收货地址" prop="acceptAddress" style="position: relative">
          <el-input v-model="order.acceptAddress" readonly></el-input>
          <el-button plain style="position: absolute;top:1px;right: 14px;border: none" @click="addressFormVisible=true">修改</el-button>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="createOrder('order')">付 款</el-button>
        <el-button @click="orderFormVisible = false">取 消</el-button>
      </div>
    </el-dialog>
    <!--底部-->
    <MallFooter></MallFooter>
  </div>
</template>

<script>
import {regionData, CodeToText} from 'element-china-area-data'
import MallFooter from "./MallFooter";

export default {
    name: "MallPurchase",
    components: {
      MallFooter
    },
    data() {
      let validateOption = (rule, value, callback) => {
        if (value === null || value.length===0) {
          callback(new Error('请选择收货地址'));
        } else {
          callback();
        }
      };
      let validateAddress = (rule, value, callback) => {
        if (value === null || value.trim().length===0) {
          callback(new Error('请填写详细地址'));
        } else {
          callback();
        }
      };
      let validateUserName = (rule, value, callback) => {
        if (value === null || value.trim().length===0) {
          callback(new Error('请填写收货人'));
        } else {
          callback();
        }
      };
      let validateContactWay = (rule, value, callback) => {
        //匹配手机号正则表达式
        let regex= /^((0\d{2,3}-\d{7,8})|(1[3584]\d{9}))$/;
        if (value === null || value.trim().length===0) {
          callback(new Error('请填写联系方式'));
        } else if(!regex.test(value)){
          callback(new Error('请填写正确的手机号'));
        } else {
          callback();
        }
      };
      let validateAcceptAddress = (rule, value, callback) => {
        if (value === null || value.trim().length===0) {
          callback(new Error('请填写收货地址'));
        } else {
          callback();
        }
      };
      return {
        isVip:null,
        buyId:null,
        totalPrice:0,
        productSpec:null,
        options: regionData,
        username:'',
        discountStrength:98,
        addressForm:{
          selectedOptions: [],
          detailAddress:''     //详细地址
        },
        addressRules:{
          selectedOptions:[{ validator: validateOption, trigger: 'blur' }],
          detailAddress:[{ validator: validateAddress, trigger: 'blur' }]
        },
        addressFormVisible:false,
        orderFormVisible:false,
        productInfo:{
          productName:'',
          outPrice:'',
          productDescribe:'',
          productUrl:'',
        },
        order:{
          orderNo:'',
          productNo:'',
          userAccount:'',
          userName:'',
          contactWay:'',
          payPrice:'',
          discountPrice:'',
          productSpecs:null,
          payAmount:1,
          payType:'',
          orderFrom:'',
          orderState:'',
          returnState:false,
          acceptAddress:'',
        },
        shoppingCart:{
          accountNumber:'',
          productId:null,
          payAmount:1,
          productSpecs:null
        },
        orderRules:{
          userName:[{ validator: validateUserName, trigger: 'blur' }],
          contactWay:[{ validator: validateContactWay, trigger: 'blur' }],
          acceptAddress:[{ validator: validateAcceptAddress, trigger: 'blur' }]
        }
      }
    },
    methods:{
      handleChange (value) {
        // console.log(value)
      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            let str='';
            let ad = this.addressForm.selectedOptions;
            for (let i=0,len=ad.length;i<len;i++){
              str +=CodeToText[ad[i]]+' ';
            }
            str+='('+this.addressForm.detailAddress+')';
            this.order.acceptAddress=str;
            this.addressFormVisible=false;
          } else {
            return false;
          }
        });
      },
      buy(){
        this.getDateNow();  //获得订单编号
        this.order.productNo = this.productInfo.productNo;
        this.order.userAccount = this.$store.state.user['accountNumber'];
        if(this.order.userName!==null && this.order.userName.length===0){
          this.order.userName = this.$store.state.user['userName'];
        }
        this.order.payPrice = this.totalPrice;
        this.order.payType='支付宝';
        this.order.orderFrom='网页商城';
        this.order.orderState='待付款';
        this.order.returnState=false;   //未退货
        if(this.isVip){
          this.discountStrength = 9.8;
          if(this.order.payPrice>=1000 && this.order.payPrice<5000){
            this.discountStrength=9.5;
          }else if(this.order.payPrice>=5000){
            this.discountStrength=9;
          }
          this.order.discountPrice = Math.floor(this.order.payPrice * this.discountStrength /10);
        }
        if(this.order.contactWay!==null && this.order.contactWay.length===0){
          if(this.$store.state.user['telephone']===null || this.$store.state.user['telephone'].length===0){
            this.$confirm('检测到您未绑定手机号，快递小哥哥将无法找到您', '提示', {
              confirmButtonText: '现在绑定',
              cancelButtonText: '临时填写',
              type: 'warning'
            }).then(() => {this.$router.push('/personalCenter')}).catch(() => {
              this.orderFormVisible=true;
            });
          }else{
            this.order.contactWay = this.$store.state.user['telephone']
            this.orderFormVisible=true;
          }
        }else{
          this.orderFormVisible=true;
        }
      },
      createOrder(formName){
        this.$refs[formName].validate((valid) => {
            if (valid) {
              if(this.isVip){
                this.order.payPrice = this.order.discountPrice;
              }
              let loading = this.$loading({lock: true, text: "订单提交中",background:"rgba(255,255,255,0.1)"});
              this.$http.post('/order/add',this.$qs.stringify(this.order,{skipNulls: true})).then((rep)=>{
                loading.close();
                if(rep.data.code===200){
                  let orderNo = this.order.orderNo;
                  let orderName = '花卷商城-'+this.productInfo.productType+'-'+this.productInfo.productName+'支付订单';
                  let payPrice = this.order.payPrice;
                  let loading = this.$loading({lock: true, text: "正在跳转支付页面",background:"rgba(255,255,255,0.1)"});
                  this.$http.post('/alipay/create?orderNo='+orderNo+'&orderName='+orderName+'&payPrice='+payPrice).then((response)=>{
                    loading.close();
                    const div = document.createElement('div');
                    div.innerHTML = response.data;
                    document.body.appendChild(div);
                    document.forms[document.forms.length-1].submit();
                  }).catch((err)=>{loading.close();this.$msg.error(err)})
                }
              }).catch((err)=>{loading.close();this.$msg.error(err);})
              this.orderFormVisible=false;
            }else{
              return false;
            }
        });
      },
      toReview(){
        this.$router.push({path:'/productReview',query:{keyword:this.productInfo.productNo}})
      },
      calculationPrice(){
        this.totalPrice =  (this.order.payAmount * this.productInfo.outPrice).toFixed(2)  //四舍五入 保留两位小数
      },
      joinCart(){
        this.$confirm('将商品放入到购物车中', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.shoppingCart.productId = this.buyId;
          this.shoppingCart.productSpecs = this.order.productSpecs;
          this.shoppingCart.payAmount = this.order.payAmount;
          let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.1)"});
          this.$http.post('/shoppingCart/add',this.$qs.stringify(this.shoppingCart)).then((rep)=>{
            loading.close();
            if(rep.data.code===200){
              this.$message({type: 'success', message: '成功加入购物车!'});
            }
          }).catch((err)=>{loading.close();this.$msg.error(err)});
        }).catch(() => {});
      },
      getDateNow(){
        let time = new Date(); // 时间戳
        let year= String(time.getFullYear()); // 年
        year = year.substring(2);
        let mouth= String(time.getMonth() + 1); // 月
        let day= String(time.getDate()); // 日
        let hours= String(time.getHours()); // 时
        if(hours.length<2){
          hours='0' + hours
        }
        let seconds= String(time.getSeconds()); // 秒
        if(seconds.length<2) {
          seconds='0' + seconds
        }
        let max=9000;
        let min=1000;
        let random = Math.floor((Math.random()*max+min)+1) //随机数
        this.order.orderNo = year + mouth + day + hours + seconds + random
      }
    },
    created() {
      if(this.$store.state.user){
        this.isVip = this.$store.state.user['isVip'];
        this.order.acceptAddress = this.$store.state.user['userAddress'];
        this.shoppingCart.accountNumber = this.$store.state.user['accountNumber']
      }

      this.buyId = this.$route.query.id;
      if(this.buyId!=null){
        let loading = this.$loading({lock: true, text: "数据加载中",background:"rgba(255,255,255,0.2)"});
        this.$http.post('/product/findById?productId='+this.buyId).then((rep)=>{
          loading.close();
          if(rep.data.code===200){
            this.productInfo = rep.data.data;
            this.totalPrice = this.productInfo.outPrice.toFixed(2) ;
          }else{
            this.$msg.error(rep.data.message);
          }
        }).catch((err)=>{this.$msg.error(err)})
        this.$http.post('/productSpecs/findAllByProId?productId='+this.buyId).then((rep)=>{
          if(rep.data.code===200){
            this.productSpec = rep.data.data;
            this.order.productSpecs=this.productSpec[0]; //默认选择第一个商品规格
          }else{
            this.$msg.error(rep.data.message);
          }
        }).catch((err)=>{this.$msg.error(err)})
      }
    }
  }
</script>

<style>
  .mall-purchase{
    margin: 0;
    padding: 0;
    background: #F5F5F5;
    user-select: none;
    min-height: 100%;
  }
  .mall-purchase .product-box{
    position: relative;
    z-index: 4;
    background: #fff;
    border-top: 1px solid #e0e0e0;
    border-bottom: 1px solid #e0e0e0;
    -webkit-box-shadow: 0 5px 5px rgba(0,0,0,.07);
    box-shadow: 0 5px 5px rgba(0,0,0,.07);
  }

  .mall-purchase .product-box .nav-bar{
    position: relative;
    height: 63px;
    width: 100%;
    margin-top: 0;
    color: #616161;
  }

  .mall-purchase .nav-bar .container{
    width: 80%;
    min-width: 1226px;
    margin-right: auto;
    margin-left: auto;
    text-align: left;
  }

  .mall-purchase .nav-bar .container h2{
    margin: 0;
    font-size: 18px;
    font-weight: 400;
    line-height: 60px;
    color: #424242;
    display: inline-block;
  }

  .mall-purchase .nav-bar .container div{
    display: inline-block;
    margin-left: 5px;
    font-size: 15px;
    margin-top: 21px;
  }

  .mall-purchase .nav-bar .container .el-link{
    position: absolute;
    top: 25px;
    right: 10%;
    font-size: 15px;
  }


  .mall-purchase .nav-bar  div .separator{
    margin: 0 .5em;
    color: #424242;
  }
  .mall-purchase .product-info{
    width: 80%;
    min-width: 1325px;
    margin: 15px auto;
    min-height: 700px;
  }

  .product-info .product-info-content{
    padding-top: 32px;
    padding-bottom: 12px;
    background: #FFFFFF;
  }

  .mall-purchase .product-info-content .img{
    float: left;
    width: 40%;
    min-width: 540px;
    height: 560px;
  }

  .mall-purchase .product-info-content .product-con{
    float: left;
    width: 58%;
    min-width: 750px;
    padding-left: 8px;
    text-align: left;
    user-select: text;
  }

  .mall-purchase .product-con h2{
    margin: 0;
    padding:0;
    font-size: 24px;
    font-weight: 400;
    color: #212121;
  }

  .mall-purchase .product-con .sale-desc{
    color: #b0b0b0;
    margin: 0;
    padding: 8px 0 0;
    font: 14px/1.5 Helvetica Neue,Helvetica,Arial,Microsoft Yahei,Hiragino Sans GB,Heiti SC,WenQuanYi Micro Hei,sans-serif;
  }

  .mall-purchase .product-con .price-info{
    display: block;
    font-size: 18px;
    line-height: 1;
    color: #ff6700;
    padding: 12px 0 10px;
  }

  .line{
    margin-top: 12px;
    border-bottom: 1px solid #e0e0e0;
  }

  .mall-purchase .product-con .address-box{
    padding: 30px 50px;
    position: relative;
    background: #fafafa;
    border: 1px solid #e0e0e0;
    margin: 20px 0;
  }

  .mall-purchase .address-box i{
    float: left;
    padding-top: 4px;
    padding-right: 5px;
  }
  .mall-purchase .address-box .address-con{
    float: left;
  }
  .mall-purchase .address-box .address-alter{
    float: left;
    cursor: pointer;
    color: #ff6700;
    margin-left: 30px;
    font: 14px/1.5 Helvetica Neue,Helvetica,Arial,Microsoft Yahei,Hiragino Sans GB,Heiti SC,WenQuanYi Micro Hei,sans-serif;
  }

  .mall-purchase .option-box{
    margin-bottom: 30px;
  }

  .mall-purchase .option-box .title{
    font-size: 18px;
  }

  .mall-purchase .option-list .el-radio-button{
    margin: 6px;
  }

  .mall-purchase .option-box .el-radio-button__inner{
    border-left: 1px solid #DCDFE6;
    width: 250px!important;
    height: 39px!important;
  }
  .el-radio-button__inner:hover{
    color: #ff6700;
  }
  .mall-purchase .option-box .el-radio-button__inner:hover{
    border-color: #ff6700;
  }

  .mall-purchase .product-con .selected-list{
    background: #f9f9fa;
    padding: 30px;
    margin-bottom: 30px;
    font: 14px/1.5 Helvetica Neue,Helvetica,Arial,Microsoft Yahei,Hiragino Sans GB,Heiti SC,WenQuanYi Micro Hei,sans-serif;
  }

  .mall-purchase .product-con .total-price{
    float: left;
    color: #ff6700;
    font-size: 24px;
    padding-top: 20px;
  }

  .mall-purchase .product-con .selected-list .el-button{
    padding: 10px 44px;
  }

  .mall-purchase .mall-footer{
    height: 130px!important;
    border-top: 1px solid #e0e0e0;
  }

  .container:after {
    clear: both;
  }
  .container:after, .container:before {
    content: " ";
    display: table;
  }

  .mall-purchase .address-dialog .el-dialog__body{
    padding: 30px 80px;
  }

  .mall-purchase .el-form-item__error{
    padding-left: 74px;
  }

  .mall-purchase .order-form .el-form-item__label{
    width: 88px;
  }

  .mall-purchase .order-form .el-input{
    width: 80%;
  }
  .mall-purchase .order-form .el-form-item__error{
    padding-left: 100px;
  }

  .mall-purchase .order-form .discount-price .el-input__inner{
    color: red;
  }

  .mall-purchase .delete-line .el-input__inner{
    text-decoration:line-through
  }

  .mall-purchase .order-form .vip-info{
    position: absolute;
    right: 16px;
    color:#F8C4A2;
    background-color:#181818;
    padding: 8px 6px;
    top: 4px;
  }

  .mall-purchase .order-form .vip-info:hover{
    color:#F8C4A2;
    background-color:#181818;
  }
</style>
