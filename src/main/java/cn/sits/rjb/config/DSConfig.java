//package cn.sits.rjb.config;
//
//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import javax.sql.DataSource;
//
///**
// * 多数据源，集成druid
// *
// */
//@Configuration
//public class DSConfig {
//    @Bean(name = "primaryDataSource")
//    @Qualifier("primaryDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.primary")
//    @Primary
//    public DataSource primaryDataSource(){
//        return DruidDataSourceBuilder.create().build();
//        //return DataSourceBuilder.create().build();
//    }
//
////    @Bean(name = "secondaryDataSource")
////    @Qualifier("secondaryDataSource")
////    @ConfigurationProperties(prefix = "spring.datasource.secondary")//水雨情监测库
////    public DataSource secondaryDataSource(){
////        return DruidDataSourceBuilder.create().build();
////        //return DataSourceBuilder.create().build();
////    }
////    @Bean(name = "thirdDataSource")
////    @Qualifier("thirdDataSource")
////    @ConfigurationProperties(prefix = "spring.datasource.third") //内涝库
////    public DataSource thirdDataSource(){
////        return DruidDataSourceBuilder.create().build();
////        //return DataSourceBuilder.create().build();
////    }
//
//
//
//
//
//    @Bean(name = "primaryJdbcTemplate")
//    public  JdbcTemplate primaryJdbcTemplate(@Qualifier("primaryDataSource")DataSource dataSource){
//        return new JdbcTemplate(dataSource);
//    }
//
////    @Bean(name = "secondaryJdbcTemplate")
////    public JdbcTemplate secondaryJdbcTemplate(@Qualifier("secondaryDataSource")DataSource dataSource){
////        return new JdbcTemplate(dataSource);
////    }
////
////    @Bean(name = "thirdJdbcTemplate")
////    public JdbcTemplate thirddbcTemplate(@Qualifier("thirdDataSource")DataSource dataSource){
////        return new JdbcTemplate(dataSource);
////    }
//}
