package br.com.lucaspapini.consumer;

import br.com.lucaspapini.dto.MarcasDTO;
import br.com.lucaspapini.services.MarcaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;


@ApplicationScoped
public class KafkaConsumer {

    private static final Logger LOGGER = Logger.getLogger(KafkaConsumer.class);

    @Inject
    MarcaService marcaService;

    @Incoming("marcas-in")
    public void consume(String payload) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            MarcasDTO marca = mapper.readValue(payload, MarcasDTO.class);
            System.out.println("Marca recebida do Kafka: " + marca);
            marcaService.processarMarca(marca.getCodigo(), marca.getNome());
        } catch (Exception e) {
            LOGGER.error("Erro ao processar mensagem: " + payload, e);
        }
    }
}