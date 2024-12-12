package com.dnd.fbs.repositories;

import com.dnd.fbs.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepositories extends JpaRepository<Ticket,Integer> {

}
