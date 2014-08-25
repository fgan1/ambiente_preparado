package model.impl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import model.contrato.Persistivel;

@MappedSuperclass // Significa que essa classe não terá representação no banco de dados, apenas suas filhas
public abstract class AbstractModel implements Persistivel{

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "data_criacao") //Para seguir a convenção
	private Date dataCriacao;	
	
	@Version //Para garantir a concorrencia. Conhecido como Locking Otimista
	private long versao;
	
	private String usuarioDono;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Date getDataCriacao() {
		return dataCriacao;
	}
	
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public long getVersao() {
		return versao;
	}

	public void setVersao(long versao) {
		this.versao = versao;
	}

	public String getUsuarioDono() {
		return usuarioDono;
	}

	public void setUsuarioDono(String usuarioDono) {
		this.usuarioDono = usuarioDono;
	}
			
	//public abstract String toString();
}