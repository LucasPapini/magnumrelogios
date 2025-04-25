package br.com.lucaspapini.services;

import br.com.lucaspapini.dto.MarcaNomeDTO;
import br.com.lucaspapini.dto.MarcasDTO;
import br.com.lucaspapini.repository.VeiculoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MarcaService {
    @Inject
    VeiculoRepository veiculo;

    public List<String> pegaMarcasComDistinct(){
        try{
            List<String> marcas = veiculo.buscarMarcasComDistinct();
            return marcas;
        }catch (Exception e){
            System.out.println("Error: " + e);
            return null;
        }
    }
}
