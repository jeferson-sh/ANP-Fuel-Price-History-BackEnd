package com.learning.fuelpricehistory.services;

import com.learning.fuelpricehistory.exceptions.DataNotFoundException;
import com.learning.fuelpricehistory.models.GenericModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericService<M extends GenericModel<Long>, R extends PagingAndSortingRepository<M, Long>> {

    @Autowired
    private R repository;

    /** Create */
    public M create(M model) {
        return this.repository.save(model);
    }

    /** Read by ID */
    public M findById(long id) {
        return this.repository.findById(id).orElseThrow(() -> new DataNotFoundException(id));
    }

    /**Read ALL */
    public Page<M> findAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    /** Update */
    public M update(Long id, M model) {
        M modelRetrieved = this.repository.findById(id)
            .orElseThrow(() -> new DataNotFoundException(id));
        model.setId(modelRetrieved.getId());
        modelRetrieved = model;
        return this.repository.save(modelRetrieved);

    }

    // DELETE
    public M deleteById(Long id) {
        M model = this.repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(id));
        this.repository.delete(model);
        return model;
    }

}