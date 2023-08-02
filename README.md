# HouseSys

对个人完成的项目危房改造管理系统的后端介绍说明，项目已商用，本仓库仅做介绍与框架学习用途，项目代码不开源
线上商用地址：https://weifangguanli.com

Web 管理系统主页截图：
![Image Text](https://github.com/tpian/HouseSys/blob/main/figure/homepage.png)
小程序主页截图：
![Image Text](https://github.com/tpian/HouseSys/blob/main/figure/homepageWe.png)

## 系统架构：

![Image Text](https://github.com/tpian/HouseSys/blob/main/figure/S-C.png)
系统中主要包括 MySQL、Redis、后端服务器、Web 管理系统、微信小程序五个部分。

1. Web 管理系统：
   **登录用户**：村、镇街、局县、民政局、住建局干部；
   **主要作用**：

- 进行系统用户管理，干部注册；
- 完成对农户的危房改造申请进行审核审批的功能；
- 发布新政策、违规警告；
- 系统数据看板；
- 完工的危房改造建设项目生成 word 档案，并在线打印

2. 微信小程序
   **登录用户**：申请危房改造的农户；村、镇街、局县、民政局、住建局干部；
   **主要作用**：

- 农户

* 农户注册；
* 提出危房改造申请；
* 改造申请通过后，建房过程中，提交房屋图片、建房件货、责任书等材料

- 干部

* 审核/驳回申请

3. 后端服务器：

- 连接数据库，实现数据管理
- 与客户端（web、小程序）进行网络通信，提供服务 api

4. MySQL 数据库：存储主要的业务数据，如乡镇选项、系统用户、用户权限、申请记录、建设记录等。
5. Redis 缓存：缓存验证码、用户权限、token、在线状态等有过期时间数据或热点数据。

## 技术栈

```
Java
Sping Boot
Spring Cloud
Mybatis
Nacos 注册中心
Sentinel 流量卫兵
```

## 项目环境

```
后端：
JDK 1.8
MySQL 5.7.36
Redis
maven
IDEA编译器，IDE插件，lombok插件
```

## 主要模块

### 用户管理

#### 权限控制

#### 流量控制

### 申报审批管理

### 政策宣传
