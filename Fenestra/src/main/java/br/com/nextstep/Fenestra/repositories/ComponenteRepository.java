package br.com.nextstep.Fenestra.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nextstep.Fenestra.model.Componente;

public interface ComponenteRepository extends JpaRepository<Componente, Long>{

	Page<Componente> findByNameLike(String name, Pageable pageable);

}
