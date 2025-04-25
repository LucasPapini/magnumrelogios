package br.com.lucaspapini.client;

import br.com.lucaspapini.dto.MarcasDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("api/v1/carros/marcas")
@RegisterRestClient(configKey = "api")
public interface ApiClient {
    @GET
    List<MarcasDTO> getMarcas();
}
