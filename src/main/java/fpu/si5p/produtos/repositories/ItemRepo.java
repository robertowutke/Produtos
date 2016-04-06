package fpu.si5p.produtos.repositories;

import org.springframework.data.repository.CrudRepository;

import fpu.si5p.produtos.entities.Item;


public interface ItemRepo extends CrudRepository<Item, Long> {}
