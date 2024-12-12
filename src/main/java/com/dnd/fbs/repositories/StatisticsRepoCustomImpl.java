package com.dnd.fbs.repositories;

import com.dnd.fbs.payload.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StatisticsRepoCustomImpl implements StatisticsRepoCustom{

    private EntityManager em;

    @Autowired
    public StatisticsRepoCustomImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<TicketStatistics> statisticTicketByMonth() {
        String sql =
                "select count(t.ticketID) as ticketCount, month(o.date) as month, year(o.date) as year " +
                "from OrderInfo o join Ticket t on o.orderID = t.order.orderID " +
                "group by month, year " +
                "order by year(o.date), month(o.date) ";
        Query query = em.createQuery(sql);
        List<Object> result = query.getResultList();
        if (result == null || result.size() == 0) {
            throw new RuntimeException("uwu");
        }
        List<TicketStatistics> ListTS = new ArrayList<>();
        for (Object o : result) {
            Object[] rs = (Object[]) o;
            TicketStatistics ticketStatistics;
            ticketStatistics = new TicketStatistics();
            ticketStatistics.setTicketCount((long) rs[0]);
            ticketStatistics.setMonth((int) rs[1]);
            ticketStatistics.setYear((int) rs[2]);
            ListTS.add(ticketStatistics);
        }
        return ListTS;
    }



    @Override
    public List<CostStatistics> statisticCostByMonth() {
        String sql =
                "select sum(oi.total_cost), month(oi.date), year(oi.date) " +
                        "from OrderInfo oi " +
                        "group by month(oi.date), year(oi.date) " +
                        "order by year(oi.date), month(oi.date)";
        Query query = em.createQuery(sql);
        List<Object> result = query.getResultList();
        if (result == null || result.size() == 0) {
            throw new RuntimeException("uwu");
        }
        List<CostStatistics> ListCS = new ArrayList<>();
        for (Object o : result) {
            Object[] rs = (Object[]) o;
            CostStatistics costStatistics;
            costStatistics = new CostStatistics();
            costStatistics.setTicketCost((long) rs[0]);
            costStatistics.setMonth((int) rs[1]);
            costStatistics.setYear((int) rs[2]);
            ListCS.add(costStatistics);
        }
        return ListCS;
    }

    @Override
    public List<TicketStatisticsByQuarter> statisticTicketByQuarter() {
        String sql =
                "select quarter(o.date), year(o.date), count(t.order.orderID) " +
                "from Ticket t join OrderInfo o on t.order.orderID = o.orderID " +
                "group by quarter(o.date), year(o.date) " +
                "order by year(o.date), quarter(o.date)";
        Query query = em.createQuery(sql);
        List<Object> result = query.getResultList();
        List<TicketStatisticsByQuarter> ListSBQ = new ArrayList<>();
        for (Object o : result) {
            Object[] rs = (Object[]) o;
            TicketStatisticsByQuarter ticketStatisticsByQuarter;
            ticketStatisticsByQuarter = new TicketStatisticsByQuarter();
            ticketStatisticsByQuarter.setQuarter((int) rs[0]);
            ticketStatisticsByQuarter.setYear((int) rs[1]);
            ticketStatisticsByQuarter.setTicketCount((long) rs[2]);
            ListSBQ.add(ticketStatisticsByQuarter);
        }
        return ListSBQ;
    }

    @Override
    public List<CostStatisticsByQuarter> costStatisticsByQuarter() {
        String sql =
                "select quarter(o.date), year(o.date), sum(o.total_cost) " +
                        "from OrderInfo o " +
                        "group by quarter(o.date), year(o.date) " +
                        "order by year(o.date), quarter(o.date)";
        Query query = em.createQuery(sql);
        List<Object> result = query.getResultList();
        List<CostStatisticsByQuarter> ListSBQ = new ArrayList<>();
        for (Object o : result) {
            Object[] rs = (Object[]) o;
            CostStatisticsByQuarter costStatisticsByQuarter;
            costStatisticsByQuarter = new CostStatisticsByQuarter();
            costStatisticsByQuarter.setQuarter((int) rs[0]);
            costStatisticsByQuarter.setYear((int) rs[1]);
            costStatisticsByQuarter.setTicketCost((long) rs[2]);
            ListSBQ.add(costStatisticsByQuarter);
        }
        return ListSBQ;
    }

    @Override
    public List<CountOrderOfQuantityTicket> statisticsTicketPerOrder() {
        String sql =
                "select luong.tt, count(luong.too) " +
                "from " +
                        "(select count(t.ticketID) as tt, t.order.orderID as too " +
                        "from OrderInfo o join Ticket t on o.orderID = t.order.orderID " +
                        "group by t.order.orderID) " +
                        "as luong " +
                "group by luong.tt";
        Query query = em.createQuery(sql);
        List<Object> result = query.getResultList();
        if (result == null || result.size() == 0) {
            throw new RuntimeException("uwu");
        }
        List<CountOrderOfQuantityTicket> ListTPO = new ArrayList<>();
        for(Object o : result) {
            Object[] rs = (Object[]) o;
            CountOrderOfQuantityTicket countOrderOfQuantityTicket;
            countOrderOfQuantityTicket = new CountOrderOfQuantityTicket();
            countOrderOfQuantityTicket.setQuantityTicketPerOrder((long) rs[0]);
            countOrderOfQuantityTicket.setQuantitySameNumberOfTicketPerOrder((long) rs[1]);
            ListTPO.add(countOrderOfQuantityTicket);
        }
        return ListTPO;
    }

    @Override
    public List<Integer> getUniqueYear() {
        String sql =
                "select distinct year(o.date) " +
                "from OrderInfo o " +
                "order by year(o.date) asc";
        Query query = em.createQuery(sql);
        List<Integer> result = query.getResultList();
        if (result == null || result.size() == 0) {
            throw new RuntimeException("UwU");
        }
        return new ArrayList<>(result);
    }

    @Override
    public List<Integer> getNumberYearsFrom(int year, int numberOfYear) {
//        String sql =
//                "select distinct year(o.date) " +
//                "from Ticket t join OrderInfo o on t.order.orderId = o.orderId " +
//                "where year(o.date)  >= :year " +
//                "order by year(o.date) asc";
        String sql =
                "select distinct year(o.date) " +
                "from OrderInfo o " +
                "where year(o.date)  >= :year " +
                "order by year(o.date) asc";
        Query query = em.createQuery(sql);
        query.setParameter("year", year);
        query.setMaxResults(numberOfYear);
        List<Integer> result = query.getResultList();
        return new ArrayList<>(result);
    }


}
