package br.com.alura.mvc.mudi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.alura.mvc.mudi.dto.RequisicaoNovoUser;
import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.repository.UserRepository;

@Controller
@RequestMapping("cadastro")
public class CadastroController {

	@Autowired
	private UserRepository userRepository;
	
	
	@RequestMapping
	public ModelAndView cadastro() {
		ModelAndView mv = new ModelAndView("cadastro");
		return mv;
	}

	@PostMapping("/novo")
	public ModelAndView novoCadastro(@Valid RequisicaoNovoUser requisicao, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println(result);
			ModelAndView mv = new ModelAndView("cadastro");
			return mv;
		}
		User user = requisicao.toUser();
		System.out.println(user.toString());
		userRepository.save(user);
		ModelAndView mv = new ModelAndView("redirect:/login");
		return mv;
		
	}
}