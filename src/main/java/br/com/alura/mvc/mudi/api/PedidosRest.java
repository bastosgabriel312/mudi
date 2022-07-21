package br.com.alura.mvc.mudi.api;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.mudi.response.ResponseHandler;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosRest {
	@Autowired
	private PedidoRepository pedidoRepository;

	@GetMapping("{status}/{pagina}")
	public ResponseEntity<Object> getPedidosStatusDinamico(@PathVariable("status") String status,
			@PathVariable("pagina") String pagina, Principal principal) {
		try {
			Sort sort = Sort.by("id").descending();
			Integer paginaEscolhida = Integer.parseInt(pagina);
			PageRequest page = PageRequest.of(paginaEscolhida, 5, sort);
			Page<Pedido> pedidos = pedidoRepository.findByStatusAndUsuario(StatusPedido.valueOf(status.toUpperCase()),
					principal.getName().toUpperCase(), page);
			return ResponseHandler.generateResponse("Success", HttpStatus.OK, pedidos);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
	}

	@GetMapping("all/{pagina}")
	public ResponseEntity<Object> getPedidos(@PathVariable("pagina") String pagina, Principal principal) {
		try {
			Sort sort = Sort.by("id").descending();
			Integer paginaEscolhida = Integer.parseInt(pagina);
			PageRequest page = PageRequest.of(paginaEscolhida, 5, sort);
			Page<Pedido> pedidos = pedidoRepository.findAllByUsuario(principal.getName().toUpperCase(), page);
			return ResponseHandler.generateResponse("Success", HttpStatus.OK, pedidos);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
	}

}
