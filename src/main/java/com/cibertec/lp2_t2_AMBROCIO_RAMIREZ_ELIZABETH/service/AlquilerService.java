package com.cibertec.lp2_t2_AMBROCIO_RAMIREZ_ELIZABETH.service;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;



import com.cibertec.lp2_t2_AMBROCIO_RAMIREZ_ELIZABETH.model.Alquiler;
import com.cibertec.lp2_t2_AMBROCIO_RAMIREZ_ELIZABETH.model.DetalleAlquiler;
import com.cibertec.lp2_t2_AMBROCIO_RAMIREZ_ELIZABETH.model.DetalleAlquilerId;
import com.cibertec.lp2_t2_AMBROCIO_RAMIREZ_ELIZABETH.model.Pelicula;
import com.cibertec.lp2_t2_AMBROCIO_RAMIREZ_ELIZABETH.model.EstadoAlquiler;
import com.cibertec.lp2_t2_AMBROCIO_RAMIREZ_ELIZABETH.model.Cliente;

import com.cibertec.lp2_t2_AMBROCIO_RAMIREZ_ELIZABETH.repository.AlquilerRepository;
import com.cibertec.lp2_t2_AMBROCIO_RAMIREZ_ELIZABETH.repository.DetalleAlquilerRepository;
import com.cibertec.lp2_t2_AMBROCIO_RAMIREZ_ELIZABETH.repository.PeliculaRepository;
@Service
public class AlquilerService {

    private final AlquilerRepository alquilerRepo;
    private final DetalleAlquilerRepository detalleRepo;
    private final PeliculaRepository peliculaRepo;

    public AlquilerService(AlquilerRepository ar, DetalleAlquilerRepository dr, PeliculaRepository pr) {
        this.alquilerRepo = ar;
        this.detalleRepo = dr;
        this.peliculaRepo = pr;
    }

    public List<Pelicula> listarPeliculasConStock() {
        return peliculaRepo.findAll().stream()
            .filter(p -> p.getStock() > 0)
            .collect(Collectors.toList());
    }

    public Alquiler registrarAlquiler(Long clienteId, Long peliculaId, int cantidad) {
        Cliente cli = new Cliente();
        cli.setId(clienteId); // o fetch si quieres verificar existencia

        Pelicula peli = peliculaRepo.findById(peliculaId)
            .orElseThrow(() -> new IllegalArgumentException("Película no encontrada"));

        if (peli.getStock() < cantidad) throw new IllegalArgumentException("Stock insuficiente");

        Alquiler alq = new Alquiler();
        alq.setCliente(cli);
        alq.setFecha(LocalDate.now());
        alq.setEstado(EstadoAlquiler.ACTIVO);
        alq.setTotal(cantidad);
        alq = alquilerRepo.save(alq);

        DetalleAlquiler det = new DetalleAlquiler();
        det.setAlquiler(alq);
        det.setPelicula(peli);
        det.setCantidad(cantidad);
        det.setId(new DetalleAlquilerId(alq.getId(), peli.getIdPelicula()));
        detalleRepo.save(det);

        peli.setStock(peli.getStock() - cantidad);
        peliculaRepo.save(peli);

        return alq;
    }
    public List<Alquiler> obtenerTodosAlquileres() {
    return alquilerRepo.findAll();
}

public Alquiler obtenerAlquilerPorId(Long id) {
    return alquilerRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Alquiler no encontrado"));
}

}
