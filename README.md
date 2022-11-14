## smart-ddd-scaffold

### 建模

![订单领域建模](./docs/订单模型关系图.png)
![smart-ddd建模](./docs/smart-ddd建模.png)

### Smart DDD模式

1. 纯粹的面向对象，没有service和manager等贫血对象。
2. 所有模型会被设计成完全连接的对象图，中间不存在孤立的对象。
3. 所有的模型概念图会被映射为RESTful API并通过HATEOAS连接。
4. 成为一直屏蔽实现细节的抽象层。
5. 在概念模型/模型/API之间形成完全的映射和一致，看到API能想到模型的能力暴露，是一个完全充血的模型。

### 传统DDD的困境

```text
领域驱动设计实现逻辑：先找到领域模型，再找到他们之间的聚合和引用关系，然后通过他们之间的聚合和引用关系把业务表达出来，最后通过聚合和实体范围做为业务一致性的边界。
聚合边界同时也意味着生命周期的边界，同时创建和销毁的实体往往在同一个聚合并拥有相同的生命周期。跨生命周期访问实体往往通过Repository去加载不同聚合不同生命周期的实体。
```

1. 通过领域上下文和聚合边界来保持业务的一致性，外部对象只能引用聚合根不能引用非聚合根的实体。
2. 领域服务的现状：跨生命周期完成业务领域某种程度的一致性。领域服务会把领域概念中需要维持一致性的业务逻辑从它的一致性边界中移出去，会将DDD变得越来越贫血。

### 指南

#### 分层

分层需要权衡：适当的分层促进解耦，过度的分层破坏抽象。

* main: 应用启动入口。
* api: 展现层，定义RESTful API。
* domain: 核心领域层。没有任何依赖。
* persistent: 数据持久化层。管理领域对象生命周期具体的实现。

```text
聚合根负责实体的生命周期管理，聚合根委托给Repository来完成生命周期的管理。
Customer、Order、Product是聚合根，聚合根的生命周期有Repository来管理即Customers、Orders、Products。
```

#### 脚手架

1. 使用Mybatis的目的：domain层可以与Spring Jpa解耦。

### 本地运行

`./gradlew clean :main:bootRun`

### 参考

1. [整洁架构](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
2. [Hexagonal Architecture](https://herbertograca.com/2017/11/16/explicit-architecture-01-ddd-hexagonal-onion-clean-cqrs-how-i-put-it-all-together/)
3. [阿里COLA](https://github.com/alibaba/COLA)
4. [Smart DDD](https://github.com/Business-Oriented-Design/business-oriented.design)
5. [CRC建模](http://c2.com/doc/oopsla89/paper.html)
