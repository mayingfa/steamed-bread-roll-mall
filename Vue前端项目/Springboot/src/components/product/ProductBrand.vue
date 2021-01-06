<template>
  <div class="product-brand">
    <el-card class="product-hd" shadow="never">
      <div slot="header" style="text-align: left;">
        <i class="el-icon-search"/>
        <span>筛选搜索</span>
        <el-button class="el-button-search" style="right: 111px" type="primary" @click="queryProductBrand()">查询</el-button>
        <el-button class="el-button-search" style="right: 20px" @click="resetForm('productBrandForm')">重置</el-button>
      </div>
      <el-form :model="productBrandForm" ref="productBrandForm" inline class="query-form">
        <el-form-item label="输入搜索：" prop="key">
          <el-input v-model="productBrandForm.key" @keyup.enter.native="queryProductBrand" clearable placeholder="品牌名称/关键字"></el-input>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card class="product-box" shadow="never">
      <div slot="header" style="text-align: left;position: relative">
        <i class="el-icon-coin"/>
        <span>数据列表</span>
        <el-button class="el-button-add" @click="addBrand">添加</el-button>
      </div>
      <el-table :data="productBrand" class="brand-table" height="425"  border style="width: 100%">
        <el-table-column prop="brandId" label="编号" width="150"></el-table-column>
        <el-table-column prop="brandName" label="品牌名称" width="350"></el-table-column>
        <el-table-column prop="brandDescribe" label="品牌描述" width="600"></el-table-column>
        <el-table-column label="操作" align="center" min-width="200">
          <template slot-scope="scope">
            <el-button size="mini" style="margin: 0 2%" @click="openChange(scope.$index)">编辑</el-button>
            <el-button size="mini" style="margin: 0 2%" type="danger" slot="reference" @click="deleteBrand(scope.$index, productBrand)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-dialog title="编辑商品品牌信息" class="chang-product" :close-on-click-modal="false" :visible.sync="dialogFormVisible" center>
      <el-form class="info-form" inline :rules="alterRules" :model="alterForm" ref='changeFrom'>
        <el-form-item label="品牌名称：" prop="brandName" style="width: 100%">
          <el-input size="small" v-model="alterForm.brandName" placeholder="品牌名称"></el-input>
        </el-form-item>
        <el-form-item label="品牌描述：" prop="brandDescribe" style="width: 100%">
          <el-input size="small" type="textarea" autosize v-model="alterForm.brandDescribe" placeholder="品牌描述"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" style="padding: 12px 54px;margin: 0 55px;" @click="submitChange('changeFrom')">确 定</el-button>
        <el-button style="padding: 12px 54px;margin: 0 55px;" @click="cancelChange('changeFrom')">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    name: "ProductBrand",
    data(){
      return{
        productBrand:null,
        productBrandResource:null,
        handleIndex:0,
        dialogFormVisible:false,
        insert:false,   //插入时的标志
        productBrandForm:{
          key:''
        },
        alterForm:{
          brandId:'',
          brandName:'',
          brandDescribe:'',
        },
        alterRules:{
          brandName: [{ required: true, message: '请输入品牌名称', trigger: 'blur' },
                      { min: 1, max: 30, message: '名称长度不允许超过 30 个字符', trigger: 'blur' }],
          brandDescribe: [{ required: true, message: '请输入品牌描述', trigger: 'blur' },
                      { min: 1, max: 100, message: '描述信息长度不允许超过 100 个字符', trigger: 'blur' }]
        }
      }
    },
    methods:{
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      queryProductBrand(){
        if(this.productBrandForm.key.length!==0){
          this.productBrand=[];
          for(let i=0,len=this.productBrandResource.length;i<len;i++){
            if(this.productBrandResource[i].brandName.indexOf(this.productBrandForm.key)!==-1){
              this.productBrand.push(this.productBrandResource[i]);
            }
          }
        }else{
          this.productBrand = this.productBrandResource;
        }
      },
      openChange(index){
        this.handleIndex=index;
        this.insert=false;
        this.alterForm.brandId = this.productBrand[index].brandId;
        this.alterForm.brandName = this.productBrand[index].brandName;
        this.alterForm.brandDescribe = this.productBrand[index].brandDescribe;
        this.dialogFormVisible=true;
      },
      //删除类别操作
      deleteBrand(index, rows){
        let name = this.productBrand[index].brandName;
        this.$http.post('/product/existsBrand?productBrand='+name).then((rep)=>{
          if(rep.data.code===200){
            if(rep.data.data){
              this.$alert('品牌('+name+')\t下存在商品，无法删除', '警告', {confirmButtonText: '确定', type:"warning"});
            }else{
              this.$confirm('此操作将永久删除该品牌信息, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(() => {
                let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.1)"});
                this.$http.post('/productBrand/deleteById?brandId='+this.productBrand[index].brandId).then((rep)=>{
                  loading.close();
                  if(rep.data.code === 200){
                    rows.splice(index, 1);//删掉该行
                    this.$notify({title: '成功', message: '品牌('+name+')\t移除成功', type: 'success'});
                  }else{
                    this.$notify({title: '错误', message: rep.data.message, type: 'error'});
                  }
                }).catch((err)=>{
                  loading.close();
                  this.$msg.error(err);
                })
              }).catch(() => {
                this.$notify({title: '成功', message: '操作取消', type: 'success'});
              });
            }
          }
        }).catch((err)=>{this.$msg.error(err)})
      },
      addBrand(){
        delete this.alterForm.brandId;
        this.alterForm.brandName='';
        this.alterForm.brandDescribe='';
        this.insert=true;
        this.dialogFormVisible=true;
      },
      submitChange(formName){
        this.$refs[formName].validate((valid) => {
          if (valid) {
            if(this.insert){
              this.$http.post('/productBrand/existsBrandName?brandId=0&brandName='+this.alterForm.brandName).then((rep)=>{
                if(rep.data.code===200){
                  if(rep.data.data){
                    this.$alert('品牌('+this.alterForm.brandName+')\t已存在，不能重复添加', '警告', {confirmButtonText: '确定', type:"warning"});
                  }else{
                    let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.1)"});
                    this.$http.post('/productBrand/add',this.$qs.stringify(this.alterForm)).then((rep)=>{
                      loading.close();
                      if(rep.data.code===200){
                        this.dialogFormVisible=false;
                        this.reqInfo();
                        this.$notify({title: '成功', message: this.alterForm.brandName+'\t品牌添加成功', type: 'success'});
                      }else{
                        this.$notify({title: '错误', message: rep.data.message, type: 'error'});
                      }
                    }).catch((err)=>{loading.close();this.$msg.error(err)})
                  }
                }
              }).catch((err)=>{this.$msg.error(err)})
            }else{
              this.$http.post('/productBrand/existsBrandName?brandId='+this.productBrand[this.handleIndex].brandId+'&brandName='+this.alterForm.brandName).then((rep)=> {
                if (rep.data.code === 200) {
                  if (rep.data.data) {
                    this.$alert('品牌('+this.alterForm.brandName + ')\t已存在，无法完成修改操作', '警告', {
                      confirmButtonText: '确定',
                      type: "warning"
                    });
                  } else {
                    let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.1)"});
                    this.$http.post('/productBrand/update', this.$qs.stringify(this.alterForm)).then((rep) => {
                      loading.close();
                      if (rep.data.code === 200) {
                        this.dialogFormVisible = false;
                        this.productBrand[this.handleIndex] = this.alterForm;
                        this.$notify({title: '成功', message: '品牌信息更新成功', type: 'success'});
                      } else {
                        this.$notify({title: '错误', message: rep.data.message, type: 'error'});
                      }
                    }).catch((err) => {
                      loading.close();
                      this.$msg.error(err)
                    })
                  }
                }
              }).catch((err)=>{this.$msg.error(err)})
            }
          } else {
            return false;
          }
        });
      },
      cancelChange(formName){
        this.dialogFormVisible=false;
        this.$refs[formName].resetFields();
        this.$notify({title: '操作取消', message: '', type: 'success'});
      },
      reqInfo(){
        this.$http.post('/productBrand/findAll').then((rep)=>{
          if(rep.data.code===200){
            this.productBrandResource=this.productBrand=rep.data.data;
          }
        }).catch((err)=>{this.$msg.error(err)})
      }
    },
    created() {
      this.reqInfo();
    }
  }
</script>

<style>
  .product-brand{
    margin: 0;
    padding: 0;
    min-height: 94%;
    user-select: none;
    min-width: 1226px;
  }
  .product-brand .product-hd{
    height: 138px;
    margin: 30px auto 10px;
    width: 95%;
  }

  .product-brand .product-hd .el-card__header{
    position: relative;
  }

  .product-brand .chang-product{
    min-width: 600px;
  }

  .product-brand .info-box{
    height: 600px;
    border:1px solid #EBEEF5;
  }

  .product-brand .product-hd .el-button-search{
    position: absolute;
    top: 14px;
    padding: 3px 0;
    width: 70px;
    height: 30px;
  }

  .product-brand .query-form{
    text-align: left;
  }
  .product-brand .product-box{
    height: 530px;
    margin: 20px auto 10px;
    width: 95%;
  }

  .product-brand .product-box th,.product-brand .product-box td{
    text-align: center;
  }

  .product-brand .el-table th.gutter{
    display: table-cell!important;
  }

  .product-brand .info-form{
    position: relative;
  }

  .product-brand .el-upload-dragger{
    width: 200px;
    height: 100px;
  }

  .product-brand .el-dialog .el-input__inner{
    width: 500px!important;
  }

  .product-brand .el-dialog .el-textarea__inner{
    height: 180px!important;
    width: 500px!important;
  }

  .product-brand .product-box .el-button-add{
    position: absolute;
    right: 32px;
    top: -5px;
    padding: 3px 0;
    width: 80px;
    height: 32px;
  }

  .product-brand .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 102px;
    line-height: 102px;
    text-align: center;
  }

  .product-brand .brand-table{
    user-select: text;
  }
</style>
