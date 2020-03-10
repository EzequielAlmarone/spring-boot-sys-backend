package com.curso_spring.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso_spring.domain.Categoria;
import com.curso_spring.repositories.CategoriaRepository;


@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id){
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new com.curso_spring.services.exceptions.ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ",  Endereço Classe: " + Categoria.class.getName())); 
	}
}
