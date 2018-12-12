负载均衡: 将负载分摊到多个执行单元上
	常见方式: 1.独立进程单元,通过负载均衡策略,将请求转发到不同的执行单元上,如Ngnix
			  2.将负载均衡逻辑以代码的形式封装到服务消费者的客户端上,服务消费者客户端维护了一份服务提供者的信息列表,有了信息列表,通过负载均衡策略将
			  请求分摊给多个服务提供者,从而达到负载均衡的目的。如Ribbon
			Ribbon 是一个经过了云端测试的 IPC库，司以很好地控制 HTTP 和 TCP 客户端的负载均衡行为。
负载均衡核心类: LoadBalancerClient(加载负载均衡客户端) 可以获取负载均衡的提供者的实例信息

springCloud ribbon使用方式:
1.与RestTemplate结合
	@LoadBalanced注解https://blog.csdn.net/Tincox/article/details/79210309这个得认真看一下
	DynamicServerListLoadBalancer追踪 到这个类的时候做了很多
	
####################################################################################################################################################################################################################
	p115 如此上下若干page 还需很多知识(锁，各种定时任务,标志性注解等,深入的线程知识) 再说也太多 目前计划不能深入理解
	
	由此可见， LoadBalancerClient 是在初始化时向 Eureka 获取服务注册列表信息 ， 井且每 10
秒向 EurekaClient 发送“ping”，来判断服务的可用性 。 如果服务的可用性发生了改变或者服务
数量和之前的不一致，则更新或者重新拉取。 LoadBalancerClient 有了这些服务注册列表信息，
就可以根据具体的 ！Rule 的策略来进行负载均衡。
最后 ，回到问题的本身，为什么在 RestTemplate 类的 Bean 上加一个＠LoadBalance 注解就
可以使用 Ribbon 的负载均衡呢？
	全局搜索（ IDEA 的快捷键为“ Ctrl ＂＋ “ Shift ＂＋ “F ”〉 查看有哪些类用到了＠LoadBalanced
注解。通过搜索，可以发现 LoadBalancerAutoConfiguration 类（ LoadBalancer 向动配置类〉使
用到了该注解， LoadBalancerAutoConfiguration 类的代码如下 ：

####################################################################################################################################################################################################################

p117

在 LoadBalancerAutoConfiguration 类中 ， 首先维护了 一 个被@LoadBalanced 修饰的
RestTemplate 对象的 L ist 。在初始化的过程中，通过调用 customizer.customize(restTemplate）方
法来给 RestTemplate 增加拦截器 LoadBalancerlnterceptor 。 LoadBalancerinterceptor 用于实时拦
截，在 LoadBalancerlnterceptor 中 实现了负载均衡的方法。 LoadBalancerlnterceptor 类的拦截方
法的代码如下：
自 Override
public ClientHttpResponse intercept(final HttpRequest request , final byte[] body,
	final ClientHttpRequestExecuti on execution) throws IOException
	final URI originalUrl＝ request . getURI();
	String serviceName = originalUri getHost() ;
	return this. loadBalancer . execute (servceName , requestFactory.createRequest (request ,
	body , execution) );
综上所述 ， Ribbon 的负载均衡主要是通过 LoadBalancerClient 来实现的，而 LoadBalancerClient 具体交给了 ILoadBalancer 来处理， ILoadBalancer 通过配置 IRule 、 IPing 等，向 EurekaClient
获取注册列表的信息，默认每 10 秒向 EurekaClient 发送一次“ping ”， 进而检查是否需要更新
服务的注册列表信息 。最后 ，在得到服务注册列表信息后， ILoadBalancer 根据！Rule 的策略进
行负载均衡。
而 RestTemplate 加上@LoadBalance 注解后，在远程调度时能够负载均衡， 主要是维护了
一个被@LoadBalance 注解的 RestTemplate 列表，并给该列表中 的 RestTemplate 对象添加了拦
截器。在拦截器的方法中 ，将远程调度方法交给了 Ribbon 的负载均衡器 LoadBalancerClient
去处理，从而达到了负载均衡的目的 。

####################################################################################################################################################################################################################

2.与Feign结合
