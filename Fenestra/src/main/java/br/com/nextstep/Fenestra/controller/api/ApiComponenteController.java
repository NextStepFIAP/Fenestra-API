package br.com.nextstep.Fenestra.controller.api;

import java.net.URI;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.nextstep.Fenestra.model.Componente;
import br.com.nextstep.Fenestra.repositories.ComponenteRepository;

@RestController
@RequestMapping("/api/componente")
public class ApiComponenteController {

	@Autowired
	private ComponenteRepository repository;
	
	@GetMapping
	public Page<Componente> index(@RequestParam(required = false) String name, @PageableDefault(size = 5) Pageable pageable) {
		if(name == null){
			return repository.findAll(pageable);
				
		}
		return repository.findByNameLike("%" + name + "%", pageable);
	}

	@PostMapping
	public ResponseEntity<Componente> create(@RequestBody Componente componente, UriComponentsBuilder uriBuilder) {
		
		repository.save(componente);
		
		URI uri = uriBuilder.path("/api/user/1").buildAndExpand(componente.getId()).toUri();
		
		return ResponseEntity.created(uri).body(componente);
	}

	@GetMapping("{id}")
	public ResponseEntity<Componente> show(@PathVariable Long id){
		return ResponseEntity.of(repository.findById(id));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Componente> destroy(@PathVariable Long id){
		Optional<Componente> componente = repository.findById(id);
		
		if(componente.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		repository.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("{id}")
	public ResponseEntity<Componente> update(@PathVariable Long id, @RequestBody Componente newComponente){
		Optional<Componente> optional = repository.findById(id);
		
		if(optional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Componente componente = optional.get();
		componente.setName(newComponente.getName());
		
		repository.save(componente);
		
		return ResponseEntity.ok(componente);
		
	}

}
