<!--个人中心-->
<template>
  <div class="person-center">
    <div class="user-info-box">
      <el-card class="box-card box-abs" shadow="hover">
        <!--背景图片-->
        <div style="height: 66%; width:100%;position: absolute">
          <el-image
            style="width: 100%; height: 100%"
            :src="this.userForm.backgroundUrl"
            fit="none">
            <div slot="error" style="height: 100%"><!--加载失败或未设置图片后显示的图片内容-->
              <el-image :lazy="true" style="width: 100%; height: 100%" fit="none"
                        src="https://oss-qiu.oss-cn-hangzhou.aliyuncs.com/qiu-blogs-images/background.jpg">
              </el-image>
            </div>
          </el-image>
          <el-button plain size="small" @click="backDialogClose=true"
                     style="position: absolute;top: 20px;right: 20px;background-color: rgba(255,255,255,0);">
            <i class="el-icon-camera"/>
            编辑封面图片
          </el-button>
        </div>
        <div style="position: absolute;width: 100%;top: 48%;">
          <!--头像图片-->
          <el-avatar shape="square" class="avatarImg" :size="140" @error="errorHandler" :src="this.userForm.avatarUrl">
            <img src="https://spring-oss.oss-cn-beijing.aliyuncs.com/images/img/avat.gif" alt="默认头像"/>
          </el-avatar>
          <!--昵称 和 身份-->
          <div style="display: inline-block; position: relative;top: -22px;margin-left: 35px;height: 50%">
            <h1 style="font-size: 45px; user-select:text; margin:0;display: inline">{{ this.userForm.userName }}</h1>
            <span v-for="role in roles" style="margin-left: 20px;font-size: 14px;">
            <el-tag type="success" style="position: relative;top: -5px;font-size: 15px;">{{ role }}</el-tag>
          </span>
            <el-tag class="animate__animated animate__wobble" v-if="isVip" @click="vipVisible=true"
                    style="margin-left: 20px;position: relative;top: -5px;cursor:pointer;font-size: 15px;background-color: #292C32;color: #E1BE92;">
              花卷VIP
            </el-tag>
          </div>
          <el-tooltip effect="dark" content="88元加入花卷VIP" placement="bottom-start">
            <el-button size="medium"
                       style="position: absolute;right:160px;bottom: 20px;font-weight:700;color: #000;background-color: rgb(225, 190, 146);"
                       @click="vipVisible=true" v-if="!isVip">开通VIP
            </el-button>
          </el-tooltip>
          <el-button plain size="medium" style="position: absolute;right: 20px;bottom: 20px;" @click="editFrom">编辑个人资料
          </el-button>
        </div>
      </el-card>
      <!--用户表单-->
      <el-card class="box-card box-info" shadow="hover">
        <el-form :model="userForm" :disabled=isEdit :rules="rules" class="personal-form" ref="userForm" inline
                 style="height: 100%;padding: 40px 80px;">
          <el-form-item class="el-user-item" style="position: relative">
            <el-image v-if="isVip"
                      src="http://img.alicdn.com/tfs/TB1felqRXXXXXa7XXXXXXXXXXXX-22-22.png"
                      alt="vip" style="position: absolute;top: -21px;right: 22px;width: 35px;"></el-image>
            <el-avatar :size="80" :src="this.userForm.avatarUrl" alt="头像" style="margin: 0 30px 0 8px;"
                       @error="errorHandler">
              <img src="https://spring-oss.oss-cn-beijing.aliyuncs.com/images/img/avat.gif" alt="默认头像"/>
            </el-avatar>
            <el-button type="success" round plain size="mini" @click="avatarDialogClose=true"
                       style="position: absolute;top: 35%;left: 100%;">更改头像
            </el-button>
          </el-form-item>
          <el-form-item class="el-user-item" label="账号" prop="accountNumber">
            <el-input v-model="userForm.accountNumber" readonly placeholder="账号信息"></el-input>
          </el-form-item>
          <el-form-item class="el-user-item" label="昵称" prop="userName">
            <el-input v-model="userForm.userName" placeholder="昵称信息"></el-input>
          </el-form-item>
          <el-form-item class="el-user-item" label="性别" prop="userSex">
            <el-select v-model="userForm.userSex" placeholder="未填" clearable style="width: 206px">
              <el-option v-for="item in sexOption" :key="item.value" :label="item.label"
                         :value="item.value"></el-option>
            </el-select>
          </el-form-item>
          <el-tooltip class="item" effect="light" content="绑定手机后，可使用手机号登录" placement="bottom-end">
            <el-form-item class="el-user-item" label="手机" prop="telephone">
              <el-input v-model="userForm.telephone" placeholder="手机" readonly style="width: 83%"></el-input>
              <el-button size="mini" icon="el-icon-edit" circle @click="isCheckMail"></el-button>
            </el-form-item>
          </el-tooltip>
          <el-form-item class="el-user-item" label="简介" prop="summary">
            <el-input type="textarea" :rows="1" v-model="userForm.summary" style="height: 40px;width: 206px"
                      placeholder="填写你的个人简介"></el-input>
          </el-form-item>
          <el-form-item class="el-user-item address" label="地址" prop="userAddress">
            <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4}" v-model="userForm.userAddress"
                      placeholder="地址" readonly style="width: 206px;position: absolute"></el-input>
            <el-button size="mini" icon="el-icon-edit" style="position: absolute;right: -37px;margin: 10px 0" circle
                       @click="addressFormVisible=true"></el-button>
          </el-form-item>
          <el-row style="text-align: center;margin: 20px auto;">
            <el-button type="primary" round v-if="!isEdit" style="width: 150px; margin:0 4%"
                       @click="submitForm('userForm')">保存
            </el-button>
            <el-button round v-if="!isEdit" style="width: 150px; margin:0 4%" @click="cancelForm('userForm')">取消
            </el-button>
          </el-row>
        </el-form>
      </el-card>
      <!--上传背景图片-->
    </div>
    <el-dialog
      title="背景图片"
      :visible.sync="backDialogClose"
      width="20%"
      class="personal-dialog"
      style="text-align: center;">
      <el-upload
        class="personal-upload"
        :action="this.$VAR.http+'/uploadImage?name=background'"
        :on-success="uploadSuccess"
        :on-error="uploadError"
        :before-upload="beforeAvatarUpload"
        :drag="true"
        list-type="picture"
        ref="backGroundUpload">
        <i class="el-icon-plus"></i>
      </el-upload>
      <span slot="footer" class="dialog-footer">
        <el-button @click="backDialogClose = false">取 消</el-button>
      </span>
    </el-dialog>
    <!--上传头像-->
    <el-dialog
      title="上传头像"
      :visible.sync="avatarDialogClose"
      width="20%"
      class="personal-dialog"
      style="text-align: center;">
      <el-upload
        class="personal-upload"
        :action="this.$VAR.http+'/uploadImage?name=userAvatar'"
        :on-success="uploadAvaterSuccess"
        :on-error="uploadError"
        :drag="true"
        :before-upload="beforeAvatarUpload"
        list-type="picture"
        ref="avatarUpload">
        <i class="el-icon-plus"></i>
      </el-upload>
      <span slot="footer" class="dialog-footer">
        <el-button @click="avatarDialogClose = false">取 消</el-button>
      </span>
    </el-dialog>
    <!--绑定手机号前验证身份-->
    <el-dialog
      title="安全验证"
      :visible.sync="mailDialogClose"
      width="20%"
      class="phone-dialog">
      <p style="text-align: left">
        为了保证你的帐号安全，请先验证身份，验证成功后进行下一步操作，验证码10分钟内有效
      </p>
      <h3>用邮箱{{ this.mailHide }}验证</h3>
      <div style="position: relative">
        <el-input v-model="mailCode" placeholder="验证码">
          <i slot="prefix" class="el-input__icon el-icon-position"></i>
        </el-input>
        <el-button type="primary" round size="small" :disabled="!show"
                   style="position:absolute;right:3px;top:0;margin:4px 0;" @click="sendEmailCode">
          <span v-show="show">发送验证码</span>
          <span v-show="!show" class="count">重新发送({{ count }}s)</span>
        </el-button>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="bindingPhone" :disabled="isEditPhone">确 定</el-button>
        <el-button @click="mailDialogClose = false">取 消</el-button>
      </span>
    </el-dialog>
    <!--绑定手机号-->
    <el-dialog
      title="绑定手机号"
      :visible.sync="phoneDialogClose"
      width="20%"
      class="phone-dialog">
      <el-input v-model="phoneInfo" placeholder="请输入你常用的手机号" style="margin: 10px 0;">
        <i slot="prefix" class="el-input__icon el-icon-mobile-phone"></i>
      </el-input>
      <div style="position: relative">
        <el-input v-model="phoneCode" placeholder="验证码">
          <i slot="prefix" class="el-input__icon el-icon-position"></i>
        </el-input>
        <el-button type="primary" round size="small" :disabled="!show"
                   style="position:absolute;right:3px;top:0;margin:4px 0;" @click="editPhoneCode()">
          <span v-show="show">发送验证码</span>
          <span v-show="!show" class="count">重新发送({{ count }}s)</span>
        </el-button>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitPhone" :disabled="editPhone">
          <span v-if="this.userForm.telephone">更 换</span>
          <span v-else>确 定</span>
        </el-button>
        <el-button v-if="this.userForm.telephone" @click="unbindPhone">解 绑</el-button>
        <el-button v-else @click="phoneDialogClose = false">取 消</el-button>
      </span>
    </el-dialog>
    <!--收货地址-->
    <el-dialog title="地址信息" class="address-dialog" width="450px" :visible.sync="addressFormVisible">
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
        <el-button type="primary" @click="submitAddress('addressForm')">确 定</el-button>
      </div>
    </el-dialog>
    <!--开通vip-->
    <el-dialog
      title="花卷VIP"
      class="vip-dialog"
      :visible.sync="vipVisible"
      :show-close="isVip"
      top=0
      :modal="false">
      <span v-if="isVip" style="color:#F8C4A2">{{ getDate(vip['overdueTime']) }} 到期</span>
      <span v-else style="color:#F8C4A2">开通仅需88元</span>
      <div class="vip-box">
        <div class="discount-card" v-for="(item,index) in vipInfo" :key="index">
          <h3>折扣卡</h3>
          <div>
            <span style="font-size: xx-large;color: #F8313C;">{{ item['discount'] }}</span>
            <span style="color: #F8313C;">折</span>
          </div>
          <h6>{{ item['desc'] }}</h6>
          <div class="vip-use">
            <el-button type="danger" class="mini-button" size="mini" round @click="$router.push('/MallHome')">立即使用
            </el-button>
          </div>
          <div class="left-dots"></div>
          <div class="right-dots"></div>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button v-if="!isVip" type="primary" @click="openVip">开通</el-button>
        <el-button v-if="!isVip" @click="vipVisible = false">取消</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {regionData, CodeToText} from 'element-china-area-data'

export default {
  name: "PersonalCenter",
  data() {
    let validateOption = (rule, value, callback) => {
      if (value === '' || value.length === 0) {
        callback(new Error('请选择收货地址'));
      } else {
        callback();
      }
    };
    let validateAddress = (rule, value, callback) => {
      if (value === '' || value.trim().length === 0) {
        callback(new Error('请填写详细地址'));
      } else {
        callback();
      }
    };
    return {
      isEdit: true,
      isVip: this.$store.state.user['isVip'],
      vipVisible: false,
      vip: {overdueTime: ''},
      vipInfo: [{discount: 98, desc: '全场商品享受'}, {discount: 95, desc: '满1000可用'}, {discount: 90, desc: '满5000可用'}],
      roles: this.$store.state.roleInfo,
      backDialogClose: false,
      avatarDialogClose: false,
      phoneDialogClose: false,
      mailDialogClose: false,
      mailHide: '',    //加密后的邮箱，eg:132*****4003@qq.com
      mailCode: '',  //绑定手机时，用于暂时存放向邮箱发送的验证码
      phoneCode: '', //绑定手机号，发给用户的手机验证码
      phoneInfo: '', //临时存储手机号
      isEditPhone: true,
      editPhone: true,
      //发送验证码倒计时
      show: true,
      count: null,
      timer: null,
      options: regionData,
      addressFormVisible: false,
      addressForm: {
        selectedOptions: [],
        detailAddress: ''     //详细地址
      },
      addressRules: {
        selectedOptions: [{validator: validateOption, trigger: 'blur'}],
        detailAddress: [{validator: validateAddress, trigger: 'blur'}]
      },
      order: {
        orderNo: '',
        productNo: '',
        userAccount: '',
        userName: '',
        contactWay: '',
        payPrice: '',
        productSpecs: null,
        payAmount: 1,
        payType: '',
        orderFrom: '',
        orderState: '',
        returnState: false,
        acceptAddress: this.$store.state.user['userAddress'],
      },
      //用户信息
      userForm: {
        userId: this.$store.state.user['userId'],               /*id*/
        accountNumber: this.$store.state.user['accountNumber'],    /*账号*/
        userName: this.$store.state.user['userName'],              /*昵称*/
        userSex: this.$store.state.user['userSex'],                /*性别*/
        telephone: this.$store.state.user['telephone'],           /*手机号*/
        summary: this.$store.state.user['summary'],                /*简介*/
        userAddress: this.$store.state.user['userAddress'],          /*地址信息*/
        backgroundUrl: this.$store.state.user['backgroundUrl'],   /*背景图片链接*/
        avatarUrl: this.$store.state.user['avatarUrl']             /*头像图片链接*/
      },
      sexOption: [{
        value: '男',
        label: '男'
      }, {
        value: '女',
        label: '女'
      }, {
        value: '保密',
        label: '保密'
      }],
      rules: {
        userName: [
          {required: true, message: '请输入昵称', trigger: 'blur'},
          {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
        ]
      }
    }
  },
  created() {
    let serialNumber = this.$route.query.serialNumber;
    let vipSerialNumber = this.$store.state.vipSerialNumber;
    if (serialNumber === 'ERROR') {
      this.$msg.error('非常抱歉，未检查到VIP序列号，请联系客服人员协助解决');
    } else if (vipSerialNumber === serialNumber) {
      this.$store.commit('setVipSerialNumber', '');
      this.$http.post('/vip/existsVip?accountNumber=' + this.$store.state.user['accountNumber']).then((rep) => {
        if (rep.data.code === 200) {
          if (rep.data.data) {
            this.isVip = true;
            this.$store.state.user['isVip'] = true;
            localStorage.setItem("store", JSON.stringify(this.$store.state));
            this.$notify({
              title: '开通成功',
              message: '尊贵的用户，欢迎加入花卷VIP',
              type: 'success'
            });
            this.vipVisible = false;
            this.vip = rep.data.data;
          } else {
            this.$http.post('/vip/addVip?accountNumber=' + this.$store.state.user['accountNumber']).then((rep) => {
              if (rep.data.code === 200) {
                this.isVip = true;
                this.$store.state.user['isVip'] = true;
                localStorage.setItem("store", JSON.stringify(this.$store.state));
                this.$notify({
                  title: '开通成功',
                  message: '尊贵的用户，欢迎加入花卷VIP',
                  type: 'success'
                });
                this.vipVisible = false;
                this.vip = rep.data.data;
              }
            }).catch((err) => {
              this.$msg.error(err);
            })
          }
        }
      }).catch((err) => {
        this.$msg.error(err);
      })
    }
    //对帐号信息进行加密
    let str = this.$store.state.user['accountNumber'];
    this.mailHide = str.substr(0, 3) + '****' + str.substr(7);
    if (this.isVip) {
      this.$http.post('/vip/findVipByKey?accountNumber=' + this.$store.state.user['accountNumber']).then((rep) => {
        if (rep.data.code === 200) {
          this.vip = rep.data.data;
        }
      }).catch((err) => {
        this.$msg.error(err);
      })
    }
  },
  methods: {
    getDate(time) {
      let date = new Date(time);
      let yy = date.getFullYear();
      let mm = date.getMonth() + 1;
      let dd = date.getDate();
      return yy + '-' + mm + '-' + dd;
    },
    openVip() {
      this.$confirm('是否开通花卷VIP?', '温馨提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.pay()
      }).catch(() => {
      });
    },
    errorHandler() {
      return true
    },
    editFrom() {
      this.isEdit = false
    },
    submitAddress(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let str = '';
          let ad = this.addressForm.selectedOptions;
          for (let i = 0, len = ad.length; i < len; i++) {
            str += CodeToText[ad[i]] + ' ';
          }
          str += '(' + this.addressForm.detailAddress + ')';
          this.userForm.userAddress = str;
          this.addressFormVisible = false;
        } else {
          return false;
        }
      });
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http.post('/allow/update', this.$qs.stringify(this.userForm, {skipNulls: true})).then((res) => {
            if (res.data.code === 200) {
              this.$store.state.user = this.userForm;
              this.$store.state.user['isVip'] = this.isVip;
              localStorage.setItem("store", JSON.stringify(this.$store.state))
              this.$msg.success(res.data.message);
            } else {
              this.$msg.warning(res.data.message);
            }
          }).catch((err) => {
            console.log('提交信息时，发生错误' + err);
            this.$msg.error({message: '网络错误，请稍后再试或联系客服~', showClose: true, duration: 1500});
          })
          this.isEdit = true;
        } else {
          return false;
        }
      });
    },  //重置表单
    cancelForm(formName) {
      this.$refs[formName].resetFields();
      if (localStorage.getItem("store")) {
        this.$store.replaceState(Object.assign({}, this.$store.state, JSON.parse(localStorage.getItem("store"))))
      }
      this.userForm = this.$store.state.user;
      this.isEdit = true;
    },
    uploadSuccess(response) {
      if (response.code === 200) {
        this.$http.post('/allow/update?userId=' + this.$store.state.user['userId'] + '&backgroundUrl=' + response.data).then((res) => {
          if (res.data.code === 200) {
            this.userForm.backgroundUrl = response.data;
            this.$store.state.user['backgroundUrl'] = response.data;
            this.backDialogClose = false;
            localStorage.setItem("store", JSON.stringify(this.$store.state))
            this.$msg.success(response.message);
          } else {
            this.$msg.warning(res.data.message);
          }
        }).catch(() => {
          this.$msg.error("背景上传失败！")
        })
        this.$refs['backGroundUpload'].clearFiles();
      } else {
        this.$msg.error("背景上传失败！")
      }
    },
    uploadError() {
      this.$msg.warning("图片上传失败！")
    },
    uploadAvaterSuccess(response) {
      if (response.code === 200) {
        this.$http.post('/allow/update?userId=' + this.$store.state.user['userId'] + '&avatarUrl=' + response.data).then((res) => {
          if (res.data.code === 200) {
            this.userForm.avatarUrl = response.data;
            this.$store.state.user['avatarUrl'] = response.data;
            localStorage.setItem("store", JSON.stringify(this.$store.state))
            this.avatarDialogClose = false;
            this.$msg.success(response.message);
          } else {
            this.$msg.warning(res.data.message);
          }
        }).catch(() => {
          this.$msg.error("头像上传失败！")
        })
        this.$refs['avatarUpload'].clearFiles();
      } else {
        this.$msg.error("头像上传失败！")
      }
    },
    isCheckMail() {  //身份验证成功后，5分钟内无需再次身份验证
      let accounts = this.$store.state.user['accountNumber'];
      if (sessionStorage.getItem(accounts) !== null) {
        if (this.isExpired(accounts, 1000 * 60 * 5)) { //5分钟
          this.phoneDialogClose = true;
        } else {
          this.mailDialogClose = true;
        }
      } else {
        this.mailDialogClose = true;
      }
    },
    //发送验证码后60s倒计时
    getCode() {
      const TIME_COUNT = 60;
      if (!this.timer) {
        this.count = TIME_COUNT;
        this.show = false;
        this.timer = setInterval(() => {
          if (this.count > 0 && this.count <= TIME_COUNT) {
            this.count--;
          } else {
            this.show = true;
            clearInterval(this.timer);
            this.timer = null;
          }
        }, 1000)
      }
    },
    //向邮箱发送验证码，验证身份
    sendEmailCode() {
      this.getCode();
      this.$http.post('/allow/sendHtmlCode?email=' + this.$store.state.user['accountNumber']).then((res) => {
        if (res.data.code === 200) {
          this.$msg.success({message: res.data.message, showClose: true, duration: 1500});
          this.isEditPhone = false;
        } else {
          this.$msg.error({message: res.data.message, showClose: true, duration: 1500});
        }
      }).catch((err) => {
        console.log("绑定手机号时，向手机号发送验证码失败" + err);
        this.$msg.error({message: '网络错误，请稍后再试或联系客服~', showClose: true, duration: 1500});
      })
    },
    bindingPhone() {
      if (this.mailCode.trim().length === 0) {
        this.$msg.warning("验证码不能为空")
      } else {
        this.$http.post('/allow/checkCode?key=' + this.$store.state.user['accountNumber'] + '&code=' + this.mailCode.trim()).then((res) => {
          if (res.data.code === 200) {
            if (res.data.data) {
              this.mailDialogClose = false;
              this.mailCode = '';
              this.show = true;
              if (!this.timer) {
                clearInterval(this.timer);
              }
              this.setKey(this.$store.state.user['accountNumber'], '身份验证成功');
              this.timer = null;
              this.isEditPhone = true;
              this.phoneDialogClose = true
              this.$msg.success('身份验证成功，五分钟内无需再次验证')
            } else {
              this.$msg.warning({message: res.data.message, showClose: true, duration: 1500});
            }
          } else {
            this.$msg.warning({message: res.data.message, showClose: true, duration: 1500});
          }
        }).catch((err) => {
          console.log("邮箱验证身份，后台发生错误" + err);
          this.$msg.error({message: '网络错误，请稍后再试或联系客服~', showClose: true, duration: 1500});
        })
      }
    },
    editPhoneCode() {
      //匹配手机号正则表达式
      let regex = /^((0\d{2,3}-\d{7,8})|(1[3584]\d{9}))$/;
      if (regex.test(this.phoneInfo)) {
        this.$http.post('/allow/existPhone?telephone=' + this.phoneInfo.trim()).then((res) => {
          if (res.data.code === 200) {
            if (res.data.data === false) {//data 为false  代表手机号未被查询到，所以未被绑定
              this.getCode();
              this.$http.post('/util/smsCode?phone=' + this.phoneInfo.trim()).then((res) => {
                if (res.data.code === 200) {
                  //绑定手机号的确定按钮可用
                  this.$msg.success({message: res.data.message, showClose: true, duration: 1500});
                  this.editPhone = false;
                } else {
                  this.$msg.warning({message: res.data.message, showClose: true, duration: 1500});
                }
              }).catch((err) => {
                console.log("绑定手机号时，向手机号发送验证码失败" + err);
                this.$msg.error({message: '网络错误，请稍后再试或联系客服~', showClose: true, duration: 1500});
              })
            } else {
              this.$msg.warning("手机号已被绑定，请更换")
            }
          } else {
            this.$msg.error({message: res.data.message, showClose: true, duration: 1500});
          }
        }).catch((err) => {
          console.log("验证此手机号是否已被绑定" + err);
          this.$msg.error({message: '网络错误，请稍后再试或联系客服 ' + err, showClose: true, duration: 1500});
        })
      } else {
        this.$msg.warning("请输入正确手机号")
      }
    },
    submitPhone() {
      if (this.phoneCode.trim().length === 0) {
        this.$msg.warning("验证码不能为空")
      } else {
        this.$http.post('/allow/checkCode?key=' + this.phoneInfo + '&code=' + this.phoneCode.trim()).then((res) => {
          if (res.data.code === 200) {
            if (res.data.data) {
              this.userForm.telephone = this.phoneInfo;
              this.$store.state.user['telephone'] = this.phoneInfo;
              //清空后台绑定的手机号
              this.$http.post('/allow/update?userId=' + this.$store.state.user['userId'] + '&telephone=' + this.userForm.telephone).then((res) => {
                if (res.data.code === 200) {
                  this.phoneDialogClose = false;
                  this.show = true;
                  if (!this.timer) {
                    clearInterval(this.timer);
                  }
                  this.timer = null;
                  this.editPhone = true;
                  localStorage.setItem("store", JSON.stringify(this.$store.state))
                  this.$msg.success("手机号绑定成功");
                } else {
                  this.$msg.warning("手机号绑定失败");
                }
              }).catch((err) => {
                console.log("解除绑定手机号发生错误" + err);
                this.$msg.error({message: '网络错误，请稍后再试或联系客服~', showClose: true, duration: 1500});
              })
              this.phoneInfo = '';
              this.phoneCode = '';
            } else {
              this.$msg.warning({message: res.data.message, showClose: true, duration: 1500});
            }
          } else {
            this.$msg.warning({message: res.data.message, showClose: true, duration: 1500});
          }
        }).catch((err) => {
          console.log("绑定手机号发生错误" + err);
          this.$msg.error({message: '网络错误，请稍后再试或联系客服~', showClose: true, duration: 1500});
        })
      }
    },
    //解除绑定的手机号
    unbindPhone() {
      this.$confirm('将要解除绑定的手机号, 是否继续?', '提示', {
        cancelButtonText: '取消',
        confirmButtonText: '确定',
        type: 'warning'
      }).then(() => {
        this.$store.state.user['telephone'] = null;
        this.userForm.telephone = null;
        //清空后台绑定的手机号
        this.$http.post('/allow/update?userId=' + this.$store.state.user['userId'] + '&telephone=' + this.userForm.telephone).then((res) => {
          if (res.data.code === 200) {
            this.phoneDialogClose = false;
            localStorage.setItem("store", JSON.stringify(this.$store.state))
            this.$msg.success("已解除手机号绑定")
          } else {
            this.$msg.warning("手机号解绑失败")
          }
        }).catch((err) => {
          console.log("解除绑定手机号发生错误" + err);
          this.$msg.error({message: '网络错误，请稍后再试或联系客服~', showClose: true, duration: 1500});
        })
        //点击取消按钮后，什么也不触发
      }).catch(() => {
      })
    },
    // 设置sessionStorage的过期时间
    //封装过期控制代码
    setKey(key, value) {
      let curTime = new Date().getTime();
      sessionStorage.setItem(key, JSON.stringify({data: value, time: curTime}));
    },
    //封装过期时间代码   false  代表 过期  true 未过期
    isExpired(key, exp) { //exp 毫秒级别  1000 为 1秒
      let data = sessionStorage.getItem(key);
      let dataObj = JSON.parse(data);
      if (new Date().getTime() - dataObj.time > exp) {
        sessionStorage.removeItem(key)
        return false;
      } else {
        return true;
      }
    },
    getDateNow() {
      let time = new Date(); // 时间戳
      let year = String(time.getFullYear()); // 年
      year = year.substring(2);
      let mouth = String(time.getMonth() + 1); // 月
      let day = String(time.getDate()); // 日
      let hours = String(time.getHours()); // 时
      if (hours.length < 2) {
        hours = '0' + hours
      }
      let seconds = String(time.getSeconds()); // 秒
      if (seconds.length < 2) {
        seconds = '0' + seconds
      }
      let max = 90;
      let min = 10;
      let random = Math.floor((Math.random() * max + min) + 1); //随机数
      this.order.orderNo = 'Vip' + year + mouth + day + hours + random + seconds
    },
    pay() {
      this.getDateNow();  //获得订单编号
      this.order.productNo = 'Vip0001';
      this.order.userAccount = this.$store.state.user['accountNumber'];
      this.order.userName = this.$store.state.user['userName'];
      if (this.$store.state.user['telephone'] !== null) {
        this.order.contactWay = this.$store.state.user['telephone'];
      } else {
        this.order.contactWay = '暂无信息';
      }
      this.order.payPrice = 88;
      this.order.payType = '支付宝';
      this.order.orderFrom = '网页商城';
      this.order.orderState = '待付款';
      this.order.returnState = false;   //未退货
      if (this.$store.state.user['userAddress'] !== null) {
        this.order.acceptAddress = this.$store.state.user['userAddress']
      } else {
        this.order.acceptAddress = '暂无信息';
      }
      this.createOrder();
      this.orderFormVisible = true;
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
      } else {
        if (!isLt5M) {
          this.$msg.error('上传图片大小不能超过 5MB!');
        }
      }
      return (isJPG || isGIF || isPNG || isBMP || isWEBP) && isLt5M;
    },
    createOrder() {
      let max = 900;
      let min = 100;
      let random = Math.floor((Math.random() * max + min) + 1); //随机数
      let vipSerialNumber = random.toString();
      this.$store.commit('setVipSerialNumber', vipSerialNumber)
      let loading = this.$loading({lock: true, text: "订单提交中", background: "rgba(255,255,255,0.1)"});
      this.$http.post('/order/add', this.$qs.stringify(this.order, {skipNulls: true})).then((rep) => {
        loading.close();
        if (rep.data.code === 200) {
          let orderNo = this.order.orderNo;
          let orderName = '花卷商城-开通花卷Vip支付订单';
          let payPrice = this.order.payPrice;
          let loading = this.$loading({lock: true, text: "正在跳转支付页面", background: "rgba(255,255,255,0.1)"});
          this.$http.post('/alipay/vip?orderNo=' + orderNo + '&orderName=' + orderName + '&payPrice=' + payPrice + '&serialNumber=' + vipSerialNumber).then((response) => {
            loading.close();
            const div = document.createElement('div');
            div.innerHTML = response.data;
            document.body.appendChild(div);
            document.forms[document.forms.length - 1].submit();
          }).catch((err) => {
            this.$msg.error(err)
          })
        }
      }).catch((err) => {
        loading.close();
        this.$msg.error(err)
      })
      this.orderFormVisible = false;
    },
  }
}
</script>

<style>
.person-center {
  margin: 0;
  padding: 0;
  min-height: 100%;
  border: 1px solid #FFF;
  user-select: none;
  min-width: 1226px;
  background: #f5f5f5;
}

.person-center .user-info-box{
  display: flex;
  justify-content: center;
}

.person-center .box-abs {
  height: 300px;
  width: 80%;
  min-width: 1100px;
  border-radius: 5px;
  text-align: left;
  top: 85px;
  position: absolute;
}

.person-center .box-abs .el-card__body, .box-info .el-card__body {
  height: 100%;
  padding: 0;
}

.person-center .avatarImg {
  border: 3px solid rgb(255, 255, 255);
  background: rgb(255, 255, 255);
  border-radius: 7px;
  margin-left: 3%;
}

.person-center .box-info {
  position: absolute;
  width: 80%;
  min-width: 1100px;
  top: 410px;
  border-radius: 5px;
  text-align: left;
}

.person-center .el-user-item {
  width: 30%;
  min-width: 200px;
}

.person-center .personal-form .el-form-item {
  vertical-align: middle !important;
}

.person-center .personal-form .el-textarea__inner {
  height: 40px;
  min-height: 40px;
}

.person-center .personal-form .el-form-item__content {
  max-width: 206px;
}

.person-center .personal-form .address {
  position: relative;
  width: 40%;
}

.person-center .personal-form .address .el-form-item__content {
  width: 242px;
}


.person-center .personal-form .el-form-item__label {
  width: auto;
}

/*上传背景或头像弹窗样式*/
.person-center .personal-dialog .el-dialog {
  position: absolute;
  top: 30%;
  left: 40%;
  height: auto;
  min-width: 230px;
  margin: 0 !important;
  text-align: center;
  border-radius: 0;
}

.person-center .personal-dialog .el-dialog__header {
  font-weight: 700;
  border-bottom: 1px solid #E3E3E3;
}

.person-center .personal-dialog .el-dialog__footer {
  border-top: 1px solid #E3E3E3;
  padding: 10px 20px;
}

/*上传图片中间的蓝色方框样式*/
.person-center .personal-upload .el-upload-dragger {
  width: 180px;
  height: 180px;
  line-height: 180px;
}

/*绑定手机号弹窗样式*/
.person-center .phone-dialog .el-dialog {
  position: absolute;
  top: 30%;
  left: 40%;
  height: auto;
  min-width: 230px;
  margin: 0 !important;
  text-align: center;
  border-radius: 0;
}

.person-center .phone-dialog .el-dialog__header {
  font-weight: 700;
  border-bottom: 1px solid #E3E3E3;
}

.person-center .phone-dialog .el-dialog__body {
  padding: 16px 20px;
}

.person-center .personal-form .el-form-item__label {
  width: 50px;
}

.person-center .personal-form .el-textarea {
  vertical-align: middle;
}

.person-center .address-dialog .el-dialog__body {
  padding: 30px 80px;
}

.person-center .vip-dialog {
  border-radius: 5px;
  z-index: 2013;
  width: 340px;
  left: 40%;
  top: 30%;
}

.person-center .vip-dialog .el-dialog {
  width: 100%;
  margin: 0 auto;
}

.person-center .vip-dialog .el-dialog__header {
  border-radius: 5px 5px 0 0;
  text-align: left;
  background: #181818;
}

.person-center .vip-dialog .el-dialog__title {
  color: #F8C4A2;
}

.person-center .vip-dialog .el-dialog__body {
  padding: 20px 0 20px;
  background: #181818;
  height: 222px;
}

.person-center .vip-dialog .vip-box {
  width: 90%;
  margin: 20px auto 0;
  height: 200px;
  position: relative;
}

.person-center .vip-dialog .vip-box .discount-card {
  height: 135px;
  margin: 3px;
  width: 31%;
  display: inline-block;
  background-color: #FEF2F4;
  border-radius: 5px;
  position: relative;
}

.person-center .vip-dialog .vip-box .vip-use {
  display: flex;
  justify-content: center;
  align-items: center;
  background: #ffe4e9;
  height: 44px;
}

.person-center .vip-dialog .vip-box .vip-use .mini-button {
  background-color: rgba(0, 0, 0, 0);
  color: rgb(248, 49, 60);
  padding: 4px 12px;
}

.person-center .vip-dialog .vip-box .left-dots {
  position: absolute;
  height: 9px;
  width: 9px;
  background: #181818;
  left: -5px;
  border-radius: 50%;
  top: 86px;
}

.person-center .vip-dialog .vip-box .right-dots {
  position: absolute;
  height: 9px;
  width: 9px;
  background: #181818;
  right: -5px;
  border-radius: 50%;
  top: 86px;
}

.person-center .vip-dialog .vip-box h3 {
  color: #000;
  font-size: 13px;
  margin: 5px auto;
}

.person-center .vip-dialog .vip-box h6 {
  margin: 5px auto;
  font-size: revert;
  font-weight: 100;
}


.person-center .vip-dialog .el-dialog__footer {
  border-radius: 0 0 5px 5px;
  background: #181818;
}
</style>
