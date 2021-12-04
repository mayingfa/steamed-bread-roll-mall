# Steamed Bread Roll Project

## 项目文档

### 项目介绍

花卷商城，一款基于 Springboot+Vue 的电商项目，前后端分离项目

项目演示地址：[花卷买卖](http://mall.qiudb.top/)

本项目为简单的课设项目，禁止修改后商用，不承担一切责任

项目部署失败或不会部署的，可以加QQ联系我，简单问题会回复。

对项目部署不熟悉，想省事，或想快速部署测试效果的，可以考虑远控帮你部署。

:point_right: QQ：1325554003 

### 测试账号

> 后台管理账号

- 账号：1325554003@qq.com

- 密码：M123456

> 门户账号

- 账号：qiudb.top@aliyun.com

- 密码：M123456

> 支付宝支付账号

- 支付帐号：ilikkj2173@sandbox.com

- 登录密码：111111

- 支付密码：111111



### 迭代更新

> 更新时间：2021-12-05

问题修复：

1. 创建用户、生成订单等操作造成时间字段报null空指针错误。【已修复】
2. 从购物车下单后，购物车中的商品还存在问题。【已修复】
3. 注册用户页面，发送邮箱验证码时，数字验证码一直不变问题，【已修复】
4. 项目文件目录规范化，目录命名规范化。
5. Vue项目启动命令为：npm run serve 或 npm start，项目启动后自动打开网页。

新功能：

1. 项目启动时，可通过application.yml配置文件配置超级管理员账号，在admin属性下配置邮箱、用户名等信息。当项目启动时自动检测邮箱是否已被注册；若此邮箱没有被注册，则自动帮你注册为超级管理员账号。
2. 商品评价时，增加敏感词汇过滤功能，在项目resources文件夹下有个words.txt文件存储敏感词汇信息，当评价商品时，会将敏感词汇替换成`*`；例如words.txt中有【刷单】这个词，当评论中存在刷单时，会被替换为`*`。

3. vue项目中引入**animate.css**动画库，个别地方增加了一些动画效果，例如首页logo、花卷会员等地方。如果想要自定义动画效果，只需要在对应的标签上加个class属性就可以。

   **animate.css**官网链接：https://animate.style/

   ```html
   <h1 class="animate__animated animate__bounce">An animated element</h1>
   ```

4. 我的订单页面，允许隐藏已经购买完成的订单信息，当购买数量过多时，会显得页面特别乱，增加了隐藏订单功能。



以上是本次项目迭代更新的全部功能，如果项目中你发现了新的问题，可以加QQ讨论如何修复此问题。

---



### 软件架构

后端技术

| 技术       | 说明           | 官网                                                         |
| ---------- | -------------- | ------------------------------------------------------------ |
| SpringBoot | 容器+MVC框架   | [https://spring.io/projects/spring-boot](https://gitee.com/link?target=https%3A%2F%2Fspring.io%2Fprojects%2Fspring-boot) |
| Shiro      | 认证和授权框架 | [Apache Shiro Simple. Java. Security.](https://shiro.apache.org/) |
| MyBatis    | ORM框架        | [http://www.mybatis.org/mybatis-3/zh/index.html](https://gitee.com/link?target=http%3A%2F%2Fwww.mybatis.org%2Fmybatis-3%2Fzh%2Findex.html) |
| MySQL      | 数据库         | [https://www.mysql.com/](https://gitee.com/link?target=https%3A%2F%2Fwww.mysql.com%2F) |
| Redis      | 分布式缓存     | [https://redis.io/](https://gitee.com/link?target=https%3A%2F%2Fredis.io%2F) |
| Druid      | 数据库连接池   | [https://github.com/alibaba/druid](https://gitee.com/link?target=https%3A%2F%2Fgithub.com%2Falibaba%2Fdruid) |

前端技术

| 技术                | 说明               | 官网                                                         |
| ------------------- | ------------------ | ------------------------------------------------------------ |
| Vue                 | 前端框架           | [https://vuejs.org/](https://gitee.com/link?target=https%3A%2F%2Fvuejs.org%2F) |
| Vue-router          | 路由框架           | [https://router.vuejs.org/](https://gitee.com/link?target=https%3A%2F%2Frouter.vuejs.org%2F) |
| Vuex                | 全局状态管理框架   | [https://vuex.vuejs.org/](https://gitee.com/link?target=https%3A%2F%2Fvuex.vuejs.org%2F) |
| Element             | 前端UI框架         | [https://element.eleme.io](https://gitee.com/link?target=https%3A%2F%2Felement.eleme.io%2F) |
| Axios               | 前端HTTP框架       | [https://github.com/axios/axios](https://gitee.com/link?target=https%3A%2F%2Fgithub.com%2Faxios%2Faxios) |
| vue-clipboard2      | 将内容复制到剪贴板 | [https://github.com/Inndy/vue-clipboard2](https://gitee.com/link?target=https%3A%2F%2Fgithub.com%2FInndy%2Fvue-clipboard2) |
| vuex-persistedstate | vuex持久化         | [https://www.npmjs.com/package/vuex-persistedstate](https://gitee.com/link?target=https%3A%2F%2Fwww.npmjs.com%2Fpackage%2Fvuex-persistedstate) |
| nprogress           | 进度条控件         | [https://github.com/rstacruz/nprogress](https://gitee.com/link?target=https%3A%2F%2Fgithub.com%2Frstacruz%2Fnprogress) |

#### 开发环境

| 工具  | 版本号 | 下载                                                         |
| ----- | ------ | ------------------------------------------------------------ |
| JDK   | 1.8    | [https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html](https://gitee.com/link?target=https%3A%2F%2Fwww.oracle.com%2Ftechnetwork%2Fjava%2Fjavase%2Fdownloads%2Fjdk8-downloads-2133151.html) |
| Mysql | 5.7    | [https://www.mysql.com/](https://gitee.com/link?target=https%3A%2F%2Fwww.mysql.com%2F) |
| Redis | 5.0.10 | [https://redis.io/download](https://gitee.com/link?target=https%3A%2F%2Fredis.io%2Fdownload) |

#### 第三方技术

| 工具           | 官网                                      |
| -------------- | ----------------------------------------- |
| 支付宝沙箱技术 | https://opendocs.alipay.com/common/02kkv7 |
| OSS 存储       | https://cn.aliyun.com/                    |
| 阿里云短信服务 | https://cn.aliyun.com/                    |
| 网易邮箱服务   | https://email.163.com/                    |

#### 项目部署

将项目通过maven打成jar包，服务器运行jar包即可

需开放的端口号：

```
9999	# 项目端口号
3306	# mysql 主机端口
6379	# redis 数据库
994		# 网易163邮箱 服务端口
```



### 软件截图

**登录界面**

![image-20210123150534593](https://oss-qiu.oss-cn-hangzhou.aliyuncs.com/qiu-blogs-typecho/20210123150557.png)



**个人中心**

![image-20210123145636758](https://oss-qiu.oss-cn-hangzhou.aliyuncs.com/qiu-blogs-typecho/20210123150216.png)



**修改密码**

![image-20210123145727924](https://oss-qiu.oss-cn-hangzhou.aliyuncs.com/qiu-blogs-typecho/20210123150214.png)



**我的订单**

![image-20210123145844095](https://oss-qiu.oss-cn-hangzhou.aliyuncs.com/qiu-blogs-typecho/20210123150211.png)



**我的购物车**

![image-20210123145912312](https://oss-qiu.oss-cn-hangzhou.aliyuncs.com/qiu-blogs-typecho/20210123150207.png)



**商品搜索**

![image-20210123150025628](https://oss-qiu.oss-cn-hangzhou.aliyuncs.com/qiu-blogs-typecho/20210123150201.png)



**购买页面**

![image-20210123150056791](https://oss-qiu.oss-cn-hangzhou.aliyuncs.com/qiu-blogs-typecho/20210123150157.png)



**商品评价**

![image-20210123150147433](https://oss-qiu.oss-cn-hangzhou.aliyuncs.com/qiu-blogs-typecho/20210123150153.png)



**管理后台**

由于数据安全性，想要进入后台需要我授予权限后才能访问

![image-20210123151009898](https://oss-qiu.oss-cn-hangzhou.aliyuncs.com/qiu-blogs-typecho/20210123151331.png)



**商品列表**

![image-20210123151322117](https://oss-qiu.oss-cn-hangzhou.aliyuncs.com/qiu-blogs-typecho/20210123151338.png)



**商品分类**

![image-20210123151555762](https://oss-qiu.oss-cn-hangzhou.aliyuncs.com/qiu-blogs-typecho/20210123151558.png)



**商品品牌**

![image-20210123151424726](https://oss-qiu.oss-cn-hangzhou.aliyuncs.com/qiu-blogs-typecho/20210123151431.png)



**商品采购**

![image-20210123151512327](https://oss-qiu.oss-cn-hangzhou.aliyuncs.com/qiu-blogs-typecho/20210123151609.png)



**订单列表**

![image-20210123151939162](https://oss-qiu.oss-cn-hangzhou.aliyuncs.com/qiu-blogs-typecho/20210123151941.png)



**退货申请处理**

![image-20210123151707001](https://oss-qiu.oss-cn-hangzhou.aliyuncs.com/qiu-blogs-typecho/20210123151751.png)



**退货原因设置**

![image-20210123151741155](https://oss-qiu.oss-cn-hangzhou.aliyuncs.com/qiu-blogs-typecho/20210123151758.png)



**商品轮播图**

![image-20210123152018922](https://oss-qiu.oss-cn-hangzhou.aliyuncs.com/qiu-blogs-typecho/20210123152020.png)



**用户列表**

![image-20210123152141960](https://oss-qiu.oss-cn-hangzhou.aliyuncs.com/qiu-blogs-typecho/20210123152155.png)



**角色列表**

![image-20210123152215106](https://oss-qiu.oss-cn-hangzhou.aliyuncs.com/qiu-blogs-typecho/20210123152503.png)

## 数据库文档

### 数据库介绍

##### 数据库名

目前数据库名为 **qiu** ，若需更改数据库名称时，需要将 <u>数据库事件</u> 中的数据库名一并更改。

##### 数据库编码

字符集：utf8

数据库排序规则：utf8_general_ci



### 数据库表（18个）

##### 用户表（user）

| 列名           | 数据类型  | 长度 | 默认              | 主键 | 非空 | 自增 | 注释       |
| -------------- | :-------- | ---- | ----------------- | ---- | ---- | ---- | :--------- |
| user_id        | int       | 5    |                   | ✔    | ✔    | ✔    | 用户ID     |
| account_number | varchar   | 30   |                   |      | ✔    |      | 用户账号   |
| user_name      | varchar   | 30   |                   |      | ✔    |      | 用户昵称   |
| password       | varchar   | 50   |                   |      | ✔    |      | 用户密码   |
| user_sex       | varchar   | 6    |                   |      |      |      | 用户性别   |
| telephone      | varchar   | 11   |                   |      |      |      | 用户手机号 |
| creat_time     | timestamp |      | CURRENT_TIMESTAMP |      | ✔    |      | 注册时间   |
| login_time     | timestamp |      | CURRENT_TIMESTAMP |      | ✔    |      | 登录时间   |
| user_state     | tinyint   | 1    | 1                 |      | ✔    |      | 用户状态   |
| summary        | varchar   | 50   |                   |      |      |      | 个人简介   |
| user_address   | varchar   | 100  |                   |      |      |      | 用户地址   |
| avatar_url     | varchar   | 200  |                   |      |      |      | 用户头像   |
| background_url | varchar   | 200  |                   |      |      |      | 背景图片   |



##### 角色表（role）

| 列名          | 数据类型 | 长度 | 默认 | 主键 | 非空 | 自增 | 注释     |
| ------------- | :------- | ---- | ---- | ---- | ---- | ---- | :------- |
| role_id       | int      | 5    |      | ✔    | ✔    | ✔    | 角色id   |
| role_name     | varchar  | 30   |      |      | ✔    |      | 角色名称 |
| role_describe | varchar  | 50   |      |      | ✔    |      | 角色描述 |
| role_state    | tinyint  | 1    | 1    |      | ✔    |      | 是否启用 |



##### 用户角色表（user_role）

| 列名    | 数据类型 | 长度 | 默认 | 主键 | 非空 | 自增 | 注释   |
| ------- | -------- | ---- | ---- | ---- | ---- | ---- | ------ |
| user_id | int      | 5    |      |      | ✔    |      | 用户id |
| role_id | int      | 5    |      |      | ✔    |      | 角色id |



##### 花卷VIP表（vip）

| 列名           | 数据类型  | 长度 | 默认              | 主键 | 非空 | 自增 | 注释     |
| -------------- | --------- | ---- | ----------------- | ---- | ---- | ---- | -------- |
| vip_id         | int       | 6    |                   | ✔    | ✔    | ✔    | vip ID   |
| account_number | varchar   | 30   |                   |      | ✔    |      | 用户帐号 |
| creat_time     | timestamp |      | CURRENT_TIMESTAMP |      | ✔    |      | 充值时间 |
| overdue_time   | timestamp |      | CURRENT_TIMESTAMP |      | ✔    |      | 过期时间 |



##### 商品表（product）

| 列名             | 数据类型  | 长度 | 默认              | 主键 | 非空 | 自增 | 注释     |
| ---------------- | :-------- | ---- | ----------------- | ---- | ---- | ---- | :------- |
| product_id       | int       | 5    |                   | ✔    | ✔    | ✔    | 商品ID   |
| product_no       | varchar   | 30   |                   |      | ✔    |      | 商品编号 |
| product_name     | varchar   | 30   |                   |      | ✔    |      | 商品名称 |
| product_type     | varchar   | 30   |                   |      | ✔    |      | 商品类别 |
| product_describe | varchar   | 100  |                   |      | ✔    |      | 副标题   |
| product_brand    | varchar   | 30   |                   |      | ✔    |      | 品牌     |
| in_price         | double    |      |                   |      | ✔    |      | 进价     |
| out_price        | double    |      |                   |      | ✔    |      | 售价     |
| product_stock    | int       | 6    |                   |      | ✔    |      | 库存     |
| lowest_stock     | int       | 6    |                   |      | ✔    |      | 最低库存 |
| is_stockout      | tinyint   | 1    | 0                 |      | ✔    |      | 是否缺货 |
| is_new           | tinyint   | 1    | 1                 |      | ✔    |      | 是否新品 |
| is_sale          | tinyint   | 1    | 1                 |      | ✔    |      | 是否上架 |
| sale_time        | timestamp |      | CURRENT_TIMESTAMP |      | ✔    |      | 上架时间 |
| product_url      | varchar   | 200  |                   |      | ✔    |      | 商品图片 |



##### 商品类别表（product_type）

| 列名          | 数据类型 | 长度 | 默认 | 主键 | 非空 | 自增 | 注释       |
| ------------- | -------- | ---- | ---- | ---- | ---- | ---- | ---------- |
| type_id       | int      | 5    |      | ✔    | ✔    | ✔    | 类别id     |
| type_name     | varchar  | 30   |      |      | ✔    |      | 类别名称   |
| type_describe | varchar  | 100  |      |      | ✔    |      | 类别描述   |
| type_url_left | varchar  | 200  |      |      | ✔    |      | 左侧宣传图 |
| type_url_top  | varchar  | 200  |      |      | ✔    |      | 横幅宣传图 |



##### 商品品牌表（product_brand）

| 列名           | 数据类型 | 长度 | 默认 | 主键 | 非空 | 自增 | 注释     |
| -------------- | -------- | ---- | ---- | ---- | ---- | ---- | -------- |
| brand_id       | int      | 5    |      | ✔    | ✔    | ✔    | 品牌ID   |
| brand_name     | varchar  | 30   |      |      | ✔    |      | 品牌名称 |
| brand_describe | varchar  | 100  |      |      | ✔    |      | 品牌描述 |



##### 规格表（specs）

| 列名         | 数据类型 | 长度 | 默认 | 主键 | 非空 | 自增 | 注释     |
| ------------ | -------- | ---- | ---- | ---- | ---- | ---- | -------- |
| specs_id     | int      | 5    |      | ✔    | ✔    | ✔    | 规格ID   |
| specs_name   | varchar  | 50   |      |      | ✔    |      | 规格类型 |
| product_type | varchar  | 30   |      |      | ✔    |      | 商品类别 |



##### 商品规格表（product_specs）

| 列名       | 数据类型 | 长度 | 默认 | 主键 | 非空 | 自增 | 注释   |
| ---------- | -------- | ---- | ---- | ---- | ---- | ---- | ------ |
| product_id | int      | 5    |      |      | ✔    |      | 商品id |
| specs_id   | int      | 5    |      |      | ✔    |      | 规格id |



##### 供应商表（supplier）

| 列名          | 数据类型 | 长度 | 默认 | 主键 | 非空 | 自增 | 注释       |
| ------------- | :------- | ---- | ---- | ---- | ---- | ---- | :--------- |
| supplier_id   | int      | 5    |      | ✔    | ✔    | ✔    | 供应商ID   |
| supplier_no   | varchar  | 30   |      |      | ✔    |      | 供应商编号 |
| supplier_name | varchar  | 30   |      |      | ✔    |      | 供应商名称 |
| product_type  | varchar  | 30   |      |      | ✔    |      | 商品类别   |
| principal     | varchar  | 30   |      |      | ✔    |      | 负责人     |
| contact_way   | varchar  | 30   |      |      | ✔    |      | 联系方式   |
| status        | tinyint  | 1    | 1    |      | ✔    |      | 是否可用   |



##### 采购表（purchase）

| 列名            | 数据类型  | 长度 | 默认              | 主键 | 非空 | 自增 | 注释       |
| --------------- | :-------- | ---- | ----------------- | ---- | ---- | ---- | :--------- |
| purchase_id     | int       | 5    |                   | ✔    | ✔    | ✔    | 采购ID     |
| purchase_no     | varchar   | 30   |                   |      | ✔    |      | 采购编号   |
| purchase_number | int       | 6    |                   |      | ✔    |      | 进货数量   |
| purchase_time   | timestamp |      | CURRENT_TIMESTAMP |      | ✔    |      | 进货时间   |
| receipt_time    | timestamp |      | CURRENT_TIMESTAMP |      | ✔    |      | 收货时间   |
| product_no      | varchar   | 30   |                   |      | ✔    |      | 商品编号   |
| product_name    | varchar   | 30   |                   |      | ✔    |      | 商品名称   |
| supplier_no     | varchar   | 30   |                   |      | ✔    |      | 供应商编号 |
| supplier_name   | varchar   | 30   |                   |      | ✔    |      | 供应商名称 |
| account_number  | varchar   | 30   |                   |      | ✔    |      | 操作员编号 |
| user_name       | varchar   | 30   |                   |      | ✔    |      | 操作员名称 |
| receipt_status  | tinyint   | 1    |                   |      | ✔    |      | 收货状态   |



##### 商品评价表（product_review）

| 列名           | 数据类型  | 长度 | 默认              | 主键 | 非空 | 自增 | 注释       |
| -------------- | --------- | ---- | ----------------- | ---- | ---- | ---- | ---------- |
| review_id      | int       | 8    |                   | ✔    | ✔    | ✔    | 用户评论ID |
| account_number | varchar   | 30   |                   |      | ✔    |      | 用户帐号   |
| product_no     | varchar   | 30   |                   |      | ✔    |      | 商品编号   |
| product_specs  | varchar   | 30   |                   |      |      |      | 商品规格   |
| order_no       | varchar   | 30   |                   |      | ✔    |      | 订单编号   |
| review_time    | timestamp |      | CURRENT_TIMESTAMP |      | ✔    |      | 评论时间   |
| star_level     | double    |      |                   |      | ✔    |      | 商品评星   |
| product_review | varchar   | 300  |                   |      | ✔    |      | 商品评价   |



##### 轮播图表（banner）

| 列名         | 数据类型 | 长度 | 默认 | 主键 | 非空 | 自增 | 注释           |
| ------------ | -------- | ---- | ---- | ---- | ---- | ---- | -------------- |
| banner_id    | int      | 5    |      | ✔    | ✔    | ✔    | 商品广告牌ID   |
| product_name | varchar  | 30   |      |      | ✔    |      | 商品名称       |
| product_url  | varchar  | 200  |      |      | ✔    |      | 商品链接       |
| banner_url   | varchar  | 200  |      |      | ✔    |      | 广告宣传栏链接 |



##### 订单表（order）

| 列名           | 数据类型  | 长度 | 默认              | 主键 | 非空 | 自增 | 注释     |
| -------------- | :-------- | ---- | ----------------- | ---- | ---- | ---- | :------- |
| order_id       | int       | 7    |                   | ✔    | ✔    | ✔    | 订单ID   |
| order_no       | varchar   | 30   |                   |      | ✔    |      | 订单编号 |
| order_time     | timestamp |      | CURRENT_TIMESTAMP |      | ✔    |      | 下单时间 |
| product_no     | varchar   | 30   |                   |      | ✔    |      | 商品编号 |
| product_specs  | varchar   | 30   |                   |      |      |      | 商品规格 |
| user_account   | varchar   | 30   |                   |      | ✔    |      | 用户账号 |
| user_name      | varchar   | 30   |                   |      | ✔    |      | 用户名称 |
| contact_way    | varchar   | 30   |                   |      | ✔    |      | 联系方式 |
| pay_price      | double    |      |                   |      | ✔    |      | 商品金额 |
| pay_amount     | int       | 6    |                   |      | ✔    |      | 购买数量 |
| pay_type       | varchar   | 20   |                   |      | ✔    |      | 支付方式 |
| order_from     | varchar   | 20   |                   |      | ✔    |      | 订单来源 |
| order_state    | varchar   | 20   |                   |      | ✔    |      | 订单状态 |
| accept_address | varchar   | 100  |                   |      | ✔    |      | 收货地址 |
| return_state   | tinyint   | 1    | 0                 |      | ✔    |      | 退货状态 |



##### 物流表（logistics）

| 列名         | 数据类型 | 长度 | 默认 | 主键 | 非空 | 自增 | 注释           |
| ------------ | :------- | ---- | ---- | ---- | ---- | ---- | :------------- |
| logistic_id  | int      | 6    |      | ✔    | ✔    | ✔    | 物流ID         |
| order_no     | varchar  | 30   |      |      | ✔    |      | 订单编号       |
| sender       | varchar  | 30   |      |      | ✔    |      | 发货人         |
| sender_tel   | varchar  | 30   |      |      | ✔    |      | 发货人联系方式 |
| sender_add   | varchar  | 50   |      |      | ✔    |      | 发货人联系地址 |
| receiver     | varchar  | 30   |      |      | ✔    |      | 收货人         |
| receiver_tel | varchar  | 30   |      |      | ✔    |      | 收货人联系方式 |
| receiver_add | varchar  | 50   |      |      | ✔    |      | 收货人联系地址 |
| parcel_name  | varchar  | 30   |      |      | ✔    |      | 物流公司       |



##### 退货原因表（return_reason）

| 列名        | 数据类型 | 长度 | 默认 | 主键 | 非空 | 自增 | 注释     |
| ----------- | -------- | ---- | ---- | ---- | ---- | ---- | -------- |
| reason_id   | int      | 5    |      | ✔    | ✔    | ✔    | 退货ID   |
| reason_name | varchar  | 30   |      |      | ✔    |      | 退货理由 |
| status      | tinyint  | 1    | 1    |      | ✔    |      | 是否启用 |



##### 商品退货表（return_goods）

| 列名            | 数据类型  | 长度 | 默认              | 主键 | 非空 | 自增 | 注释       |
| --------------- | :-------- | ---- | ----------------- | ---- | ---- | ---- | :--------- |
| return_id       | int       | 5    |                   | ✔    | ✔    | ✔    | 退货ID     |
| apply_time      | timestamp |      | CURRENT_TIMESTAMP |      | ✔    |      | 申请时间   |
| order_no        | varchar   | 30   |                   |      | ✔    |      | 订单编号   |
| user_number     | varchar   | 30   |                   |      | ✔    |      | 用户账号   |
| user_name       | varchar   | 30   |                   |      | ✔    |      | 用户名称   |
| return_price    | double    |      |                   |      | ✔    |      | 退款金额   |
| operator_number | varchar   | 30   |                   |      | ✔    |      | 操作员账号 |
| operator_name   | varchar   | 30   |                   |      | ✔    |      | 操作员名称 |
| deal_time       | timestamp |      |                   |      |      |      | 处理时间   |
| return_reason   | varchar   | 30   |                   |      | ✔    |      | 退货原因   |
| return_state    | varchar   | 20   |                   |      | ✔    |      | 退货状态   |



##### 购物车表（shopping_cart）

| 列名           | 数据类型 | 长度 | 默认 | 主键 | 非空 | 自增 | 注释     |
| -------------- | -------- | ---- | ---- | ---- | ---- | ---- | -------- |
| cart_id        | int      | 8    |      | ✔    | ✔    | ✔    | 购物车ID |
| account_number | varchar  | 30   |      |      | ✔    |      | 用户帐号 |
| product_id     | int      | 5    |      |      | ✔    |      | 商品ID   |
| pay_amount     | int      | 5    |      |      | ✔    |      | 购买数量 |
| product_specs  | varchar  | 100  |      |      |      |      | 商品规格 |





### 数据库触发器（2个）

##### 用户表插入触发器（user_insert）

注册用户时，自动向 用户角色表添加信息，将新注册的用户权限设置为 **普通用户**

```mysql
CREATE
    TRIGGER `user_insert` AFTER INSERT ON `user` 
    FOR EACH ROW BEGIN
	DECLARE roleId INT;
	SET roleId = (SELECT role_id FROM role WHERE role_name ='顾客');
		INSERT user_role VALUES(new.user_id,roleId);
    END;
$$
```



##### 订单表删除触发器（order_delete）

当用户取消**未付款订单**时，自动将订单购买数量加到商品库存上

```mysql
CREATE
    TRIGGER `order_delete` BEFORE DELETE ON `order` 
    FOR EACH ROW BEGIN
	UPDATE product SET product_stock = product_stock + old.pay_amount WHERE product_no = old.product_no;
    END;
$$
```





### 数据库事件（1个）

##### 商品表定时事件（product_event）

通过定时事件，每天定时检查

①  当商品上架时间超过15天后，自动取消新品状态，（商品新品状态保留15天）

②  当用户下单后，但并未付款，为其保留24小时付款时间，24小时超过后，商品收回，自动取消订单

③  当VIP会员过期后，自动检查并从表中删除过期会员的信息

```mysql
DELIMITER $$

CREATE EVENT `qiu`.`product_event`

ON SCHEDULE EVERY 1 DAY

STARTS '2021-1-7 02:00:00'
#开始时间自行更改
ON COMPLETION PRESERVE

DO
	BEGIN
		UPDATE product SET is_new = 0 WHERE sale_time<(CURRENT_TIMESTAMP() + INTERVAL -15 DAY);#修改15天前的新品数据
		DELETE FROM qiu.`order` WHERE order_time < DATE_SUB(CURDATE(),INTERVAL 1 DAY) AND order_state='待付款';	#清除24小时内未付款的订单信息
		DELETE FROM vip WHERE overdue_time <= CURDATE(); #清除 VIP表 会员过期的信息
	END$$

DELIMITER ;
```



**检测事件是否开启**

```mysql
show variables like 'event_scheduler';
```

 **开启事件**

```mysql
set global event_scheduler = on;
```



**开启执行事件**

```mysql
alter event product_event on completion preserve enable;
```

**关闭执行事件**

```mysql
alter event product_event on completion preserve disable;
```

 **删除事件**

```mysql
drop event event_name;
```



