package com.dnd.fbs.services;

import com.dnd.fbs.models.Ticket;
import com.dnd.fbs.payload.CountOrderOfQuantityTicket;
import com.dnd.fbs.payload.TicketStatistics;
import com.dnd.fbs.payload.TicketStatisticsByQuarter;
import com.dnd.fbs.payload.UniqueYear;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TicketService {
    Page<Ticket> findAll(int pageNo, int pageSize, String sortBy, String sortDir);
    List<TicketStatistics> countTicket();
    List<TicketStatisticsByQuarter> countTicketByQuarter();
    List<CountOrderOfQuantityTicket> ticketPerOrder();
    List<Integer> getUniqueYear();
    List<Integer> getNumberYearsFrom(int year, int numberOfYear);
}
