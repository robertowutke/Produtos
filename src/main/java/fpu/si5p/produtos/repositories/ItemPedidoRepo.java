package fpu.si5p.produtos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import fpu.si5p.produtos.entities.ItemPedido;


public interface ItemPedidoRepo extends CrudRepository<ItemPedido, Long> 
{
	@Query("from ItemPedido i where estado = 1 order by i.pedido.id")
	public List<ItemPedido> findAllEstadoAberto();
	
	@Query("from ItemPedido i where i.pedido.estado = 1 order by i.pedido.id")
	public List<ItemPedido> findAllOrderPedido();	
	
	@Query("from ItemPedido i where i.pedido.id = ?1")
	public List<ItemPedido> findAllItensDoPedido(Long id);
	
	@Modifying
	@Transactional
	@Query("update ItemPedido set estado = 0 where id = ?1")
	public void setEstadoFinalizado(Long id);
	
	@Query("select count(i) from ItemPedido i where estado = 1 and i.pedido.id = ?1")
	public int contaItensPedidoEmAberto(Long id);
	
	@Query("select count(i) from ItemPedido i where i.pedido.id = ?1 and i.item.id = ?2")
	public int verificaItensPedido(Long idPedido, Long idItem);
	
	@Query("select i.id from ItemPedido i where i.pedido.id = ?1 and i.item.id = ?2")
	public Long retornaItemPedido(Long idPedido, Long idItem);
}
