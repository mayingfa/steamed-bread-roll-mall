<!--商品列表组件-->
<template>
  <div class="product-list">
    <el-card class="box-card product-list-card" shadow="never">
      <div slot="header" class="clearfix">
        <i class="el-icon-search"/>
        <span>筛选搜索</span>
        <el-button class="el-button-search" type="primary" @click="queryProduct(tableSource)">查询</el-button>
        <el-button class="el-button-search" @click="resetForm('ruleForm')">重置</el-button>
      </div>
        <el-form :model="ruleForm" class="el-form" ref="ruleForm" inline>
          <el-form-item label="商品名称：" prop="name">
            <el-input v-model="ruleForm.name" placeholder="商品名称"></el-input>
          </el-form-item>
          <el-form-item label="商品货号：" prop="goodsNo">
            <el-input v-model="ruleForm.goodsNo" placeholder="商品货号"></el-input>
          </el-form-item>
          <el-form-item label="商品分类：" prop="sort">
            <el-select v-model="ruleForm.sort" filterable placeholder="请选择分类" clearable>
              <el-option v-for="item in productSort" :key="item" :label="item" :value="item"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="商品品牌：" prop="brand">
            <el-select v-model="ruleForm.brand" filterable placeholder="请选择品牌" clearable>
              <el-option v-for="item in productBrand" :key="item" :label="item" :value="item"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="上架状态：" prop="put_status">
            <el-select v-model="ruleForm.put_status" placeholder="全部" clearable>
              <el-option v-for="(item,index) in putStatus" :key="index" :label="item" :value="item"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="新品状态：" prop="new_status">
            <el-select v-model="ruleForm.new_status" placeholder="全部" clearable>
              <el-option v-for="(item,index) in newStatus" :key="index" :label="item" :value="item"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="缺货状态：" prop="stockout_status">
            <el-select v-model="ruleForm.stockout_status" placeholder="全部" clearable>
              <el-option v-for="(item,index) in stockoutStatus" :key="index" :label="item" :value="item"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
    </el-card>
    <el-card class="box-card product-list-card" shadow="never">
      <div slot="header" class="clearfix">
        <i class="el-icon-coin"/>
        <span>数据列表</span>
        <el-button class="el-button-search" @click="$router.push('/ProductAdd')">添加</el-button>
      </div>
      <div>
        <el-table :data="tableData" height="500px" border slot="empty" stripe highlight-current-row style="width: 100%">
            <el-table-column type="selection" align="center" width="50"></el-table-column>
            <el-table-column prop="productId" label="编号" align="center" width="50"></el-table-column>
            <el-table-column label="商品图片" align="center" width="150">
              <template slot-scope="scope">
                <el-image v-if="tableData[scope.$index]"
                  style="width: 100px; height: 100px"
                  :src="tableData[scope.$index].productUrl"
                  fit="fit"/>
              </template>
            </el-table-column>
            <el-table-column label="商品名称" align="center" width="300">
              <template slot-scope="scope" >
                <p>货号：{{tableData[scope.$index].productNo}}</p>
                <p v-text="tableData[scope.$index].productName"></p>
              </template>
            </el-table-column>
            <el-table-column label="分类/品牌" align="center" width="140">
              <template slot-scope="scope">
                <p>分类：{{tableData[scope.$index].productType}}</p>
                <p>品牌：{{tableData[scope.$index].productBrand}}</p>
              </template>
            </el-table-column>
            <el-table-column label="价格" align="center" width="140">
              <template slot-scope="scope">
                <p>进价：￥{{tableData[scope.$index].inPrice}}</p>
                <p>售价：￥{{tableData[scope.$index].outPrice}}</p>
              </template>
            </el-table-column>
            <el-table-column label="标签" align="center" width="140">
              <template slot-scope="scope">
                <p>上架：<el-switch @change="isSaleState(scope.$index)" v-model="tableData[scope.$index].isSale"/></p>
                <p>新品：<el-switch @change="isNewState(scope.$index)" v-model="tableData[scope.$index].isNew"/></p>
              </template>
            </el-table-column>
            <el-table-column label="库存" width="90" align="center">
              <template slot-scope="scope">
                <el-tooltip class="item" effect="dark" v-if="tableData" placement="right"
                            :content="'最低库存：'+tableData[scope.$index].lowestStock">
                  <p v-text="tableData[scope.$index].productStock" style="cursor: pointer;"></p>
                </el-tooltip>
              </template>
            </el-table-column>
            <el-table-column label="缺货状态" align="center" width="90">
              <template slot-scope="scope">
                <span v-if="tableData[scope.$index].isStockOut" style="color: #ff0000">缺货</span>
                <span v-else>正常</span>
              </template>
            </el-table-column>
            <el-table-column label="开售时间" align="center" width="100" prop="saleTime">
            </el-table-column>
            <el-table-column label="操作" align="center" min-width="150">
              <template slot-scope="scope">
                <div>
                  <el-button size="mini" type="danger" slot="reference" @click="purchase(scope.$index)" style="margin-bottom: 20px!important;">商品采购</el-button>
                </div>
                <div>
                  <el-button size="mini" @click="openChange(scope.$index)">编辑</el-button>
                  <el-button size="mini" type="danger" slot="reference" @click="deleteCheck(scope.$index, tableData)">删除</el-button>
                </div>
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
            :total="productTotal">
          </el-pagination>
        </div>
      </div>
    </el-card>
    <el-dialog title="编辑商品" class="chang-product" :visible.sync="dialogFormVisible" center v-if="tableData[handleIndex]">
      <el-form class="el-form" inline :rules="alterRules" :model="alterForm" ref='changeFrom'>
        <el-form-item label="商品名称：" prop="productName">
          <el-input  v-model="alterForm.productName" placeholder="商品名称"></el-input>
        </el-form-item>
        <el-form-item label="商品分类：" prop="productType">
          <el-select v-model="alterForm.productType" filterable placeholder="请选择分类">
            <el-option v-for="(item,index) in productSort" :key="index" :label="item" :value="item"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="商品进价：" prop="inPrice">
          <el-input type='number' min=0 v-model="alterForm.inPrice" placeholder="商品进价"></el-input>
        </el-form-item>
        <el-form-item label="商品品牌：" prop="productBrand">
          <el-select v-model="alterForm.productBrand" filterable placeholder="请选择品牌">
            <el-option v-for="(item,index) in productBrand" :key="index" :label="item" :value="item"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="商品售价：" prop="outPrice">
          <el-input type='number' min=0 v-model="alterForm.outPrice" placeholder="商品售价"></el-input>
        </el-form-item>
        <el-form-item label="最低库存：" prop="lowestStock">
          <el-input-number size="medium" v-model="alterForm.lowestStock" min=0 max=1000  placeholder="最低库存"></el-input-number>
        </el-form-item>
        <el-form-item label="商品描述：" prop="productDescribe">
          <el-input type="textarea" autosize v-model="alterForm.productDescribe" placeholder="商品描述"></el-input>
        </el-form-item>
        <el-form-item label="商品图片：" prop="productUrl">
          <el-upload
            class="product-upload"
            :action="this.$VAR.http+'/uploadImage?name=productImage'"
            :on-success="uploadProductUrlSuccess"
            :before-upload="beforeAvatarUpload"
            :drag="true"
            list-type="picture"
            style="display: flex;justify-content: center;align-items: center;height: 155px;width: 217px"
            ref="productUpload">
            <el-image :src="alterForm.productUrl" style="height: 155px;width: 217px"></el-image>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" style="padding: 12px 54px;margin: 0 55px;" @click="submitChange('changeFrom')">确 定</el-button>
        <el-button style="padding: 12px 54px;margin: 0 55px;" @click="cancelChange('changeFrom')">取 消</el-button>
      </div>
    </el-dialog>
    <el-dialog title="商品采购" class="chang-purchase" :close-on-click-modal="false" :visible.sync="purchaseFormVisible" width="500px" center>
      <el-form class="info-form" inline :model="purchaseForm" :rules="purchaseRules" ref='changeFrom'>
        <el-form-item label="商品编号：" prop="productNo">
          <el-input size="small" readonly v-model="purchaseForm.productNo" placeholder="商品编号"></el-input>
        </el-form-item>
        <el-form-item label="商品名称：" prop="productName">
          <el-input size="small" readonly v-model="purchaseForm.productName" placeholder="商品名称"></el-input>
        </el-form-item>
        <el-form-item label="供应商：" prop="supplierNo">
          <el-select size="small" v-model="supplierIndex" filterable placeholder="请选择供应商">
            <el-option v-for="(item,index) in supplierInfo" :key="index" :label="item.supplierName" :value="index"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="采购数量：" prop="purchaseNumber">
          <el-input-number size="small" style="width:186px" @change="numberChange" v-model="purchaseForm.purchaseNumber" :min=1 :max=1000 label="采购数量"></el-input-number>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" style="padding: 10px 35px; margin:0 30px 0 60px;" @click="submitPurchaseInfo">确 定</el-button>
        <el-button style="padding: 10px 35px; margin: 0 30px" @click="purchaseFormVisible=false">取消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    name: "ProductList",
    data(){
      return{
        dialogImageUrl: '',   //图片墙上传
        dialogVisible: false, //图片墙上传
        dialogFormVisible:false,
        purchaseFormVisible:false,  //商品采购界面
        handleIndex:0,
        productTotal: 6, // 数据的总条数
        pageIndex: 1, //当前页面显示的页码
        pageSize: 10, // 每页显示的数据条数
        pageSizes:[10,30,60,100,200],
        supplierIndex:0,  //被选择的供应商编号
        supplierInfo:'',  //供应商信息
        // 存储数据的对象，获取到的数据存储到这里
        tableSource:{},
        // 查询后的数据，方便分页方法的使用
        tableTemp:{},
        //搜索表单
        ruleForm:{
          name:'',
          goodsNo:'',
          sort: '',
          brand:'',     /*商品品牌*/
          put_status:'',    /*上架状态*/
          new_status:'',  /*新品状态*/
          stockout_status:'',/*缺货状态*/
        },
        // 修改商品信息表单
        alterForm:{
          productId:'',
          productName:'',
          productType:'',
          productBrand:'',
          outPrice:'',
          inPrice:'',
          lowestStock:'',
          productDescribe:'',
          productUrl:'',
          isStockOut:'',
        },
        alterRules: {
          productName: [{ required: true, message: '请输入商品名称', trigger: 'blur' },
            { min: 1, max: 30, message: '名称长度不允许超过 30 个字符', trigger: 'blur' }],
          outPrice:[{ required: true, message: '请输入商品售价', trigger: 'blur' }],
          inPrice:[{ required: true, message: '请输入商品进价', trigger: 'blur' }],
          productDescribe: [{ required: true, message: '请输入商品描述', trigger: 'blur' },
            { min: 1, max: 100, message: '描述信息长度不允许超过 100 个字符', trigger: 'blur' }]
        },
        purchaseForm:{
          purchaseNo:'',
          productNo:'',
          productName:'',
          purchaseNumber:'',
          supplierNo:'',
          supplierName:'',
          accountNumber:'',
          userName:'',
          receiptStatus:false,
        },
        purchaseRules:{},
        productSort:[],
        productBrand:[],
        putStatus:['上架','下架'],
        newStatus:['新品','正常'],
        stockoutStatus:['正常','缺货'],
        tableData: []
      }
    },
    created() {
      this.$http.post('/product/findCount').then((rep)=>{
        if(rep.data.code ===200){
          this.pageSize =  this.productTotal = rep.data.data;
          this.pageSizes.push(this.productTotal);
        }else{
          this.$msg.error(rep.data.message);
        }
      }).catch((err)=>{
        this.$msg.error(err);
      })
      this.$http.post('/productBrand/findAllName').then((rep)=>{
        if(rep.data.code ===200){
          this.productBrand = rep.data.data;
        }else{
          this.$msg.error(rep.data.message);
        }
      }).catch((err)=>{
        this.$msg.error(err);
      })
      this.$http.post('/productType/findAllName').then((rep)=>{
        if(rep.data.code ===200){
          this.productSort = rep.data.data;
        }else{
          this.$msg.error(rep.data.message);
        }
      }).catch((err)=>{
        this.$msg.error(err);
      })
      this.reqInfo();
      this.$http.post('/supplier/findAllUsable').then((rep)=>{
        if(rep.data.code ===200){
          this.supplierInfo = rep.data.data;
        }else{
          this.$msg.error(rep.data.message);
        }
      }).catch((err)=>{
        this.$msg.error(err);
      })
    },
    methods: {
      reqInfo(){
        let loading = this.$loading({lock: true, text: "数据加载中",background:"rgba(255,255,255,0.1)"});
        this.$http.post('/product/findAll').then((rep)=>{
          loading.close();
          if(rep.data.code ===200){
            this.tableData = rep.data.data;
            this.tableSource=this.tableTemp=this.tableData;
            this.handleSizeChange(this.pageSize);
          }else{
            this.$msg.error(rep.data.message);
          }
        }).catch((err)=>{
          loading.close();
          this.$msg.error(err);
        })
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isGIF = file.type === 'image/gif';
        const isPNG = file.type === 'image/png';
        const isBMP = file.type === 'image/bmp';
        const isWEBP = file.type === 'image/webp';
        const isLt5M = file.size / 1024 / 1024 < 5;

        if (!(isJPG || isGIF || isPNG || isBMP ||isWEBP )) {
          this.$msg.error('仅支持上传 JPG、JPEG、PNG、GIF、BMP、WEBP 格式文件');
        }else{
          if (!isLt5M) {
            this.$msg.error('上传图片大小不能超过 5MB!');
          }
        }
        return (isJPG || isGIF || isPNG || isBMP ||isWEBP) && isLt5M;
      },
      openChange(index){
        this.alterForm.productId=this.tableData[index].productId;
        this.alterForm.productName=this.tableData[index].productName;
        this.alterForm.productType=this.tableData[index].productType;
        this.alterForm.inPrice=this.tableData[index].inPrice;
        this.alterForm.outPrice=this.tableData[index].outPrice;
        this.alterForm.productBrand=this.tableData[index].productBrand;
        this.alterForm.lowestStock=this.tableData[index].lowestStock;
        this.alterForm.productDescribe=this.tableData[index].productDescribe;
        this.alterForm.productUrl=this.tableData[index].productUrl;
        this.alterForm.isStockOut=this.tableData[index].isStockOut;
        this.dialogFormVisible = true;
        this.handleIndex=index;
      },
      submitChange(formName){
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.alterForm.isStockOut = this.tableData[this.handleIndex].productStock < this.alterForm.lowestStock;
            let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.1)"});
            this.$http.post('/product/update',this.$qs.stringify(this.alterForm)).then((rep)=>{
              loading.close();
              if(rep.data.code===200){
                this.tableData[this.handleIndex].productName=this.alterForm.productName;
                this.tableData[this.handleIndex].productType=this.alterForm.productType;
                this.tableData[this.handleIndex].inPrice=this.alterForm.inPrice;
                this.tableData[this.handleIndex].outPrice=this.alterForm.outPrice;
                this.tableData[this.handleIndex].productBrand=this.alterForm.productBrand;
                this.tableData[this.handleIndex].lowestStock=this.alterForm.lowestStock;
                this.tableData[this.handleIndex].productDescribe=this.alterForm.productDescribe;
                this.tableData[this.handleIndex].productUrl=this.alterForm.productUrl;
                this.tableData[this.handleIndex].isStockOut=this.alterForm.isStockOut;
                this.dialogFormVisible=false;
                this.$notify({title: '成功', message: '商品信息更新成功', type: 'success'});
              }else{
                this.$msg.error(rep.data.message)
              }
            }).catch((err)=>{loading.close();this.$msg.error(err)})
          } else {
            return false;
          }
        });
      },
      cancelChange(formName){
        this.$refs[formName].resetFields();
        this.dialogFormVisible=false;
      },
      isSaleState(index){
        let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.1)"});
        this.$http.post('/product/update?productId=' + this.tableData[index].productId + '&isSale=' + this.tableData[index].isSale).then((rep) => {
          loading.close();
          if (rep.data.code === 200) {
            if (this.tableData[index].isSale) {
              this.$notify({title: '成功', message: this.tableData[index].productName+'\t已上架', type: 'success'});
            } else {
              this.$notify({title: '成功', message: this.tableData[index].productName+'\t已下架', type: 'success'});
            }
          }
        }).catch((err) => {
          loading.close();
          this.$msg.error(err)
        })
      },
      isNewState(index){
        let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.1)"});
        this.$http.post('/product/update?productId='+this.tableData[index].productId+'&isNew='+this.tableData[index].isNew).then((rep)=>{
          loading.close()
          if(rep.data.code===200){
            if(this.tableData[index].isNew){
              this.reqInfo();
              this.$notify({title: '成功', message: this.tableData[index].productName+'\t设为新品', type: 'success'});
            }else{
              this.$notify({title: '成功', message: this.tableData[index].productName+'\t设为正常', type: 'success'});
            }
          }
        }).catch((err)=>{loading.close();this.$msg.error(err)})
      },
      //重置按钮调用函数
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      //查询按钮操作
      queryProduct(data){
        this.tableTemp=[];
        let queryData = this.ruleForm;
        let count =0;
        if(queryData.name.length===0 &&
          queryData.goodsNo.length===0 &&
          queryData.brand.length===0 &&
          queryData.put_status.length===0 &&
          queryData.new_status.length===0 &&
          queryData.stockout_status.length===0 &&
          queryData.sort.length===0
        ){
          this.tableTemp = this.tableSource;
          this.productTotal = this.tableSource.length;
        }else{
          for(let i=0;i<data.length;i++){
            if(queryData.name.length!==0){
              if(data[i].productName.indexOf(queryData.name)!==-1){
                this.tableTemp.push(data[i]);
                count++;
              }
            }
            if(queryData.goodsNo.length!==0){
              if(data[i].productNo===queryData.goodsNo){
                this.tableTemp.push(data[i]);
                count++;
              }
            }
            if(queryData.sort.length!==0){
              if(data[i].productType===queryData.sort){
                this.tableTemp.push(data[i]);
                count++;
              }
            }
            if(queryData.brand.length!==0){
              if(data[i].productBrand===queryData.brand){
                this.tableTemp.push(data[i]);
                count++;
              }
            }
            if(queryData.put_status.length!==0){
              if(queryData.put_status==="上架" && data[i].isSale){
                this.tableTemp.push(data[i]);
                count++;
              }else if(queryData.put_status==="下架" && !data[i].isSale){
                this.tableTemp.push(data[i]);
                count++;
              }
            }
            if(queryData.new_status.length!==0){
              if(queryData.new_status==="新品" && data[i].isNew){
                this.tableTemp.push(data[i]);
                count++;
              }else if(queryData.new_status==="正常" && !data[i].isNew){
                this.tableTemp.push(data[i]);
                count++;
              }
            }
            if(queryData.stockout_status.length!==0){
              if(queryData.stockout_status==="缺货" && data[i].isStockOut){
                this.tableTemp.push(data[i]);
                count++;
              }else if(queryData.stockout_status==="正常" && !data[i].isStockOut){
                this.tableTemp.push(data[i]);
                count++;
              }
            }
          }
          this.productTotal=count;
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
        this.currentChangePage(this.tableTemp, pageIndex);
      },
      currentChangePage(data, pageIndex) {
        // 分页开始的下标
        let i = (pageIndex - 1) * this.pageSize;// 当前页面选中的分页下标-1 * 展示的数据的条数
        // 分页结束的下标
        let sum = pageIndex * this.pageSize;// 当前页面选中的分页下标 * 展示的数据的条数
        if(sum > this.productTotal){
          sum = this.productTotal;
        }

        // 让原有的数据清空，原有的数据有一个王小虎在哪里，所以清空掉！
        this.tableData = [];

        // 这一段就是分页的核心代码，利用for循环和计算出来的下标，实现数据的分割！
        for (; i < sum; i++) {
          this.tableData.push(data[i]);
        }
      },
      //删除产品操作
      deleteCheck(index, rows){
        this.$confirm('此操作将永久删除该商品, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let name = this.tableData[index].productName;
          this.$http.post('/product/delete?productId='+this.tableData[index].productId).then((rep)=>{
            if(rep.data.code === 200){
              rows.splice(index, 1);//删掉该行
              this.$notify({title: '成功', message: name+'\t商品移除成功', type: 'success'});
            }
          }).catch((err)=>{
            this.$msg.error(err);
          })
        }).catch(() => {
          this.$notify({title: '成功', message: '操作取消', type: 'success'});
        });
      },
      //上传图片URL
      uploadProductUrlSuccess(response){
        if(response.code===200){
          this.$msg.success("商品图片上传成功！")
          this.alterForm.productUrl=response.data;
          this.$refs['productUpload'].clearFiles();
        }else{
          this.$msg.error("商品图片上传失败！")
        }
      },
      numberChange(){
        this.purchaseForm.purchaseNumber = parseInt(this.purchaseForm.purchaseNumber);
      },
      //根据时间获取采购编号
      getPurchaseNo(){
        let time = new Date(); // 时间戳
        let year= String(time.getFullYear()); // 年
        let mouth= String(time.getMonth() + 1); // 月
        let day= String(time.getDate()); // 日
        let seconds= String(time.getSeconds()); // 秒
        if(seconds.length<2) {
          seconds='0' + seconds
        }
        let max=9000;
        let min=1000;
        let random = Math.floor((Math.random()*max+min)+1); //随机数
        this.purchaseForm.purchaseNo ='P'+ year + mouth + day + seconds +random;
      },
      purchase(index){
        this.purchaseForm.productNo = this.tableData[index].productNo;
        this.purchaseForm.productName = this.tableData[index].productName;
        this.purchaseForm.purchaseNumber = 0;

        this.purchaseForm.accountNumber = this.$store.state.user['accountNumber'];
        this.purchaseForm.userName = this.$store.state.user['userName'];
        this.purchaseForm.receiptStatus=false;

        this.purchaseFormVisible=true;
      },
      submitPurchaseInfo(){
        this.getPurchaseNo();
        this.purchaseForm.supplierNo = this.supplierInfo[this.supplierIndex].supplierNo;
        this.purchaseForm.supplierName = this.supplierInfo[this.supplierIndex].supplierName;
        let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.1)"});
        this.$http.post('/purchase/addPurchase',this.$qs.stringify(this.purchaseForm)).then((rep)=>{
          loading.close();
          if(rep.data.code===200){
            loading.close()
            this.$notify({title: '成功', message: this.purchaseForm.productName+'\t采购信息提交成功', type: 'success'});
            this.purchaseFormVisible=false;
          }else{
            this.$notify.error({title: '失败', message: this.purchaseForm.productName+'\t采购信息提交失败'});
          }
        }).catch((err)=>{loading.close();this.$msg.error(err)})
      }
    }
  }
</script>

<style>
  .product-list{
    margin: 0;
    padding: 0;
    min-height: 100%;
    background: #FFF;
    border: 1px solid #FFF;
    user-select: none;
  }

  .product-list .el-card__body{
    padding: 0;
  }
  .product-list .clearfix{
    text-align: left;
    line-height: 35px;
  }

  .product-list .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .product-list .clearfix:after {
    clear: both
  }

  .product-list .box-card {
    margin: 30px 30px 10px;
  }

  .product-list .el-card__header{
    padding: 10px 13px!important;
  }
  .product-list .el-card__body{
    padding-bottom: 10px!important;
    user-select: text;
  }

  .product-list .el-button-search{
    float: right;
    padding: 3px 0;
    width: 70px;
    height: 35px;
    margin-left:20px;
  }

  .product-list .el-form{
    text-align: left;
  }
  .product-list .el-form-item__label{
    width: 140px;
  }
  .product-list .el-input--suffix .el-input__inner{
    padding-right: 15px;
  }

  /*表格属性*/
  .product-list .el-table-column--selection .cell{
    padding-left: 9px;
  }
  .product-list .el-table th.gutter{
    display: table-cell!important;
  }

  .product-list .el-table{
    height: 445px!important;
  }

  .product-list .el-pagination{
    margin-top: 10px;
  }

  .product-list .product-list-card .el-card__body{
    padding: 20px;
  }

  .product-list .chang-product .el-dialog--center{
    min-width: 790px;
  }

  .product-list .product-upload .el-upload-dragger{
    width: 217px;
    height: 155px;
  }

  .product-list .chang-purchase .el-form--inline .el-form-item__label{
    width: 100px;
  }

  .product-list .chang-purchase .el-form-item__content{
    width: 200px;
  }

  .product-list .chang-purchase .el-dialog{
    border-radius: 15px;
  }

  .product-list .chang-purchase .info-form{
    padding-left: 66px;
  }

  .product-list .chang-purchase .info-form .el-form-item{
    width: 100%;
  }

  .product-list .chang-purchase .el-dialog__body{
    padding: 25px 25px 10px;
  }
</style>
