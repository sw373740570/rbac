package com.example.demo.shiro;

import com.example.demo.entity.SysResource;
import com.example.demo.service.ResourceService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service("myShiroService")
public class ShiroService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ShiroFilterFactoryBean shiroFilterFactoryBean;

    public Map<String, String> filterChainDefinitionMap() {
//拦截器.authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/logout", "logout");//配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/doLogin","anon");
        filterChainDefinitionMap.put("/css/**","anon");
        filterChainDefinitionMap.put("/js/**","anon");
        filterChainDefinitionMap.put("/images/**","anon");
        filterChainDefinitionMap.put("/font/**","anon");
        filterChainDefinitionMap.put("/assets/**","anon");
        filterChainDefinitionMap.put("/Widget/**","anon");
        //自定义加载权限资源关系
        List<SysResource> resourceList = resourceService.findAll();
        for (SysResource resource : resourceList) {
            if(StringUtils.isNotEmpty(resource.getPermission())) {
                String permission = "perms[" + resource.getPermission() + "]";
                filterChainDefinitionMap.put(resource.getUrl(),permission);
            }
        }

        //<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        filterChainDefinitionMap.put("/**", "authc");
        return filterChainDefinitionMap;
    }

    public synchronized void updatePermission() throws Exception {
        AbstractShiroFilter shiroFilter = (AbstractShiroFilter)shiroFilterFactoryBean.getObject();
        PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver)shiroFilter.getFilterChainResolver();
        DefaultFilterChainManager manager = (DefaultFilterChainManager)filterChainResolver.getFilterChainManager();
        manager.getFilterChains().clear();//清除旧的权限;
        shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap());
        // 重新构建生成
        Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
        for (Map.Entry<String, String> entry : chains.entrySet()) {
            String url = entry.getKey();
            String chainDefinition = entry.getValue().trim().replace(" ", "");
            manager.createChain(url, chainDefinition);
        }
        logger.info("成功更新权限");
    }
}
