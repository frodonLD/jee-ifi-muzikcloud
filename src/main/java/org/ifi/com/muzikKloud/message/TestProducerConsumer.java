package org.ifi.com.muzikKloud.message;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestProducerConsumer {
	public static void main(final String[] args) {
		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext(
				new String[] { "spring-jms.xml" });
//		System.out.println("envoi du message");
//		JmsProducer jmsProducer = (JmsProducer) appContext
//				.getBean("jmsProducer");
//		jmsProducer.envoyerMessage("Test");

		while(true){
			System.out.println("reception du message");
			JmsConsumer jmsConsumer = (JmsConsumer) appContext
					.getBean("jmsConsumer");
			jmsConsumer.recevoirMessage();
			
		}
	}
}