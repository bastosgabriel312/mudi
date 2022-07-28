package br.com.alura.mvc.mudi.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	@Cacheable("pedido")
	Page<Pedido> findByStatus(@Param("status") StatusPedido status, Pageable page);

	@Cacheable("pedido")
	@Query("SELECT p FROM Pedido p JOIN p.user u WHERE p.status = :status AND u.username != :username")
	Page<Pedido> findByStatusUserDiff(@Param("username") String username, @Param("status") StatusPedido status, Pageable page);
	
	@Cacheable("pedido")
	@Query("SELECT p FROM Pedido p JOIN p.user u WHERE u.username = :username")
	Page<Pedido> findAllByUsuario(@Param("username") String username, Pageable page);
	
	@Cacheable("pedido")
	@Query("SELECT p FROM Pedido p JOIN p.user u WHERE u.username = :username AND p.status = :status")
	Page<Pedido> findByStatusAndUsuario(@Param("status") StatusPedido statusPedido, @Param("username") String username, Pageable page);

	@Query("SELECT p FROM Pedido p JOIN p.user u WHERE u.username = :username AND p.id = :id")
	Pedido findByIdAndUsuario(@Param("id") Long id, @Param("username") String username);
}
