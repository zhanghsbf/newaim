git项目地址
访问网页 https://github.com/zhanghsbf/newaim.git
或者执行命令 
```
git clone git@github.com:zhanghsbf/newaim.git
```
## 练习题解答
Please attempt as many of these as possible:
1. Fix the failing tests
```commandline
1. contacts模型主键重复，没有时间或者状态字段来判断去重逻辑，因此直接删除最后一条重复联系人
2. transactions模型category类目的枚举检查失败，删除交易表不在统计范围内的类目
3. 删除交易表不在contacts表id范围内的用户的交易
4. contacts_joined_with_transactions模型 是交易粒度的表，不应该让id即用户id唯一，因为用户会产生多条交易；
因此注释不合理的 唯一id检查， 或者改成 transaction_id唯一检查
```

2. Add a test to validate the referential integrity of the `transactions` table (`contact_id` refers to a valid contact) and ensure it passes
```
已添加，见/option1-customer360/models/staging/web/_web__sources.yml
```

3. We want to ensure our `transactions` data is not older than 1 day. How to do this and when to run these checks?
```commandline
增加过滤条件即可，见注释部分/option1-customer360/models/staging/web/stg_web__transactions.sql
为方便验证，过滤条件先注释了
```


4. Add tests for macros
```commandline
见/option1-customer360/models/marco_test1.sql 和 marco_test2.sql
```

5. Macros contain Postgres-specific functions, however our production environment is in Databricks. How would you refactor them, to allow switching between these two syntax?
```commandline
设置2套profile，在宏中通过profile走不同的语法分支来兼容；在使用时通过 dbt run --profile=xxx来指定环境
见 integration_tests/profiles.yml 和 macros/as_timestamp_utc.sql
```


6. We'd like to enhance the `customers` model by adding few more attributes:
   + `first_purchase_date` and `last_purchase_date` (time of first and last purchase respectively)
   + total amount of purchase per category eg. columns like `app_and_games_amount`, `beauty_amount` etc. (refer to `_web__sources.yml` for a static list of product categories)
   
   Please update the `customers` model for these columns. Also, write singular test(s) to validate the logic.
```commandline
见 models/marts/marketing/customers.sql
  test/customer_test.sql
```


7. Any other improvements you'd like to make?
```commandline
可以增加一张销售情况的月度报表
见 models/marts/marketing/sales_month_report.sql
```

8. Think of how you can implement the following (what additional datasets would you use, how will the models look etc). We will explore these add-ons during tech interview stage. Additional points, if you can implement them now!
   + Product Category Recommender - how to implement Next Best Product Category?
   + Data Sharing: how would you implement PII on this data, so it can be safely shared with, say, partners?
```commandline
1. 推荐系统，可以考虑从实时，累计销售情况，相似用户购买，优惠活动等场景来生成推荐模型，目前已实现一个简单推荐系统：
- newaim-dataapi系统，通过api的方式将推荐数据分享给下游，可以兼容不同的用数场景
- api介绍: localhost:8080/api/recommend/hotcatagory
    按T+1的时效离线计算销售前3的类目存入pg中，通过jdbc访问数据，返回给api调用方
- 计算逻辑见 models/recommend/top3_hot_categorys.sql
- 系统代码见 /option1-customer360/newaim-dataapi
- 后续可按下游需求增加推荐的内容

2. 数据共享时个人敏感信息问题：
可以设计一套完整的数据安全体系，库、表、字段、甚至行设计安全级别，从用户等级和安全级别构建访问权限矩阵;
并且按访问环境和方式的不同设置脱敏算法以及是否落盘。 
数据api系统实现了一个简单的脱敏接口，将contactor联系人信息的  邮箱，地址这两个敏感字段处理为 '***'后返回
- api: localhost:8080/api/pii/contactor?username=要查询的用户

3. 测试方法：
- 进入integration_tests 目录
- docker-compose up -d  调起pg
- dbt build;dbt run 跑出模型结果
- cd ../;  java -jar  newaimdataapi-0.0.1-SNAPSHOT.jar   调起数据api服务
- curl -X GET localhost:8080/api/recommend/hotcatagory   获取推荐类目
- curl -X GET localhost:8080/api/pii/contactor?username=lisa83  获取脱敏联系人信息

```