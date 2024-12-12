package com.dnd.fbs.services;

import com.dnd.fbs.payload.CostStatistics;
import com.dnd.fbs.payload.CostStatisticsByQuarter;
import com.dnd.fbs.repositories.OrderInfoRepository;
import com.dnd.fbs.repositories.StatisticsRepoCustomImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderInfoServiceImpl implements OrderInfoService{
    private OrderInfoRepository orderInfoRepository;
    private StatisticsRepoCustomImpl srci;

    @Autowired
    public OrderInfoServiceImpl(OrderInfoRepository orderInfoRepository, StatisticsRepoCustomImpl srci) {
        this.orderInfoRepository = orderInfoRepository;
        this.srci = srci;
    }

//    @Override
//    public List<OrderInfoDto> findAll() {
//        List<OrderInfo> orderInfos = orderInfoRepository.findAll();
//        return orderInfos.stream().map(this::mapToDto).toList();
//    }

    @Override
    public List<CostStatistics> statisticsCostByMonth() {
        return srci.statisticCostByMonth();
    }

    @Override
    public List<CostStatisticsByQuarter> costStatisticsByQuarter() {
        return srci.costStatisticsByQuarter();
    }

//    private OrderInfoDto mapToDto(OrderInfo orderInfo) {
//        List<TicketDto> ticketDtoList = new ArrayList<>();
//
//        OrderInfoDto orderInfoDto = new OrderInfoDto();
//        orderInfoDto.setOrderId(orderInfo.getOrderId());
//        orderInfoDto.setDate(orderInfo.getDate());
//        orderInfoDto.setStatus(orderInfo.getStatus());
//        for (Ticket ticket:orderInfo.getTickets()) {
//            ticketDtoList.add(mapToDtoTicket(ticket));
//        }
//        orderInfoDto.setTickets(ticketDtoList);
//        return orderInfoDto;
//    }

//    private TicketDto mapToDtoTicket(Ticket ticket) {
//        TicketDto ticketDto = new TicketDto();
//        ticketDto.setTicketId(ticket.getTicketId());
//        ticketDto.setAirfares(ticket.getAirfares());
//        ticketDto.setLuggageId(ticket.getLuggageId());
//        ticketDto.setFlightId(ticket.getFlightId());
//        return ticketDto;
//    }
}
