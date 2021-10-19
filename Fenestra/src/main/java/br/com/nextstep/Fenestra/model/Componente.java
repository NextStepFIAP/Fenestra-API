package br.com.nextstep.Fenestra.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_COMPONENTE")
@SequenceGenerator(name="componente", sequenceName = "SQ_TB_COMPONENTE", allocationSize = 1)
public class Componente {
	
	@Id @GeneratedValue(generator = "log", strategy = GenerationType.SEQUENCE)
	@Column(name = "CD_COMPONENTE", nullable = false)
	private Long id;
	
	@Column(name = "NM_COMPONENTE", nullable = false, length = 32)
	private String name;

	public Componente(String name) {
		this.name = name;
	}
}
