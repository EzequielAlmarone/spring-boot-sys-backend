package com.curso_spring.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.curso_spring.domain.Categoria;
import com.curso_spring.repositories.CategoriaRepository;
import com.curso_spring.services.exceptions.DataIntegrityException;


@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id){
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new com.curso_spring.services.exceptions.ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ",  Endereço Classe: " + Categoria.class.getName())); 
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produto");
		}
		
		
	}
}
