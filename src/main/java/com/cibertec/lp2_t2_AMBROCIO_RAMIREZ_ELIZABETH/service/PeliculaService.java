package com.cibertec.lp2_t2_AMBROCIO_RAMIREZ_ELIZABETH.service;

import com.cibertec.lp2_t2_AMBROCIO_RAMIREZ_ELIZABETH.model.Pelicula;
import com.cibertec.lp2_t2_AMBROCIO_RAMIREZ_ELIZABETH.repository.PeliculaRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;

    public PeliculaService(PeliculaRepository peliculaRepository) {
        this.peliculaRepository = peliculaRepository;
    }

    public List<Pelicula> listarPeliculas() {
        return peliculaRepository.findAll();
    }

    public Pelicula guardar(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }


    public Optional<Pelicula> obtenerPorId(Long id) {
    return peliculaRepository.findById(id);
}

public void eliminar(Long id) {
    peliculaRepository.deleteById(id);
}
}
