package FootballApp.utility;

import FootballApp.entities.BaseEntity;
import FootballApp.entities.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DatabaseManager<T extends BaseEntity> implements ICRUD<T> {
	protected ArrayList<T> veriListesi = new ArrayList<>();
	
	@Override
	public Optional<T> save(T t) {
		if (veriListesi.add(t)) {
			return Optional.of(t);
		} else {
			return Optional.empty();
		}
	}
	
	@Override
	public List<T> saveAll(List<T> t) {
		if (veriListesi.addAll(t)) {
			return t;
		} else {
			return new ArrayList<>();
		}
	}
	
	@Override
	public Optional<T> update(T t) {
		int index = veriListesi.indexOf(t);
		if(index>=0){
			return Optional.of(veriListesi.set(index,t));
		}
		return Optional.empty();
	}
	
	@Override
	public Optional<List<T>> findAll() {
		if(veriListesi.isEmpty()){
			return Optional.empty();
		}
		else{
			return Optional.of(veriListesi);
		}
	}
	
	@Override
	public Optional<T> findByID(int id) {
		for (T entity : veriListesi ){
			if(entity.getId() == id){
				return Optional.of(entity);
			}
		}
		return Optional.empty();
	}
	
	public void delete(T t) {
		veriListesi.remove(t);
	}
	
}