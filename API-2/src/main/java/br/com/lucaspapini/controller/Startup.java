package br.com.lucaspapini.controller;

import br.com.lucaspapini.services.MarcaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/test")
public class Startup {
    @Inject
    MarcaService marcaService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }

    /**
     * Só um testizinho pra pegar os dados...
     * @param marca
     * @return
     */
    /*@GET
    @Path("teste/processar/{marca}")
    public Response processarMarca(@PathParam("marca") String marca) {
        System.out.println("Chegamos aqui..." +  marca);
        marcaService.processarMarca(marca, marca);
        return Response.ok("Marca processada: " + marca).build();
    }*/
}
