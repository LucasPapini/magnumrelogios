package br.com.lucaspapini.controller;

import br.com.lucaspapini.dto.MarcaNomeDTO;
import br.com.lucaspapini.dto.MarcasDTO;
import br.com.lucaspapini.services.ApiService;
import br.com.lucaspapini.services.MarcaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/marcas")
public class MarcasController {
    @Inject
    ApiService service;

    @Inject
    MarcaService marcaService;

    @GET
    @Path("/debug/marcas")
    public List<MarcasDTO> buscarMarcasDebbug(){
        List<MarcasDTO> marcas = service.buscarMarcas();
        if (marcas != null) {
            marcas.forEach(marcasJson -> {
                System.out.println("Obtendo as marcas: " + marcasJson);
            });
        } else {
            System.out.println("A lista de marcas veio nula.");
        }
        return marcas;
    }

    /**
     * Aqui eu faco o envio para o Kafka quando dou o request
     * @return
     */
    @GET
    @Path("enviar-marcas")
    public Response enviarMarcarParaFila(){
        service.buscarAsMarcasEnviaParaKafka();
        return Response.ok("Marcas envidas para a fila com sucesso").build();
    }

    @GET
    @Path("lista-marcas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMarcas(){
        List<String> marcas = marcaService.pegaMarcasComDistinct();
        return Response.ok(marcas).build();
    }
}
