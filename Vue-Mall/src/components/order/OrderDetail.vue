<template>
  <div class="order-detail">
    <div class="order-box">
      <div class="box-title">
        <el-alert class="order-state"  type="warning" show-icon>当前订单状态：{{orderInfo.orderState}}</el-alert>
      </div>
      <div class="order-info">
        <div class="info-title">
          <i class="el-icon-edit"></i>
          基本信息
        </div>
        <div class="info-content">
          <div class="info-row">
            <div class="info-item order-no">
              <div class="item-title">订单编号</div>
              <div class="item-content">{{orderInfo.orderNo}}</div>
            </div>
            <div class="info-item user-account">
              <div class="item-title">用户帐号</div>
              <div class="item-content">{{orderInfo.userAccount}}</div>
            </div>
            <div class="info-item user-name">
              <div class="item-title">用户名称</div>
              <div class="item-content">{{orderInfo.userName}}</div>
            </div>
            <div class="info-item pay-type">
              <div class="item-title">支付方式</div>
              <div class="item-content">{{orderInfo.payType}}</div>
            </div>
            <div class="info-item order-form">
              <div class="item-title">订单来源</div>
              <div class="item-content">{{orderInfo.orderFrom}}</div>
            </div>
          </div>
          <div class="info-row">
            <div class="info-item product-no">
              <div class="item-title">商品编号</div>
              <div class="item-content">{{orderInfo.productNo}}</div>
            </div>
            <div class="info-item pay-amount">
              <div class="item-title">购买数量</div>
              <div class="item-content">{{orderInfo.payAmount}}</div>
            </div>
            <div class="info-item pay-price">
              <div class="item-title">支付金额</div>
              <div class="item-content">{{orderInfo.payPrice}}</div>
            </div>
            <div class="info-item order-time">
              <div class="item-title">下单时间</div>
              <div class="item-content">{{orderInfo.orderTime}}</div>
            </div>
            <div class="info-item logistics-company">
              <div class="item-title">物流公司</div>
              <div class="item-content">{{logistics.parcelName}}</div>
            </div>
          </div>
        </div>
      </div>
      <div class="order-info" v-if="logistics.receiver.length!==0">
        <div class="info-title">
          <i class="el-icon-user"></i>
          收货人信息
        </div>
        <div class="info-content" style="height: 120px">
          <div class="info-row">
            <div class="info-item delivery-item receiver">
              <div class="item-title">收货人</div>
              <div class="item-content">{{logistics.receiver}}</div>
            </div>
            <div class="info-item delivery-item receiver-tel">
              <div class="item-title">手机号码</div>
              <div class="item-content">{{logistics.receiverTel}}</div>
            </div>
            <div class="info-item delivery-item receiver-add" style="width: 40%!important;">
              <div class="item-title">收货地址</div>
              <div class="item-content">{{logistics.receiverAdd}}</div>
            </div>
          </div>
        </div>
      </div>
      <div class="order-info" v-if="logistics.sender.length!==0">
        <div class="info-title">
          <i class="el-icon-user-solid"></i>
          发货人信息
        </div>
        <div class="info-content" style="height: 120px">
          <div class="info-row">
            <div class="info-item delivery-item sender">
              <div class="item-title">发货人</div>
              <div class="item-content">{{logistics.sender}}</div>
            </div>
            <div class="info-item delivery-item sender-tel">
              <div class="item-title">手机号码</div>
              <div class="item-content">{{logistics.senderTel}}</div>
            </div>
            <div class="info-item delivery-item sender-add" style="width: 40%!important;">
              <div class="item-title">发货地址</div>
              <div class="item-content">{{logistics.senderAdd}}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "OrderDetail",
    data() {
        return{
          orderInfo:{
            orderId:'',
            orderNo:'',
            orderTime:'',
            productNo:'',
            userAccount:'',
            userName:'',
            contactWay:'',
            payPrice:'',
            payAmount:'',
            payType:'',
            orderFrom:'',
            orderState:'',
          },
          logistics:{
            logisticId:'',
            orderNo:'',
            sender:'',
            senderTel:'',
            senderAdd:'',
            receiver:'',
            receiverTel:'',
            receiverAdd:'',
            parcelName:''
          }
        }
    },
    methods:{

    },
    created(){
      let orderNo = this.$route.query.orderNo;
      let state = this.$route.query.state;
      if(state === '0'){
        this.$http.post('/orderDetail/orderInfo?orderNo='+orderNo).then((res)=>{
          if(res.data.code===200){
            this.orderInfo=res.data.data[0];
            if(res.data.data[1]!=null){
              this.logistics=res.data.data[1];
            }else{
              this.logistics.parcelName='暂无信息';
            }

          }
        }).catch((err)=>{console.log(err)})
      }else if(state === '1'){
        this.$http.post('/orderDetail/returnInfo?orderNo='+orderNo).then((res)=>{
          if(res.data.code===200){
            this.orderInfo=res.data.data[0];
            if(res.data.data[1]!=null){
              this.logistics=res.data.data[1];
            }else{
              this.logistics.parcelName='暂无信息';
            }

          }
        }).catch((err)=>{console.log(err)})
      }

    }
  }
</script>

<style>
  .order-detail{
    margin: 0;
    padding: 20px 0 0;
    min-height: 100%;
    user-select: none;
    min-width: 1226px;
    background-color: #f6f8f9;
  }
  .order-detail .order-box{
    width: 95%;
    margin: 0 auto;
    min-height: 600px;
    padding-bottom: 20px;
    border: 1px solid #ebeef5;
    background-color: #fff;
  }

  .order-detail .order-box .box-title{
    background-color:#f2f6fc ;
    height: 52px;
  }

  .order-detail .order-box .box-title .order-state{
    background-color: #f2f6fc;
    color: #f56c6c;
  }

  .order-detail .order-box .box-title .order-state {
   padding: 16px 20px;
  }

  .order-detail .order-box .box-title .order-state .el-alert__description{
    color: #f56c6c;
    font-size: 15px;
    font-weight: 500;
  }
  .order-detail .order-box .box-title .order-state .el-alert__content{
    padding: 0;
  }
  .order-detail .order-box .box-title .order-state .el-alert__icon{
    font-size: 19px;
  }
  .order-detail .order-box .box-title .order-state .el-alert__description{
    margin: 0;
  }

  .order-detail .order-box .order-info{
    padding:0 30px;
  }

  .order-detail .order-box .order-info .info-title{
    margin-top: 20px;
    padding-left: 10px;
    text-align: left;
    margin-bottom: 20px;
  }

  .order-detail .order-box .order-info .info-content{
    height: 204px;
  }
  .order-detail .order-box .order-info .info-content .info-row{
    height: 100px;
    position: relative;
  }
  .order-detail .order-box .order-info .info-content .info-row .info-item{
    width: 20%;
    height: 100px;
    position: absolute;
    border: 1px solid #ebeef5;
  }
  .order-detail .order-box .order-info .info-content .info-row .info-item .item-title{
    height: 35px;
    line-height: 35px;
    border: 1px solid #dcdfe6;
    background-color: #f2f6fc;
  }

  .order-detail .order-box .order-info .info-content .info-row .info-item .item-content{
    height: 65px;
    line-height: 65px;
  }

  .order-detail .order-box .order-info .info-content .info-row .order-no{
    left: 0;
  }
  .order-detail .order-box .order-info .info-content .info-row .user-account{
    left: 20%;
  }
  .order-detail .order-box .order-info .info-content .info-row .user-name{
    left: 40%;
  }
  .order-detail .order-box .order-info .info-content .info-row .pay-type{
    left: 60%;
  }
  .order-detail .order-box .order-info .info-content .info-row .order-form{
    left: 80%;
  }

  .order-detail .order-box .order-info .info-content .info-row .product-no{
    left: 0;
  }
  .order-detail .order-box .order-info .info-content .info-row .pay-amount{
    left: 20%;
  }
  .order-detail .order-box .order-info .info-content .info-row .pay-price{
    left: 40%;
  }
  .order-detail .order-box .order-info .info-content .info-row .order-time{
    left: 60%;
  }
  .order-detail .order-box .order-info .info-content .info-row .logistics-company{
    left: 80%;
  }

  .delivery-item{
    width: 30%!important;
  }

  .order-detail .order-box .order-info .info-content .info-row .sender{
    left: 0;
  }
  .order-detail .order-box .order-info .info-content .info-row .sender-tel{
    left: 30%;
  }
  .order-detail .order-box .order-info .info-content .info-row .sender-add{
    left: 60%;
  }

  .order-detail .order-box .order-info .info-content .info-row .receiver{
    left: 0;
  }
  .order-detail .order-box .order-info .info-content .info-row .receiver-tel{
    left: 30%;
  }
  .order-detail .order-box .order-info .info-content .info-row .receiver-add{
    left: 60%;
  }



</style>
