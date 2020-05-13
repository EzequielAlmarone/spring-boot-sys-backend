package com.curso_spring.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso_spring.domain.Cliente;
import com.curso_spring.repositories.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id){
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new com.curso_spring.services.exceptions.ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ",  Endereço Classe: " + Cliente.class.getName())); 
	}
}
