package com.conexus.api.bootstrap;

import com.conexus.api.domain.Professional;
import com.conexus.api.services.ProfessionalService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProfessionalService professionalService;

    public DataLoader(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }

    @Override
    public void run(String... args) throws Exception {
            loadData();
    }

    private void  loadData() {
        Professional professional = new Professional();
        professional.setName("Kar");
        professional.setCpf("5577864565");
        Professional savedProfessional = professionalService.save(professional);
    }
}
