package com.dnd.fbs.services;

import com.dnd.fbs.models.OrderInfo;
import com.dnd.fbs.models.Ticket;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class DataMapper {
    public Context setData(OrderInfo order, ArrayList<Ticket> tickets){
        Context context = new Context();
        Map<String, Object> data = new HashMap<>();
        data.put("order",order);
        data.put("tickets",tickets);
        context.setVariables(data);
        return context;
    }
}
