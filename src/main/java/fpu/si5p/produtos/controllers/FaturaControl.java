package fpu.si5p.produtos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fpu.si5p.produtos.entities.Pedido;
import fpu.si5p.produtos.repositories.ItemPedidoRepo;
import fpu.si5p.produtos.repositories.PedidoRepo;

@Controller
public class FaturaControl 
{	
	@Autowired
	private ItemPedidoRepo itemPedidoRepo;
	
	@Autowired
	private PedidoRepo pRepo;
	
	@RequestMapping(value = "/fatura")
	public String listaEditaPedido(Pedido pedido, Model model)
	{
		model.addAttribute("iPedidos", itemPedidoRepo.findAllItensDoPedido(pedido.getId()));
		model.addAttribute("pedido", pRepo.findOne(pedido.getId()));
		return "Fatura";
	}
}
