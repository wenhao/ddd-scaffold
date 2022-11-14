## smart-ddd-scaffold

### 建模

![订单领域建模](./docs/订单模型关系图.png)
![smart-ddd建模](./docs/smart-ddd建模.png)

### 指南

#### 分层

分层需要权衡：适当的分层促进解耦，过度的分层破坏抽象。

* main: 应用启动入口。
* api: 展现层，定义RESTful API。
* domain: 核心领域层。没有任何依赖。
* persistent: 数据持久化层。管理领域对象生命周期具体的实现。

Customer、Order、Product是聚合根，聚合根的生命周期有Repository来管理即Customers、Orders、Products。

#### 脚手架

1. 使用Mybatis的目的：domain层可以与Spring Jpa解耦。

### 本地运行

`./gradlew clean :main:bootRun`

### 参考

1. [整洁架构](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
2. [Hexagonal Architecture](https://herbertograca.com/2017/11/16/explicit-architecture-01-ddd-hexagonal-onion-clean-cqrs-how-i-put-it-all-together/)
3. [阿里COLA](https://github.com/alibaba/COLA)
4. [Smart DDD](https://github.com/Business-Oriented-Design/business-oriented.design)
