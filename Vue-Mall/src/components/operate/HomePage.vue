<!--主页组件-->
<template>
  <div class="home-page">
    <!--用户信息-->
    <div class="user-info">
      <!--头像图片-->
      <el-avatar shape="square" class="avatarImg" :size="64" :src="this.user.avatarUrl">
        <img src="https://spring-oss.oss-cn-beijing.aliyuncs.com/images/img/avat.gif" alt="默认头像"/>
      </el-avatar>
      <!--用户信息-->
      <div class="info-box">
        <h3>{{user.greetings}}</h3>
        <span v-if="user.sendWord && user.sendWord.length!==0">寄语：{{user.sendWord}}</span>
        <span v-else>这个人很懒什么都没有留下</span>
      </div>
    </div>
    <!--订单信息-->
    <div class="order-info">
      <!--今日订单总数-->
      <div class="order-total">
        <i class="el-icon-s-order"></i>
        <h3>今日订单总数</h3>
        <countTo :startVal='0' useEasing :endVal='dataInfo.todayOrderCount' :duration='5000'></countTo>
      </div>
      <!--今日销售总额-->
      <div class="sales-total">
        <i class="el-icon-bank-card"></i>
        <h3>今日销售总额</h3>
        <countTo :startVal='0' :endVal='dataInfo.todayOrderMoney' :duration='5000' prefix="￥"></countTo>
      </div>
      <!--昨日销售总额-->
      <div class="yesterday-sales-total">
        <i class="el-icon-coin"></i>
        <h3>昨日销售总额</h3>
        <countTo :startVal='0' useEasing :endVal='dataInfo.yesterdayOrderMoney' :duration='5000' prefix="￥"></countTo>
      </div>
    </div>
    <!--订单处理-->
    <div class="order-deal" v-if="currentRole.indexOf(this.$VAR.adminRole)!==-1 || currentRole.indexOf(this.$VAR.orderRole)!==-1">
      <div class="title">
        <h3>待处理订单</h3>
      </div>
      <div class="deal-box">
        <div class="deal-row">
          <div class="deal-item order-count">
            <h4>全部订单</h4>
            <span>({{dataInfo.orderCount}})</span>
          </div>
          <div class="deal-item no-pay">
            <h4>待付款订单</h4>
            <span>({{dataInfo.noPayCount}})</span>
          </div>
          <div class="deal-item no-send-goods">
            <h4>待发货订单</h4>
            <span>({{dataInfo.noSendGoodsCount}})</span>
          </div>
          <div class="deal-item send-goods">
            <h4>已发货订单</h4>
            <span>({{dataInfo.sendGoodsCount}})</span>
          </div>
        </div>
        <div class="deal-row">
          <div class="deal-item receive-goods">
            <h4>待确认收货订单</h4>
            <span>({{dataInfo.sendGoodsCount}})</span>
          </div>
          <div class="deal-item finish-order">
            <h4>已完成订单</h4>
            <span>({{dataInfo.finishOrderCount}})</span>
          </div>
          <div class="deal-item no-deal-return">
            <h4>待处理退货订单</h4>
            <span>({{dataInfo.noDealCount}})</span>
          </div>
          <div class="deal-item no-return-money">
            <h4>已处理退款订单</h4>
            <span>({{dataInfo.noRefundCount}})</span>
          </div>
        </div>
        <div class="deal-row">
          <div class="deal-item vip-order">
            <h4>VIP订单</h4>
            <span>({{dataInfo.VIPCount}})</span>
          </div>
          <div class="deal-item refusal-order">
            <h4>拒绝退货订单</h4>
            <span>({{dataInfo.refusalCount}})</span>
          </div>
        </div>
      </div>
    </div>
    <!--商品信息-->
    <div class="product-info" v-if="currentRole.indexOf(this.$VAR.adminRole)!==-1 || currentRole.indexOf(this.$VAR.productRole)!==-1">
      <div class="title">
        <h3>商品总览</h3>
      </div>
      <div class="product-box">
        <div class="item all-product">
          <h3>全部商品</h3>
          <span>{{dataInfo.productCount}}</span>
        </div>
        <div class="item sale-product">
          <h3>已上架</h3>
          <span>{{dataInfo.saleCount}}</span>
        </div>
        <div class="item no-sale-product">
          <h3>已下架</h3>
          <span>{{dataInfo.noSaleCount}}</span>
        </div>
        <div class="item stockout-product">
          <h3>库存紧张</h3>
          <span>{{dataInfo.stockOutCount}}</span>
        </div>
      </div>
    </div>
    <!--订单分析-->
    <div class="order-analyze" >
      <div class="title">
        <h3>订单统计</h3>
      </div>
      <div class="analyze-box">
        <div class="block">
          <el-date-picker v-model="dateTime" type="daterange" size="mini" :default-value="new Date()"
                          value-format="yyyy-MM-dd"
                          style="width: 280px"
                          @change="dealData"  unlink-panels range-separator="至"
                          start-placeholder="开始日期" end-placeholder="结束日期" :picker-options="pickerOptions">
          </el-date-picker>
        </div>
        <div class="ring-chart">
          <div class="total-data">
            <span style="color: #FF7993;">订单总数
              <countTo :startVal='0' useEasing :endVal='phaseOrderCount' :duration='3000'></countTo>
            </span>
            <span style="color: #63C2FF; padding-left: 30px;">销售总额
              <countTo :startVal='0' useEasing :endVal='phaseOrderAmount' prefix="￥" :duration='3000'></countTo>
            </span>
          </div>
          <ve-ring :data="ringData" height="330px" :settings="ringSettings">
            <div v-if="ringEmpty" class="data-empty">暂无数据😂</div>
          </ve-ring>
        </div>
        <div class="line-chart">
          <ve-line width="100%" height="350px" :data="lineData"  :extend="lineExtend" :settings="lineSettings">
            <div v-if="lineEmpty" class="data-empty">暂无数据😂</div>
          </ve-line>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import countTo from 'vue-count-to';
  import 'v-charts/lib/style.css'
  export default {
    name: "HomePage",
    components: {countTo},
    data: function () {
      return{
        currentRole:this.$store.state.role,
        user:{
          greetings:'',   //问候  上午好，下午好、、
          userName:'',
          sendWord:'',      //作者寄语
          avatarUrl:'',
          hope:'',    //希望语
        },
        dataInfo:{
          VIPCount: 0,
          finishOrderCount:0,
          noDealCount:0,
          noPayCount: 0,
          noRefundCount:0,
          noSaleCount:0,
          noSendGoodsCount:0,
          orderCount: 0,
          productCount: 0,
          refusalCount:0,
          saleCount: 0,
          sendGoodsCount:0,
          stockOutCount:0,
          todayOrderCount:0,
          todayOrderMoney: 0,
          yesterdayOrderMoney: 0,
        },
        pickerOptions: {
          shortcuts: [{
            text: '最近一周',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近一个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近三个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit('pick', [start, end]);
            }
          }]
        },
        lineEmpty:false,
        ringEmpty:false,
        dateTime:'',
        lineData: {
          columns: ['date', 'orderCount', 'orderAmount'],
          rows: []
        },
        lineSettings:{
          metrics: ['orderCount', 'orderAmount'],
          dimension: ['date'],
          axisSite: {right: ['orderAmount']},
          yAxisType: ['normal', 'normal'],
          yAxisName: ['订单总数', '订单金额'],
          labelMap: {'orderCount': '订单总数', 'orderAmount': '订单金额'},
          tooltip: {
            trigger: 'axis'   // axis   item   none三个值
          },
        },
        lineExtend: {
          series: {
            symbolSize: 5,
            label: {
              normal: {
                show: true
              }
            },
          }
        },
        ringSettings:{
          label: {
            normal: {
              formatter: '{b}: {d}%',
              padding: [0, -10]
            }
          }
        },
        ringData: {
          columns: ['productType','productCount'],
          rows: []
        },
        phaseOrderCount:0,
        phaseOrderAmount:0
      }
    },
    methods:{
      dealData(){
        let startTime = this.dateTime[0];
        let endTime = this.dateTime[1];
        this.$http.post('/view/order-chart-date?startTime='+startTime+'&endTime='+endTime).then((res)=>{
          if(res.data.code===200){
            this.lineEmpty=false;
            this.ringEmpty=false;
            this.lineData.rows = res.data.data['lineData'];
            if(this.lineData.rows===undefined){
              this.lineEmpty=true;
            }
            this.ringData.rows = res.data.data['ringData'];
            if(this.ringData.rows===undefined){
              this.ringEmpty=true
            }
            if(res.data.data['countData']===undefined){
              this.phaseOrderCount = 0;
              this.phaseOrderAmount = 0;
            }else{
              this.phaseOrderCount = res.data.data['countData']['orderCount']
              this.phaseOrderAmount = res.data.data['countData']['orderAmount']
            }
          }
        }).catch((err)=>{console.log(err)})
      },
      getCurrentMonth() {
        let date = new Date()
        date.setDate(1)
        let month = (date.getMonth() + 1)
        let day = date.getDate()
        if (month < 10)  month = '0' + month
        if (day < 10)  day = '0' + day
        let startTime = date.getFullYear() + '-' + month + '-' + day

        date = new Date()
        month = (date.getMonth()+ 1)
        day = date.getDate()
        if (month < 10)  month = '0' + month
        if (day < 10)  day = '0' + day
        let endTime = date.getFullYear() + '-' + month + '-' + day
        this.dateTime=[startTime,endTime];
        this.dealData()
      },
    },
    created() {
      let loading = this.$loading({lock: true, text: "数据加载中",background:"rgba(255,255,255,0.1)"});
      this.$http.post('/view/dataInfo').then((res)=>{
        loading.close();
        if(res.data.code===200){
          this.dataInfo = res.data.data;
        }
      }).catch((err)=>{loading.close();console.log(err)})
      this.getCurrentMonth();
      if(this.$store.state.user!==null){
        this.user.userName=this.$store.state.user.userName;
        this.user.sendWord=this.$store.state.user.summary;
        this.user.avatarUrl=this.$store.state.user.avatarUrl;
      }
      let hours = new Date().getHours();
      let str;
      if(hours>5 && hours<8){
        str = '早上好'
        this.user.hope = '今天也要元气满满呀。'
      }else if(hours>=8 && hours<12){
        str = '上午好'
        this.user.hope = '今日快乐值MAX。'
      }else if(hours>=12 && hours<13){
        str = '中午咯'
        this.user.hope = '快去干饭吧。'
      }else if(hours>=13 && hours<18){
        str = '下午好'
        this.user.hope = '你一定有些累了，喝杯咖啡提提神。'
      }else if(hours>=18 && hours<23){
        str = '晚上好'
        this.user.hope = '愿你天黑有灯，下雨有伞。'
      }else{
        str = '太晚了'
        this.user.hope = '要注意休息哦。'
      }
      this.user.greetings=str+' '+this.user.userName+'，'+this.user.hope;
    },
  }
</script>

<style>
  .home-page{
    margin: 0;
    padding: 20px 0 30px;
    min-height: 100%;
    user-select: none;
    min-width: 1226px;
    background-color: #f6f8f9;
  }
  .home-page .user-info{
    position: relative;
    width: 93%;
    height: 60px;
    margin: 0 auto 15px;
    background-color: #FFFFFF;
    padding:20px 0 20px;
    color: #909399;
    border: 1px solid #ebeef5;
  }

  .home-page .user-info .avatarImg{
    position: absolute;
    left: 36px;
    border-radius: 50%;
  }

  .home-page .user-info .info-box{
    position: absolute;
    left: 118px;
    height: 77px;
    width: auto;
    text-align: left;
    color: #343a40;
  }

  .home-page .user-info .info-box h3{
    margin-top: 5px;
    margin-bottom: 15px;
  }

  .home-page .user-info .info-box span{
    font-size: 14px;
    color: #999;
  }

  .home-page .order-info{
    position: relative;
    width: 93%;
    height: 83px;
    margin: 0 auto 15px;
    background-color: #f6f8f9;
    padding:0 0 20px;
    color: #909399;

  }

  .home-page .order-info .order-total{
    position: absolute;
    width: 31%;
    height: 100px;
    background-color: #ffffff;
    border: 1px solid #ebeef5;
  }

  .home-page .order-info .sales-total{
    position: absolute;
    width: 31%;
    height: 100px;
    left: 34.5%;
    background-color: #ffffff;
    border: 1px solid #ebeef5;
  }

  .home-page .order-info .yesterday-sales-total{
    position: absolute;
    width: 31%;
    height: 100px;
    left: 69%;
    background-color: #ffffff;
    border: 1px solid #ebeef5;
  }

  .home-page .order-info i{
    position: absolute;
    top: 17px;
    left: 50px;
    color: #20a0ff;
    font-size: 72px;
  }

  .home-page .order-info h3{
    position: absolute;
    top: 7px;
    left: 140px;
    font-weight: 500;
    color: #343a40;
  }

  .home-page .order-info span{
    position: absolute;
    top: 58px;
    left: 140px;
    font-weight: 500;
    font-size: 20px;
    color: #343a40;
  }

  .home-page .order-deal{
    position: relative;
    width: 93%;
    height: 260px;
    margin: 0 auto 15px;
    padding:0 0 20px;
    background-color: #FFFFFF;
    border: 1px solid #dcdfe6;
    color: #909399;
  }

  .home-page .order-deal .title{
    color: #606266;
    padding: 15px 20px;
    background: #f2f6fc;
    font-weight: 700;
  }
  .home-page .order-deal .title h3{
    margin: 0;
    text-align: left;
    font-size: 17px;
  }
  .home-page .order-deal .deal-box{
    position: relative;
    height: 167px;
  }

  .home-page .order-deal .deal-box .deal-row{
    position: relative;
    height: 72px;
  }

  .home-page .order-deal .deal-box .deal-item{
    position: absolute;
    width: 20%;
    height: 21px;
    margin: 21px;
    border-bottom: 1px solid #ebeef5;
    padding: 10px;
  }

  .home-page .order-deal .deal-box .order-count{
    left: 0;
  }

  .home-page .order-deal .deal-box .no-pay{
    left: 25%;
  }

  .home-page .order-deal .deal-box .no-send-goods{
    left: 50%;
  }

  .home-page .order-deal .deal-box .send-goods{
    left: 75%;
  }

  .home-page .order-deal .deal-box .receive-goods{
    left: 0;
  }

  .home-page .order-deal .deal-box .finish-order{
    left: 25%;
  }

  .home-page .order-deal .deal-box .no-deal-return{
    left: 50%;
  }

  .home-page .order-deal .deal-box .no-return-money{
    left: 75%;
  }

  .home-page .order-deal .deal-box .vip-order{
    left: 0;
  }

  .home-page .order-deal .deal-box .refusal-order{
    left: 25%;
  }

  .home-page .order-deal .deal-box .deal-item h4{
    position: absolute;
    left: 10px;
    margin: 0;
    font-weight: 500;
    color: black;
  }

  .home-page .order-deal .deal-box .deal-item span{
    position: absolute;
    right: 15px;
    color: #f56c6c;;
  }

  .home-page .product-info{
    position: relative;
    width: 93%;
    height: 120px;
    margin: 0 auto 15px;
    padding:0 0 20px;
    background-color: #FFFFFF;
    border: 1px solid #dcdfe6;
    color: #909399;
  }

  .home-page .product-info .title{
    color: #606266;
    padding: 15px 20px;
    background: #f2f6fc;
    font-weight: 700;
  }

  .home-page .product-info .title h3{
    margin: 0;
    text-align: left;
    font-size: 17px;
  }

  .home-page .product-info .product-box{
    height: 85px;
  }

  .home-page .product-info .product-box .item{
    position: absolute;
    height: 85px;
    width: 20%;
  }

  .home-page .product-info .product-box .all-product{
    left: 5%;
  }
  .home-page .product-info .product-box .sale-product{
    left: 30%;
  }
  .home-page .product-info .product-box .no-sale-product{
    left: 55%;
  }
  .home-page .product-info .product-box .stockout-product{
    left: 80%;
  }

  .home-page .product-info .product-box .item h3{
    position: absolute;
    margin: 0;
    line-height: 85px;
    left: 25px;
    color: black;
  }

  .home-page .product-info .product-box .item span{
    line-height: 85px;
    font-size: 22px;
    color: #f56c6c;
  }


  .home-page .order-analyze{
    position: relative;
    width: 93%;
    min-height: 300px;
    margin: 0 auto 15px;
    padding:0 0 20px;
    background-color: #FFFFFF;
    border: 1px solid #dcdfe6;
    color: #909399;
  }

  .home-page .order-analyze .title{
    color: #606266;
    padding: 15px 20px;
    background: #f2f6fc;
    font-weight: 700;
  }

  .home-page .order-analyze .title h3{
    margin: 0;
    text-align: left;
    font-size: 17px;
  }

  .home-page .order-analyze .analyze-box{
    height: 100%;
    position: relative;
  }
  .home-page .order-analyze .block{
    padding-top: 5px;
    position: absolute;
    right: 40px;
    top: 0;
    z-index: 999;
  }
  .home-page .order-analyze{
    height: 400px;
  }
  .home-page .order-analyze .line-chart{
    padding-top: 10px;
    width: 70%;
    position: absolute;
    right: 0;
  }
  .home-page .order-analyze .ring-chart{
    position: absolute;
    padding-top: 10px;
    left: 0;
    height: 358px;
    width: 29%;
    border-right:1px solid #dcdfe6;
  }
  .home-page .order-analyze .ring-chart .total-data{
    position: absolute;
    bottom: 10px;
    width: 100%;
  }

  .home-page .data-empty {
    position: absolute;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: rgba(255, 255, 255, 1);
    color: #888;
    font-size: 14px;
  }
</style>
