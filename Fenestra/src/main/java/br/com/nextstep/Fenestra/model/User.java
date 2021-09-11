package br.com.nextstep.Fenestra.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Data
@Entity(name = "TB_USUARIO")
@SequenceGenerator(name="user", sequenceName = "SQ_TB_USUARIO", allocationSize = 1)
public class User {

	@Id @GeneratedValue(generator = "user", strategy = GenerationType.SEQUENCE)
	@Column(name = "CD_USUARIO", nullable = false)
	private Long id;
	
	@Column(name = "NM_USUARIO", nullable = false, length = 64)
	private String name;
	
	@Column(name = "NM_SENHA", nullable = false, length = 20)
	private String password;

}
