<template>
  <div class="shopping-cart">
    <el-button v-if="this.tableData && tableData.length!==0" class="clear-cart" type="primary" icon="el-icon-delete-solid" title="清空购物车" @click="clearCart" circle></el-button>
    <div class="product-box">
      <div class="nav-bar">
        <div class="container">
          <router-link to="/MallHome" style="text-decoration: none" title="花卷官网">
            <el-image class="logo" style="width: 55px;height: 55px;margin-top: 10px;margin-right: 15px;" src="https://spring-oss.oss-cn-beijing.aliyuncs.com/images/img/lg.png"></el-image>
          </router-link>
          <div style="display: inline-block;position:absolute;top: 15px">
            <h2>我的购物车</h2>
            <div class="tip">
              <span>温馨提示：产品是否购买成功，以最终下单为准哦，请尽快结算</span>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="page-main" v-if="tableData">
      <div class="cart-info" v-if="tableData.length!==0">
        <el-table ref="cartTable" :data="tableData" tooltip-effect="dark" :highlight-current-row="false"
                  style="width: 100%;" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="100"></el-table-column>
            <el-table-column label="商品名称">
              <template slot-scope="scope">
                <router-link :to="'/MallPurchase?id='+scope.row.productId" style="text-decoration:none;">
                  <div class="product-name">
                    <el-image class="product-img" :src="scope.row.productUrl"></el-image>
                    <span class="name">{{scope.row.productName}}</span>
                  </div>
                </router-link>
              </template>
            </el-table-column>
          <el-table-column prop="productSpecs" label="规格" width="150"></el-table-column>
          <el-table-column label="单价" width="150">
            <template slot-scope="scope">
              <span>{{scope.row.productPrice}}元</span>
            </template>
          </el-table-column>
          <el-table-column label="数量" width="180">
            <template slot-scope="scope">
              <el-input-number size="small" :min="1" :max="scope.row.productStock" @change="calculationPrice(scope.$index)"  v-model="scope.row.payAmount"></el-input-number>
            </template>
          </el-table-column>
          <el-table-column label="会员折扣" width="120">
            <template slot-scope="scope">
              <span v-if="scope.row.discount===100">无</span>
              <el-tag type="warning" v-else>{{scope.row.discount}}折</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="小计" width="150">
            <template slot-scope="scope">
              <span style="color: #ff6700">{{scope.row.totalPrice}}元</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="150">
            <template slot-scope="scope">
              <el-button type="danger" icon="el-icon-delete" circle @click="deleteProduct(scope.$index)"></el-button>
            </template>

          </el-table-column>
        </el-table>
        <div class="cart-bar">
          <div class="section-left">
            <router-link to="/MallHome" style="text-decoration: none">
              <span class="to-shop">
                继续购物
              </span>
            </router-link>
            <el-divider direction="vertical"></el-divider>
            <span>
              共
              <span style="color: #FF6700">{{this.tableData.length}}</span>
              件商品，已选择
              <span style="color: #FF6700">{{this.selectedData.length}}</span>
              件
            </span>
          </div>
          <div class="section-right">
            <span class="total">
              合计：
              <span style="font-size: 30px">{{summation}}</span>
              元
            </span>
            <el-tooltip content="请勾选需要结算的商品" v-if="selectedData" :disabled="selectedData.length!==0" placement="top" effect="light">
              <div class="button">
                <el-button type="danger" style="height: 100%;width: 100%" :disabled="selectedData.length===0" @click="buy" >去结算</el-button>
              </div>
            </el-tooltip>
          </div>
        </div>
      </div>
      <div v-else class="empty-cart-top">
        <h2>
          您的购物车还是空的！
        </h2>
        <el-button type="success" @click="goShopping">马上去购物</el-button>
      </div>
    </div>

    <el-dialog title="收货地址" class="address-dialog" width="450px"  :visible.sync="addressFormVisible">
      <el-form :model="addressForm" :rules="addressRules" ref="addressForm">
        <el-form-item label="所在地区" prop="selectedOptions">
          <el-cascader size="large" :options="options" separator=" "
                       v-model="addressForm.selectedOptions">
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
      <el-form :model="baseInfo" class="order-form" :rules="baseInfoRules" ref="order">
        <el-form-item label="收货人" prop="userName">
          <el-input v-model="baseInfo.userName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="联系方式" prop="contactWay">
          <el-input v-model="baseInfo.contactWay" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="收货地址" prop="acceptAddress" style="position: relative">
          <el-input v-model="baseInfo.acceptAddress" readonly></el-input>
          <el-button plain style="position: absolute;top:1px;right: 14px;border: none" @click="addressFormVisible=true">修改</el-button>
        </el-form-item>
        <el-form-item label="预计支付">
          <el-input v-model="baseInfo.payMoney+' 元'" readonly></el-input>
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
import MallFooter from "../mall/MallFooter";
    export default {
      name: "ShoppingCart",
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
          tableData:null,
          selectedData:[],
          summation:0,
          options: regionData,
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
          orders:[],
          baseInfo:{
            userName:this.$store.state.user['userName'],
            contactWay:'',
            payMoney:0,
            acceptAddress:this.$store.state.user['userAddress'],
          },
          baseInfoRules:{
            userName:[{ validator: validateUserName, trigger: 'blur' }],
            contactWay:[{ validator: validateContactWay, trigger: 'blur' }],
            acceptAddress:[{ validator: validateAcceptAddress, trigger: 'blur' }]
          },
        }
      },
      methods:{
        submitForm(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              let str='';
              let ad = this.addressForm.selectedOptions;
              for (let i=0,len=ad.length;i<len;i++){
                str +=CodeToText[ad[i]]+' ';
              }
              str+='('+this.addressForm.detailAddress+')';
              this.baseInfo.acceptAddress=str;
              this.addressFormVisible=false;
            } else {
              return false;
            }
          });
        },
        buy(){
          //总支付金额
          this.baseInfo.payMoney = this.summation;
          if(this.baseInfo.userName!==null && this.baseInfo.userName.length===0){
            this.baseInfo.userName = this.$store.state.user['userName'];
          }
          if(this.baseInfo.contactWay!==null && this.baseInfo.contactWay.length===0){
            if(this.$store.state.user['telephone']===null || this.$store.state.user['telephone'].length===0){
              this.$confirm('检测到您未绑定手机号，快递小哥哥将无法找到您', '提示', {
                confirmButtonText: '现在绑定',
                cancelButtonText: '临时填写',
                type: 'warning'
              }).then(() => {this.$router.push('/personalCenter')}).catch(() => {
                this.orderFormVisible=true;
              });
            }else{
              this.baseInfo.contactWay = this.$store.state.user['telephone'];
              this.orderFormVisible=true;
            }
          }else{
            this.orderFormVisible=true;
          }



        },
        createOrder(){
          this.orders.length=0;
          let cartIds = [];
          for(let index=0,len = this.selectedData.length;index < len; index++) {
            let order={
              orderNo : this.getDateNow(),
              userAccount:this.$store.state.user['accountNumber'],
              productNo : this.selectedData[index].productNo,
              payPrice : this.selectedData[index].totalPrice,
              productSpecs : this.selectedData[index].productSpecs,
              payAmount : this.selectedData[index].payAmount,
              userName : this.baseInfo.userName,
              contactWay : this.baseInfo.contactWay,
              payType : '支付宝',
              orderFrom : '网页商城',
              orderState : '待付款',
              returnState : false,   //未退货
              acceptAddress : this.baseInfo.acceptAddress   //未退货
            }
            cartIds.push(this.selectedData[index].cartId);
            this.orders.push(order);
          }
          let max=90000;
          let min=10000;


          let orderNo = Math.floor((Math.random()*max+min)+1) //随机数
          let send ={
            orderNo : orderNo,
            ordersInfo : JSON.stringify(this.orders),
            cartIds: JSON.stringify(cartIds)
          }
          let loading = this.$loading({lock: true, text: "订单处理中",background:"rgba(255,255,255,0.1)"});
          this.$http.post('/order/cartOrder',this.$qs.stringify(send)).then((rep)=>{
            loading.close();
            if(rep.data.code===200){
              let productNo = rep.data.data.split(',');
              let money = 0;
              for (let i=0,len = this.selectedData.length;i<len;i++) {
                for (let j=0;j< productNo.length;j++) {
                  if(this.selectedData[i].productNo === productNo[j]){
                    money+=Number(this.selectedData[i].totalPrice);
                  }
                }
              }
              let msg = '共'+productNo.length+'商品购买成功，需支付 '+money+' 元';
              this.$alert(msg, '下单成功', {
                confirmButtonText: '确定',
                type: 'success',
                callback: () => {
                  let orderName = this.getCurrentDate()+'花卷商城-'+this.baseInfo.userName+'购物车支付订单';
                  let payPrice = this.baseInfo.payMoney;
                  let loading = this.$loading({lock: true, text: "正在跳转支付页面",background:"rgba(255,255,255,0.1)"});
                  this.$http.post('/alipay/create?orderNo='+orderNo+'&orderName='+orderName+'&payPrice='+payPrice).then((response)=>{
                    loading.close();
                    const div = document.createElement('div');
                    div.innerHTML = response.data;
                    document.body.appendChild(div);
                    document.forms[document.forms.length-1].submit();
                  }).catch((err)=>{loading.close();this.$msg.error(err)})
                }
              });
            }
            if(rep.data.code===500){
              this.$message({type: 'warning', message: rep.data.message});
            }
          }).catch((err)=>{loading.close();this.$msg.error(err)})
          this.orderFormVisible=false;
        },
        getCurrentDate(){
          let time = new Date(); // 时间戳
          let year= String(time.getFullYear()); // 年
          let mouth= String(time.getMonth() + 1); // 月
          let day= String(time.getDate()); // 日
          let hours= String(time.getHours()); // 时
          let minute= String(time.getMinutes()); // 分
          return year+'-'+mouth+'-'+day+' '+hours+':'+minute+' ';
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
          return year + mouth + day + hours + seconds + random
        },
        calculationPrice(index){
          this.tableData[index].totalPrice=(this.tableData[index].payAmount * Math.floor(this.tableData[index].productPrice * this.tableData[index].discount / 100)).toFixed(2);
          this.summation=0;
          for(let i=0,len=this.selectedData.length;i<len;i++){
            this.summation+=Number(this.selectedData[i].totalPrice);
          }
          let cartInfo = {};
          cartInfo['cartId'] =this.tableData[index].cartId;
          cartInfo['payAmount'] =this.tableData[index].payAmount;
          this.$http.post('/shoppingCart/update',this.$qs.stringify(cartInfo));
        },
        deleteProduct(index){
          this.$confirm('此商品将从购物车中移除?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.1)"});
            this.$http.post('/shoppingCart/deleteById?cartId='+this.tableData[index].cartId).then((rep)=>{
              loading.close();
              if(rep.data.code===200){
                this.reqInfo();
                this.$message({type: 'success', message: '移除成功!'});
              }
            }).catch((err)=>{loading.close();this.$msg.error(err)})
          }).catch(() => {});
        },
        handleSelectionChange(val){
          this.summation=0;
          this.selectedData=val;
          for(let i=0,len=this.selectedData.length;i<len;i++){
            this.summation+=Number(this.selectedData[i].totalPrice);
          }
        },
        goShopping(){
          this.$router.push({path:'/MallHome'})
        },
        clearCart(){
          this.$confirm('是否清空购物车？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            let loading = this.$loading({lock: true, text: "数据处理中",background:"rgba(255,255,255,0.1)"});
            this.$http.post('/shoppingCart/deleteByUser?accountNumber='+this.$store.state.user['accountNumber']).then((rep)=>{
              loading.close();
              if(rep.data.code===200){
                this.reqInfo();
                this.$message({type: 'success', message: '购物车已清空!'});
              }
            }).catch((err)=>{loading.close();this.$msg.error(err)})
          }).catch(() => {});
        },
        reqInfo(){
          let loading = this.$loading({lock: true, text: "数据加载中",background:"rgba(255,255,255,0.1)"});
          this.$http.post('/shoppingCart/findAll?accountNumber='+this.$store.state.user['accountNumber']).then((rep)=>{
            loading.close();
            let isVip = this.$store.state.user['isVip'];
            if(rep.data.code===200){
              this.tableData=rep.data.data;
              for (let i=0,len=this.tableData.length;i<len;i++){
                if(isVip){
                  this.tableData[i].discount = 98;
                  if(this.tableData[i].productPrice>=1000 && this.tableData[i].productPrice<5000){
                    this.tableData[i].discount=95;
                  }else if(this.tableData[i].productPrice>=5000){
                    this.tableData[i].discount=90;
                  }
                }else{
                  this.tableData[i].discount = 100;
                }
                this.tableData[i].totalPrice=(this.tableData[i].payAmount * Math.floor(this.tableData[i].productPrice * this.tableData[i].discount / 100)).toFixed(2);
              }
            }
          }).catch((err)=>{loading.close();this.$msg.error(err)})
        }
      },
      created() {
        this.reqInfo();
      }
    }
</script>

<style>
.shopping-cart{
  margin: 0;
  padding: 0;
  background: #F5F5F5;
  user-select: none;
  min-height: 100%;
  min-width: 1226px;
}
.shopping-cart .product-box{
  position: relative;
  z-index: 4;
  background: #fff;
  border-top: 1px solid #e0e0e0;
  border-bottom: 1px solid #e0e0e0;
  -webkit-box-shadow: 0 5px 5px rgba(0,0,0,.07);
  box-shadow: 0 5px 5px rgba(0,0,0,.07);
}

.shopping-cart .product-box .nav-bar{
  position: relative;
  height: 80px;
  width: 100%;
  margin-top: 0;
  color: #616161;
  border-bottom: 1px solid #ff6700;

}

.shopping-cart .nav-bar .container{
  position: relative;
  width: 80%;
  min-width: 1226px;
  margin-right: auto;
  margin-left: auto;
  text-align: left;
}

.shopping-cart .nav-bar .container .logo:hover{
  animation: myAnimation 3s;
}

@keyframes myAnimation
{
  0%   {transform:rotateY(0deg) scale(.9,.9);}
  50%  {transform:rotateY(180deg) scale(.9,.9);}
  100% {transform:rotateY(360deg) scale(.9,.9);}
}

.shopping-cart .nav-bar .container h2{
  margin: 0;
  font-size: 28px;
  font-weight: 400;
  line-height: 60px;
  color: #424242;
  display: inline-block;
}

.shopping-cart .nav-bar .container .tip{
  display: inline-block;
  margin-left: 20px;
  font-size: 15px;
  margin-top: 21px;
}

.shopping-cart .nav-bar  div span{
  font-size: 12px;
  color: #757575;
}

.shopping-cart .page-main{
  min-height: 65vh;
  padding: 30px 0;
}

.shopping-cart .page-main .cart-info{
  margin: 0 auto;
  width: 80%;
  min-height: 65vh;
  min-width: 1170px;
}

/*表格属性*/
.shopping-cart .el-table-column--selection .cell{
  padding-left: 9px;
}
.shopping-cart .el-table th.gutter{
  display: table-cell!important;
}

.shopping-cart .el-table th{
  height: 70px;
}


.shopping-cart .el-table th .cell{
  text-align: center;
  color: #424242;
}

.shopping-cart .el-table__body td{
  text-align: center;
  color: #424242;
  font-size: 16px;
}
.shopping-cart .cart-info .product-name{
  text-align: left;
  vertical-align: middle;
}

.shopping-cart .cart-info .product-name .product-img{
  width: 80px;
  height: 80px;
  margin: 10px 10px 10px 0;
  vertical-align: middle;
}

.shopping-cart .cart-info .product-name  .name{
  font-size: 18px;
  font-weight: 400;
  color: #424242;
  white-space:nowrap;
  overflow: hidden;
  vertical-align: middle;
  display: inline-block;
  max-width: 260px;
  overflow: hidden;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
  word-break: break-all;
  text-overflow: ellipsis;
}

.shopping-cart .cart-info .cart-bar{
  position: relative;
  margin: 20px 0;
  width: 100%;
  height: 50px;
  background-color: #fff;
  -webkit-transition: background .3s ease,top .3s ease;
  transition: background .3s ease,top .3s ease;
  line-height: 50px;
}

.shopping-cart .cart-info .cart-bar .section-left{
  position: absolute;
  line-height: 50px;
  margin-left: 32px;
  left: 0;
}

.shopping-cart .cart-info .cart-bar .section-left span{
  font-size: 14px;
  color: #757575
}

.shopping-cart .cart-info .cart-bar .section-left .to-shop:hover{
  color: #ff6700;

}

.shopping-cart .cart-info .cart-bar .section-right{
  position: absolute;
  right: 0;
}

.shopping-cart .cart-info .cart-bar .section-right .total{
  position: absolute;
  top: 0;
  right: 170px;
  height: 50px;
  width: 200px;
  text-align: right;
  margin-left: 20px;
  color: #FF6700;
  font-size: 14px;
}

.shopping-cart .cart-info .cart-bar .section-right .button{
  position: absolute;
  top: 0;
  right: 0;
  height: 50px;
  width: 150px;
}


.shopping-cart .page-main .empty-cart-top{
  height: 273px;
  padding-left: 50%;
  margin: 65px 0 130px;
  background: url(https://spring-oss.oss-cn-beijing.aliyuncs.com/images/img/cart-empty.png) no-repeat 30% 0;
  color: #b0b0b0;
  overflow: hidden;
  text-align: left;
}

.shopping-cart .page-main .empty-cart-top h2{
  margin: 70px 0 0 0;
  font-size: 36px;
}

.shopping-cart .page-main .empty-cart-top .el-button{
  margin: 15px 0 0;
  width: 170px;
  height: 46px;
  font-size: 18px;
}

.shopping-cart .address-dialog .el-dialog__body{
  padding: 30px 80px;
}

.shopping-cart .el-form-item__error{
  padding-left: 74px;
}

.shopping-cart .order-form .el-form-item__label{
  width: 88px;
}

.shopping-cart .order-form .el-input{
  width: 80%;
}
.shopping-cart .order-form .el-form-item__error{
  padding-left: 100px;
}

.shopping-cart .order-form .discount-price .el-input__inner{
  color: red;
}

.shopping-cart .clear-cart{
  position: fixed;
  right: 4%;
  bottom: 6%;
  z-index: 9999;
}

.shopping-cart .clear-cart .el-icon-delete-solid{
  font-size: 22px;
}
</style>
