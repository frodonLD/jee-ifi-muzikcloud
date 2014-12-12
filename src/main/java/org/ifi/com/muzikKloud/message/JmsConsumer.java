package org.ifi.com.muzikKloud.message;

import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;

public class JmsConsumer {

	private JmsTemplate jmsTemplate;

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void recevoirMessage() {
		Message msg = jmsTemplate.receive();
		try {
			TextMessage textMessage = (TextMessage) msg;
			if (msg != null) {
				System.err.println("Message = " + textMessage.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setJmsTemplate(final JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
}
