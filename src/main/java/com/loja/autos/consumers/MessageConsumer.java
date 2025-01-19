package com.loja.autos.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;

@Service
public class MessageConsumer implements ChannelAwareMessageListener {

    
	@Override
    @RabbitListener(queues = "myQueue", ackMode = "MANUAL")
    public void onMessage(org.springframework.amqp.core.Message message, Channel channel) throws Exception {
        String messageBody = new String(message.getBody());

        try {
            // Simular envio de e-mail
        	notificaAlgumaAplicação(messageBody);
            
            // Confirmação manual da mensagem no RabbitMQ
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            System.out.println("Mensagem processada com sucesso e confirmada: " + messageBody);
        } catch (Exception e) {
            // Log do erro
            System.err.println("Erro ao processar mensagem: " + messageBody);
            
            // NACK (não confirmar) a mensagem, com requeue = true para reenviar à fila
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }

    public void notificaAlgumaAplicação(String message) throws Exception {
        // Simulação de envio de e-mail
    	
    	if(false) {
    		throw new Exception("Simulação de erro no envio de e-mail");
    	}
        
    	System.out.println("=================================================================");
    	System.out.println("Simulando envio de e-mail com o conteúdo da mensagem: " + message);
    	System.out.println("=================================================================");
        // Lógica para realmente enviar um e-mail pode ser colocada aqui
        // Integrar com um serviço de email como SendGrid, Amazon SES, etc.
    }

}



/**
 * aqui abaixo é o modelo simples sem confirmação se deu certo ou nao o envio do email
 */
//@Service
//public class MessageConsumer {
//
//    @RabbitListener(queues = "myQueue")
//    public void receiveMessage(String message) {
//        // Simular envio de e-mail
//			notificaAlgumaAplicação(message);
//    }
//
//    public void notificaAlgumaAplicação(String message) throws Exception {
//        // Simulação de envio de e-mail
//    	
//    	System.out.println("=================================================================");
//    	System.out.println("Simulando envio de e-mail com o conteúdo da mensagem: " + message);
//    	System.out.println("=================================================================");
//        // Lógica para realmente enviar um e-mail pode ser colocada aqui
//        // Integrar com um serviço de email como SendGrid, Amazon SES, etc.
//    }
//}
