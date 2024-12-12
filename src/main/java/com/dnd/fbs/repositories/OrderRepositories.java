package com.dnd.fbs.repositories;

import com.dnd.fbs.models.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositories extends JpaRepository<OrderInfo,Integer> {
    public OrderInfo getOrderInfoByQrCode(String qrCode);
}
