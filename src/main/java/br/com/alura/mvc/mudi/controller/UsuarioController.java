package br.com.alura.mvc.mudi.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("usuario")
public class UsuarioController {
	@Autowired
	private PedidoRepository pedidoRepository;

	@GetMapping("pedido")
	public ModelAndView home(Principal principal) {
		ModelAndView mv = new ModelAndView("usuario/home");
		return mv;
	}

	@GetMapping("pedido/{status}")
	public ModelAndView pedidoPorStatus(@PathVariable("status") String status, Principal principal) {
		ModelAndView mv = new ModelAndView("usuario/home");
		return mv;
	}
	
	@GetMapping("pedido/detalhes/{idPedido}")
	public ModelAndView ofertasDoPedido(@PathVariable("idPedido") String idPedido, Principal principal) {
		ModelAndView mv = new ModelAndView("usuario/detalhesPedido");
		return mv;
	}
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/usuario/home";
	}
}
