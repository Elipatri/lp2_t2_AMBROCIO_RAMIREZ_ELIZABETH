package com.cibertec.lp2_t2_AMBROCIO_RAMIREZ_ELIZABETH.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cibertec.lp2_t2_AMBROCIO_RAMIREZ_ELIZABETH.model.Alquiler;
import com.cibertec.lp2_t2_AMBROCIO_RAMIREZ_ELIZABETH.model.Cliente;
import com.cibertec.lp2_t2_AMBROCIO_RAMIREZ_ELIZABETH.repository.ClienteRepository;
import com.cibertec.lp2_t2_AMBROCIO_RAMIREZ_ELIZABETH.service.AlquilerService;

@Controller
@RequestMapping("/alquileres")
public class AlquilerController {

    private final AlquilerService alquilerService;
    private final ClienteRepository clienteRepo;

    public AlquilerController(AlquilerService alquilerService,
                              ClienteRepository clienteRepo) {
        this.alquilerService = alquilerService;
        this.clienteRepo = clienteRepo;
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioAlquiler(Model model) {
        model.addAttribute("peliculas", alquilerService.listarPeliculasConStock());
        model.addAttribute("cantidad", 1);
        return "alquileres/formulario";
    }
    @GetMapping("/lista")
public String listarAlquileres(Model model) {
    model.addAttribute("alquileres", alquilerService.obtenerTodosAlquileres());
    return "alquileres/lista"; // este es el archivo HTML que mostrarás
}


    @PostMapping("/procesar")
    public String procesarAlquiler(@RequestParam String nombre,
                                   @RequestParam String email,
                                   @RequestParam Long peliculaId,
                                   @RequestParam int cantidad,
                                   ModelMap model) {
        try {
            // Crear cliente nuevo y guardarlo
            Cliente cliente = new Cliente();
            cliente.setNombre(nombre);
            cliente.setEmail(email);
            cliente = clienteRepo.save(cliente);

            // Registrar alquiler con el cliente recién creado
            Alquiler alq = alquilerService.registrarAlquiler(cliente.getId(), peliculaId, cantidad);
            model.put("mensaje", "Alquiler registrado, total: " + alq.getTotal());
        } catch (Exception e) {
            model.put("error", "Error al registrar alquiler: " + e.getMessage());
        }
        // Volver a mostrar formulario con lista de películas
        model.put("peliculas", alquilerService.listarPeliculasConStock());
        model.put("cantidad", 1);
        return "alquileres/formulario";
    }
    @GetMapping("/detalle")
public String verDetalle(@RequestParam Long id, Model model) {
    Alquiler alquiler = alquilerService.obtenerAlquilerPorId(id);
    model.addAttribute("alquiler", alquiler);
    model.addAttribute("detalles", alquiler.getDetalles());
    return "alquileres/detalle";
}

}
