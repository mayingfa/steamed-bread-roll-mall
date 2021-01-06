<template>
  <div class="return-goods">
    <el-card class="box-card return-goods-card" shadow="never">
      <div slot="header" class="clearfix">
        <i class="el-icon-search"/>
        <span>筛选搜索</span>
        <el-button class="el-button-search" type="primary" @click="queryOrder(returnGoodsSource)">查询</el-button>
        <el-button class="el-button-search" @click="resetForm('queryForm')">重置</el-button>
      </div>
      <el-form :model="queryForm" class="el-form" ref="queryForm" inline>
        <el-form-item label="订单编号：" prop="orderNo">
          <el-input v-model="queryForm.orderNo" placeholder="订单编号"></el-input>
        </el-form-item>
        <el-form-item label="申请人：" prop="user">
          <el-input v-model="queryForm.user" placeholder="申请人帐号/姓名" auto-complete="no"></el-input>
        </el-form-item>
        <el-form-item label="操作员：" prop="operator">
          <el-input v-model="queryForm.operator" placeholder="操作员帐号/姓名" auto-complete="no"></el-input>
        </el-form-item>
        <el-form-item label="退货状态：" prop="returnState">
          <el-select v-model="queryForm.returnState" filterable placeholder="全部" clearable>
            <el-option v-for="item in returnStates" :key="item" :label="item" :value="item"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="退货原因：" prop="returnReason">
          <el-select v-model="queryForm.returnReason" placeholder="全部" clearable>
            <el-option v-for="(item,index) in returnReasonInfo" :key="index" :label="item" :value="item"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card class="box-card return-goods-card" shadow="never">
      <div slot="header" class="clearfix">
        <i class="el-icon-coin"/>
        <span>数据列表</span>
      </div>
      <div>
        <el-table :data="tableData" height="500px" border slot="empty" stripe highlight-current-row style="width: 100%">
          <el-table-column prop="returnId" label="编号" align="center" width="50"></el-table-column>
          <el-table-column prop="applyTime" label="申请时间" align="center" width="180"></el-table-column>
          <el-table-column label="用户帐号" align="center" width="180">
            <template slot-scope="scope" >
              <el-tooltip class="item" effect="dark" v-if="tableData" placement="right"
                          :content="'姓名：'+tableData[scope.$index].userName">
                <p v-text="tableData[scope.$index].userNumber" style="cursor: pointer"></p>
              </el-tooltip>
            </template>
          </el-table-column>
          <el-table-column prop="orderNo" label="订单编号" align="center" width="140"></el-table-column>
          <el-table-column prop="returnPrice" label="退款金额" align="center" width="100"></el-table-column>
          <el-table-column prop="returnReason" label="退货原因" align="center" width="140"></el-table-column>
          <el-table-column label="操作员" align="center" width="180">
            <template slot-scope="scope" >
              <el-tooltip class="item" effect="dark" v-if="tableData" placement="right"
                          :content="'姓名：'+tableData[scope.$index].operatorName">
                <p v-text="tableData[scope.$index].operatorNumber" style="cursor: pointer"></p>
              </el-tooltip>
            </template>
          </el-table-column>
          <el-table-column label="申请状态" align="center" width="90">
            <template slot-scope="scope">
              <span v-if="scope.row.returnState==='待处理' || scope.row.returnState==='待退款'" style="color: #ff0000">{{scope.row.returnState}}</span>
              <span v-else>{{scope.row.returnState}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="dealTime" label="处理时间" align="center" width="180"></el-table-column>

          <el-table-column label="操作" align="center" min-width="150">
            <template slot-scope="scope">
              <el-button size="mini" v-if="scope.row.returnState==='待收货'" type="primary" @click="receiveGoods(scope.$index)">确认收货</el-button>
              <el-button size="mini" v-if="scope.row.returnState==='待处理'" @click="agree(scope.$index)">同意</el-button>
              <el-button size="mini" v-if="scope.row.returnState==='待处理'" type="danger" @click="reject(scope.$index)">拒绝</el-button>
              <el-button size="mini" v-if="scope.row.returnState==='待退款'" @click="refund(scope.$index)">退款</el-button>
              <el-button size="mini" v-if="scope.row.returnState==='待退款'" type="danger" @click="rejectRefund(scope.$index)">拒绝</el-button>
              <el-button size="mini" v-else-if="scope.row.returnState!=='待收货' && scope.row.returnState!=='待处理'" @click="orderInfo(scope.$index)">订单详情</el-button>
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
            :total="returnGoodsTotal">
          </el-pagination>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
    export default {
      name: "ReturnGoods",
      data(){
        return{
          dialogFormVisible:false,
          handleIndex:0,
          returnGoodsTotal: 0, // 数据的总条数
          pageIndex: 1, //当前页面显示的页码
          pageSize: 10, // 每页显示的数据条数
          pageSizes:[3,5,10,15,20],
          tableData: [],
          // 存储数据的对象，获取到的数据存储到这里
          returnGoodsSource:{},
          // 查询后的数据，方便分页方法的使用
          returnGoodsTemp:{},
          //退货原因
          returnReasonInfo:[],
          //搜索表单
          queryForm:{
            orderNo:'',   /*订单编号*/
            user: '',   /*申请人*/
            operator: '',   /*操作员*/
            returnState:'',/*退货状态*/
            returnReason:'',  /*退货原因*/
          },
          //退货订单状态
          returnStates:['待处理','待收货','已拒绝','退款完成','拒绝退款','允许退货','退货完成'],
        }
      },
      created() {
        this.$http.post('/returnReason/findReasonName').then((rep)=>{
          if(rep.data.code ===200){
            this.returnReasonInfo = rep.data.data;
          }else{
            this.$msg.error(rep.data.message);
          }
        }).catch((err)=>{
          this.$msg.error(err);
        })
        this.reqInfo()
      },
      methods: {
        cancelChange(formName){
          this.$refs[formName].resetFields();
          this.dialogFormVisible=false;
        },
        orderInfo(index){
          let orderNo = this.tableData[index].orderNo;
          this.$router.push({path:'/OrderDetail',query:{orderNo:orderNo,state:'1'}})
        },
        agree(index){
          this.$confirm('同意买家的退货申请？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            let operatorNumber = this.$store.state.user['accountNumber'];
            let operatorName = this.$store.state.user['userName'];
            let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.1)"});
            this.$http.post('/returnGoods/dealWithReturn?returnId='+this.tableData[index].returnId+'&operatorNumber='+operatorNumber+'&operatorName='+operatorName).then((rep)=>{
              loading.close();
              if(rep.data.code===200){
                this.reqInfo()
                this.$notify({title: '成功', message: this.tableData[index].userName+'\t退货申请已处理', type: 'success'});
              }
            }).catch((err)=>{loading.close();this.$msg.error(err)})
          }).catch(() => {});
        },
        //退款
        refund(index){
          let orderNo = this.tableData[index].orderNo;
          let returnPrice = this.tableData[index].returnPrice;
          const confirmText = [`请核查订单编号：${orderNo}，退款金额：${returnPrice}元` , `同意买家的退款申请？`];
          const newData = []
          const h = this.$createElement
          let i =0;
          for ( i in confirmText) {
            if (confirmText.hasOwnProperty(i)) {
              newData.push(h('p', null, confirmText[i]))
            }
          }
          this.$confirm(
            '提示',
            {
              title: '提示',
              message: h('div', null, newData),
              showCancelButton: true,
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }
          ).then(() => {
            let loading = this.$loading({lock: true, text: "订单提交中",background:"rgba(255,255,255,0.1)"});
            this.$http.get('/alipay/refund?orderNo='+orderNo+"&payPrice="+returnPrice).then((rep)=>{
              loading.close();
              if(rep.data.code===200){
                let operatorNumber = this.$store.state.user['accountNumber'];
                let operatorName = this.$store.state.user['userName'];
                let loading = this.$loading({lock: true, text: "数据处理中",background:"rgba(255,255,255,0.1)"});
                this.$http.post('/returnGoods/dealRefund?returnId='+this.tableData[index].returnId+'&operatorNumber='+operatorNumber+'&operatorName='+operatorName).then((rep)=>{
                  loading.close();
                  if(rep.data.code===200){
                    this.reqInfo()
                    this.$notify({title: '成功', message: '商品退款成功，退款金额：'+returnPrice+'￥', type: 'success'});
                  }
                }).catch((err)=>{loading.close();this.$msg.error(err)})
              }
            }).catch((err)=>{loading.close();this.$msg.error(err)})
          }).catch(() => {});
        },
        //收货
        receiveGoods(index){
          this.$confirm('请收到商品并检查无误后，再确认收货', '提示', {
            confirmButtonText: '已收到货',
            cancelButtonText: '未收到货',
            type: 'success'
          }).then(() => {
            let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.2)"});
            let returnId = this.tableData[index].returnId;
            this.$http.post('/returnGoods/receipt?returnId='+returnId).then((rep)=>{
              loading.close();
              if(rep.data.code===200){
                this.reqInfo();
                this.$notify({title: '成功', message: '收货成功', type: 'success'});
                let orderNo = this.tableData[index].orderNo;
                let returnPrice = this.tableData[index].returnPrice;
                let message = "请核查订单编号："+orderNo+",退款金额："+returnPrice;
                this.$alert(message, '自动退款', {
                  confirmButtonText: '确定',
                  type: 'success',
                  callback: () => {
                    this.$http.get('/alipay/refund?orderNo='+orderNo+"&payPrice="+returnPrice).then((rep)=>{
                      if(rep.data.code===200){
                        this.$notify({title: '成功', message: '商品退款成功，退款金额：'+returnPrice+'￥', type: 'success'});
                      }
                    }).catch((err)=>{loading.close();this.$msg.error(err)})
                  }
                });
              }
            }).catch((err)=>{this.$msg.error(err)})
          }).catch(() => {});
        },
        reject(index){
          this.$confirm('拒绝买家的退货申请？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            let operatorNumber = this.$store.state.user['accountNumber'];
            let operatorName = this.$store.state.user['userName'];
            let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.1)"});
            this.$http.post('/returnGoods/refuseReturn?returnId='+this.tableData[index].returnId+'&operatorNumber='+operatorNumber+'&operatorName='+operatorName).then((rep)=>{
              loading.close();
              if(rep.data.code===200){
                this.reqInfo()
                this.$notify({title: '成功', message: this.tableData[index].userName+'\t退货申请已拒绝', type: 'success'});
              }
            }).catch((err)=>{loading.close();this.$msg.error(err)})
          }).catch(() => {});
        },
        rejectRefund(index){
          this.$confirm('拒绝买家的退款申请？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            let operatorNumber = this.$store.state.user['accountNumber'];
            let operatorName = this.$store.state.user['userName'];
            let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.1)"});
            this.$http.post('/returnGoods/rejectRefund?returnId='+this.tableData[index].returnId+'&operatorNumber='+operatorNumber+'&operatorName='+operatorName).then((rep)=>{
              loading.close();
              if(rep.data.code===200){
                this.reqInfo()
                this.$notify({title: '成功', message: this.tableData[index].userName+'\t退款申请已拒绝', type: 'success'});
              }
            }).catch((err)=>{loading.close();this.$msg.error(err)})
          }).catch(() => {});
        },
        //重置按钮调用函数
        resetForm(formName) {
          this.$refs[formName].resetFields();
        },
        //查询按钮操作
        queryOrder(data){
          this.returnGoods=[];
          let queryData = this.queryForm;
          let count =0;
          if(queryData.orderNo.length===0 &&
            queryData.user.length===0 &&
            queryData.operator.length===0 &&
            queryData.returnState.length===0 &&
            queryData.returnReason.length===0
          ){
            this.returnGoods = this.returnGoodsSource;
            this.returnGoodsTotal = this.returnGoodsSource.length;
          }else{
            for(let i=0;i<data.length;i++){
              if(queryData.orderNo.length!==0){
                if(data[i].orderNo===queryData.orderNo){
                  this.returnGoods.push(data[i]);
                  count++;
                }
              }
              if(queryData.user.length!==0){
                if(data[i].userName.indexOf(queryData.user)!==-1){
                  this.returnGoods.push(data[i]);
                  count++;
                } else if(data[i].userNumber.indexOf(queryData.user)!==-1){
                  this.returnGoods.push(data[i]);
                  count++;
                }
              }
              if(queryData.operator.length!==0){
                if(data[i].operatorName.indexOf(queryData.operator)!==-1){
                  this.returnGoods.push(data[i]);
                  count++;
                } else if(data[i].operatorNumber.indexOf(queryData.operator)!==-1){
                  this.returnGoods.push(data[i]);
                  count++;
                }
              }
              if(queryData.returnState.length!==0){
                if(data[i].returnState.indexOf(queryData.returnState)!==-1){
                  this.returnGoods.push(data[i]);
                  count++;
                }
              }
              if(queryData.returnReason.length!==0){
                if(data[i].returnReason.indexOf(queryData.returnReason)!==-1){
                  this.returnGoods.push(data[i]);
                  count++;
                }
              }
            }
            this.returnGoodsTotal=count;
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
          this.currentChangePage(this.returnGoods, pageIndex);
        },
        currentChangePage(data, pageIndex) {
          // 分页开始的下标
          let i = (pageIndex - 1) * this.pageSize;// 当前页面选中的分页下标-1 * 展示的数据的条数
          // 分页结束的下标
          let sum = pageIndex * this.pageSize;// 当前页面选中的分页下标 * 展示的数据的条数
          if(sum > this.returnGoodsTotal){
            sum = this.returnGoodsTotal;
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
          this.$http.post('/returnGoods/findCount').then((rep)=>{
            loading.close();
            if(rep.data.code ===200){
              this.returnGoodsTotal = rep.data.data;
              this.pageSizes.push(this.returnGoodsTotal);
              this.$http.post('/returnGoods/findAllReturn').then((rep)=>{
                if(rep.data.code ===200){
                  this.tableData = rep.data.data;
                  this.returnGoodsSource=this.returnGoods=this.tableData;
                  this.handleSizeChange(this.pageSize);
                }else{
                  this.$msg.error(rep.data.message);
                }
              }).catch((err)=>{
                loading.close();
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
  .return-goods{
    margin: 0;
    padding: 0;
    min-height: 100%;
    background: #FFF;
    border: 1px solid #FFF;
    user-select: none;
  }

  .return-goods .el-card__body{
    padding: 0;
  }
  .return-goods .clearfix{
    text-align: left;
    line-height: 35px;
  }

  .return-goods .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .return-goods .clearfix:after {
    clear: both
  }

  .return-goods .box-card {
    margin: 30px 30px 10px;
  }

  .return-goods .el-card__header{
    padding: 10px 13px!important;
  }
  .return-goods .el-card__body{
    padding-bottom: 10px!important;
    user-select: text;
  }

  .return-goods .el-button-search{
    float: right;
    padding: 3px 0;
    width: 70px;
    height: 35px;
    margin-left:20px;
  }

  .return-goods .el-form{
    text-align: left;
  }
  .return-goods .el-form-item__label{
    width: 140px;
  }
  .return-goods .el-input--suffix .el-input__inner{
    padding-right: 15px;
  }

  /*表格属性*/
  .return-goods .el-table-column--selection .cell{
    padding-left: 9px;
  }
  .return-goods .el-table th.gutter{
    display: table-cell!important;
  }

  .return-goods .el-table{
    height: 445px!important;
  }

  .return-goods .el-pagination{
    margin-top: 10px;
  }

  .return-goods .return-goods-card .el-card__body{
    padding: 20px;
  }

  .return-goods .chang-product .el-dialog--center{
    min-width: 790px;
  }

  .return-goods .product-upload .el-upload-dragger{
    width: 217px;
    height: 155px;
  }
</style>
