# 数据库设计

## 视图

### v_login(登录视图)

| 字段名   | 中文名        |
| -------- | ------------- |
| userid   | 用户ID        |
| username | 用户名        |
| passwd   | 密码(加盐MD5) |

## 数据表

### t_user(用户表)

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





