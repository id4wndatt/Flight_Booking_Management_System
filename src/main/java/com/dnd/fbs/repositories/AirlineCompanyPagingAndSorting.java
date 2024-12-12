package com.dnd.fbs.repositories;

import com.dnd.fbs.models.AirlineCompany;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AirlineCompanyPagingAndSorting extends PagingAndSortingRepository<AirlineCompany, Integer> {
    @Query("SELECT a from AirlineCompany a WHERE concat(a.airlineID, ' ', a.airlineName) like %?1%")
    public Page<AirlineCompany> findAll(String keyword, Pageable pageable);

    public Long countByAirlineID(Integer id);
}
