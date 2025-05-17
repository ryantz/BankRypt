package com.app.bankrypt.service;

import com.app.bankrypt.model.Test;
import com.app.bankrypt.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestService {

    @Autowired
    private TestRepository TRepo;

    public List<Test> getAllTestValues() {
        return TRepo.findAll();
    }

    public Test createTestValue(String val) {
        Test newTest = new Test();
        newTest.setValue(val);

        return TRepo.save(newTest);
    }
}
