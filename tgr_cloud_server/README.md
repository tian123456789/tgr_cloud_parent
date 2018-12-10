eurake常见问题	https://blog.csdn.net/lc0817/article/details/54375802
Eureka配置最佳实践参考        https://github.com/spring-cloud/spring-cloud-netflix/issues/203
EMERGENCY! EUREKA MAY BE INCORRECTLY CLAIMING INSTANCES ARE UP WHEN THEY'RE NOT. RENEWALS ARE LESSER THAN THRESHOLD AND HENCE THE INSTANCES ARE NOT BEING EXPIRED JUST TO BE SAFE.

eurake概念:
	Registe「一一服务注册
		当 Eureka Client 向 Eureka Server 注册时 ， Eureka Client 提供自身的元数据，比如 IP 地址、
		端口、运行状况H1标的 Uri 、 主页地址等信息。
		
		 client端
		 eruke-client-1.6.2.jar
		 com.netflix.discovery.DiscoveryClient.java.register()
		 	InstancelnfoReplicator 类是在 DiscoveryClient 初始化过程 中 使用的， 其中有 一个
				initScheduledTasks （）方法 ， 该方法主要开启了 获取服务注册列表 的 信息。 如果需要 向 Eureka
				Server 注册，则开启注册，同时开启 了 定 时任务 向 Eureka Server 服务续约 ，

		 	private InstanceInfoReplicator instanceInfoReplicator;
		 			这个类的run方法也调用了这个方法
		 	class InstanceInfoReplicator implements Runnable {实例信息复制器
		 		  public void run() {
			        try {
			            discoveryClient.refreshInstanceInfo();
			
			            Long dirtyTimestamp = instanceInfo.isDirtyWithTime();
			            if (dirtyTimestamp != null) {
			                discoveryClient.register();
			                instanceInfo.unsetIsDirty(dirtyTimestamp);
		 	}
		   而且这个
		 com.netflix.discovery.shared.transport.EurekaHttpResponse<T>.java
		   响应结果集很有借鉴价值
		   
		 server端 	cloud p94
		 eureka-core-1.6.2.jar
		 com.netflix.eureka.EurekaBootStrap implements ServletContextListener{
		 
		 }
		 经过一系列的源码追踪，可以发现 PeerAwarelnstanceRegis町Imp！ 类的 register（）方法实现了服
		务的注册，并且向其他 Eureka Server 的 Peer 节点同步了该注册信息，那么 register（）方法被谁调用
		了呢？ 在前文中有关 Eureka Client 的分析中可以知道， Eureka Client 是通过 Http 来 向 Eureka Server
		注册的，那么 Eureka Server 肯定会提供一个服务注册的 API 接口给 Eureka Client 调用，
		PeerAwarelnstanceRegistryImpl的 register（）方法最终肯定会被暴露的 Http 接口所调用 
		
	Renew一一服务续约
	
	另外服务续约有两个参数是可以配置的，即 Eureka Client 发送续约心跳的时间参数和
	Eureka Server 在多长时间内没有收到心跳将实例剔除的时间参数。在默认情况下，这两个参数
	分别为 30 秒和 90 秒， 官方的建议是不要修改，如果有特殊需求还是可以调整的，只需要分别
	在 Eureka Client 和 Eureka Server 的配置文件 application.yml 中加以下的配置：
	
	eureka.instance.leaseRenewalIntervalInSeconds
	eureka.instance.leaseExpirationDurationinSeconds
	
	Fetch Registries一一获取服务注册列表信息
	Cancel－一服务下线
		DiscoveryManager.getinstance().shutdownComponent();
	Eviction一一服务剔除
		Eureka Client 连续 90 秒没有向 Eureka Server 发送服务续约（即心跳〉
		时， Eureka Server 会将该服务实例从服务注册列表删除，即服务剔除。
		


	eureka server响应缓存
		Eureka Server 维护每 30 秒更新一次响应缓存，可通过更改配置 
	
		eureka.ser飞1er.responseCacheUpdatelntervalMs 
		来修改 。所以即使是刚刚注册的实例，也不会立即出现在服务注册列表中。
	
	Eureka Client 的缓存
		Eureka Client 保留注册表信息的缓存。该缓存每 30 秒更新一次
		
	LoadBalancer 的缓存
		Ribbon 的负载平衡器从本地的 Eureka Client 获取服务注册列表信息。 Ribbon 本身还维护
		了缓存，以避免每个请求都需要从 Eureka Client 获取服务注册列表。此缓存每 30 秒刷新一次
		（可由 ribbon.ServerListRe企eshlnterval 配置） ，所以可能至少需要 3 0 秒的时间才能使用新注册
		的实例。
	综上因素，一个新注册的实例，默认延迟 40 秒向服务注册中心注册，所以不能马上被
	Eureka Server 发现。另外，刚注册的 Eureka Client 也不能立即被其他服务调用，原因是调用方
	由于各种缓存没有及时获取到最新的服务注册列表信息。
		
	Eureka 的自我保护模式
		新加server 默认从旁边server获取注册表信息,如果一个出现错误 尝试从其他节点获取 ，获取成功 从配置文件设置阈值
		如果一定时间内再更新失败多少次 注册列表信息不再剔除 从而保证有注册表可用
		
		关闭设置:
			eureka:server:enable-self-preservation: false
















	