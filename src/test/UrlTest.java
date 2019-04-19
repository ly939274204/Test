package test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import entity.Longurl;
import service.UrlService;

public class UrlTest {
	
	@Test
	public void test1(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext(
				"spring/spring-dao.xml");
		UrlService urlService = beanFactory.getBean("urlService",
				UrlService.class);
		Longurl longurl = new Longurl();
		longurl.setLongurl("Http://www.douyu.com");
		String key = null;
		int row = urlService.ChangeUrl(longurl, key);
		System.out.println(row);
	}
	@Test
	public void test2(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext(
				"spring/spring-dao.xml");
		UrlService urlService = beanFactory.getBean("urlService",
				UrlService.class);
		String shorturl = "QRFNju";
		String longurl = urlService.getUrl(shorturl);
		System.out.println(longurl);
	}
	@Test
	public void test3(){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext(
				"spring/spring-dao.xml");
		UrlService urlService = beanFactory.getBean("urlService",
				UrlService.class);
		String longurl = "Http://www.baidu.com";
		List<String> shorturls = urlService.getallUrl(longurl);
		for (String shorturl : shorturls) {
			System.out.println(shorturl);
		}
		
	}
}
