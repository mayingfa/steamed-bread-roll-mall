<template>
  <div class="supplier">
    <el-card class="supplier-hd" shadow="never">
      <div slot="header" style="text-align: left;">
        <i class="el-icon-search"/>
        <span>筛选搜索</span>
        <el-button class="el-button-search" style="right: 111px" type="primary" @click="querySupplier()">查询</el-button>
        <el-button class="el-button-search" style="right: 20px" @click="resetForm('supplierForm')">重置</el-button>
      </div>
      <el-form :model="supplierForm" ref="supplierForm" inline class="query-form">
        <el-form-item label="输入搜索：" prop="key">
          <el-input v-model="supplierForm.key" @keyup.enter.native="querySupplier" clearable placeholder="供应商"></el-input>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card class="supplier-box" shadow="never">
      <div slot="header" style="text-align: left;position: relative">
        <i class="el-icon-coin"/>
        <span>数据列表</span>
        <el-button class="el-button-add" @click="addSupplier">添加</el-button>
      </div>
      <el-table :data="supplierData" class="type-table" height="425"  border style="width: 100%;user-select: text">
        <el-table-column prop="supplierId" label="编号" width="100"></el-table-column>
        <el-table-column prop="supplierNo" label="供应商编号" width="180"></el-table-column>
        <el-table-column prop="supplierName" label="供应商名称" width="180"></el-table-column>
        <el-table-column prop="productType" label="商品类别" width="180"></el-table-column>
        <el-table-column prop="principal" label="负责人" width="180"></el-table-column>
        <el-table-column prop="contactWay" label="联系方式" width="180"></el-table-column>
        <el-table-column label="是否启用" width="180">
          <template slot-scope="scope">
            <el-switch @change="isSupplierState(scope.$index)" v-model="supplierData[scope.$index].status"/>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" min-width="200">
          <template slot-scope="scope">
            <el-button size="mini" style="margin: 0 4%" @click="openChange(scope.$index)">编辑</el-button>
            <el-button size="mini" style="margin: 0 4%" type="danger" slot="reference" @click="deleteReason(scope.$index, supplierData)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-dialog title="编辑供应商信息" class="chang-supplier" :close-on-click-modal="false" :visible.sync="dialogFormVisible" width="420px" center>
      <el-form class="info-form" inline :rules="alterRules" :model="alterForm" ref='changeFrom'>
        <el-form-item label="供应商名称：" prop="supplierName" style="width: 100%">
          <el-input size="small" v-model="alterForm.supplierName" placeholder="供应商名称"></el-input>
        </el-form-item>
        <el-form-item label="商品分类：" prop="productType" style="width: 100%">
          <el-select size="small" v-model="alterForm.productType" filterable placeholder="请选择类别">
            <el-option v-for="(item,index) in productTypeInfo" :key="index" :label="item" :value="item"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="供应商负责人：" prop="principal" style="width: 100%">
          <el-input size="small" v-model="alterForm.principal" placeholder="供应商负责人"></el-input>
        </el-form-item>
        <el-form-item label="联系方式：" prop="contactWay" style="width: 100%">
          <el-input size="small" v-model="alterForm.contactWay" placeholder="联系方式"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" style="padding: 12px 30px;margin: 0 30px;" @click="submitChange('changeFrom')">确 定</el-button>
        <el-button style="padding: 12px 30px;margin: 0 30px;" @click="cancelChange('changeFrom')">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "Supplier",
  data(){
    let checkTelephone = (rule, value, callback) => {
      let reg = new RegExp("^((0\\d{2,3}-\\d{7,8})|(1[3584]\\d{9}))$");
      if (!reg.test(value)) {
        return callback(new Error("请填写正确的联系方式"));
      } else {
        callback();
      }
    };
    return{
      supplierData:null,
      supplierResource:null,
      handleIndex:0,
      dialogFormVisible:false,
      productTypeInfo:[],
      insert:false,   //插入时的标志
      supplierForm:{
        key:''
      },
      alterForm:{
        supplierId:'',
        supplierNo:'',
        supplierName:'',
        productType:'',
        principal:'',   //负责人
        contactWay:'',
        status:'',
      },
      alterRules:{
        supplierName: [{ required: true, message: '请填写供应商名称', trigger: 'blur' },
          { min: 1, max: 30, message: '名称长度不允许超过 30 个字符', trigger: 'blur' }],
        productType: [{ required: true, message: '请选择商品分类', trigger: 'blur' }],
        principal: [{ required: true, message: '请填写供应商负责人', trigger: 'blur' }],
        contactWay:[{ required: true, message: '请填写联系方式', trigger: 'blur' }
                    ,{validator: checkTelephone, trigger: 'blur'}],
      }
    }
  },
  methods:{
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    querySupplier(){
      if(this.supplierForm.key.length!==0){
        this.supplierData=[];
        for(let i=0,len=this.supplierResource.length;i<len;i++){
          if(this.supplierResource[i].supplierName.indexOf(this.supplierForm.key)!==-1){
            this.supplierData.push(this.supplierResource[i]);
          }
        }
      }else{
        this.supplierData = this.supplierResource;
      }
    },
    isSupplierState(index){
      let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.2)"});
      this.$http.post('/supplier/updateSupplier?supplierId=' + this.supplierData[index].supplierId + '&status=' + this.supplierData[index].status).then((rep) => {
        loading.close();
        if (rep.data.code === 200) {
          if (this.supplierData[index].status) {
            this.$notify({title: '成功', message: '供应商（'+this.supplierData[index].supplierName+'）\t已启用', type: 'success'});
          } else {
            this.$notify({title: '成功', message: '供应商（'+this.supplierData[index].supplierName+'）\t已禁用', type: 'success'});
          }
        }
      }).catch((err) => {
        loading.close();
        this.$msg.error(err)
      })
    },
    openChange(index){
      this.handleIndex=index;
      this.insert=false;
      this.alterForm = this.supplierData[index];
      this.dialogFormVisible=true;
    },
    //删除类别操作
    deleteReason(index, rows){
      let name = this.supplierData[index].supplierName;
      this.$confirm('此操作将永久删除该供应商信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.2)"});
        this.$http.post('/supplier/deleteSupplier?supplierId='+this.supplierData[index].supplierId).then((rep)=>{
          loading.close();
          if(rep.data.code === 200){
            rows.splice(index, 1);//删掉该行
            this.$notify({title: '成功', message: '供应商('+name+')\t移除成功', type: 'success'});
          }else{
            this.$notify({title: '错误', message: rep.data.message, type: 'error'});
          }
        }).catch((err)=>{
          loading.close();
          this.$msg.error(err);
        })
      }).catch(() => {
        this.$notify({title: '操作取消', message: '', type: 'success'});
      });
    },
    addSupplier(){
      this.getDateNow();
      delete this.alterForm.supplierId;
      this.handleIndex=-1;
      this.alterForm.supplierName='';
      this.alterForm.productType='';
      this.alterForm.principal='';
      this.alterForm.contactWay='';
      this.alterForm.status=true;
      this.insert=true;
      this.dialogFormVisible=true;
    },
    //根据时间获取商品编号
    getDateNow(){
      let time = new Date(); // 时间戳
      let year= String(time.getFullYear()); // 年
      let mouth= String(time.getMonth() + 1); // 月
      let day= String(time.getDate()); // 日
      let hours= String(time.getHours()); // 时
      if(hours.length<2){
        hours='0' + hours
      }
      let minutes= String(time.getMinutes()); // 分
      if(minutes.length<2) {
        minutes='0' + minutes
      }
      let seconds= String(time.getSeconds()); // 秒
      if(seconds.length<2) {
        seconds='0' + seconds
      }
      let max=900;
      let min=100;
      let random = Math.floor((Math.random()*max+min)+1); //随机数
      this.alterForm.supplierNo = year + mouth + day + hours + minutes + seconds +random;
    },
    submitChange(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if(this.insert){
            this.$http.post('/supplier/existSupplier?supplierId=0&supplierName='+this.alterForm.supplierName).then((rep)=>{
              if(rep.data.code===200){
                if(rep.data.data){
                  this.$alert('供应商('+this.alterForm.supplierName+')\t已存在，不能重复添加', '警告', {confirmButtonText: '确定', type:"warning"});
                }else{
                  let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.1)"});
                  this.$http.post('/supplier/addSupplier',this.$qs.stringify(this.alterForm)).then((rep)=>{
                    loading.close()
                    if(rep.data.code===200){
                      this.dialogFormVisible=false;
                      this.reqInfo();
                      this.$notify({title: '成功', message: '供应商('+this.alterForm.supplierName+')\t添加成功', type: 'success'});
                    }else{
                      this.$notify({title: '错误', message: rep.data.message, type: 'error'});
                    }
                  }).catch((err)=>{loading.close();this.$msg.error(err)})
                }
              }
            }).catch((err)=>{this.$msg.error(err)})
          }else{
            this.$http.post('/supplier/existSupplier?supplierId='+this.supplierData[this.handleIndex].supplierId+'&supplierName='+this.alterForm.supplierName).then((rep)=> {
              if (rep.data.code === 200) {
                if (rep.data.data) {
                  this.$alert('供应商('+this.alterForm.supplierName+')\t已存在，无法完成修改操作', '警告', {
                    confirmButtonText: '确定',
                    type: "warning"
                  });
                } else {
                  this.dialogFormVisible = false;
                  let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.1)"});
                  this.$http.post('/supplier/updateSupplier', this.$qs.stringify(this.alterForm)).then((rep) => {
                    loading.close()
                    if (rep.data.code === 200) {
                      this.supplierData[this.handleIndex] = this.alterForm;
                      this.$notify({title: '成功', message: '供应商('+this.alterForm.supplierName+')\t更新成功', type: 'success'});
                    } else {
                      this.$notify({title: '错误', message: rep.data.message, type: 'error'});
                    }
                  }).catch((err) => {
                    loading.close()
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
      this.$http.post('/supplier/findAll').then((rep)=>{
        if(rep.data.code===200){
          this.supplierResource=this.supplierData=rep.data.data;
        }
      }).catch((err)=>{this.$msg.error(err)})
      this.$http.post('/productType/findAllName').then((rep)=>{
        if(rep.data.code===200){
          this.productTypeInfo = rep.data.data;
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
.supplier{
  margin: 0;
  padding: 0;
  min-height: 94%;
  user-select: none;
  min-width: 1226px;
}
.supplier .supplier-hd{
  height: 138px;
  margin: 30px auto 10px;
  width: 95%;
}

.supplier .supplier-hd .el-card__header{
  position: relative;
}


.supplier .info-box{
  height: 600px;
  border:1px solid #EBEEF5;
}

.supplier .supplier-hd .el-button-search{
  position: absolute;
  top: 14px;
  padding: 3px 0;
  width: 70px;
  height: 30px;
}

.supplier .query-form{
  text-align: left;
}
.supplier .supplier-box{
  height: 530px;
  margin: 20px auto 10px;
  width: 95%;
}

.supplier .supplier-box th,.supplier .supplier-box td{
  text-align: center;
}

.supplier .el-table th.gutter{
  display: table-cell!important;
}

.supplier .info-form{
  position: relative;
}

.supplier .el-upload-dragger{
  width: 200px;
  height: 100px;
}


.supplier .supplier-box .el-button-add{
  position: absolute;
  right: 32px;
  top: -5px;
  padding: 3px 0;
  width: 80px;
  height: 32px;
}

.supplier .avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 102px;
  line-height: 102px;
  text-align: center;
}

.supplier .chang-supplier .el-dialog--center .el-dialog__body{
  padding: 25px 25px 0;
}

.supplier .chang-supplier .el-form--inline .el-form-item__label{
  width: 128px;
}

.supplier .chang-supplier .el-input__inner{
  width: 200px;
}
</style>
