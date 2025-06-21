package com.cibertec.lp2_t2_AMBROCIO_RAMIREZ_ELIZABETH.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibertec.lp2_t2_AMBROCIO_RAMIREZ_ELIZABETH.repository.ClienteRepository;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepo;

    public ClienteController(ClienteRepository clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    @GetMapping("/lista")
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteRepo.findAll());
        return "clientes/lista"; // apunta a la vista lista.html
    }
}
