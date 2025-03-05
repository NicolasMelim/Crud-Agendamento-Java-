package com.eventmanagement.controller;

import com.eventmanagement.DTO.EventoDTO;
import com.eventmanagement.model.Evento;
import com.eventmanagement.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/eventos")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {
        RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS
})
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public List<Evento> getAllEventos() {
        return eventService.getAllEventos();
    }

    @PostMapping
    public ResponseEntity<Evento> criarEvento(@RequestBody EventoDTO eventoDTO) {
        Evento evento = converterDTOParaEvento(eventoDTO);
        Evento novoEvento = eventService.criarEvento(evento);
        return ResponseEntity.ok(novoEvento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Long id) {
        return eventService.getEventoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> atualizarEvento(
            @PathVariable Long id,
            @RequestBody EventoDTO eventoDTO
    ) {
        Evento evento = converterDTOParaEvento(eventoDTO);
        Evento updatedEvento = eventService.atualizarEvento(id, evento);
        return ResponseEntity.ok(updatedEvento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEvento(@PathVariable Long id) {
        eventService.deletarEvento(id);
        return ResponseEntity.ok().build();
    }

    private Evento converterDTOParaEvento(EventoDTO eventoDTO) {
        Evento evento = new Evento();
        evento.setNome(eventoDTO.getNome());
        evento.setData(LocalDate.parse(eventoDTO.getData()));
        evento.setHora(LocalTime.parse(eventoDTO.getHora()));
        evento.setDescricao(eventoDTO.getDescricao());
        return evento;
    }
}