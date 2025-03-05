package com.eventmanagement.repository;

import com.eventmanagement.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Evento, Long> {
}