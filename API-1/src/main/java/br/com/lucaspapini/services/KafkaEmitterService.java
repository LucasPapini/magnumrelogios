package br.com.lucaspapini.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class KafkaEmitterService {

    @Inject
    @Channel("marcas-out")
    Emitter<String> emitter;

    public void enviarParaKafka(String json){
        System.out.println("Enviado para o Kafka enfileirar..." + json);
        emitter.send(json).whenComplete((result, throwable) -> {
            if(throwable != null){
                System.out.println("Ocorreu um erro ao enviar mensagem para o Kafka" + throwable.getMessage());
            }else{
                System.out.println("Mensagens enviadas com sucesso para o Kafka! Graca a DEUSSSSS");
            }
        });
    }
}
