package gui;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UseClient {

	public static void main(String[] args) {
		//ChatClient가 has a 관계로 보유하고 있는 객체들을 직접 new 하지 말고,
		//스프링의 ApplicationContext를 이용하여 인스턴스들을 주입(=Injection)하자!
		//memo43
		ClassPathXmlApplicationContext context = null;
		
		context = new ClassPathXmlApplicationContext("spring/config/gui-context.xml");
		
		ChatClient chatClient = (ChatClient)context.getBean("chatClient");
		
		chatClient.init();
	}
}
