package adj.felix.redis.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppContext {
	private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-redis.xml");
	private static final AppContext APP_CONTEXT = new AppContext();
	
	private AppContext() {
	}
	
	public static AppContext getInstance() {
		return APP_CONTEXT;
	}
	
	public <T> T getBean(String name, Class<T> clazz) {
		return context.getBean(name, clazz);
	}
}
