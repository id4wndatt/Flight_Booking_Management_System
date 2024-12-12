package com.dnd.fbs.repositories;

import com.dnd.fbs.payload.*;

import java.util.List;

public interface StatisticsRepoCustom {
    List<TicketStatistics> statisticTicketByMonth();
    List<CostStatistics> statisticCostByMonth();
    List<TicketStatisticsByQuarter> statisticTicketByQuarter();
    List<CostStatisticsByQuarter> costStatisticsByQuarter();
    List<CountOrderOfQuantityTicket> statisticsTicketPerOrder();
    List<Integer> getUniqueYear();
    List<Integer> getNumberYearsFrom(int year, int numberOfYear);
}
