package com.curso_spring;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.curso_spring.domain.Categoria;
import com.curso_spring.domain.Cidade;
import com.curso_spring.domain.Cliente;
import com.curso_spring.domain.Endereco;
import com.curso_spring.domain.Estado;
import com.curso_spring.domain.ItemPedido;
import com.curso_spring.domain.Pagamento;
import com.curso_spring.domain.PagamentoComBoleto;
import com.curso_spring.domain.PagamentoComCartao;
import com.curso_spring.domain.Pedido;
import com.curso_spring.domain.Produto;
import com.curso_spring.domain.enums.EstadoPagamento;
import com.curso_spring.domain.enums.TipoCliente;
import com.curso_spring.repositories.CategoriaRepository;
import com.curso_spring.repositories.CidadeRepository;
import com.curso_spring.repositories.ClienteRepository;
import com.curso_spring.repositories.EnderecoRepository;
import com.curso_spring.repositories.EstadoRepository;
import com.curso_spring.repositories.ItemPedidoRepository;
import com.curso_spring.repositories.PagamentoRepository;
import com.curso_spring.repositories.PedidoRepository;
import com.curso_spring.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoSpringApplication implements CommandLineRunner{
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository; 
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursoSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Ferramentas Manuais");
		Categoria cat5 = new Categoria(null, "Ferramentas eletricas");
		Categoria cat6 = new Categoria(null, "Eletronicos");
		Categoria cat7 = new Categoria(null, "Mecânicos");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		
		Estado est1 = new Estado(null, "Mato Grosso Do Sul");
		Estado est2 = new Estado(null, "Paraná");
		
		Cidade c1 = new Cidade(null, "Naviraí", est1);
		Cidade c2 = new Cidade(null, "Icaraima", est2);
		Cidade c3 = new Cidade(null, "Dourados", est1);
		
		est1.getCidades().addAll(Arrays.asList(c1, c3)); 
		est2.getCidades().addAll(Arrays.asList(c2));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Ezequiel", "ezequiel@gmail.com", "65775454775", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("989985985948", "7859758495702"));
		Endereco e1 = new Endereco(null, "Rua Brasil", "32", "casa","centro", "76565463", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua parana", "345", "casa", "centro", "67685674", cli1, c2);	
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/04/2020 10:00"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("03/05/2020 13:00"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/05/2020 12:43"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
	} 
	
		
	

}
