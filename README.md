
## 配置管理接口

### 获取接口配置列表

通过关键词查询接口配置。查询关键词作用于接口名称、接口URL已及授权码。该接口支持分页，如不传分页参数，则返回最近添加的20条数据。

请求方法：**GET**

接口URL：**/base/auth/v1.0/configs**

请求参数如下：

|类型|字段|是否必需|字段说明|
|----|----|----|----|
|String|keyword|否|查询关键词|
|Integer|page|否|分页页码|
|Integer|size|否|每页记录数|

请求示例：

```bash
curl "http://192.168.16.1:6200/base/auth/v1.0/configs?keyword=users" \
 -H 'Accept: application/json' \
 -H 'Accept-Encoding: gzip, identity' \
 -H 'Authorization: eyJpZCI6IjUyZmFlYWI5OWUxMTQwNzBhOTliZDk2YTI0MmM3YWE2IiwidXNlcklkIjoiMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAiLCJ1c2VyTmFtZSI6bnVsbCwic2VjcmV0IjoiMWQyNWY3MDEwYzVhNDFhNGJiMGE2OTE0ZDA4OWZlNzQifQ==' \
 -H 'Content-Type: application/json'
```

接口返回数据类型：

|类型|字段|字段说明|
|----|----|----|
|String|id|接口配置ID|
|String|name|接口名称|
|String|method|请求方法(GET|POST|PUT|DELETE)|
|String|url|接口URL|
|String|authCode|授权码,仅授权接口需要具有授权码|
|Boolean|verify|是|是否需要验证,如配置了authCode,则需进行鉴权|
|Boolean|limit|是|是否限流,如配置为限流,则需配置对应限流参数|

返回结果示例：

```json
{
  "success": true,
  "code": 200,
  "message": "请求成功",
  "data": [
    {
      "id": "d7c405a9ce1d49dbab17f3d7a3e0fabf",
      "name": "获取用户列表",
      "method": "GET",
      "url": "/base/user/v1.0/users",
      "authCode": null,
      "verify": true,
      "limit": true
    }
  ],
  "option": 1
}
```

[回目录](#目录)

### 获取接口配置详情

获取指定ID的接口配置详情。

请求方法：**GET**

接口URL：**/base/auth/v1.0/configs/{id}**

请求参数如下：

|类型|字段|是否必需|字段说明|
|----|----|----|----|
|String|id|是|接口配置ID|

接口返回数据类型：

|类型|字段|字段说明|
|----|----|----|
|String|id|接口配置ID|
|String|name|接口名称|
|String|method|请求方法(GET|POST|PUT|DELETE)|
|String|url|接口URL|
|String|authCode|授权码|
|Integer|limitGap|访问最小时间间隔(秒)|
|Integer|limitCycle|限流周期(秒)|
|Integer|limitMax|最多调用次数/限流周期|
|String|message|限流时返回的错误消息|
|String|remark|备注|
|Boolean|verify|是|是否需要验证,如配置了authCode,则需进行鉴权|
|Boolean|limit|是|是否限流,如配置为限流,则需配置对应限流参数|
|Date|createdTime|创建时间|

请求示例：

```bash
curl "http://192.168.16.1:6200/base/auth/v1.0/configs/c0592bb8dc3a11e9bc200242ac110004" \
 -H 'Accept: application/json' \
 -H 'Accept-Encoding: gzip, identity' \
 -H 'Authorization: eyJpZCI6IjUyZmFlYWI5OWUxMTQwNzBhOTliZDk2YTI0MmM3YWE2IiwidXNlcklkIjoiMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAiLCJ1c2VyTmFtZSI6bnVsbCwic2VjcmV0IjoiMWQyNWY3MDEwYzVhNDFhNGJiMGE2OTE0ZDA4OWZlNzQifQ==' \
 -H 'Content-Type: application/json'
```

返回结果示例：

```json
{
  "success": true,
  "code": 200,
  "message": "请求成功",
  "data": {
    "id": "c0592bb8dc3a11e9bc200242ac110004",
    "name": "验证短信验证码",
    "method": "GET",
    "url": "/base/message/sms/v1.0/messages/codes/{key}/status",
    "authCode": null,
    "limitGap": 1,
    "limitCycle": null,
    "limitMax": null,
    "message": null,
    "remark": null,
    "createdTime": "2019-09-21 14:40:50",
    "verify": false,
    "limit": true
  },
  "option": null
}
```

[回目录](#目录)

### 新增接口配置

新增一个接口配置。

请求方法：**POST**

接口URL：**/base/auth/v1.0/configs**

请求参数如下：

|类型|字段|是否必需|字段说明|
|----|----|----|----|
|String|name|是|接口名称|
|String|method|是|请求方法(GET|POST|PUT|DELETE)|
|String|url|是|接口URL|
|String|authCode|否|授权码,仅授权接口需要具有授权码|
|Integer|limitGap|否|访问最小时间间隔(秒)|
|Integer|limitCycle|否|限流周期(秒)|
|Integer|limitMax|否|最多调用次数/限流周期|
|String|remark|否|备注|
|Boolean|verify|是|是否需要验证,如配置了authCode,则需进行鉴权|
|Boolean|limit|是|是否限流,如配置为限流,则需配置对应限流参数|

请求参数示例：

```json
{
  "name": "获取用户列表",
  "method": "GET",
  "url": "/base/user/v1.0/users",
  "authCode": null,
  "limitGap": 1,
  "limitCycle": null,
  "limitMax": null,
  "message": null,
  "remark": null,
  "verify": true,
  "limit": true
}
```

返回结果示例：

```json
{
  "success": true,
  "code": 200,
  "message": "请求成功",
  "data": "5697c57cf0954631a5f347d7c001ecee",
  "option": null
}
```

[回目录](#目录)

### 编辑接口配置

修改指定ID的接口配置信息。

请求方法：**PUT**

接口URL：**/base/auth/v1.0/configs**

请求参数如下：

|类型|字段|是否必需|字段说明|
|----|----|----|----|
|String|id|是|接口配置ID|
|String|name|是|接口名称|
|String|method|是|请求方法(GET|POST|PUT|DELETE)|
|String|url|是|接口URL|
|String|authCode|否|授权码,仅授权接口需要具有授权码|
|Integer|limitGap|否|访问最小时间间隔(秒)|
|Integer|limitCycle|否|限流周期(秒)|
|Integer|limitMax|否|最多调用次数/限流周期|
|String|remark|否|备注|
|Boolean|verify|是|是否需要验证,如配置了authCode,则需进行鉴权|
|Boolean|limit|是|是否限流,如配置为限流,则需配置对应限流参数|

请求参数示例：

```json
{
  "id": "d7c405a9ce1d49dbab17f3d7a3e0fabf",
  "name": "获取用户列表",
  "method": "GET",
  "url": "/base/user/v1.0/users",
  "authCode": "getUser",
  "limitGap": 1,
  "limitCycle": null,
  "limitMax": null,
  "message": null,
  "remark": null,
  "verify": true,
  "limit": true
}
```

返回结果示例：

```json
{
  "success": true,
  "code": 200,
  "message": "请求成功",
  "data": null,
  "option": null
}
```

[回目录](#目录)

### 删除接口配置

删除指定ID的接口配置数据。

请求方法：**DELETE**

接口URL：**/base/auth/v1.0/configs**

请求参数如下：

|类型|字段|是否必需|字段说明|
|----|----|----|----|
|String|-|是|接口配置ID|

请求参数示例：

```json
"7179f5e4c7f84879bdfb70de0999b067"
```

返回结果示例：

```json
{
  "success": true,
  "code": 200,
  "message": "请求成功",
  "data": null,
  "option": null
}
```

[回目录](#目录)

### 获取日志列表

通过关键词查询接口配置数据变更记录。查询关键词作用于操作类型、业务名称、业务ID、操作人ID和操作人姓名。该接口支持分页，如不传分页参数，则返回最近添加的20条数据。

请求方法：**GET**

接口URL：**/base/auth/v1.0/configs/logs**

请求参数如下：

|类型|字段|是否必需|字段说明|
|----|----|----|----|
|String|keyword|否|查询关键词|
|Integer|page|否|分页页码|
|Integer|size|否|每页记录数|

请求示例：

```bash
curl "http://192.168.16.1:6200/base/auth/v1.0/configs/logs?keyword=UPDATE" \
 -H 'Accept: application/json' \
 -H 'Accept-Encoding: gzip, identity' \
 -H 'Authorization: eyJpZCI6IjUyZmFlYWI5OWUxMTQwNzBhOTliZDk2YTI0MmM3YWE2IiwidXNlcklkIjoiMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAiLCJ1c2VyTmFtZSI6bnVsbCwic2VjcmV0IjoiMWQyNWY3MDEwYzVhNDFhNGJiMGE2OTE0ZDA4OWZlNzQifQ==' \
 -H 'Content-Type: application/json'
```

接口返回数据类型：

|类型|字段|字段说明|
|----|----|----|
|String|id|日志ID|
|String|type|操作类型|
|String|business|业务名称|
|String|businessId|业务ID|
|String|creator|创建人|
|String|creatorId|创建人ID|
|Date|createdTime|创建时间|

返回结果示例：

```json
{
  "success": true,
  "code": 200,
  "message": "请求成功",
  "data": [
    {
      "id": "fbd221108249433c9e850285263804d7",
      "type": "UPDATE",
      "business": "接口配置管理",
      "businessId": "c097f07552ca47c190f76803f9e89fb1",
      "content": null,
      "creator": "系统管理员",
      "creatorId": "00000000000000000000000000000000",
      "createdTime": "2019-09-13 17:11:36"
    },
    {
      "id": "e7c4643e8c4942a08dc4a9a72b4a9ff5",
      "type": "UPDATE",
      "business": "接口配置管理",
      "businessId": "c097f07552ca47c190f76803f9e89fb1",
      "content": null,
      "creator": "系统管理员",
      "creatorId": "00000000000000000000000000000000",
      "createdTime": "2019-09-13 17:12:59"
    }
  ],
  "option": 2
}
```

[回目录](#目录)

### 获取日志详情

获取指定ID的日志详情。

请求方法：**GET**

接口URL：**/base/auth/v1.0/configs/logs/{id}**

请求参数如下：

|类型|字段|是否必需|字段说明|
|----|----|----|----|
|String|id|是|日志ID|

接口返回数据类型：

|类型|字段|字段说明|
|----|----|----|
|String|id|日志ID|
|String|type|操作类型|
|String|business|业务名称|
|String|businessId|业务ID|
|Object|content|日志内容|
|String|creator|创建人|
|String|creatorId|创建人ID|
|Date|createdTime|创建时间|

请求示例：

```bash
curl "http://192.168.16.1:6200/base/auth/v1.0/configs/logs/bc3e1a2256af4445a64420b92776411c" \
 -H 'Accept: application/json' \
 -H 'Accept-Encoding: gzip, identity' \
 -H 'Authorization: eyJpZCI6IjUyZmFlYWI5OWUxMTQwNzBhOTliZDk2YTI0MmM3YWE2IiwidXNlcklkIjoiMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAiLCJ1c2VyTmFtZSI6bnVsbCwic2VjcmV0IjoiMWQyNWY3MDEwYzVhNDFhNGJiMGE2OTE0ZDA4OWZlNzQifQ==' \
 -H 'Content-Type: application/json'
```

返回结果示例：

```json
{
  "success": true,
  "code": 200,
  "message": "请求成功",
  "data": {
    "id": "aa8c5ee4b421440bae6e0099b6550992",
    "type": "INSERT",
    "business": "接口配置管理",
    "businessId": "c097f07552ca47c190f76803f9e89fb1",
    "content": {
      "id": "c097f07552ca47c190f76803f9e89fb1",
      "url": "/base/user/v1.0/user",
      "name": "获取用户列表",
      "limit": true,
      "method": "GET",
      "remark": null,
      "verify": true,
      "message": "获取Code接口每24小时调用次数为360次,请合理使用",
      "authCode": null,
      "limitGap": 1,
      "limitMax": 360,
      "limitCycle": 86400,
      "createdTime": null
    },
    "creator": "系统管理员",
    "creatorId": "00000000000000000000000000000000",
    "createdTime": "2019-09-13 17:10:38"
  },
  "option": null
}
```

[回目录](#目录)

### 加载接口配置到缓存

从数据库读取全部接口配置数据，并加载到Redis。

请求方法：**GET**

接口URL：**/base/auth/v1.0/configs/load**

请求示例：

```bash
curl "http://192.168.16.1:6200/base/auth/v1.0/configs/load" \
 -H 'Accept: application/json' \
 -H 'Accept-Encoding: gzip, identity' \
 -H 'Authorization: eyJpZCI6IjUyZmFlYWI5OWUxMTQwNzBhOTliZDk2YTI0MmM3YWE2IiwidXNlcklkIjoiMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAiLCJ1c2VyTmFtZSI6bnVsbCwic2VjcmV0IjoiMWQyNWY3MDEwYzVhNDFhNGJiMGE2OTE0ZDA4OWZlNzQifQ==' \
 -H 'Content-Type: application/json'
```

返回结果示例：

```json
{
  "success": true,
  "code": 200,
  "message": "请求成功",
  "data": null,
  "option": null
}
```

[回目录](#目录)
