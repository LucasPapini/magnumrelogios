package br.com.lucaspapini.repository;

import br.com.lucaspapini.dto.VeiculoDTO;
import br.com.lucaspapini.model.VeiculoModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;

import java.util.List;

@ApplicationScoped
public class VeiculoRepository implements PanacheRepository<VeiculoModel> {
    @Inject
    EntityManager em;

    public List<String> buscarMarcasComDistinct(){
        List<String> marcas = em.createNativeQuery("SELECT DISTINCT marca FROM veiculos").getResultList();
        return  marcas;
    }

    public VeiculoModel encontraPorCodico(String codigo){
        try{
            return (VeiculoModel) em.createNativeQuery("SELECT * FROM veiculos WHERE codigo = :codigo", VeiculoModel.class)
                    .setParameter("codigo", codigo)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public List<VeiculoModel>  encontraPorMarca(String marca){
        try{
            return  em.createNativeQuery("SELECT * FROM veiculos v WHERE v.marca=:marca", VeiculoModel.class)
                    .setParameter("marca", marca)
                    .getResultList();
        }catch (NoResultException e){
            return null;
        }
    }

    public void update(VeiculoModel veiculoModel){
        VeiculoModel veiculoModelExis = encontraPorCodico(veiculoModel.getCodigo());

        if(veiculoModelExis != null){
            veiculoModelExis.setMarca(veiculoModel.getMarca());
            veiculoModelExis.setObservacoes(veiculoModel.getObservacoes());

            em.merge(veiculoModelExis);
        } else {
            throw new EntityNotFoundException("Veículo não encontrado com o código: " + veiculoModel.getCodigo());
        }
    }
}
