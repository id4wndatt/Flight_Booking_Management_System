package com.dnd.fbs.services;

import com.dnd.fbs.exception.AirlineCompanyNotFoundException;
import com.dnd.fbs.models.AirlineCompany;
import com.dnd.fbs.repositories.AirlineCompanyPagingAndSorting;
import com.dnd.fbs.repositories.AirlineCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AirlineCompanyServiceImpl implements AirlineCompanyService {
    public static final int AIRLINE_COMPANY_PER_PAGE=4;
    private AirlineCompanyRepository airlineCompanyRepository;
    private AirlineCompanyPagingAndSorting airlineCompanyPagingAndSorting;

    @Autowired
    public AirlineCompanyServiceImpl(AirlineCompanyRepository airlineCompanyRepository, AirlineCompanyPagingAndSorting airlineCompanyPagingAndSorting) {
        this.airlineCompanyRepository = airlineCompanyRepository;
        this.airlineCompanyPagingAndSorting = airlineCompanyPagingAndSorting;
    }

    @Override
    public Page<AirlineCompany> listByPage(int pageNum, String sortField, String sortDir, String keyword) {
        Sort sort =Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable =PageRequest.of(pageNum - 1, AIRLINE_COMPANY_PER_PAGE, sort);

        if (keyword != null) {
            return airlineCompanyPagingAndSorting.findAll(keyword, pageable);
        }
        return airlineCompanyPagingAndSorting.findAll(pageable);
    }

    @Override
    public List<AirlineCompany> findAll() {
        return airlineCompanyRepository.findAll();
    }

    @Override
    public AirlineCompany save(AirlineCompany airlineCompany) {
            return airlineCompanyRepository.save(airlineCompany);
    }

    @Override
    public Optional<AirlineCompany> findById(int id) {
            return airlineCompanyRepository.findById(id);
    }

    @Override
    public void deleteById(int id) throws AirlineCompanyNotFoundException {
            Long countById = airlineCompanyPagingAndSorting.countByAirlineID(id);
            airlineCompanyRepository.deleteById(id);
        if (countById == null || countById == 0) {
            throw new AirlineCompanyNotFoundException("Could not find the airline company ID " + id);
        }
    }

    @Override
    public AirlineCompany get(int id) throws AirlineCompanyNotFoundException {
        try {
            return airlineCompanyRepository.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new AirlineCompanyNotFoundException("Could not find the airline company ID " + id);
        }
    }
}
