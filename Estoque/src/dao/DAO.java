package dao;

import java.util.List;

public interface DAO<T> {
	
	public List<T> select();
	public int insert(T r);
	public boolean update(T r);
	public boolean delete(int id);

}