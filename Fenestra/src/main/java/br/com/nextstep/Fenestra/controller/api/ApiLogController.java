package br.com.nextstep.Fenestra.controller.api;

import java.net.URI;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.nextstep.Fenestra.model.Log;
import br.com.nextstep.Fenestra.repositories.LogRepository;

@RestController
@RequestMapping("/api/log")
public class ApiLogController {

	@Autowired
	private LogRepository repository;
	
	
	@GetMapping
	public Page<Log> index(@RequestParam(required = false) Date date, @PageableDefault(size = 5) Pageable pageable) {
		if(date == null){
			return repository.findAll(pageable);
				
		}
		return repository.findByDateRegistro(date, pageable);
	}

	@PostMapping
	public ResponseEntity<Log> create(@RequestBody Log log, UriComponentsBuilder uriBuilder) {
		
		repository.save(log);
		
		URI uri = uriBuilder.path("/api/log/{id}").buildAndExpand(log.getId()).toUri();
		
		return ResponseEntity.created(uri).body(log);
	}

	@GetMapping("{id}")
	public ResponseEntity<Log> show(@PathVariable Long id){
		return ResponseEntity.of(repository.findById(id));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Log> destroy(@PathVariable Long id){
		Optional<Log> log = repository.findById(id);
		
		if(log.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		repository.deleteById(id);
		return ResponseEntity.ok().build();
	}

	//Log não permite edições

}
