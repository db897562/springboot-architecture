package com.architecture.mybatisplus;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisPlusGenerator {

	public static void main(String[] args) throws SQLException {

		//1. 全局配置
		GlobalConfig config = new GlobalConfig();
		config.setActiveRecord(true) // 是否支持AR模式
				.setAuthor("dirk") // 作者
				//.setOutputDir("D:\\workspace_mp\\mp03\\src\\main\\java") // 生成路径
				.setOutputDir("H:\\GITHUB\\springboot-architecture\\src\\main\\java") // 生成路径
				.setFileOverride(true)  // 文件覆盖
				.setIdType(IdType.AUTO) // 主键策略
				.setServiceName("%sService")  // 设置生成的service接口的名字的首字母是否为I
				// IEmployeeService
				.setEnableCache(false) //生成二级缓存
				.setBaseResultMap(true)//生成基本的resultMap
				.setBaseColumnList(false);//生成基本的SQL片段

		//2. 数据源配置
		DataSourceConfig dsConfig = new DataSourceConfig();
		dsConfig.setDbType(DbType.MYSQL)  // 设置数据库类型
				.setDriverName("com.mysql.cj.jdbc.Driver")
				.setUrl("jdbc:mysql://192.168.179.128:3308/information_schema")
				.setUsername("root")
				.setPassword("123");

		//3. 策略配置globalConfiguration中
		StrategyConfig stConfig = new StrategyConfig();
		stConfig.setCapitalMode(true) //全局大写命名
				.setDbColumnUnderline(true)  // 指定表名 字段名是否使用下划线
				.setNaming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
				//.setTablePrefix("tbl_")
				.setInclude("CHARACTER_SETS");  // 生成的表

		//4. 包名策略配置
		PackageConfig pkConfig = new PackageConfig();
		pkConfig.setParent("com.architecture")
				.setMapper("mapper")//dao
				.setService("service")//servcie
				.setController("controller")//controller
				.setEntity("entity");
				//.setXml("/mapper");//mapper.xml

		//5. 整合配置
		AutoGenerator ag = new AutoGenerator();
		ag.setGlobalConfig(config)
				.setDataSource(dsConfig)
				.setStrategy(stConfig)
				.setPackageInfo(pkConfig);
		// 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
				this.setMap(map);
			}
		};
		List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
		focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return "H:\\GITHUB\\springboot-architecture\\src\\main\\resources\\mapper\\" + tableInfo.getEntityName() + "Mapper.xml";
			}
		});
		cfg.setFileOutConfigList(focList);
		ag.setCfg(cfg);
		// 关闭默认 xml 生成，调整生成 至 根目录
		TemplateConfig tc = new TemplateConfig();
		tc.setXml(null);
		ag.setTemplate(tc);
		//6. 执行
		ag.execute();
	}

}