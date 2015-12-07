package com.ai.paas.ipaas.ats.consumer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 消息服务方法签名标注<br/>
 * 只有指定此标注的方法，才会通过消息通道向外提供
 * 
 * @Title: ServiceProviderSignature.java 
 * @author wusheng
 * @date 2015年3月30日 下午2:21:28 
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceProviderSignature {
	/**
	 * 指定服务签名
	 * 
	 * @return
	 */
	String id() default "";
	
	/**
	 * 指定服务签名的属性
	 * 
	 * @return
	 */
	String idFromAttribute() default "";
}
