package br.com.nextstep.Fenestra.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_USUARIO")
@Table(name = "TB_USUARIO")
@SequenceGenerator(name="user", sequenceName = "SQ_TB_USUARIO", allocationSize = 1)
public class User {

	@Id @GeneratedValue(generator = "user", strategy = GenerationType.SEQUENCE)
	@Column(name = "CD_USUARIO", nullable = false)
	private Long id;
	
	@Column(name = "NM_USUARIO", nullable = false, length = 64)
	private String name;
	
	@Column(name = "DS_EMAIL", nullable = false, length = 128, unique = true)
	private String email;
	
	@Column(name = "NM_SENHA", nullable = false, length = 20)
	private String password;


	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Componente> componentes;

	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public User(Long id) {
		this.id = id;
	}
}
