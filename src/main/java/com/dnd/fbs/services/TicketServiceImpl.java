package com.dnd.fbs.services;

import com.dnd.fbs.models.Ticket;
import com.dnd.fbs.payload.*;
import com.dnd.fbs.repositories.StatisticsRepoCustomImpl;
import com.dnd.fbs.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService{
    private TicketRepository ticketRepository;
    private StatisticsRepoCustomImpl srci;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, StatisticsRepoCustomImpl srci) {
        this.ticketRepository = ticketRepository;
        this.srci = srci;
    }


    @Override
    public Page<Ticket> findAll(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        Page<Ticket> tickets = ticketRepository.findAll(pageable);
        return tickets;
    }

    @Override
    public List<TicketStatistics> countTicket() {
        return srci.statisticTicketByMonth();
    }

    @Override
    public List<TicketStatisticsByQuarter> countTicketByQuarter() {
        return srci.statisticTicketByQuarter();
    }

    @Override
    public List<CountOrderOfQuantityTicket> ticketPerOrder() {
        return srci.statisticsTicketPerOrder();
    }

    @Override
    public List<Integer> getUniqueYear() {
        return srci.getUniqueYear();
    }

    @Override
    public List<Integer> getNumberYearsFrom(int year, int numberOfYear) {
        return srci.getNumberYearsFrom(year, numberOfYear);
    }


//    private TicketDto mapToDto(Ticket ticket) {
//        TicketDto ticketDto = new TicketDto();
//        ticketDto.setTicketId(ticket.getTicketId());
//        ticketDto.setAirfares(ticket.getAirfares());
//        ticketDto.setLuggageId(ticket.getLuggageId());
//        ticketDto.setFlightId(ticket.getFlightId());
//        return ticketDto;
//    }
}
