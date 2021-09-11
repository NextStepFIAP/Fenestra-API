package br.com.nextstep.Fenestra.repositories;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nextstep.Fenestra.model.Log;

public interface LogRepository extends JpaRepository<Log, Long>{

	Page<Log> findByDateRegistro(Date date, Pageable pageable);

}
