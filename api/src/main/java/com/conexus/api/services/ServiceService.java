package com.conexus.api.services;

import com.conexus.api.domain.Services;

import java.util.List;

public interface ServiceService extends CrudService<Services, Long> {

    List<Services> findAllByProfessionalId(Long professionalId);

    List<Services> findAllByClientId(Long clientId);

    Services updateByServiceId(Long id, Services service);

}
