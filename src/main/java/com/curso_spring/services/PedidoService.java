package com.curso_spring.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso_spring.domain.Pedido;
import com.curso_spring.repositories.PedidoRepository;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id){
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new com.curso_spring.services.exceptions.ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ",  Endereço Classe: " + Pedido.class.getName())); 
	}
}
