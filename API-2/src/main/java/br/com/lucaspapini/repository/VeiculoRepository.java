package br.com.lucaspapini.repository;

import br.com.lucaspapini.model.VeiculoModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VeiculoRepository implements PanacheRepository<VeiculoModel> {
}
