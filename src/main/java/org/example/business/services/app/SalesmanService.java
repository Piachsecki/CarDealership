package org.example.business.services.app;

import lombok.AllArgsConstructor;
import org.example.business.dao.SalesmanDAO;
import org.example.infrastructure.database.entity.SalesmanEntity;

import java.util.Optional;

@AllArgsConstructor
public class SalesmanService {
    private final SalesmanDAO salesmanDAO;
    public SalesmanEntity findSalesman(String pesel) {
        Optional<SalesmanEntity> salesman = salesmanDAO.findSalesmanByPesel(pesel);
        if(salesman.isEmpty()){
            throw new RuntimeException("The salesman doesnt exist!");
        }
        return salesman.get();
    }
}
