package model.contrato;

import java.util.Date;

public interface Persistivel {

	public long getId();

	public void setId(long id);

	public Date getDataCriacao();

	public void setDataCriacao(Date date);

	public long getVersao();

	public void setVersao(long versao);
}