# 数据库设计

## 视图

### v_login(登录视图)

| 字段名  | 中文名                     |
| ------- | -------------------------- |
| userid  | 用户ID                     |
| keyword | 关键字(用户名,邮箱,手机号) |
| passwd  | 密码(加盐MD5)              |

## 数据表

### t_user(用户表)

#### 字段设计

| 字段名   | 类型        | 不能为NULL | 默认值 | 属性  | 注释          |
| -------- | ----------- | ---------- | ------ | ----- | ------------- |
| userid   | int         | 是         | 自增   | 主键1 | 用户ID        |
| username | varchar(32) | 是         | 无     | 主键2 | 用户名        |
| passwd   | varchar(32) | 是         | 无     |       | 密码(MD5加盐) |
| nickname | varchar(64) | 否         | 无     |       | 昵称          |
| regtime  | datetime    | 是         | 无     |       | 注册时间      |
| usertype | int         | 是         | 无     |       | 用户类型      |
| email    | varchar(64) | 否         | 无     |       | 邮箱          |
| phone    | varchar(16) | 否         | 无     |       | 电话          |
| islocked | bit         | 是         | 0      |       | 账户锁定状态  |

#### 索引设计

| 索引名         | 索引字段             | 索引类型 | 注释                             |
| -------------- | -------------------- | -------- | -------------------------------- |
| keyword_unique | username,email,phone | unique   | 防止出现重复                     |
| nickname       | nickname             | normal   | 索引昵称，加快查找速度           |
| login_key      | username,email,phone | normal   | 索引登录关键字，加快登录查询速度 |
| login_pass     | passwd               | normal   | 索引密码，加快登录查询速度       |





