<template>
  <div class="product-type">
    <el-card class="product-hd" shadow="never">
      <div slot="header" style="text-align: left;">
        <i class="el-icon-search"/>
        <span>筛选搜索</span>
        <el-button class="el-button-search" style="right: 111px" type="primary" @click="queryProductType()">查询</el-button>
        <el-button class="el-button-search" style="right: 20px" @click="resetForm('productTypeForm')">重置</el-button>
      </div>
      <el-form :model="productTypeForm" ref="productTypeForm" inline class="query-form">
        <el-form-item label="输入搜索：" prop="key">
          <el-input v-model="productTypeForm.key" @keyup.enter.native="queryProductType" clearable placeholder="类别名称/关键字"></el-input>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card class="product-box" shadow="never">
      <div slot="header" style="text-align: left;position: relative">
        <i class="el-icon-coin"/>
        <span>数据列表</span>
        <el-button class="el-button-add" @click="addType">添加</el-button>
      </div>
      <el-table :data="productType" class="type-table" height="425"  border style="width: 100%">
        <el-table-column prop="typeId" label="编号" width="100"></el-table-column>
        <el-table-column prop="typeName" label="类别名称" width="150"></el-table-column>
        <el-table-column prop="typeDescribe" label="类别描述" width="400"></el-table-column>
        <el-table-column label="横幅宣传图" align="center" width="300">
          <template slot-scope="scope">
            <el-image v-if="scope.row.typeUrlTop"
                      :preview-src-list="[scope.row.typeUrlTop]"
                      style="width: 275px; height: 100px"
                      :src="scope.row.typeUrlTop"
                      fit="fit"/>
          </template>
        </el-table-column>
        <el-table-column label="左侧宣传图" align="center" width="300">
          <template slot-scope="scope">
            <el-image v-if="scope.row.typeUrlLeft"
                      :preview-src-list="[scope.row.typeUrlLeft]"
                      style="width: 100px; height: 100px"
                      :src="scope.row.typeUrlLeft"
                      fit="fit"/>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" min-width="150">
          <template slot-scope="scope">
            <el-button size="mini" @click="openChange(scope.$index)">编辑</el-button>
            <el-button size="mini" type="danger" slot="reference" @click="deleteCheck(scope.$index, productType)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-dialog title="编辑商品类别信息" class="chang-product" :close-on-click-modal="false" :visible.sync="dialogFormVisible" center>
      <el-form class="info-form" inline :rules="alterRules" :model="alterForm" ref='changeFrom'>
        <el-form-item label="商品名称：" prop="typeName" style="width: 100%">
          <el-input size="small" v-model="alterForm.typeName" placeholder="商品名称"></el-input>
        </el-form-item>
        <el-form-item label="商品描述：" prop="typeDescribe" style="width: 100%">
          <el-input size="small" type="textarea" autosize v-model="alterForm.typeDescribe" placeholder="商品描述"></el-input>
        </el-form-item>
        <el-form-item label="横幅宣传图" prop="typeUrlTop" style="width: 45%;position:absolute;top: 0;right: 20px;">
          <el-upload
            class="product-upload"
            :action="this.$VAR.http+'/uploadImage?name=productTypeImage'"
            :on-success="uploadTopSuccess"
            :before-upload="beforeAvatarUpload"
            :drag="true"
            list-type="picture"
            style="display: flex;justify-content: center;align-items: center;height: 100px;width: 217px"
            ref="topUpload">
            <el-image v-if="alterForm.typeUrlTop" :src="alterForm.typeUrlTop" style="height: 100px;width: 217px"></el-image>
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="左侧宣传图" prop="typeUrlLeft" style="width: 45%;position:absolute;top: 170px;right: 20px;">
          <el-upload
            class="product-upload"
            :action="this.$VAR.http+'/uploadImage?name=productTypeImage'"
            :on-success="uploadLeftSuccess"
            :before-upload="beforeAvatarUpload"
            :drag="true"
            list-type="picture"
            style="display: flex;justify-content: center;align-items: center;height: 100px;width: 217px"
            ref="leftUpload">
            <el-image v-if="alterForm.typeUrlLeft" :src="alterForm.typeUrlLeft" style="height: 100px;width: 217px"></el-image>
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
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
    name: "ProductType",
    data(){
      return{
        productType:null,
        productTypeResource:null,
        handleIndex:0,
        dialogFormVisible:false,
        insert:false,   //插入时的标志
        productTypeForm:{
          key:'',
        },
        alterForm:{
          typeId:'',
          typeName:'',
          typeDescribe:'',
          typeUrlTop:'',
          typeUrlLeft:''
        },
        alterRules:{
          typeName: [{ required: true, message: '请输入类别名称', trigger: 'blur' },
            { min: 1, max: 30, message: '名称长度不允许超过 30 个字符', trigger: 'blur' }],
          typeDescribe: [{ required: true, message: '请输入类别描述', trigger: 'blur' },
            { min: 1, max: 100, message: '描述信息长度不允许超过 100 个字符', trigger: 'blur' }],
          typeUrlTop: [{ required: true, message: '横幅宣传图', trigger: 'blur' }],
          typeUrlLeft: [{ required: true, message: '左侧宣传图', trigger: 'blur' }],
        }
      }
    },
    methods:{
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      queryProductType(){
        if(this.productTypeForm.key.length!==0){
          this.productType=[];
          for(let i=0,len=this.productTypeResource.length;i<len;i++){
            if(this.productTypeResource[i].typeName.indexOf(this.productTypeForm.key)!==-1){
              this.productType.push(this.productTypeResource[i]);
            }
          }
        }else{
          this.productType = this.productTypeResource;
        }
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isGIF = file.type === 'image/gif';
        const isPNG = file.type === 'image/png';
        const isBMP = file.type === 'image/bmp';
        const isWEBP = file.type === 'image/webp';
        const isLt5M = file.size / 1024 / 1024 < 5;

        if (!(isJPG || isGIF || isPNG || isBMP || isWEBP)) {
          this.$msg.error('仅支持上传 JPG、JPEG、PNG、GIF、BMP、WEBP 格式文件');
        }else{
          if (!isLt5M) {
            this.$msg.error('上传图片大小不能超过 5MB!');
          }
        }
        return (isJPG || isGIF || isPNG || isBMP || isWEBP) && isLt5M;
      },
      openChange(index){
        this.handleIndex=index;
        this.insert=false;
        this.alterForm.typeId = this.productType[index].typeId;
        this.alterForm.typeName = this.productType[index].typeName;
        this.alterForm.typeDescribe = this.productType[index].typeDescribe;
        this.alterForm.typeUrlTop = this.productType[index].typeUrlTop;
        this.alterForm.typeUrlLeft = this.productType[index].typeUrlLeft;
        this.dialogFormVisible=true;
      },
      //删除类别操作
      deleteCheck(index, rows){
        this.$http.post('/product/existsType?productType='+this.productType[index].typeName).then((rep)=>{
          if(rep.data.code===200){
            let name = this.productType[index].typeName;
            if(rep.data.data){
              this.$alert('类别('+name+')\t存在商品，无法删除', '警告', {confirmButtonText: '确定', type:"warning"});
            }else{
              this.$confirm('此操作将永久删除该类别信息, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(() => {
                let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.1)"});
                this.$http.post('/productType/deleteById?typeId='+this.productType[index].typeId).then((rep)=>{
                  loading.close();
                  if(rep.data.code === 200){
                    rows.splice(index, 1);//删掉该行
                    this.$notify({title: '成功', message: '类别('+name+')\t移除成功', type: 'success'});
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
      submitChange(formName){
        this.$refs[formName].validate((valid) => {
          if (valid) {
            if(this.insert){
              this.$http.post('/productType/existsTypeName?typeId=0&typeName='+this.alterForm.typeName).then((rep)=>{
                if(rep.data.code===200){
                  if(rep.data.data){
                    this.$alert('类别('+this.alterForm.typeName+')\t已存在，不能重复添加', '警告', {confirmButtonText: '确定', type:"warning"});
                  }else{
                    let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.1)"});
                    this.$http.post('/productType/add',this.$qs.stringify(this.alterForm)).then((rep)=>{
                      loading.close();
                      if(rep.data.code===200){
                        this.dialogFormVisible=false;
                        this.reqInfo();
                        this.$notify({title: '成功', message: this.alterForm.typeName+'\t类别添加成功', type: 'success'});
                      }else{
                        this.$notify({title: '错误', message: rep.data.message, type: 'error'});
                      }
                    }).catch((err)=>{loading.close();this.$msg.error(err)})
                  }
                }
              }).catch((err)=>{this.$msg.error(err)})
            }else{
              this.$http.post('/productType/existsTypeName?typeId='+this.alterForm.typeId+'&typeName='+this.alterForm.typeName).then((rep)=> {
                if (rep.data.code === 200) {
                  if (rep.data.data) {
                    this.$alert('类别('+this.alterForm.typeName + ')\t已存在，无法完成修改操作', '警告', {
                      confirmButtonText: '确定',
                      type: "warning"
                    });
                  } else {
                    let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.1)"});
                    this.$http.post('/productType/update', this.$qs.stringify(this.alterForm)).then((rep) => {
                      loading.close();
                      if (rep.data.code === 200) {
                        this.dialogFormVisible = false;
                        this.productType[this.handleIndex] = this.alterForm;
                        this.$notify({title: '成功', message: '类别信息更新成功', type: 'success'});
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
      uploadTopSuccess(response){
        if(response.code===200){
          this.$msg.success("横幅宣传图上传成功！")
          this.alterForm.typeUrlTop=response.data;
          this.$refs['topUpload'].clearFiles();
        }else{
          this.$msg.error("横幅宣传图上传失败！")
        }
      },
      uploadLeftSuccess(response){
        if(response.code===200){
          this.$msg.success("左侧宣传图上传成功！")
          this.alterForm.typeUrlLeft=response.data;
          this.$refs['leftUpload'].clearFiles();
        }else{
          this.$msg.error("左侧宣传图上传失败！")
        }
      },
      addType(){
        delete this.alterForm.typeId;
        this.alterForm.typeName='';
        this.alterForm.typeDescribe='';
        this.alterForm.typeUrlTop='';
        this.alterForm.typeUrlLeft='';
        this.insert=true;
        this.dialogFormVisible=true;
      },
      reqInfo(){
        this.$http.post('/productType/findAll').then((rep)=>{
          if(rep.data.code===200){
            this.productTypeResource=this.productType=rep.data.data;
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
  .product-type{
    margin: 0;
    padding: 0;
    min-height: 94%;
    user-select: none;
    min-width: 1226px;
  }
  .product-type .product-hd{
    height: 138px;
    margin: 30px auto 10px;
    width: 95%;
  }

  .product-type .product-hd .el-card__header{
    position: relative;
  }


  .product-type .info-box{
    height: 600px;
    border:1px solid #EBEEF5;
  }

  .product-type .product-hd .el-button-search{
    position: absolute;
    top: 14px;
    padding: 3px 0;
    width: 70px;
    height: 30px;
  }

  .product-type .query-form{
    text-align: left;
  }

  .product-type .chang-product{
    min-width: 650px;
  }


  .product-type .product-box{
    height: 530px;
    margin: 20px auto 10px;
    width: 95%;
  }

  .product-type .product-box th,.product-type .product-box td{
    text-align: center;
  }

  .product-type .el-table th.gutter{
    display: table-cell!important;
  }

  .product-type .info-form{
    position: relative;
  }

  .product-type .el-upload-dragger{
    width: 200px;
    height: 100px;
  }

  .product-type .el-textarea__inner{
    height: 280px!important;
    width: 188px!important;
  }

  .product-type .product-box .el-button-add{
    position: absolute;
    right: 32px;
    top: -5px;
    padding: 3px 0;
    width: 80px;
    height: 32px;
  }

  .product-type .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 102px;
    line-height: 102px;
    text-align: center;
  }

  .product-type .type-table{
    user-select: text;
  }
</style>
