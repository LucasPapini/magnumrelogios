package br.com.lucaspapini.services;

import br.com.lucaspapini.client.ApiClient;
import br.com.lucaspapini.dto.MarcasDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import static br.com.lucaspapini.helpers.ConverteToJsonHelper.toJson;

import java.util.List;

@ApplicationScoped
public class ApiService {

    @RestClient
    ApiClient apiClient;

    @Inject
    KafkaEmitterService service;

    public List<MarcasDTO> buscarMarcas() {
        return apiClient.getMarcas();
    }

    public void buscarAsMarcasEnviaParaKafka(){
        List<MarcasDTO> marcas = apiClient.getMarcas();

        /**
         * Vou percorrer a lista que obtem a marca e manda para a fila...
         * */
        marcas.forEach(marca -> {
            System.out.println("Iteracao sobre o objeto iniciada");
            String json = toJson(marca);
            System.out.println("json da marca: " + json);
            service.enviarParaKafka(json);
            System.out.println("Enviou." + json.toString());
        });
    }
}
