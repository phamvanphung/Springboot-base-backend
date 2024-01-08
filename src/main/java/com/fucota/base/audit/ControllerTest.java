package com.fucota.base.audit;

import com.fucota.base.audit.entity.ModelTest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTest {
    @Autowired
    private ModelTestRepository modelTestRepository;


    @Operation(summary = "Test Audit")
    @PostMapping("/test")
    public ModelTest create() {
        ModelTest test = new ModelTest();
        test.setName("model 1");
        test.setModel("12");
        return modelTestRepository.save(test);

    }
}
