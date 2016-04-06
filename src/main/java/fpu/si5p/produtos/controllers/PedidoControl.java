package fpu.si5p.produtos.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fpu.si5p.produtos.entities.Pedido;
import fpu.si5p.produtos.repositories.ItemPedidoRepo;
import fpu.si5p.produtos.repositories.ItemRepo;
import fpu.si5p.produtos.repositories.PedidoRepo;

@Controller
public class PedidoControl 
{
	@Autowired
	private PedidoRepo pedidoRepo;
	
	@Autowired
	private ItemPedidoRepo itemPedidoRepo;
	
	@Autowired
	private ItemRepo itemRepo;
	
	@RequestMapping(value = "/pedido")
	public String realizaPedido(Model model)
	{
		model.addAttribute("iPedidos", itemPedidoRepo.findAllOrderPedido());
		model.addAttribute("itens", itemRepo.findAll());
		model.addAttribute("pedidos", pedidoRepo.findAllPedidosAbertos());
		return "Pedidos";
	}
	
	@RequestMapping(value = "/pedido/save")
	public String salvar()
	{
		Pedido pedido = new Pedido(new Date(), 0.0, 1);
		pedidoRepo.save(pedido);
		return "redirect:/pedido";
	}
	
	@RequestMapping(value = "/pedido/del")
	public String deletar(Pedido pedido) 
	{
		pedidoRepo.delete(pedido);
		return "redirect:/pedido";
	}
	
	@RequestMapping(value = "/pedido/finalizar")
	public String finalizaPedido(Pedido p)
	{		
		p = pedidoRepo.findOne(p.getId());
		pedidoRepo.setPedidoFinalizado(p.getId());
		return "redirect:/fatura?id="+p.getId();
	}
}
