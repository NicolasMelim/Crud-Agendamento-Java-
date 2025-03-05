package com.eventmanagement.service;

import com.eventmanagement.model.Evento;
import com.eventmanagement.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Evento> getAllEventos() {
        return eventRepository.findAll();
    }

    public Evento criarEvento(Evento evento) {
        return eventRepository.save(evento);
    }

    public Optional<Evento> getEventoById(Long id) {
        return eventRepository.findById(id);
    }

    public Evento atualizarEvento(Long id, Evento eventoDetalhes) {
        Evento evento = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        evento.setNome(eventoDetalhes.getNome());
        evento.setData(eventoDetalhes.getData());
        evento.setHora(eventoDetalhes.getHora());
        evento.setDescricao(eventoDetalhes.getDescricao());

        return eventRepository.save(evento);
    }

    public void deletarEvento(Long id) {
        Evento evento = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        eventRepository.delete(evento);
    }
}