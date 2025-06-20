package com.cibertec.lp2_t2_AMBROCIO_RAMIREZ_ELIZABETH.controller;

import com.cibertec.lp2_t2_AMBROCIO_RAMIREZ_ELIZABETH.model.Pelicula;
import com.cibertec.lp2_t2_AMBROCIO_RAMIREZ_ELIZABETH.service.PeliculaService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
@Controller
@RequestMapping("/peliculas")
public class PeliculaController {

    private final PeliculaService peliculaService;

    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    // Listar películas
    @GetMapping
    public String listarPeliculas(Model model) {
        model.addAttribute("peliculas", peliculaService.listarPeliculas());
        return "peliculas/listar"; // templates/peliculas/listar.html
    }

    // Mostrar formulario para crear película
    @GetMapping("/nuevo")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("pelicula", new Pelicula());
        return "peliculas/crear"; // templates/peliculas/crear.html
    }

    // Guardar nueva película
    @PostMapping
    public String guardarPelicula(@ModelAttribute Pelicula pelicula) {
        peliculaService.guardar(pelicula);
        return "redirect:/peliculas";
    }

// Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model) {
        Optional<Pelicula> optionalPelicula = peliculaService.obtenerPorId(id);
        if (optionalPelicula.isPresent()) {
            model.addAttribute("pelicula", optionalPelicula.get());
            return "peliculas/actualizar"; 
        } else {
            return "redirect:/peliculas"; // Si no se encuentra, redirige
        }

    }

    // Actualizar película
    @PostMapping("/actualizar")
    public String actualizarPelicula(@ModelAttribute Pelicula pelicula) {
        peliculaService.guardar(pelicula); // Usa el mismo método que guardar
        return "redirect:/peliculas";
    }

    // Eliminar película
    @GetMapping("/eliminar/{id}")
    public String eliminarPelicula(@PathVariable("id") Long id) {
        peliculaService.eliminar(id);
        return "redirect:/peliculas";
    }

    
}