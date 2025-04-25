package br.com.lucaspapini.services;

import br.com.lucaspapini.client.ApiCliente;
import br.com.lucaspapini.dto.MarcasDTO;
import br.com.lucaspapini.dto.ModelosDTO;
import br.com.lucaspapini.model.VeiculoModel;
import br.com.lucaspapini.repository.VeiculoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import java.util.List;

@ApplicationScoped
public class MarcaService {
    private static final Logger LOGGER = Logger.getLogger(MarcaService.class);

    @Inject
    @RestClient
    ApiCliente apiCliente;

    @Inject
    VeiculoRepository veiculoRepository;

    @Transactional
    public void processarMarca(String marca, String nome) {
        LOGGER.info("Processando marca: " + marca);
        try{
            ModelosDTO response = apiCliente.getModelos(marca);
            List<MarcasDTO> modelos = response.getModelos();

            System.out.println("Iniciando a insercao no bando de dados...");

            for(MarcasDTO modelo: modelos){
                VeiculoModel veiculo = new VeiculoModel();
                veiculo.setMarca(nome);
                veiculo.setModelo(modelo.getNome());
                veiculo.setCodigo(modelo.getCodigo());

                System.out.println("Realizando o insert" + " Marca: " + marca + " Modelo: " + modelo.getNome() + " Codigo: " + modelo.getCodigo());

                veiculoRepository.persist(veiculo);
            }
            LOGGER.info("Modelos da marca " + marca + " Salvo com sucesso.");
        }catch ( Exception e){
            LOGGER.error("Error ao processar a marca: " + marca);
            System.out.println("OLHA O ERROR::" + e);
        }
    }
}
