package fpu.si5p.produtos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fpu.si5p.produtos.entities.Item;
import fpu.si5p.produtos.repositories.ItemRepo;


@Controller
public class ItemControl 
{
	@Autowired
	private ItemRepo itemRepo;
	
	@RequestMapping(value = "/produto")
	public String listaMesasLivres(Item item, Model model) 
	{
		if(item.getId() != null)
			model.addAttribute("produto", itemRepo.findOne(item.getId()));
		else
			model.addAttribute("produto", new Item());
		
		model.addAttribute("produtos", itemRepo.findAll());
		return "Produtos";
	}
	
	@RequestMapping(value = "/produto/save")
	public String salvar(Item item) 
	{
		itemRepo.save(item);
		return "redirect:/produto";
	}
	
	@RequestMapping(value = "/produto/del")
	public String deletar(Item item) 
	{
		itemRepo.delete(item);
		return "redirect:/produto";
	}
}
