接口： http://localhost:8080/test/{type}
type 的取值：
  0：正常插入
  1：测试传播特性 Required：如果当前已经存在事务，那么加入该事务，如果不存在事务，创建一个事务，这是默认的传播属性值
  2：测试传播特性 RequiresNew：开启新事物，如果当前已存在事务，将当前事务挂起，新事物不影响旧事物的回滚
  3：测试传播特性 Mandatory：当前必须存在事务，否则抛出异常
  4：测试传播特性 Supports：如果当前已经存在事务，那么加入该事务，如果不存在事务，以非事务方式进行
  5：测试传播特性 Nested：嵌套事务，内部事务的回滚会影响外部事务回滚
  6：测试传播特性 Never：以非事务方式进行，如果存在事务则抛异常
  7：测试传播特性 NotSupported：以非事务方式进行，如果当前存在事务，挂起当前事务
  8：测试private或protected修饰的方法
  9：测试一个类的内部方法之间的调用
  10：测试需要回滚的异常和抛出的异常不匹配的场景
  11：测试异常被捕捉的场景
