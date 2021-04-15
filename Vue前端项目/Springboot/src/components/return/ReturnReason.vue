<template>
  <div class="return-reason">
    <el-card class="reason-hd" shadow="never">
      <div slot="header" style="text-align: left;">
        <i class="el-icon-search"/>
        <span>筛选搜索</span>
        <el-button class="el-button-search" style="right: 111px" type="primary" @click="queryReturnReason()">查询</el-button>
        <el-button class="el-button-search" style="right: 20px" @click="resetForm('returnReasonForm')">重置</el-button>
      </div>
      <el-form :model="returnReasonForm" ref="returnReasonForm" inline class="query-form">
        <el-form-item label="输入搜索：" prop="key">
          <el-input v-model="returnReasonForm.key" @keyup.enter.native="queryReturnReason" clearable placeholder="退货原因"></el-input>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card class="reason-box" shadow="never">
      <div slot="header" style="text-align: left;position: relative">
        <i class="el-icon-coin"/>
        <span>数据列表</span>
        <el-button class="el-button-add" @click="addReason">添加</el-button>
      </div>
      <el-table :data="returnReason" class="type-table" height="425"  border style="width: 100%">
        <el-table-column prop="reasonId" label="编号" width="200"></el-table-column>
        <el-table-column prop="reasonName" label="原因类型" width="600"></el-table-column>
        <el-table-column label="是否启用" width="350">
          <template slot-scope="scope">
            <el-switch @change="isReasonState(scope.$index)" v-model="returnReason[scope.$index].status"/>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" min-width="200">
          <template slot-scope="scope">
            <el-button size="mini" style="margin: 0 4%" @click="openChange(scope.$index)">编辑</el-button>
            <el-button size="mini" style="margin: 0 4%" type="danger" slot="reference" @click="deleteReason(scope.$index, returnReason)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-dialog title="编辑退货原因" class="chang-reason" :close-on-click-modal="false" :visible.sync="dialogFormVisible" width="420px" top="28vh" center>
      <el-form class="info-form" inline :rules="alterRules" :model="alterForm" ref='changeFrom'>
        <el-form-item label="退货原因：" prop="reasonName" style="width: 100%">
          <el-input size="small" v-model="alterForm.reasonName" placeholder="退货原因"></el-input>
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
        name: "ReturnReason",
      data(){
        return{
          returnReason:null,
          returnReasonResource:null,
          handleIndex:0,
          dialogFormVisible:false,
          insert:false,   //插入时的标志
          returnReasonForm:{
            key:''
          },
          alterForm:{
            reasonId:'',
            reasonName:'',
            status:'',
          },
          alterRules:{
            reasonName: [{ required: true, message: '请输入退货原因', trigger: 'blur' },
              { min: 1, max: 30, message: '退货原因长度不允许超过 30 个字符', trigger: 'blur' }]
          }
        }
      },
      methods:{
        resetForm(formName) {
          this.$refs[formName].resetFields();
        },
        queryReturnReason(){
          if(this.returnReasonForm.key.length!==0){
            this.returnReason=[];
            for(let i=0,len=this.returnReasonResource.length;i<len;i++){
              if(this.returnReasonResource[i].reasonName.indexOf(this.returnReasonForm.key)!==-1){
                this.returnReason.push(this.returnReasonResource[i]);
              }
            }
          }else{
            this.returnReason = this.returnReasonResource;
          }
        },
        isReasonState(index){
          let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.1)"});
          this.$http.post('/returnReason/updateReason?reasonId=' + this.returnReason[index].reasonId + '&status=' + this.returnReason[index].status).then((rep) => {
            loading.close();
            if (rep.data.code === 200) {
              if (this.returnReason[index].status) {
                this.$notify({title: '成功', message: this.returnReason[index].reasonName+'\t已启用', type: 'success'});
              } else {
                this.$notify({title: '成功', message: this.returnReason[index].reasonName+'\t已禁用', type: 'success'});
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
          this.alterForm = this.returnReason[index];
          this.dialogFormVisible=true;
        },
        //删除类别操作
        deleteReason(index, rows){
          let name = this.returnReason[index].reasonName;
          this.$confirm('此操作将永久删除该退货原因, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.2)"});
            this.$http.post('/returnReason/deleteReason?reasonId='+this.returnReason[index].reasonId).then((rep)=>{
              loading.close();
              if(rep.data.code === 200){
                rows.splice(index, 1);//删掉该行
                this.$notify({title: '成功', message: '退货原因（'+name+'）\t移除成功', type: 'success'});
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
        addReason(){
          delete this.alterForm.reasonId;
          this.alterForm.reasonName='';
          this.alterForm.status=true;
          this.insert=true;
          this.dialogFormVisible=true;
        },
        submitChange(formName){
          this.$refs[formName].validate((valid) => {
            if (valid) {
              if(this.insert){
                this.$http.post('/returnReason/existReasonName?reasonId=0&reasonName='+this.alterForm.reasonName).then((rep)=>{
                  if(rep.data.code===200){
                    if(rep.data.data){
                      this.$alert('退货原因('+this.alterForm.reasonName+')\t已存在，不能重复添加', '警告', {confirmButtonText: '确定', type:"warning"});
                    }else{
                      let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.2)"});
                      this.$http.post('/returnReason/addReason',this.$qs.stringify(this.alterForm)).then((rep)=>{
                        loading.close();
                        if(rep.data.code===200){
                          this.dialogFormVisible=false;
                          this.reqInfo();
                          this.$notify({title: '成功', message: '退货原因('+this.alterForm.reasonName+')\t添加成功', type: 'success'});
                        }else{
                          this.$notify({title: '错误', message: rep.data.message, type: 'error'});
                        }
                      }).catch((err)=>{loading.close();this.$msg.error(err)})
                    }
                  }
                }).catch((err)=>{this.$msg.error(err)})
              }else{
                this.$http.post('/returnReason/existReasonName?reasonId='+this.returnReason[this.handleIndex].reasonId+'&reasonName='+this.alterForm.reasonName).then((rep)=> {
                  if (rep.data.code === 200) {
                    if (rep.data.data) {
                      this.$alert('退货原因('+this.alterForm.reasonName+')\t已存在，无法完成修改操作', '警告', {
                        confirmButtonText: '确定',
                        type: "warning"
                      });
                    } else {
                      let loading = this.$loading({lock: true, text: "数据提交中",background:"rgba(255,255,255,0.2)"});
                      this.$http.post('/returnReason/updateReason', this.$qs.stringify(this.alterForm)).then((rep) => {
                        loading.close();
                        if (rep.data.code === 200) {
                          this.dialogFormVisible = false;
                          this.returnReason[this.handleIndex] = this.alterForm;
                          this.$notify({title: '成功', message: '退货原因('+this.alterForm.reasonName+')\t更新成功', type: 'success'});
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
          this.$http.post('/returnReason/findAllReason').then((rep)=>{
            if(rep.data.code===200){
              this.returnReasonResource=this.returnReason=rep.data.data;
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
.return-reason{
  margin: 0;
  padding: 0;
  min-height: 94%;
  user-select: none;
  min-width: 1226px;
}
.return-reason .reason-hd{
  height: 138px;
  margin: 30px auto 10px;
  width: 95%;
}

.return-reason .reason-hd .el-card__header{
  position: relative;
}


.return-reason .info-box{
  height: 600px;
  border:1px solid #EBEEF5;
}

.return-reason .reason-hd .el-button-search{
  position: absolute;
  top: 14px;
  padding: 3px 0;
  width: 70px;
  height: 30px;
}

.return-reason .query-form{
  text-align: left;
}
.return-reason .reason-box{
  height: 530px;
  margin: 20px auto 10px;
  width: 95%;
}

.return-reason .reason-box th,.return-reason .reason-box td{
  text-align: center;
}

.return-reason .el-table th.gutter{
  display: table-cell!important;
}

.return-reason .info-form{
  position: relative;
}

.return-reason .el-upload-dragger{
  width: 200px;
  height: 100px;
}


.return-reason .reason-box .el-button-add{
  position: absolute;
  right: 32px;
  top: -5px;
  padding: 3px 0;
  width: 80px;
  height: 32px;
}

.return-reason .avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 102px;
  line-height: 102px;
  text-align: center;
}

.return-reason .chang-reason .el-dialog--center .el-dialog__body{
  padding: 25px 25px 0;
}
</style>
