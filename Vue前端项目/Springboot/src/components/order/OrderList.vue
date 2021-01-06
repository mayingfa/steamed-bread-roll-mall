<!--商品列表组件-->
<template>
  <div class="order-list">
    <el-card class="box-card order-list-card" shadow="never">
      <div slot="header" class="clearfix">
        <i class="el-icon-search"/>
        <span>筛选搜索</span>
        <el-button class="el-button-search" type="primary" @click="queryOrder(orderSource)">查询</el-button>
        <el-button class="el-button-search" @click="resetForm('queryForm')">重置</el-button>
      </div>
      <el-form :model="queryForm" class="el-form" ref="queryForm" inline>
        <el-form-item label="订单编号：" prop="orderNo">
          <el-input v-model="queryForm.orderNo" placeholder="订单编号"></el-input>
        </el-form-item>
        <el-form-item label="商品编号：" prop="productNo">
          <el-input v-model="queryForm.productNo" placeholder="商品编号"></el-input>
        </el-form-item>
        <el-form-item label="收货人：" prop="user">
          <el-input v-model="queryForm.user" placeholder="收货人姓名/手机号"></el-input>
        </el-form-item>
        <el-form-item label="订单状态：" prop="orderState">
          <el-select v-model="queryForm.orderState" filterable placeholder="全部" clearable>
            <el-option v-for="item in orderStates" :key="item" :label="item" :value="item"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="支付方式：" prop="payType">
          <el-select v-model="queryForm.payType" placeholder="全部" clearable>
            <el-option v-for="(item,index) in payType" :key="index" :label="item" :value="item"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="订单来源：" prop="orderFrom">
          <el-select v-model="queryForm.orderFrom" placeholder="全部" clearable>
            <el-option v-for="(item,index) in orderFrom" :key="index" :label="item" :value="item"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card class="box-card order-list-card" shadow="never">
      <div slot="header" class="clearfix">
        <i class="el-icon-coin"/>
        <span>数据列表</span>
      </div>
      <div>
        <el-table :data="tableData" height="500px" border slot="empty" stripe highlight-current-row style="width: 100%">
          <el-table-column prop="orderId" label="编号" align="center" width="50"></el-table-column>
          <el-table-column prop="orderNo" label="订单编号" align="center" width="140"></el-table-column>
          <el-table-column prop="orderTime" label="提交时间" align="center" width="180"></el-table-column>
          <el-table-column prop="productNo" label="商品编号" align="center" width="180"></el-table-column>
          <el-table-column label="用户名称" align="center" width="140">
            <template slot-scope="scope">
              <el-tooltip class="item" effect="dark" v-if="tableData" placement="right"
                          :content="'帐号：'+tableData[scope.$index].userAccount+', 联系方式：'+
                          tableData[scope.$index].contactWay+', 地址：'+tableData[scope.$index].acceptAddress">
                <p v-text="tableData[scope.$index].userName" style="cursor: pointer"></p>
              </el-tooltip>
            </template>

          </el-table-column>
          <el-table-column prop="payPrice" label="订单金额" align="center" width="140"></el-table-column>
          <el-table-column prop="payType" label="支付方式" align="center" width="140"></el-table-column>
          <el-table-column prop="orderFrom" label="订单来源" align="center" width="140"></el-table-column>
          <el-table-column prop="orderState" label="订单状态" align="center" width="140"></el-table-column>
          <el-table-column label="操作" align="center" min-width="150">
            <template slot-scope="scope">
                <el-button size="mini" type="danger" v-if="tableData[scope.$index].orderState==='待发货'" @click="sendGoods(scope.$index)">确认发货</el-button>
                <el-button size="mini" v-else @click="orderInfo(scope.$index)">订单详情</el-button>
            </template>
          </el-table-column>
        </el-table>
        <div class="block">
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :page-sizes="pageSizes"
            :page-size="pageSize"
            :current-page="pageIndex"
            layout="total, sizes, prev, pager, next, jumper"
            :total="orderTotal">
          </el-pagination>
        </div>
      </div>
    </el-card>
    <el-dialog title="物流信息" class="logistics" :close-on-click-modal="false" :visible.sync="dialogFormVisible" width="800px" center>
      <el-form class="info-form" inline :rules="alterRules" :model="alterForm" ref='changeFrom'>
        <el-form-item label="订单编号：" prop="orderNo">
          <el-input size="small" v-model="alterForm.orderNo" readonly placeholder="订单编号"></el-input>
        </el-form-item>
        <el-form-item label="快递公司：" prop="parcelName">
          <el-select size="small" v-model="alterForm.parcelName" filterable placeholder="快递公司">
            <el-option v-for="(item,index) in Parcel" :key="index" :label="item" :value="item"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="收货人：" prop="receiver">
          <el-input size="small" v-model="alterForm.receiver" placeholder="收货人"></el-input>
        </el-form-item>
        <el-form-item label="收货人联系方式：" prop="receiverTel">
          <el-input size="small" v-model="alterForm.receiverTel" placeholder="收货人联系方式"></el-input>
        </el-form-item>
        <el-form-item label="收货人地址：" prop="contactWay" style="width: 100%">
          <el-input size="small" v-model="alterForm.receiverAdd" placeholder="收货人地址"></el-input>
        </el-form-item>
        <el-form-item label="发货人：" prop="sender">
          <el-input size="small" v-model="alterForm.sender" placeholder="发货人"></el-input>
        </el-form-item>
        <el-form-item label="发货人联系方式：" prop="senderTel">
          <el-input size="small" v-model="alterForm.senderTel" placeholder="发货人联系方式"></el-input>
        </el-form-item>
        <el-form-item label="发货人地址：" prop="senderAdd">
          <el-input size="small" v-model="alterForm.senderAdd" placeholder="发货人地址"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" style="padding: 12px 30px;margin: 0 30px;" @click="submitInfo('changeFrom')">确 定</el-button>
        <el-button style="padding: 12px 30px;margin: 0 30px;" @click="cancelChange('changeFrom')">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "OrderList",
  data(){
    return{
      Sender: "花卷",
      SenderTel: "400-626-1123",
      SenderAdd: "河北师范大学",
      Parcel: ['花卷物流','邮政快递','中通快递','圆通快递','百世快递'],
      dialogFormVisible:false,
      handleIndex:0,
      orderTotal: 0, // 数据的总条数
      pageIndex: 1, //当前页面显示的页码
      pageSize: 10, // 每页显示的数据条数
      pageSizes:[5,10,30,60,100,200,400],
      // 存储数据的对象，获取到的数据存储到这里
      orderSource:{},
      // 查询后的数据，方便分页方法的使用
      orderTemp:{},
      //搜索表单
      queryForm:{
        orderNo:'',   /*订单编号*/
        productNo:'', /*商品编号*/
        user: '',   /*收货人*/
        orderState:'',/*订单状态*/
        payType:'',  /*支付方式*/
        orderFrom:'',  /*订单来源*/
      },
      // 修改物流信息表单
      alterForm:{
        orderNo:'',
        receiver:'',
        receiverTel:'',
        receiverAdd:'',
        sender:'',
        senderTel:'',
        senderAdd:'',
        parcelName:'花卷物流',    //快递公司
      },
      alterRules: {
        parcelName:[{required: true, message: '请选择物流公司', trigger: 'blur'}],
        receiver: [{ required: true, message: '请填写收货人', trigger: 'blur' },
          { min: 1, max: 30, message: '名称长度不允许超过 30 个字符', trigger: 'blur' }],
        receiverTel:[{ required: true, message: '请填写收货人联系方式', trigger: 'blur' },
          {min: 1, max: 11, message: '请填写正确的联系方式', trigger: 'blur' }],
        receiverAdd:[{ required: true, message: '请填写收货人地址', trigger: 'blur' },
          {min: 1, max: 30, message: '地址不允许超过30个字符', trigger: 'blur' }],
        sender: [{ required: true, message: '请填写收货人', trigger: 'blur' },
          { min: 1, max: 30, message: '名称长度不允许超过 30 个字符', trigger: 'blur' }],
        senderTel:[{ required: true, message: '请填写收货人联系方式', trigger: 'blur' },
          {min: 1, max: 12, message: '请填写正确的联系方式', trigger: 'blur' }],
        senderAdd:[{ required: true, message: '请填写收货人地址', trigger: 'blur' },
          {min: 1, max: 30, message: '地址不允许超过30个字符', trigger: 'blur' }],
      },
      orderStates:['待付款','待发货','已发货','已收货','已评价','开通成功'],
      payType:['微信','支付宝'],
      orderFrom:['APP订单','网页商城'],
      tableData: [],
    }
  },
  created() {
    this.reqInfo();
  },
  methods: {
    sendGoods(index){
      this.alterForm.orderNo = this.tableData[index].orderNo;
      this.alterForm.receiver = this.tableData[index].userName;
      this.alterForm.receiverTel = this.tableData[index].contactWay;
      this.alterForm.receiverAdd = this.tableData[index].acceptAddress;
      this.alterForm.sender = this.Sender
      this.alterForm.senderTel=this.SenderTel;
      this.alterForm.senderAdd=this.SenderAdd;
      this.handleIndex = index;
      this.dialogFormVisible = true;
    },
    submitInfo(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.2)"});
          this.$http.post('/logistics/addInfo',this.$qs.stringify(this.alterForm)).then((rep)=>{
            if(rep.data.code===200){
              let orderId = this.tableData[this.handleIndex].orderId;
              let orderState = '已发货';
              this.$http.post('/order/update?orderId='+orderId+'&orderState='+orderState).then((rep)=>{
                if(rep.data.code===200){
                  loading.close();
                  this.reqInfo();
                  this.dialogFormVisible=false;
                  this.$notify({title: '成功', message: '物流信息更新成功', type: 'success'});
                }else{
                  this.$msg.error(rep.data.message)
                }
              }).catch((err)=>{this.$msg.error(err)})
            }else{
              this.$msg.error(rep.data.message)
            }
          }).catch((err)=>{this.$msg.error(err)})
        } else {
          return false;
        }
      });
    },
    cancelChange(formName){
      this.$refs[formName].resetFields();
      this.dialogFormVisible=false;
    },
    orderInfo(index){
      let orderNo = this.tableData[index].orderNo;
      this.$router.push({path:'/OrderDetail',query:{orderNo:orderNo,state:'0'}})
    },
    isSaleState(index){
      this.$http.post('/product/update?productId=' + this.tableData[index].productId + '&isSale=' + this.tableData[index].isSale).then((rep) => {
        if (rep.data.code === 200) {
          if (this.tableData[index].isSale) {
            this.$notify({title: '成功', message: this.tableData[index].productName+'\t已上架', type: 'success'});
          } else {
            this.$notify({title: '成功', message: this.tableData[index].productName+'\t已下架', type: 'success'});
          }
        }
      }).catch((err) => {
        this.$msg.error(err)
      })
    },
    isNewState(index){
      this.$http.post('/product/update?productId='+this.tableData[index].productId+'&isSale='+this.tableData[index].isNew).then((rep)=>{
        if(rep.data.code===200){
          if(this.tableData[index].isNew){
            this.$notify({title: '成功', message: this.tableData[index].productName+'\t设为新品', type: 'success'});
          }else{
            this.$notify({title: '成功', message: this.tableData[index].productName+'\t设为正常', type: 'success'});
          }
        }
      }).catch((err)=>{this.$msg.error(err)})
    },
    //重置按钮调用函数
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    //查询按钮操作
    queryOrder(data){
      this.orderTemp=[];
      let queryData = this.queryForm;
      let count =0;
      if(queryData.orderNo.length===0 &&
        queryData.productNo.length===0 &&
        queryData.user.length===0 &&
        queryData.orderState.length===0 &&
        queryData.payType.length===0 &&
        queryData.orderFrom.length===0
      ){
        this.orderTemp = this.orderSource;
        this.orderTotal = this.orderSource.length;
      }else{
        for(let i=0;i<data.length;i++){
          if(queryData.orderNo.length!==0){
            if(data[i].orderNo===queryData.orderNo){
              this.orderTemp.push(data[i]);
              count++;
            }
          }
          if(queryData.productNo.length!==0){
            if(data[i].productNo===queryData.productNo){
              this.orderTemp.push(data[i]);
              count++;
            }
          }
          if(queryData.user.length!==0){
            if(data[i].userName.indexOf(queryData.user)!==-1){
              this.orderTemp.push(data[i]);
              count++;
            } else if(data[i].contactWay.indexOf(queryData.user)!==-1){
              this.orderTemp.push(data[i]);
              count++;
            }
          }
          if(queryData.orderState.length!==0){
            if(data[i].orderState.indexOf(queryData.orderState)!==-1){
              this.orderTemp.push(data[i]);
              count++;
            }
          }
          if(queryData.payType.length!==0){
            if(data[i].payType.indexOf(queryData.payType)!==-1){
              this.orderTemp.push(data[i]);
              count++;
            }
          }
          if(queryData.orderFrom.length!==0){
            if(data[i].orderFrom.indexOf(queryData.orderFrom)!==-1){
              this.orderTemp.push(data[i]);
              count++;
            }
          }
        }
        this.orderTotal=count;
      }
      this.handleSizeChange(this.pageSize);
    },
    //表格分页操作
    handleSizeChange(pageSize) {
      this.pageSize=pageSize;
      this.handleCurrentChange(this.pageIndex);
    },
    handleCurrentChange(pageIndex) {
      this.pageIndex=pageIndex;
      this.currentChangePage(this.orderTemp, pageIndex);
    },
    currentChangePage(data, pageIndex) {
      // 分页开始的下标
      let i = (pageIndex - 1) * this.pageSize;// 当前页面选中的分页下标-1 * 展示的数据的条数
      // 分页结束的下标
      let sum = pageIndex * this.pageSize;// 当前页面选中的分页下标 * 展示的数据的条数
      if(sum > this.orderTotal){
        sum = this.orderTotal;
      }

      // 让原有的数据清空，原有的数据有一个王小虎在哪里，所以清空掉！
      this.tableData = [];

      // 这一段就是分页的核心代码，利用for循环和计算出来的下标，实现数据的分割！
      for (; i < sum; i++) {
        this.tableData.push(data[i]);
      }
    },
    reqInfo(){
      let loading = this.$loading({lock: true, text: "数据加载中",spinner: "el-icon-loading",background:"rgba(255,255,255,0.1)"});
      this.$http.post('/order/findCount').then((rep)=>{
        if(rep.data.code ===200){
          loading.close()
          this.pageSize = this.orderTotal = rep.data.data;
          this.pageSizes.push(this.orderTotal);
          this.$http.post('/order/findAll').then((rep)=>{
            if(rep.data.code ===200){
              this.tableData = rep.data.data;
              this.orderSource=this.orderTemp=this.tableData;
              this.handleSizeChange(this.pageSize);
            }else{
              this.$msg.error(rep.data.message);
            }
          }).catch((err)=>{
            loading.close()
            this.$msg.error(err);
          })
        }else{
          this.$msg.error(rep.data.message);
        }
      }).catch((err)=>{
        this.$msg.error(err);
      })
    }
  }
}
</script>

<style>
.order-list{
  margin: 0;
  padding: 0;
  min-height: 100%;
  background: #FFF;
  border: 1px solid #FFF;
  user-select: none;
}

.order-list .el-card__body{
  padding: 0;
}
.order-list .clearfix{
  text-align: left;
  line-height: 35px;
}

.order-list .clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.order-list .clearfix:after {
  clear: both
}

.order-list .box-card {
  margin: 30px 30px 10px;
}

.order-list .el-card__header{
  padding: 10px 13px!important;
}
.order-list .el-card__body{
  padding-bottom: 10px!important;
  user-select: text;
}

.order-list .el-button-search{
  float: right;
  padding: 3px 0;
  width: 70px;
  height: 35px;
  margin-left:20px;
}

.order-list .el-form{
  text-align: left;
}
.order-list .el-form-item__label{
  width: 140px;
}
.order-list .el-input--suffix .el-input__inner{
  padding-right: 15px;
}

/*表格属性*/
.order-list .el-table-column--selection .cell{
  padding-left: 9px;
}
.order-list .el-table th.gutter{
  display: table-cell!important;
}

.order-list .el-table{
  height: 445px!important;
}

.order-list .el-pagination{
  margin-top: 10px;
}

.order-list .order-list-card .el-card__body{
  padding: 20px;
}

.order-list .chang-product .el-dialog--center{
  min-width: 790px;
}

.order-list .product-upload .el-upload-dragger{
  width: 217px;
  height: 155px;
}

.order-list .logistics .el-dialog {
  border-radius: 15px;
}

.order-list .logistics .info-form .el-form-item{
  width: 48%;
}
</style>

