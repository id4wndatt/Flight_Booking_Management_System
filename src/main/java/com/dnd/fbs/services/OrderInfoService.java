package com.dnd.fbs.services;

import com.dnd.fbs.payload.CostStatistics;
import com.dnd.fbs.payload.CostStatisticsByQuarter;
import com.dnd.fbs.payload.OrderInfoDto;

import java.util.List;

public interface OrderInfoService {

//    List<OrderInfoDto> findAll();
    List<CostStatistics> statisticsCostByMonth();
    List<CostStatisticsByQuarter> costStatisticsByQuarter();
}
