package com.nycloud.gateway.config;

import com.nycloud.gateway.properties.PermitAllUrlProperties;
import com.nycloud.gateway.util.CustomRemoteTokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author: super.wu
 * @description: 利用资源服务器的配置，
 *               控制哪些是暴露端点不需要进行身份合法性的校验，
 *               直接路由转发，哪些是需要进行身份loadAuthentication，调用auth服务。
 **/
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private ResourceServerProperties resource;

    @Autowired
    private PermitAllUrlProperties permitAllUrlProperties;

    /**
     * 配置permitAll的请求pattern 依赖于permitAllUrlProperties对象
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers(permitAllUrlProperties.getPermitallPatterns()).permitAll()
                .anyRequest().authenticated();
    }

    /**
     * 通过自定义的CustomRemoteTokenServices 植入身份合法相关验证
     * @param resources
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        CustomRemoteTokenServices resourceServerTokenServices = new CustomRemoteTokenServices();
        resourceServerTokenServices.setCheckTokenEndpointUrl(resource.getTokenInfoUri());
        resourceServerTokenServices.setClientId(resource.getClientId());
        resourceServerTokenServices.setClientSecret(resource.getClientSecret());
        resourceServerTokenServices.setLoadBalancerClient(loadBalancerClient);
        resources.tokenServices(resourceServerTokenServices);
    }

}
