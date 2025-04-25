package br.com.lucaspapini.controller;

import br.com.lucaspapini.dto.VeiculoDTO;
import br.com.lucaspapini.model.VeiculoModel;
import br.com.lucaspapini.services.VeiculoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/veiculos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VeiculoController {

    @Inject
    VeiculoService veiculoService;

    @GET
    @Path("/buscar/marca/{marca}")
    public Response buscaVeiculoPorMarca(@PathParam("marca") String marca){
        try{
            List<VeiculoDTO> veiculos = veiculoService.buscarVeiculoPorMarca(marca);
            return Response.ok(veiculos).build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error ao buscar veiculos: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/buscar/codigo/{codigo}")
    public Response buscaVeiculoPorCodigo(@PathParam("codigo") String codigo){
        try{
            List<VeiculoDTO> veiculos = veiculoService.buscarVeiculoPorCodigo(codigo);
            return Response.ok(veiculos).build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error ao buscar veiculos: " + e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/atualizar/veiculo/{codigo}")
    public Response atualizarVeiculo(@PathParam("codigo") String codigo, VeiculoDTO viVeiculoDTO){
        try{
            VeiculoModel veiculoAtualizado = veiculoService.atualizaVeiculo(codigo, viVeiculoDTO);
            return Response.ok(veiculoAtualizado).build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao realizar atualizacao do veiculo: " + e.getMessage())
                    .build();
        }
    }
}
