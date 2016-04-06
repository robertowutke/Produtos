package fpu.si5p.produtos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import fpu.si5p.produtos.entities.Pedido;

public interface PedidoRepo extends CrudRepository<Pedido, Long> 
{
	@Query("from Pedido where estado = 1")
	public List<Pedido> findAllPedidosAbertos();
	
	@Modifying
	@Transactional
	@Query("update Pedido p set p.estado = 0 where p.id = ?1")
	public void setPedidoFinalizado(Long id);
}
