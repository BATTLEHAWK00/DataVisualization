# 数据可视化项目

### 简介

大二Web项目实践:企业大数据可视化项目  
旨在推动大二学生学习Web相关技术，并积累一些项目经验

### 文档

[API接口文档](docs/apis)

[数据库设计文档](docs/database/database.md)

### 参与贡献

1.  
    * Fork本仓库
    * 新建分支
        1. git checkout -b 分支名
        2. git push -u origin 分支名
2.  提交代码并 Push
3.  提交 Pull Request

### 项目结构

* 前端工程目录

> /webapp

* 后端工程目录

> /backend

* 工程说明

> /README.md

* 工程文档目录

> /docs



### 工程架构说明
#### 后端架构
1. DAO
   
    > 负责与数据库交互等
2. Controller
    > 提供Http Api接口，与前端Ajax对接
    * Servlet
      
        > Servlet写到这个包里
    * Filter
      
        > 请求过滤，负责权限验证，编码修改等
3. Service
   
    > 业务逻辑，负责DAO层与Controller层的逻辑交互
4. Util
   
    > 工具包，将常用代码封装，提高代码复用性
5. Bean
   
    > Java数据对象

#### 前端架构
1. css
    
    > 存放样式文件
2. img
    
    > 存放图片文件
3. js
    
    > 存放Javascript文件
* 后端接口使用Axios JS库进行ajax请求
* 图表使用 Apache Echarts JS库绘制

