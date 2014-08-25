package dao.contrato;

import java.util.List;

import model.contrato.Persistivel;

public interface IDao<T extends Persistivel> {

	public void save(T obj) throws Exception;

	public T update(T obj) throws Exception;

	public void delete(T obj);

	public T get(long id);

	public List<T> getAll();
}