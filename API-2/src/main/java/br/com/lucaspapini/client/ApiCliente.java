package br.com.lucaspapini.client;
import br.com.lucaspapini.dto.ModelosDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("api/v1/carros/marcas")
@RegisterRestClient(configKey = "api")
public interface ApiCliente {
    @GET
    @Path("/{codigo}/modelos")
    ModelosDTO getModelos(@PathParam("codigo") String codigo);
}
