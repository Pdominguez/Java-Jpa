package com.bolsadeideas.springboot.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.app.models.dao.IClienteDao;
import com.bolsadeideas.springboot.app.models.dao.services.IClienteService;
import com.bolsadeideas.springboot.app.models.entity.Cliente;

import jakarta.validation.Valid;

@SessionAttributes("cliente")
@Controller
public class ClienteController {
	
	//agregando comentarios a la clase ClienteController
	//Agregando segundo comentario  a la clase ClienteController
	
	@Autowired
    @Qualifier("clienteService")
	private IClienteService clienteService;
	@RequestMapping(value="/listar",method = RequestMethod.GET)
	public String listar(Model model) {
		
		model.addAttribute("titulo", "Listado de Clientes");
		model.addAttribute("clientes", clienteService.findAll());
		return "listar";
	}
	
	
	@RequestMapping(value="/form")
	public String crear(Map<String,Object> model) {
		
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de clientes");
		return "form";
		
	}
	
	  @GetMapping("/form/{id}")
	  public String editar(@PathVariable(value="id") Long id,Map<String, Object> model) {
			
		  Cliente cliente = null;
		  if(id >0) {
			cliente =  clienteService.findOne(id);
		  }else {
			  
			 return "redirct:listar";
		  }
		  model.put("cliente", cliente);
		  model.put("titulo", "Formulario de clientes");
	
			return "form";
		}
	  
	
	@RequestMapping(value="/form",method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente,BindingResult result,Model model,SessionStatus status) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de clientes");
			return "form";
		}
		clienteService.save(cliente);
		status.setComplete();
		return "redirect:listar";
	}
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		  
		 if(id >0) {
			 clienteService.delete(id);
	      }
		
		 return "redirect:/listar";
		
	}

}
