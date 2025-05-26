package com.app.bankrypt.controller;

import com.app.bankrypt.model.Test;
import com.app.bankrypt.repository.TestRepository;
import com.app.bankrypt.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private TestRepository Trepo;
    private TestService Tserv;

    @GetMapping("/all")
    public List<Test> getAll() {
        return Tserv.getAllTestValues();
    }

    @PostMapping("/create")
    public Test create(@RequestBody String val) {
        return Tserv.createTestValue(val);
    }

}
