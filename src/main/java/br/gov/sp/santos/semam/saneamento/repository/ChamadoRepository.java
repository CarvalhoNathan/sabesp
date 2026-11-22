package br.gov.sp.santos.semam.saneamento.repository;

import br.gov.sp.santos.semam.saneamento.model.Chamado;
import br.gov.sp.santos.semam.saneamento.model.StatusChamado;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ChamadoRepository extends JpaRepository<Chamado, Long>, JpaSpecificationExecutor<Chamado> {
    Optional<Chamado> findByProtocolo(String protocolo);

    long countByStatus(StatusChamado status);
}
