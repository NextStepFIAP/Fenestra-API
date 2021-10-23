package br.com.nextstep.Fenestra.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_COMPONENTE")
@SequenceGenerator(name="componente_sq", sequenceName = "SQ_TB_COMPONENTE", allocationSize = 1)
public class Componente {
	
	@Id @GeneratedValue(generator = "componente_sq", strategy = GenerationType.SEQUENCE)
	@Column(name = "CD_COMPONENTE", nullable = false)
	private Long id;
	
	@Column(name = "NM_COMPONENTE", nullable = false, length = 32)
	private String name;

	@ManyToOne
	@JoinColumn(name = "CD_USUARIO", nullable = false)
	@JsonBackReference
	private User user;

	@OneToMany(mappedBy = "componente", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Log> logs;
}
