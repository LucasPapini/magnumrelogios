package br.com.lucaspapini.services;

import br.com.lucaspapini.dto.VeiculoDTO;
import br.com.lucaspapini.model.VeiculoModel;
import br.com.lucaspapini.repository.VeiculoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class VeiculoService {
    @Inject
    VeiculoRepository veiculo;

    public List<VeiculoDTO> buscarVeiculoPorMarca(String marca) {
        List<VeiculoModel> veiculos = veiculo.encontraPorMarca(marca);

        List<VeiculoDTO> veiculoDTOS = new ArrayList<>();

        for (VeiculoModel veiculo : veiculos){
            VeiculoDTO dto = new VeiculoDTO();
            dto.setCodigo(veiculo.getCodigo());
            dto.setModelo(veiculo.getModelo());
            dto.setObservacao(veiculo.getObservacoes());
            veiculoDTOS.add(dto);
        }

        return veiculoDTOS;
    }

    public List<VeiculoDTO> buscarVeiculoPorCodigo(String codigo) {
        VeiculoModel veiculoPorCOdigo = veiculo.encontraPorCodico(codigo);

        List<VeiculoDTO> veiculoDTOS = new ArrayList<>();

        if(veiculoPorCOdigo != null){
            VeiculoDTO dto = new VeiculoDTO();
            dto.setCodigo(veiculoPorCOdigo.getCodigo());
            dto.setModelo(veiculoPorCOdigo.getModelo());
            dto.setMarca(veiculoPorCOdigo.getMarca());
            dto.setObservacao(veiculoPorCOdigo.getObservacoes());
            veiculoDTOS.add(dto);
        }

        return veiculoDTOS;
    }

    @Transactional
    public VeiculoModel atualizaVeiculo(String codigo, VeiculoDTO veiculoDTO){
        System.out.println("CODIGO DO VEICULO:: " + codigo);
        System.out.println("VEICULO DO VEICULO:: " + veiculoDTO.toString());
        veiculoDTO.setCodigo(codigo);
        VeiculoModel veiculoModel = veiculo.encontraPorCodico(veiculoDTO.getCodigo());

        if(veiculoModel != null){
            veiculoModel.setModelo(veiculoDTO.getModelo());
            veiculoModel.setObservacoes(veiculoDTO.getObservacao());

            veiculo.update(veiculoModel);

            return veiculoModel;
        } else {
            throw new WebApplicationException("Veículo não encontrado", Response.Status.NOT_FOUND);
        }
    }
}
