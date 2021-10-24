package br.com.nextstep.Fenestra.model;


import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_LOG")
@SequenceGenerator(name="log_sq", sequenceName = "SQ_TB_LOG", allocationSize = 1)
public class Log {

	@Id @GeneratedValue(generator = "log_sq", strategy = GenerationType.SEQUENCE)
	@Column(name = "CD_REGISTRO", nullable = false)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_REGISTRO", nullable = false)	
	private Date dateRegistro;
	
	@Column(name = "DS_REGISTRO", nullable = false, length = 64)
	private String description;

	@ManyToOne
	@JoinColumn(name = "CD_COMPONENTE", nullable = false)
	@JsonBackReference
	private Componente componente;

	public Log(Long id) {
		this.id = id;
	}

}
