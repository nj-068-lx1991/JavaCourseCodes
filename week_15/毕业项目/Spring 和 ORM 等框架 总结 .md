## 模块四 Spring 和 ORM等框架 总结

	Spring是一个非常活跃的开源框架, 它是一个基于IOC和AOP来构架多层JavaEE系统的框架,它的主要
	目地是简化企业开发，Spring以一种非侵入式的方式来管理你的代码, Spring提倡”最少侵入”，这也
	就意味着你可以适当的时候安装或卸载Spring。

### 具体描述Spring

> 1.轻量级：Spring 是非侵入性的>基于 Spring 开发的应用中的对象可以不依赖于 Spring 的 API，基础版本的Spring框架大约只有2MB.

> 组件/框架设计

> 侵入式设计
 
	 引入了框架，对现有的类的结构有影响；即需要实现或继承某些特定类。
	 例如： Struts框架

> 非侵入式设计

	 引入了框架，对现有的类结构没有影响。
	 例如：Hibernate框架 / Spring框架
	 
> 2.依赖注入(DI --- dependency injection、IOC)

	Spring使用控制反转技术实现了松耦合。依赖被注入到对象，而不是创建或寻找依赖对象。
	
> a、IOC Inversion on Control , 控制反转
 
	 其思想是反转资源获取的方向. 传统的资源查找方式要求组件向容器发起请求查找资源. 作为回应, 容
	 器适时的返回资源. 而应用了 IOC 之后, 则是容器主动地将资源推送给它所管理的组件, 组件所要做
	 的仅是选择一种合适的方式来接受资源. 这种行为也被称为查找的被动形式。可以简单的理解为对象的
	 创建交给外部容器完成，这个就叫做控制反转.
 
 > b、依赖注入， dependency injection
 
	 IOC 的另一种表述方式：即组件以一些预先定义好的方式(例如: setter 方法)接受来自如容器的资
	 源注入. 相对于 IOC 而言，这种表述更直接。
 
> 两者区别：

	 控制反转: 解决对象创建的问题 【对象创建交给别人】
	 依赖注入: 在创建完对象后，对象的关系的处理就是依赖注入 【通过set方法依赖注入】
 
> 3.面向切面编程(AOP --- aspect oriented programming)

	a、采用了面向切面编程来实现很多基础但是与业务逻辑无关的功能的解耦,比如：事务管理、日志、权限
	验证等。

	b、AOP 的主要编程对象是切面(aspect), 而切面模块化横切关注点.

	c、在应用 AOP 编程时, 仍然需要定义公共功能, 但可以明确的定义这个功能在哪里, 以什么方式应
	用, 并且不必修改受影响的类. 这样一来横切关注点就被模块化到特殊的对象(切面)里.
	
	1、关注点:
	 重复代码就叫做关注点；
	 
	2、切面:
	 关注点形成的类，就叫切面(类)！
	 面向切面编程，就是指对很多功能都有的重复的代码抽取，再在运行的时候往业务方法上动态植入“切面
	 类代码”。
	 
	3、切入点:
	 执行目标对象方法，动态植入切面代码。
	 可以通过切入点表达式，指定拦截哪些类的哪些方法； 给指定的类在运行的时候植入切面类代码。
 
> 4.容器:Spring 是一个容器, 因为它包含并且管理应用对象的生命周期

> 5.框架:Spring 实现了使用简单的组件配置组合成一个复杂的应用. 在 Spring 中可以使用 XML 和 Java 注解组合这些对象

> 6.一站式

> Spring提供了一站式解决方案：

	1） Spring Core spring的核心功能： IOC容器, 解决对象创建及依赖关系
	2） Spring Web Spring对web模块的支持。
	  可以与struts整合,让struts的action创建交给spring
	  
> spring mvc模式

	3） Spring DAO Spring 对jdbc操作的支持
	  【JdbcTemplate模板工具类】
	4） Spring ORM spring对orm的支持：
	  既可以与hibernate整合，【session】
	  也可以使用spring的对hibernate操作的封装
	5）Spring AOP 切面编程
	6）SpringEE spring 对javaEE其他模块的支持
	
	
[Spring和ORM等框架脑图](https://github.com/nj-068-lx1991/JavaCourseCodes/blob/master/week_15/%E6%AF%95%E4%B8%9A%E9%A1%B9%E7%9B%AE/Spring%20%E6%A1%86%E6%9E%B6.png)