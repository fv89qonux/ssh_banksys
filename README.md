## 本项目实现的最终作用是基于SSH在线银行业务模拟系统
## 分为2个角色
### 第1个角色为管理员角色，实现了如下功能：
 - 业务员管理
 - 业务管理
 - 修改密码
 - 管理员登录
 - 账户管理
### 第2个角色为业务员角色，实现了如下功能：
 - 业务员登录
 - 修改密码
 - 修改账户信息
 - 取款
 - 存款
 - 建立新账户
 - 账户管理
 - 转账
 - 销户
## 数据库设计如下：
# 数据库设计文档

**数据库名：** ssh_bank

**文档版本：** 


| 表名                  | 说明       |
| :---: | :---: |
| [account](#account) | 账号表 |
| [admin](#admin) | 管理员表 |
| [log](#log) |  |
| [personinfo](#personinfo) |  |
| [status](#status) |  |
| [transaction_log](#transaction_log) |  |
| [transaction_type](#transaction_type) |  |

**表名：** <a id="account">account</a>

**说明：** 账号表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | accountid |   int   | 10 |   0    |    N     |  Y   |       |   |
|  2   | username |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 用户名  |
|  3   | password |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 密码  |
|  4   | balance |   decimal   | 19 |   2    |    Y     |  N   |   NULL    |   |
|  5   | status |   int   | 10 |   0    |    Y     |  N   |   NULL    | 状态  |

**表名：** <a id="admin">admin</a>

**说明：** 管理员表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       |   |
|  2   | username |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 用户名  |
|  3   | password |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 密码  |

**表名：** <a id="log">log</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       |   |
|  2   | type |   varchar   | 50 |   0    |    Y     |  N   |   NULL    |   |
|  3   | content |   varchar   | 10000 |   0    |    Y     |  N   |   NULL    |   |
|  4   | userid |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  5   | time |   datetime   | 19 |   0    |    Y     |  N   |   current_timestamp()    |   |

**表名：** <a id="personinfo">personinfo</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       |   |
|  2   | accountid |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  3   | realname |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 真实名字  |
|  4   | age |   int   | 10 |   0    |    Y     |  N   |   NULL    | 年龄  |
|  5   | SEX |   varchar   | 2 |   0    |    Y     |  N   |   NULL    | 性别  |
|  6   | cardid |   decimal   | 19 |   0    |    Y     |  N   |   NULL    |   |
|  7   | ADDRESS |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 地址  |
|  8   | telephone |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 手机号码  |

**表名：** <a id="status">status</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       |   |
|  2   | name |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 名字  |

**表名：** <a id="transaction_log">transaction_log</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       |   |
|  2   | accountid |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  3   | otherid |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  4   | tr_money |   decimal   | 19 |   0    |    Y     |  N   |   NULL    |   |
|  5   | datetime |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  6   | ta_type |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |

**表名：** <a id="transaction_type">transaction_type</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       |   |
|  2   | name |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 名字  |

