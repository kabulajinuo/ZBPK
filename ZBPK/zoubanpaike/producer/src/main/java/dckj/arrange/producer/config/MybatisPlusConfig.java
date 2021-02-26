package dckj.arrange.producer.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname MybatisPlusConfig
 * @Description MybatisPlus配置类
 * @Date 2019/8/8 19:05
 * @Created by JinPeng
 * @Version 1.0
 */
@Configuration
@MapperScan("dckj.arrange.producer.mapper")
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
