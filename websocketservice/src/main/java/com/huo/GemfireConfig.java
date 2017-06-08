package com.huo;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.gemfire.client.ClientRegionFactoryBean;

/**
 * @author 霍闯【2597】
 *         company qianmi.com
 *         Date 2017-06-08
 */
@Configuration
@ImportResource("gemfire-context.xml")
public class GemfireConfig {

}
