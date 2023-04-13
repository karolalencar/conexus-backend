package com.conexus.api.services;

import com.conexus.api.domain.Services;

import java.util.List;

public interface ServiceService extends CrudService<Services, Long> {

    List<Services> findAllByProfessionalId(Long professional_id);

}
