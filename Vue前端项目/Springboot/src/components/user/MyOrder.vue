<template>
  <div class="my-order">
    <!--导航栏-->
    <div class="mall-nav">
      <el-menu :default-active="activeIndex" class="el-menu-demo" :collapse-transition="false" active-text-color="#409EFF" mode="horizontal" @select="handleSelect">
        <el-menu-item index="1" style="width: 22%">
          <router-link to="/MallHome" style="text-decoration: none">
            <el-image style="width: 220px;height: 60px;" src="https://spring-oss.oss-cn-beijing.aliyuncs.com/images/img/logo.png"></el-image>
          </router-link>
        </el-menu-item>
        <el-menu-item index="2" style="width: 50%">
          <el-input placeholder="请输入内容" v-model="queryProductInfo" @keyup.enter.native="queryProduct(queryProductInfo)" class="input-with-select" style="height: 45px;">
            <el-select v-model="select"  slot="prepend" placeholder="请选择" style="width: 120px;">
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
    <div class="order-box" v-if="this.orderData.length!==0">
      <el-tabs class="order-list" stretch v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="全部订单" name="first">
          <el-table :data="orderData" class="order-table">
            <el-table-column type="expand">
              <template slot-scope="props">
                <el-form label-position="left" inline class="product-form">
                  <el-form-item label="商品名称">
                    <span>{{ props.row.productName}}</span>
                  </el-form-item>
                  <el-form-item v-if="props.row.productSpecs" label="商品规格">
                    <span>{{ props.row.productSpecs }}</span>
                  </el-form-item>
                  <el-form-item label="商品类别">
                    <span>{{ props.row.productType }}</span>
                  </el-form-item>
                  <el-form-item label="商品单价">
                    <span>{{ props.row.outPrice }}</span>
                  </el-form-item>
                  <el-form-item label="购买数量">
                    <span>{{ props.row.payAmount }}</span>
                  </el-form-item>
                  <el-form-item label=" 收 货 人">
                    <span>{{ props.row.userName }}</span>
                  </el-form-item>
                  <el-form-item label=" 联系方式">
                    <span>{{ props.row.contactWay }}</span>
                  </el-form-item>
                  <el-form-item label="收货地址">
                    <span>{{ props.row.acceptAddress }}</span>
                  </el-form-item>
                  <el-form-item label="商品图片">
                    <el-image v-if="props.row.productUrl"
                              style="width: 100px; height: 100px"
                              :src="props.row.productUrl"
                              fit="fit"/>
                  </el-form-item>
                  <el-form-item v-if="props.row.orderState==='待付款'">
                    <span style="color:red">{{changeTime(props.row.orderTime)}}</span>
                  </el-form-item>
                </el-form>
              </template>
            </el-table-column>
            <el-table-column prop="orderNo" label="订单编号" align="center" width="180"></el-table-column>
            <el-table-column prop="productNo" label="商品编号" align="center" width="180"></el-table-column>
            <el-table-column prop="orderTime" label="下单时间" align="center" width="200"></el-table-column>
            <el-table-column prop="payPrice" label="付款金额" align="center" width="150"></el-table-column>
            <el-table-column prop="payType" label="支付方式" align="center" width="120"></el-table-column>
            <el-table-column prop="orderState" align="center" label="订单状态" width="120"></el-table-column>
            <el-table-column align="center" label="操作">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  v-if="scope.row.orderState==='待付款'"
                  @click="payment(scope.row.orderNo,scope.row.orderTime,scope.row.productName,scope.row.payPrice,scope.row.productType)">商品付款</el-button>
                <el-button
                  size="mini"
                  v-if="scope.row.orderState==='已发货'"
                  @click="receiveGoods(scope.row.orderId)">确认收货</el-button>
                <el-button
                  size="mini"
                  v-if="scope.row.orderState==='已拒绝'"
                  @click="service()">联系客服</el-button>
                <el-button
                  v-if="scope.row.orderState==='待付款'"
                  size="mini"
                  type="danger"
                  slot="reference"
                  @click="cancelOrder(scope.row.orderId,scope.row.orderTime)">取消订单</el-button>
                <el-button
                  v-if="scope.row.orderState==='待处理'"
                  disabled
                  size="mini"
                  type="danger"
                  slot="reference"
                  @click="">等待处理</el-button>
                <el-button
                  v-if="scope.row.orderState==='允许退货'"
                  size="mini"
                  type="primary"
                  slot="reference"
                  @click="sendBack(scope.$index)">上门取件</el-button>
                <el-button
                  v-if="scope.row.orderState==='待收货'"
                  size="mini"
                  disabled
                  type="warning"
                  slot="reference">退货中</el-button>
                <el-button
                  size="mini"
                  disabled
                  type="success"
                  v-if="scope.row.orderState==='退货完成'">退货完成</el-button>
                <el-button
                  size="mini"
                  type="primary"
                  v-if="scope.row.orderState==='已收货'"
                  @click="review(scope.row.orderNo,scope.row.productNo,scope.row.productName,scope.row.productSpecs)">评价</el-button>
                <el-button
                  v-if="scope.row.orderState==='已发货'||scope.row.orderState==='已收货'||scope.row.orderState==='已评价'"
                  size="mini"
                  type="danger"
                  slot="reference"
                  @click="applyReturn(scope.row.orderId,scope.row.orderNo,scope.row.payPrice,scope.row.orderState)">申请退货</el-button>
                <el-button
                  v-if="scope.row.orderState==='待发货'"
                  size="mini"
                  type="primary"
                  slot="reference"
                  @click="applyReturn(scope.row.orderId,scope.row.orderNo,scope.row.payPrice,scope.row.orderState)">申请退款</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="待付款" name="second">
          <el-table :data="unpaidData" class="order-table">
            <el-table-column type="expand">
              <template slot-scope="props">
                <el-form label-position="left" inline class="product-form">
                  <el-form-item label="商品名称">
                    <span>{{ props.row.productName }}</span>
                  </el-form-item>
                  <el-form-item v-if="props.row.productSpecs" label="商品规格">
                    <span>{{ props.row.productSpecs }}</span>
                  </el-form-item>
                  <el-form-item label="商品类别">
                    <span>{{ props.row.productType }}</span>
                  </el-form-item>
                  <el-form-item label="商品单价">
                    <span>{{ props.row.outPrice }}</span>
                  </el-form-item>
                  <el-form-item label="购买数量">
                    <span>{{ props.row.payAmount }}</span>
                  </el-form-item>
                  <el-form-item label=" 收 货 人">
                    <span>{{ props.row.userName }}</span>
                  </el-form-item>
                  <el-form-item label=" 联系方式">
                    <span>{{ props.row.contactWay }}</span>
                  </el-form-item>
                  <el-form-item label="收货地址">
                    <span>{{ props.row.acceptAddress }}</span>
                  </el-form-item>
                  <el-form-item label="商品图片">
                    <el-image v-if="props.row.productUrl"
                              style="width: 100px; height: 100px"
                              :src="props.row.productUrl"
                              fit="fit"/>
                  </el-form-item>
                  <el-form-item>
                    <span style="color:red">{{changeTime(props.row.orderTime)}}</span>
                  </el-form-item>
                </el-form>
              </template>
            </el-table-column>
            <el-table-column prop="orderNo" label="订单编号" align="center" width="180"></el-table-column>
            <el-table-column prop="productNo" label="商品编号" align="center" width="180"></el-table-column>
            <el-table-column prop="orderTime" label="下单时间" align="center" width="200"></el-table-column>
            <el-table-column prop="payPrice" label="付款金额" align="center" width="150"></el-table-column>
            <el-table-column prop="payType" label="支付方式" align="center" width="120"></el-table-column>
            <el-table-column prop="orderState" align="center" label="订单状态" width="120"></el-table-column>
            <el-table-column align="center" label="操作">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  v-if="scope.row.orderState==='待付款'"
                  @click="payment(scope.row.orderNo,scope.row.orderTime,scope.row.productName,scope.row.payPrice,scope.row.productType)">商品付款</el-button>
                <el-button
                  v-if="scope.row.orderState==='待付款'"
                  size="mini"
                  type="danger"
                  slot="reference"
                  @click="cancelOrder(scope.row.orderId,scope.row.orderTime)">取消订单</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="待发货" name="third">
          <el-table :data="unshippedData" class="order-table">
            <el-table-column type="expand">
              <template slot-scope="props">
                <el-form label-position="left" inline class="product-form">
                  <el-form-item label="商品名称">
                    <span>{{ props.row.productName }}</span>
                  </el-form-item>
                  <el-form-item v-if="props.row.productSpecs" label="商品规格">
                    <span>{{ props.row.productSpecs }}</span>
                  </el-form-item>
                  <el-form-item label="商品类别">
                    <span>{{ props.row.productType }}</span>
                  </el-form-item>
                  <el-form-item label="商品单价">
                    <span>{{ props.row.outPrice }}</span>
                  </el-form-item>
                  <el-form-item label="购买数量">
                    <span>{{ props.row.payAmount }}</span>
                  </el-form-item>
                  <el-form-item label=" 收 货 人">
                    <span>{{ props.row.userName }}</span>
                  </el-form-item>
                  <el-form-item label=" 联系方式">
                    <span>{{ props.row.contactWay }}</span>
                  </el-form-item>
                  <el-form-item label="收货地址">
                    <span>{{ props.row.acceptAddress }}</span>
                  </el-form-item>
                  <el-form-item label="商品图片">
                    <el-image v-if="props.row.productUrl"
                              style="width: 100px; height: 100px"
                              :src="props.row.productUrl"
                              fit="fit"/>
                  </el-form-item>
                </el-form>
              </template>
            </el-table-column>
            <el-table-column prop="orderNo" label="订单编号" align="center" width="180"></el-table-column>
            <el-table-column prop="productNo" label="商品编号" align="center" width="180"></el-table-column>
            <el-table-column prop="orderTime" label="下单时间" align="center" width="200"></el-table-column>
            <el-table-column prop="payPrice" label="付款金额" align="center" width="150"></el-table-column>
            <el-table-column prop="payType" label="支付方式" align="center" width="120"></el-table-column>
            <el-table-column prop="orderState" align="center" label="订单状态" width="120"></el-table-column>
            <el-table-column align="center" label="操作">
              <template slot-scope="scope">
                <el-button
                  v-if="scope.row.orderState==='待发货'"
                  size="mini"
                  type="primary"
                  slot="reference"
                  @click="applyReturn(scope.row.orderId,scope.row.orderNo,scope.row.payPrice,scope.row.orderState)">申请退款</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="待收货" name="fourth">
          <el-table :data="unreceivedData" class="order-table">
            <el-table-column type="expand">
              <template slot-scope="props">
                <el-form label-position="left" inline class="product-form">
                  <el-form-item label="商品名称">
                    <span>{{ props.row.productName }}</span>
                  </el-form-item>
                  <el-form-item v-if="props.row.productSpecs" label="商品规格">
                    <span>{{ props.row.productSpecs }}</span>
                  </el-form-item>
                  <el-form-item label="商品类别">
                    <span>{{ props.row.productType }}</span>
                  </el-form-item>
                  <el-form-item label="商品单价">
                    <span>{{ props.row.outPrice }}</span>
                  </el-form-item>
                  <el-form-item label="购买数量">
                    <span>{{ props.row.payAmount }}</span>
                  </el-form-item>
                  <el-form-item label=" 收 货 人">
                    <span>{{ props.row.userName }}</span>
                  </el-form-item>
                  <el-form-item label=" 联系方式">
                    <span>{{ props.row.contactWay }}</span>
                  </el-form-item>
                  <el-form-item label="收货地址">
                    <span>{{ props.row.acceptAddress }}</span>
                  </el-form-item>
                  <el-form-item label="商品图片">
                    <el-image v-if="props.row.productUrl"
                              style="width: 100px; height: 100px"
                              :src="props.row.productUrl"
                              fit="fit"/>
                  </el-form-item>
                </el-form>
              </template>
            </el-table-column>
            <el-table-column prop="orderNo" label="订单编号" align="center" width="180"></el-table-column>
            <el-table-column prop="productNo" label="商品编号" align="center" width="180"></el-table-column>
            <el-table-column prop="orderTime" label="下单时间" align="center" width="200"></el-table-column>
            <el-table-column prop="payPrice" label="付款金额" align="center" width="150"></el-table-column>
            <el-table-column prop="payType" label="支付方式" align="center" width="120"></el-table-column>
            <el-table-column prop="orderState" label="订单状态" align="center" width="120"></el-table-column>
            <el-table-column align="center" label="操作">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  v-if="scope.row.orderState==='已发货'"
                  @click="receiveGoods(scope.row.orderId)">确认收货</el-button>
                <el-button
                  v-if="scope.row.orderState==='已发货'"
                  size="mini"
                  type="danger"
                  slot="reference"
                  @click="applyReturn(scope.row.orderId,scope.row.orderNo,scope.row.payPrice,scope.row.orderState)">申请退货</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
    </div>
    <div v-else class="no-order">
      <el-image style="width:400px;height:388px" src="https://spring-oss.oss-cn-beijing.aliyuncs.com/images/img/noorder.png"></el-image>
    </div>
    <el-dialog title="提示" class="return" :visible.sync="returnFormVisible" top="30vh" width="416px">
      <el-form :model="returnFrom" :rules="returnRules" ref="returnFrom">
        <el-form-item label="退货原因"  prop="returnReason">
          <el-select v-model="returnFrom.returnReason" placeholder="请选择退货原因">
            <el-option v-for="(item,index) in returnReasonName" :key="index" :label="item" :value="item"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="returnFormVisible = false">取 消</el-button>
        <el-button size="small" type="primary" @click="submitReturnInfo('returnFrom')">确 定</el-button>
      </div>
    </el-dialog>
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
          <el-input size="small" v-model="alterForm.receiver" placeholder="收货人" readonly></el-input>
        </el-form-item>
        <el-form-item label="收货人联系方式：" prop="receiverTel">
          <el-input size="small" v-model="alterForm.receiverTel" placeholder="收货人联系方式" readonly></el-input>
        </el-form-item>
        <el-form-item label="收货人地址：" prop="contactWay" style="width: 100%">
          <el-input size="small" v-model="alterForm.receiverAdd" placeholder="收货人地址" readonly></el-input>
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
    <el-dialog title="小客服" :visible.sync="serviceVisible" width="30%" style="padding: 0 20px 0">
      <el-image src="https://spring-oss.oss-cn-beijing.aliyuncs.com/images/img/QQ%E5%9B%BE%E7%89%8720201202080302.jpg"></el-image>
      <p style="font-size: 18px;margin: 0">扫码联系客服（微信）</p>
    </el-dialog>
    <el-dialog title="商品评价" class="review" :visible.sync="reviewFormVisible" top="20vh" width="500px">
      <el-form :model="reviewFrom" :rules="reviewRules" ref="reviewFrom">
        <el-form-item label="商品名称" required  prop="productName">
          <el-input size="small" v-model="reviewFrom.productName" readonly placeholder="商品名称"></el-input>
        </el-form-item>
        <el-form-item v-if="reviewFrom.productSpecs" required label="商品规格"  prop="productSpecs">
          <el-input size="small" v-model="reviewFrom.productSpecs" readonly placeholder="商品规格"></el-input>
        </el-form-item>
        <el-form-item label="描述相符" required class="star-item" prop="starLevel" style="margin-bottom: 0">
          <el-rate v-model="reviewFrom.starLevel" :colors="colors" show-text></el-rate>
        </el-form-item>
        <el-form-item label="商品评价" prop="productReview">
          <el-input type="textarea" style="max-height: 120px" :rows="3" placeholder="从多个角度评价宝贝，可以帮助更多想买的人" v-model="reviewFrom.productReview"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="cancelReview('reviewFrom')">取 消</el-button>
        <el-button size="small" type="primary" @click="submitReview('reviewFrom')">确 定</el-button>
      </div>
    </el-dialog>
    <!--底部-->
    <MallFooter></MallFooter>
  </div>
</template>

<script>
  import MallFooter from "../mall/MallFooter";
  export default {
    name: "MyOrder",
    components: {
      MallFooter
    },
    data() {
      let checkStar = (rule, value, callback) => {
        if (value === null || value === 0) {
          callback(new Error('请选择评分'));
        } else {
          callback();
        }
      };
      return{
        receiver: "花卷",
        receiverTel: "400-626-1123",
        receiverAdd: "河北师范大学",
        serviceVisible:false,
        Parcel: ['花卷物流','邮政快递','中通快递','圆通快递','百世快递'],
        activeIndex:'',  //当前激活菜单的 index
        queryProductInfo: '',
        returnReasonName:'',  //退货原因
        returnFormVisible:false,
        dialogFormVisible:false,
        reviewFormVisible:false,
        select: "1",
        activeName: 'first',
        productType:[],
        productBrand:[],
        orderData: [],
        unpaidData: [], //待支付订单
        unshippedData: [], //待发货订单
        unreceivedData:[],  //未收货订单
        returnGoods:{
          userNumber:'',
          userName:'',
          returnPrice:'',
          orderNo:'',
          operatorNumber:'N/A',
          operatorName:'N/A',
          returnReason:''
        },
        returnFrom:{
          returnReason:''
        },
        returnRules:{
          returnReason: [{ required: true, message: '请选择退货原因', trigger: 'blur' }]
        },
        reviewFrom:{
          productNo:'',
          orderNo:'',
          productName:'',
          productSpecs:null,
          starLevel:null,
          productReview:'',
        },
        reviewRules:{
          starLevel: [{ validator: checkStar, trigger: 'blur' }],
          productReview: [{ required: true, message: '请填写商品评价', trigger: 'blur' },
            { min: 1, max: 300, message: '评价内容在 1~300 个字以内', trigger: 'blur' }]
        },
        colors: ['#99A9BF', '#F7BA2A', '#FF9900'],
        // 商品评价
        productReview:{
          accountNumber:'',
          productNo:'',
          productSpecs:'',
          orderNo:'',
          starLevel:0,
          productReview:'',
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
            {min: 1, max: 12, message: '请填写正确的联系方式', trigger: 'blur' }],
          receiverAdd:[{ required: true, message: '请填写收货人地址', trigger: 'blur' },
            {min: 1, max: 30, message: '地址不允许超过30个字符', trigger: 'blur' }],
          sender: [{ required: true, message: '请填写收货人', trigger: 'blur' },
            { min: 1, max: 30, message: '名称长度不允许超过 30 个字符', trigger: 'blur' }],
          senderTel:[{ required: true, message: '请填写收货人联系方式', trigger: 'blur' },
            {min: 1, max: 11, message: '请填写正确的联系方式', trigger: 'blur' }],
          senderAdd:[{ required: true, message: '请填写收货人地址', trigger: 'blur' },
            {min: 1, max: 30, message: '地址不允许超过30个字符', trigger: 'blur' }],
        },
      }
    },
    methods:{
      isTimeOut(time){    //判断未支付的订单是否超过24小时，超过则返回true, 否则返回 false
        let timestamp = (new Date(time).getTime()+24*60*60*1000);
        let currentTime = (new Date());
        return timestamp - currentTime <= 0;
      },
      changeTime(time){ //将下单时间转为 剩余支付时间
        let timestamp = (new Date(time).getTime()+24*60*60*1000);
        let currentTime = (new Date());
        let ms = timestamp - currentTime;
        if(ms<=0){
          return "订单24小时内未支付,稍后将被系统自动回收";
        }
        let ss = 1000;
        let mi = ss * 60;
        let hh = mi * 60;
        let hour = Math.floor(ms / hh);
        let minute = Math.floor((ms - hh * hour)/mi);
        let leftText = "等待买家付款，剩余";
        let rightText = "后自动取消订单";
        if (hour > 0) {
          return leftText + hour + "小时" + minute + "分钟"+rightText;
        } else if (minute > 0) {
          return leftText + minute + "分钟"+rightText;
        }
      },
      handleSelect(key, keyPath) {
        // console.log(key, keyPath);
      },
      handleClick(tab, event) {
        // console.log(tab, event);
      },
      //搜索框
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
      //取消订单
      cancelOrder(orderId,orderTime){
        if(this.isTimeOut(orderTime)){
            this.$alert('订单已超时，系统后续将会自动回收，无法操作', '提示', {
              confirmButtonText: '确认',
              type:'info',
            });
        }else{
          this.$confirm('订单将被取消, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.2)"});
            this.$http.get('/order/delete?orderId='+orderId).then((res)=>{
              loading.close();
              if(res.data.code===200){
                this.$notify({title: '成功', message: '订单已取消', type: 'success'});
              }else{
                this.$notify.error({title: '错误', message: '订单取消失败'});
              }
              this.reqInfo();
            }).catch((err)=>{loading.close();this.$msg.error(err)})
          }).catch(() => {});
        }
      },
      //支付金额
      payment(orderNo,orderTime,productName,payPrice,productType){
        if(this.isTimeOut(orderTime)){
          this.$alert('订单已超时，系统后续将会自动回收，无法操作', '提示', {
            confirmButtonText: '确认',
            type:'info',
          });
        }else{
          let orderName = '花卷商城-'+productType+'-'+productName+'支付订单';
          let loading = this.$loading({lock: true, text: "正在跳转支付页面",background:"rgba(255,255,255,0.1)"});
          this.$http.post('/alipay/create?orderNo='+orderNo+'&orderName='+orderName+'&payPrice='+payPrice).then((response)=>{
            loading.close();
            const div = document.createElement('div');
            div.innerHTML = response.data;
            document.body.appendChild(div);
            document.forms[document.forms.length-1].submit();
          }).catch((err)=>{loading.close();this.$msg.error(err)})
        }
      },
      //商品评价
      review(orderNo,productNo,productName,productSpecs){
        this.reviewFrom.productName = productName;
        this.reviewFrom.productNo = productNo;
        this.reviewFrom.orderNo = orderNo;
        this.reviewFrom.productSpecs = null;
        this.reviewFrom.starLevel = null;
        this.reviewFrom.productReview ='';
        if(productSpecs!==undefined){
          this.reviewFrom.productSpecs = productSpecs;
        }
        this.reviewFormVisible=true;
      },
      cancelReview(formName){
        this.reviewFormVisible = false;
        this.$refs[formName].resetFields();
      },
      //申请退货 或 退款
      applyReturn(orderId,orderNo,payPrice,orderState){
        this.$http.post('/returnReason/findReasonName').then((rep)=>{
          if(rep.data.code===200){
            this.returnReasonName = rep.data.data;
          }
        }).catch((err)=>{this.$msg.error(err)})
        this.returnGoods.userNumber = this.$store.state.user['accountNumber'];
        this.returnGoods.userName = this.$store.state.user['userName'];
        this.returnGoods.returnPrice = payPrice;
        if(orderState==="待发货"){
          this.returnGoods.returnState = "待退款";
        }else{
          this.returnGoods.returnState = "待处理";
        }
        this.returnGoods.orderNo = orderNo;
        this.returnFormVisible = true;
      },
      submitReview(formName){
        this.$refs[formName].validate((valid) => {
            if (valid) {
              this.productReview.accountNumber = this.$store.state.user.accountNumber;
              this.productReview.productNo = this.reviewFrom.productNo
              this.productReview.productSpecs = this.reviewFrom.productSpecs
              this.productReview.orderNo = this.reviewFrom.orderNo
              this.productReview.starLevel = this.reviewFrom.starLevel
              this.productReview.productReview = this.reviewFrom.productReview
              let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.2)"});
              this.$http.post('/productReview/add',this.$qs.stringify(this.productReview,{skipNulls: true})).then((rep)=>{
                loading.close();
                if(rep.data.code===200){
                  this.reqInfo();
                  this.$notify({title: '成功', message: '商品评价提交成功', type: 'success'});
                  this.reviewFormVisible=false;
                  this.$refs[formName].resetFields();
                }else{
                  this.$notify.error({title: '失败', message: "商品评价失败"});
                }
              }).catch((err)=>{loading.close();this.$msg.error(err)})
            } else {
              return false;
            }
        });
      },
      submitReturnInfo(formName){
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.returnGoods.returnReason = this.returnFrom.returnReason;
            let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.2)"});
            this.$http.post('/returnGoods/addReturn',this.$qs.stringify(this.returnGoods,{skipNulls: true})).then((rep)=>{
              loading.close();
              if(rep.data.code===200){
                this.reqInfo();
                this.$notify({title: '成功', message: '退货申请提交成功', type: 'success'});
                this.returnFormVisible = false;
                this.$refs[formName].resetFields();
              }else{
                this.$notify.error({title: '失败', message: "退货申请提交失败"});
              }
            }).catch((err)=>{loading.close();this.$msg.error(err)})
          } else {
            return false;
          }
        });
      },
      //收货
      receiveGoods(orderId){
        this.$confirm('请收到商品并检查无误后，再确认收货', '提示', {
          confirmButtonText: '已收到货',
          cancelButtonText: '未收到货',
          type: 'success'
        }).then(() => {
          let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.2)"});
          this.$http.post('/order/receipt?orderId='+orderId).then((rep)=>{
            loading.close();
            if(rep.data.code===200){
              this.reqInfo();
              this.$notify({title: '成功', message: '收货成功', type: 'success'});
            }
          }).catch((err)=>{loading.close();this.$msg.error(err)})
        }).catch(() => {});
      },
      reqInfo(){
        let loading = this.$loading({lock: true, text: "数据加载中",background:"rgba(255,255,255,1)"});
        this.orderData.length=0;
        this.$http.post('/returnGoods/findReturnInfo?userNumber='+this.$store.state.user['accountNumber']).then((rep)=>{
          if(rep.data.code===200){
            this.orderData = this.orderData.concat(rep.data.data);
          }
        }).catch((err)=>{this.$msg.error(err)})
        this.$http.post('/productType/findAllName').then((rep)=>{
          if(rep.data.code===200){
            this.productType = rep.data.data;
          }
        }).catch((err)=>{this.$msg.error(err)})
        this.$http.post('/productBrand/findAllName').then((rep)=>{
          if(rep.data.code===200){
            this.productBrand = rep.data.data;
          }
        }).catch((err)=>{this.$msg.error(err)})
        this.$http.post('/order/findOrderInfo?userAccount='+this.$store.state.user['accountNumber']).then((rep)=>{
          loading.close();
          if(rep.data.code===200){
            this.unpaidData=[];
            this.unshippedData=[];
            this.unreceivedData=[];
            this.orderData = this.orderData.concat(rep.data.data);
            for(let i=0,len=this.orderData.length;i<len;i++){
              switch (this.orderData[i]['orderState']){
                case '待付款':
                  this.unpaidData.push(this.orderData[i])
                  break;
                case '待发货':
                  this.unshippedData.push(this.orderData[i])
                  break;
                case '已发货':
                  this.unreceivedData.push(this.orderData[i])
                  break;
              }
            }
          }
        }).catch((err)=>{loading.close();this.$msg.error(err)})

      },
      sendBack(index){
        this.alterForm.orderNo = this.orderData[index].orderNo;
        this.alterForm.sender = this.orderData[index].userName;
        this.alterForm.senderTel = this.orderData[index].contactWay;
        this.alterForm.senderAdd = this.orderData[index].acceptAddress;
        this.alterForm.receiver = this.receiver
        this.alterForm.receiverTel=this.receiverTel;
        this.alterForm.receiverAdd=this.receiverAdd;
        this.handleIndex = index;
        this.dialogFormVisible = true;
      },
      service(){
        this.serviceVisible=true;
      },
      submitInfo(formName){
        this.$refs[formName].validate((valid) => {
          if (valid) {
            let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.2)"});
            this.$http.post('/logistics/addInfo',this.$qs.stringify(this.alterForm)).then((rep)=>{
              if(rep.data.code===200){
                let returnId = this.orderData[this.handleIndex].returnId;
                this.$http.post('/returnGoods/sendBack?returnId='+returnId).then((rep)=>{
                  loading.close();
                  if(rep.data.code===200){
                    this.reqInfo();
                    this.dialogFormVisible=false;
                    this.$notify({title: '成功', message: '物流信息更新成功', type: 'success'});
                  }else{
                    this.$msg.error(rep.data.message)
                  }
                }).catch((err)=>{loading.close();this.$msg.error(err)})
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
    },
    created() {
      this.reqInfo();
    }
  }
</script>

<style>
  .my-order{
    margin: 0;
    padding: 0;
    min-height: 100%;
    background: #F5F5F5;
    user-select: none;
  }
  .my-order .order-box{
    width: 80%;
    min-width: 1325px;
    margin: 0 auto 20px;
    border: 1px;
    min-height: 650px;
  }
  .my-order .order-list{
    background: #FFFFFF;
    min-height: 600px;
    margin: 10px 0 0;
  }
  .my-order .order-list .el-tabs__nav{
    padding: 10px 0;
  }

  .my-order .order-list .order-table{
    width: 95%;
    margin: 0 auto 20px;
    height: auto!important;
    min-height: 525px!important;
    user-select: text;
  }


  .my-order .has-gutter th{
    border: 1px solid #EBEEF5;
    border-collapse: collapse;/*关键代码*/
  }

  .my-order .el-table__row td{
    border-right: 1px solid #EBEEF5;
    border-bottom: 1px solid #EBEEF5;
    border-collapse: collapse;/*关键代码*/
  }

  .my-order .el-table__row td:first-child{
    border-left: 1px solid #EBEEF5;
    border-collapse: collapse;/*关键代码*/
  }
  .my-order .el-table__expanded-cell{
    border: 1px solid #EBEEF5;
  }


  .my-order .el-table th.gutter{
    display: table-cell!important;
  }

  .my-order .mall-nav .el-input__inner{
    height: 45px;
  }
  .my-order .mall-nav .el-menu-item{
    margin: 0 1%;
    padding:0;
  }

  .my-order .product-form {
    font-size: 0;
  }
  .my-order .product-form label {
    width: 90px;
    color: #99a9bf;
  }
  .my-order .product-form .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 100%;
  }
  .my-order .product-form .el-form-item__label{
    padding: 0;
    width: 100px;
    /*width: auto;*/
  }

  .my-order .no-order{
    min-height: 600px;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .my-order .return .el-dialog__header{
    text-align: left;
  }

  .my-order .return .el-select{
    width: 100%;
  }

  .my-order .return .el-dialog__body {
    padding: 10px 20px;
  }

  .my-order .logistics .el-dialog {
    border-radius: 15px;
  }

  .my-order .logistics .info-form .el-form-item__label{
    width: 140px!important;
  }
  .my-order .logistics .info-form .el-input__inner{
    width: 194px;
  }

  .my-order .logistics .info-form .el-form-item{
    width: 48%;
  }

  .my-order .el-dialog__header .el-dialog__title{
    font-size: 23px;
  }

  .my-order .el-dialog__body{
    padding-top: 0;
  }

  .my-order .review .el-form {
    width: 450px;
  }

  .my-order .review .el-form .el-form-item{
    margin-left: 25px;
    margin-bottom: 12px;
  }

  .my-order .review .el-form .el-input{
    width: 80%;
  }

  .my-order .review .el-form .el-rate{
    margin-top: 10px;
    text-align: left;
    margin-left: 88px;
  }

  .my-order .review .el-textarea textarea{
    max-height: 120px;
  }

  .my-order .review .el-dialog__body{
    padding-bottom: 10px
  }

  .my-order .review .star-item .el-form-item__error{
    top: 77%;
  }







</style>
