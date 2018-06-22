# ny-cloud( 开源项目 )

ny-cloud 是基于Spring Cloud微服务开发开源权限管理系统，可用作于前期微服务权限系统搭建，定义统一权限管理，权限资源初始化，采用Spring Security + OAuth2.0，权限设计参照RABC模型，支持多个服务并行开发， 集成了网关(gateway)，注册中心(erueka)，认证服务(auth)，权限服务(admin)，模块介绍如下：<br>
eureka-server ->  服务注册中心<br>
api-gateway -> 服务网关<br>
auth-server -> 服务认证授权中心<br>
admin ->  权限管理服务<br>
此项目采用前后端分离的开发模式，前端为ny-vue项目，具体介绍，请移动步该项目<br>
项目访问地址： http://www.stars21.cn 线上项目目前是使用docker容器在运行，仅供演示。<br>
账号密码： guest 123456<br>
技术交流群： 807233785<br>
此项目前端使用ny-vue项目<br>
项目启动顺序<br>
依次是 eureka-server(8760)，auth-serve(8761)，admin(8762)，api-gateway(7000)<br>
以上项目全部正常启动后 再启动前端ny-vue 项目即可<br>
