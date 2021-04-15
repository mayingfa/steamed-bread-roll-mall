# Steamed Bread Roll Project

## 项目文档

### 介绍

花卷商城，一款基于 Springboot+Vue 的电商项目，前后端分离项目

项目演示地址：[花卷买卖](http://mall.qiudb.top/)

课程设计的伙伴们欢迎加我，领取本地测试版本
:point_right: QQ：1325554003 




### 支付宝支付

支付帐号：ilikkj2173@sandbox.com

登录密码：111111

支付密码：111111



### 软件架构

Springboot + maven + mybatis + Vue 组成，B/S模式



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





### 安装教程

1.  yum 命令 安装 jdk1.8

    ```bash
    yum -y list java*		#查询要安装jdk的版本
    ```

    ```bash
    yum install -y java-1.8.0-openjdk.x86_64		#安装jdk1.8
    ```

    ```bash
    java -version		#查询jdk版本
    ```

    ```bash
    yum 命令 默认给安装到 usr/lib/jvm/ 
    ```

2.  安装 mysql 5.7

    - [在一台Linux服务器上安装多个MySQL实例](https://www.cnblogs.com/lijiaman/p/12588095.html)

    - [mysql主从复制、读写分离](https://www.cnblogs.com/cxyyh/p/10754231.html)

    - mysql 常见错误

      ```bash
      装完MySQL，却发现在本地登录可以，但是远程登录却报错Host is not allowed to connect to this MySQL server
      # https://blog.csdn.net/u012758088/article/details/78613812
      ```

3.  安装 redis 

    - [linux 安装redis 完整步骤](https://www.cnblogs.com/happywish/p/10944253.html)



### 使用说明

1.  Linux服务器部署（jar包）

2.  需开启相应防火墙端口号

    ```bash
    9999	# 项目端口号   如需修改，请到Springboot-Mall后端项目的application.yml文件下修改
    3306	# mysql 主机端口
    3307	# mysql 从机端口
    3308	# mysql 从机端口
    6379	# redis 数据库
    994		# 网易163邮箱 服务端口
    ```

3.  项目在数据库上采用 主从复制，读写分离原则
       - 主从复制 在mysql 设置
       - 读写分离 采用 shardingsphere 中间件

4.  第三方技术 

    - 支付宝沙箱技术
    - OSS 存储
    - 网易邮箱服务
    - 阿里云短信服务

5.  本地开发 和 项目部署需要改三个地方

    - VUE 前端  需要改   ==/src/global/index.js== 文件	bindToGlobal中的 http (本地: 127，部署: 服务器IP)

    - Springboot 后端  需要改  ==/src/resources/config.properties==   way 方式

    - Springboot 后端  需要改  ==/src/resources/application-redis.yml==   Redis服务器地址

      （本地: 服务器IP，部署: 127.0.0.1）

6.  支付宝沙箱帐号

       ```bash
    # 商家
    账号 egtpnc3170@sandbox.com
    密码 111111
       ```

       ```bash
    # 买家
    账号 ilikkj2173@sandbox.com
    密码 111111
    支付密码 111111
       ```







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



##### **用户角色表（user_role）**

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



