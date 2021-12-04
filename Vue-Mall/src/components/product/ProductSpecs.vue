<template>
  <div class="product-specs">
    <el-card class="product-hd" shadow="never">
      <div slot="header" style="text-align: left;">
        <i class="el-icon-search"/>
        <span>筛选搜索</span>
        <el-button class="el-button-search" style="right: 111px" type="primary" @click="queryProductSpecs()">查询</el-button>
        <el-button class="el-button-search" style="right: 20px" @click="resetForm('productSpecsForm')">重置</el-button>
      </div>
      <el-form :model="productSpecsForm" ref="productSpecsForm" inline class="query-form">
        <el-form-item label="输入搜索：" prop="key">
          <el-input v-model="productSpecsForm.key" @keyup.enter.native="queryProductSpecs" clearable placeholder="商品类别/规格类型"></el-input>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card class="product-specs-box" shadow="never">
      <div slot="header" style="text-align: left;position: relative">
        <i class="el-icon-coin"/>
        <span>数据列表</span>
        <el-button class="el-button-add" @click="addBrand">添加</el-button>
      </div>
      <el-table :data="productSpecs" class="type-table" height="425"  border style="width: 100%">
        <el-table-column prop="specsId" label="编号" width="170"></el-table-column>
        <el-table-column prop="productType" label="商品类型" width="500"></el-table-column>
        <el-table-column prop="specsName" label="规格类型" width="450"></el-table-column>
        <el-table-column label="操作" align="center" min-width="200">
          <template slot-scope="scope">
            <el-button size="mini" style="margin: 0 2%" @click="openChange(scope.$index)">编辑</el-button>
            <el-button size="mini" style="margin: 0 2%" type="danger" slot="reference" @click="deleteBrand(scope.$index, productSpecs)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-dialog title="编辑商品规格信息" class="chang-specs" :close-on-click-modal="false" top="30vh" width="500px" :visible.sync="dialogFormVisible" center>
      <el-form class="info-form" inline :rules="alterRules" :model="alterForm" ref='changeFrom'>
        <el-form-item label="商品类型：" prop="productType" style="width: 100%">
          <el-select v-model="alterForm.productType" filterable placeholder="全部" clearable>
            <el-option v-for="item in productType" :key="item" :label="item" :value="item"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="规格类型：" prop="specsName" style="width: 100%">
          <el-input size="small" v-model="alterForm.specsName" placeholder="规格类型"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" style="padding: 12px 33px;margin: 0 25px;" @click="submitChange('changeFrom')">确 定</el-button>
        <el-button style="padding: 12px 33px;margin: 0 25px;" @click="cancelChange('changeFrom')">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  export default {
      name: "ProductSpecs",
    data(){
      return{
        productSpecs:null,
        productSpecsResource:null,
        productType:null,
        handleIndex:0,
        dialogFormVisible:false,
        insert:false,   //插入时的标志
        productSpecsForm:{
          key:''
        },
        alterForm:{
          specsId:'',
          productType:'',
          specsName:'',
        },
        alterRules:{
          productType: [{ required: true, message: '请选择商品类型', trigger: 'blur' }],
          specsName: [{ required: true, message: '请输入规格类型', trigger: 'blur' },
            { min: 1, max: 30, message: '规格类型长度不允许超过 30 个字符', trigger: 'blur' }]
        }
      }
    },
    methods:{
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      queryProductSpecs(){
        if(this.productSpecsForm.key.length!==0){
          this.productSpecs=[];
          for(let i=0,len=this.productSpecsResource.length;i<len;i++){
            if(this.productSpecsResource[i].productType.indexOf(this.productSpecsForm.key)!==-1){
              this.productSpecs.push(this.productSpecsResource[i]);
            } else if(this.productSpecsResource[i].specsName.indexOf(this.productSpecsForm.key)!==-1){
              this.productSpecs.push(this.productSpecsResource[i]);
            }
          }
        }else{
          this.productSpecs = this.productSpecsResource;
        }
      },
      openChange(index){
        this.handleIndex=index;
        this.insert=false;
        this.alterForm.specsId = this.productSpecs[index].specsId;
        this.alterForm.productType = this.productSpecs[index].productType;
        this.alterForm.specsName = this.productSpecs[index].specsName;
        this.dialogFormVisible=true;
      },
      //删除类别操作
      deleteBrand(index, rows){
        let name = this.productSpecs[index].specsName;
        this.$confirm('此操作将永久删除该商品规格信息, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.1)"});
          this.$http.post('/specs/delete?specsId='+this.productSpecs[index].specsId).then((rep)=>{
            loading.close();
            if(rep.data.code === 200){
              rows.splice(index, 1);//删掉该行
              this.reqInfo();
              this.$notify({title: '成功', message: '商品规格('+name+')\t移除成功', type: 'success'});
            }else{
              this.$notify({title: '错误', message: '商品规格('+name+')\t移除失败', type: 'error'});
            }
          }).catch((err)=>{
            loading.close();
            this.$msg.error(err);
          })
        }).catch(() => {
          this.$notify({title: '成功', message: '操作取消', type: 'success'});
        });
      },
      addBrand(){
        this.alterForm.specsId=null;
        this.alterForm.productType='';
        this.alterForm.specsName='';
        this.insert=true;
        this.dialogFormVisible=true;
      },
      submitChange(formName){
        this.$refs[formName].validate((valid) => {
          if (valid) {
            if(this.insert){
              this.$http.post('/specs/existsSpecsName?specsId=0&productType='+this.alterForm.productType+'&specsName='+this.alterForm.specsName).then((rep)=>{
                if(rep.data.code===200){
                  if(rep.data.data){
                    this.$alert('品牌('+this.alterForm.productType+')\t已存在，不能重复添加', '警告', {confirmButtonText: '确定', type:"warning"});
                  }else{
                    let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.1)"});
                    this.$http.post('/specs/add',this.$qs.stringify(this.alterForm, { skipNulls: true })).then((rep)=>{
                      loading.close();
                      if(rep.data.code===200){
                        this.dialogFormVisible=false;
                        this.reqInfo();
                        this.$notify({title: '成功', message: '商品规格('+this.alterForm.specsName+')\t添加成功', type: 'success'});
                      }else{
                        this.$notify({title: '错误', message: '商品规格('+this.alterForm.specsName+')\t添加失败', type: 'error'});
                      }
                    }).catch((err)=>{loading.close();this.$msg.error(err)})
                  }
                }
              }).catch((err)=>{this.$msg.error(err)})
            }else{
              this.$http.post('/specs/existsSpecsName?specsId='+this.alterForm.specsId+'&productType='+this.alterForm.productType+'&specsName='+this.alterForm.specsName).then((rep)=> {
                if (rep.data.code === 200) {
                  if (rep.data.data) {
                    this.$alert('商品规格('+this.alterForm.productType + ')\t已存在，无法完成修改操作', '警告', {
                      confirmButtonText: '确定',
                      type: "warning"
                    });
                  } else {
                    let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.1)"});
                    this.$http.post('/specs/update', this.$qs.stringify(this.alterForm, { skipNulls: true })).then((rep) => {
                      loading.close();
                      if (rep.data.code === 200) {
                        this.dialogFormVisible = false;
                        this.reqInfo()
                        this.$notify({title: '成功', message: '商品规格更新成功', type: 'success'});
                      } else {
                        this.$notify({title: '错误', message: '商品规格更新失败', type: 'error'});
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
        this.$refs[formName].clearValidate();
        this.$notify({title: '操作取消', message: '', type: 'success'});
      },
      reqInfo(){
        this.$http.post('/specs/findAll').then((rep)=>{
          if(rep.data.code===200){
            this.productSpecsResource=this.productSpecs=rep.data.data;
          }
        }).catch((err)=>{this.$msg.error(err)})
        this.$http.post('/productType/findAllName').then((rep)=>{
          if(rep.data.code===200){
            this.productType=rep.data.data;
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
.product-specs{
  margin: 0;
  padding: 0;
  min-height: 94%;
  user-select: none;
  min-width: 1226px;
}
.product-specs .product-hd{
  height: 138px;
  margin: 30px auto 10px;
  width: 95%;
}

.product-specs .product-hd .el-card__header{
  position: relative;
}


.product-specs .info-box{
  height: 600px;
  border:1px solid #EBEEF5;
}

.product-specs .product-hd .el-button-search{
  position: absolute;
  top: 14px;
  padding: 3px 0;
  width: 70px;
  height: 30px;
}

.product-specs .query-form{
  text-align: left;
}
.product-specs .product-specs-box{
  height: 530px;
  margin: 20px auto 10px;
  width: 95%;
}

.product-specs .product-specs-box th,.product-specs .product-specs-box td{
  text-align: center;
}

.product-specs .el-table th.gutter{
  display: table-cell!important;
}

.product-specs .info-form{
  position: relative;
}

.product-specs .el-upload-dragger{
  width: 200px;
  height: 100px;
}

.product-specs .el-dialog .el-input__inner{
  width: 300px!important;
}


.product-specs .product-specs-box .el-button-add{
  position: absolute;
  right: 32px;
  top: -5px;
  padding: 3px 0;
  width: 80px;
  height: 32px;
}

.product-specs .avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 102px;
  line-height: 102px;
  text-align: center;
}

.product-specs .type-table{
  user-select: text;
}
</style>
