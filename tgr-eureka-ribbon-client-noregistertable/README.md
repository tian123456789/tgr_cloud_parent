用来模拟负载均衡的注册列表缓存  关闭向erueka client获取注册列表的功能 
转而应用自己配置的服务实例url

@SuppressWarnings("all")
@SpringBootApplication
public class TgrEurekaRibbonClientNoRegisterTableApplication {

没有@EnableEurekaClient
没有
eureka: 
  client: 
    serviceUrl: 
      defaultZone: http://peer1:8761/eureka/ #服务注册地址
这样去注册eureka client来获取注册列表信息
而是
通过
####################模拟负载均衡注册列表缓存###################
stores:
  ribbon:
    listOfServers: baidu.com,google.com   #配置服务实例的URL
ribbon:
  eureka:
    enable: false   #禁止调用EUREKA CLIENT获取注册列表
server:
  port: 8766

####################模拟负载均衡注册列表缓存###################

这种方式实现了注册列表的存储 stores      

