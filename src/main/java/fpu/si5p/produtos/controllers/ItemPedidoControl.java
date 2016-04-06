package fpu.si5p.produtos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fpu.si5p.produtos.entities.Item;
import fpu.si5p.produtos.entities.ItemPedido;
import fpu.si5p.produtos.entities.Pedido;
import fpu.si5p.produtos.repositories.ItemPedidoRepo;
import fpu.si5p.produtos.repositories.ItemRepo;
import fpu.si5p.produtos.repositories.PedidoRepo;

@Controller
public class ItemPedidoControl 
{
	@Autowired
	private ItemPedidoRepo itemPedidoRepo;
	
	@Autowired
	private ItemRepo itemRepo;
	
	@Autowired
	private PedidoRepo pedidoRepo;
	
	@RequestMapping(value = "/itemPedido")
	public String listaEditaPedido(Pedido p, Model model)
	{		
		if(p.getId() != null)
		{
			model.addAttribute("pedido", pedidoRepo.findOne(p.getId()));
		}else
			model.addAttribute("pedido", new Pedido());
		
		model.addAttribute("pedidos", pedidoRepo.findAll());
		model.addAttribute("itens", itemRepo.findAll());
		model.addAttribute("iPedidos", itemPedidoRepo.findAllOrderPedido());
		return "ItemPedido";
	}
	
	@RequestMapping(value = "/itemPedido2")
	public String listaIPedido(Pedido p, Model model)
	{		
		if(p.getId() != null)
		{
			model.addAttribute("pedido", pedidoRepo.findOne(p.getId()));
		}else
			model.addAttribute("pedido", new Pedido());
		
		model.addAttribute("pedidos", pedidoRepo.findAll());
		model.addAttribute("itens", itemRepo.findAll());
		model.addAttribute("iPedidos", itemPedidoRepo.findAllOrderPedido());
		return "ItemPedido2";
	}
	
	@RequestMapping(value = "/itemPedido/finalizar")
	public String finalizaItemPedido(ItemPedido itemPedido, Model model)
	{
		itemPedidoRepo.setEstadoFinalizado(itemPedido.getId());
		return "redirect:/cozinha";
	}
	
	@RequestMapping(value = "/itemPedido/save")
	public String addItemPedido(@RequestParam("qtdItem") int qtdItem, @RequestParam("item") Long i,
			@RequestParam("pedido") Long p, Model model)
	{
		Pedido pedido = pedidoRepo.findOne(p);
		Item item = itemRepo.findOne(i);
		if(item.getQtd() < qtdItem)
		{
			return "redirect:/itemPedido?error";
		}else{
			double valor = qtdItem * item.getPrecoItem();
			item.setQtd(item.getQtd() - qtdItem);
			itemPedidoRepo.save(new ItemPedido(pedido, item, qtdItem, valor));
			pedido.setValor(pedido.getValor()+valor);
			pedidoRepo.save(pedido);
		}			
		return "redirect:/pedido";
	}
	
	@RequestMapping(value = "/itemPedido/del")
	public String deletaItemPedido(ItemPedido iP)
	{
		iP = itemPedidoRepo.findOne(iP.getId());
		Pedido pedido = pedidoRepo.findOne(iP.getPedido().getId());
		pedido.setValor(pedido.getValor()-iP.getValor());
		pedidoRepo.save(pedido);
		itemPedidoRepo.delete(iP);
		
		return "redirect:/itemPedido2";
	}
	
	@RequestMapping(value = "/pedido/delete")
	public String deletaPedido(Pedido p)
	{
		List<ItemPedido> iP = itemPedidoRepo.findAllItensDoPedido(p.getId());
		Item item;
		
		for(int i = 0; i < iP.size(); i++)
		{
			item = itemRepo.findOne(iP.get(i).getItem().getId());
			item.setQtd(item.getQtd() + iP.get(i).getQtdItem());
			itemRepo.save(item);
			itemPedidoRepo.delete(iP.get(i));
		}		
		pedidoRepo.delete(p);
		
		return "redirect:/itemPedido2";
	}
}
