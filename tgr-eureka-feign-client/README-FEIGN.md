############################################################################################################################################
p124

Feign 是一个伪 Java Http 客户端 ， Feign 不做任何的请求处理。 Feign 通过处理注解生成
Request 模板，从而简化了 Http API 的开发。开发人员可以使用注解的方式定制 Request API
模板。在发送 HttpRequest 请求之前 ， Feign 通过处理注解的方式替换掉 Request 模板中的参数，
生成真正的 Request，并交给 Java Http 客户端去处理 。 利用这种方式，开发者只需要关注 Feign
注解模板的开发，而不用关注 Http 请求本身，简化了 Http 请求的过程，使得 Htφ 请求变得简
单和容易理解。
Feign 通过包扫描注入 FeignClient 的 Bean ，该源码在 FeignClientsRegistrar 类中 。首先在
程序启动时，会检查是否有@EnableFeignClients 注解，如果有该注解，则开启包扫描，扫描
被@FeignClient 注解的接口 。
############################################################################################################################################

总的来说 ， Feign 的源码实现过程如下。
( 1) 首先通过@EnableFeignClients 注解开启 FeignClient 的功能。只有这个注解存在，才
会在程序启动时开启对@FeignClient 注解的包扫描 。
(2 ）根据 Feign 的规则实现接口，井在接口上面加上@Feign Client 注解 。
(3 ）程序启动后，会进行包扫描，扫描所有的@Feign Client 的注解 的类 ，并将这些信息
注入 IoC 容器中。
( 4 ）当接口的方法被调用时 ， 通过 JDK 的代理来生成具体的 RequestTemplate 棋根对象 。
( 5 ）根据 RequestTemplate 再生成 Http 请求的 Request 对象 。
( 6 ) Request 对象交给 Client 去处理 ， 其中 Client 的网络请求框架可以是 HttpURLConnection 、
HttpClient 和 OkHttp 。
(7 ）最后 Client 被封装到 LoadBalanceClient 类，这个类结合类 Ribbon 做到了负载均衡 

############################################################################################################################################