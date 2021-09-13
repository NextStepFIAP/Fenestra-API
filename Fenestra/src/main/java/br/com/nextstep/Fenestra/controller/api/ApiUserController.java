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

import br.com.nextstep.Fenestra.model.User;
import br.com.nextstep.Fenestra.repositories.UserRepository;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {

	@Autowired
	private UserRepository repository;
	
	@GetMapping
	public Page<User> index(@RequestParam(required = false) String name, @PageableDefault(size = 5) Pageable pageable) {
		if(name == null){
			return repository.findAll(pageable);
				
		}
		return repository.findByNameLike("%" + name + "%", pageable);
	}
	
	@PostMapping
	public ResponseEntity<User> create(@RequestBody User user, UriComponentsBuilder uriBuilder) {
		
		repository.save(user);
		
		URI uri = uriBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri();
		
		return ResponseEntity.created(uri).body(user);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<User> show(@PathVariable Long id){
		return ResponseEntity.of(repository.findById(id));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<User> destroy(@PathVariable Long id){
		Optional<User> user = repository.findById(id);
		
		if(user.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		repository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User newUser){
		Optional<User> optional = repository.findById(id);
		
		if(optional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		User user = optional.get();
		user.setName(newUser.getName());
		user.setPassword(newUser.getPassword());
		
		repository.save(user);
		
		return ResponseEntity.ok(user);
		
	}

}
