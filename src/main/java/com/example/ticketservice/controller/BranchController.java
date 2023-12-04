package com.example.ticketservice.controller;

import com.example.ticketservice.model.Branch;
import com.example.ticketservice.service.BranchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Rocket/branch")
public class BranchController {

    private final BranchService branchService;


    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Branch>> getAll() {
        return new ResponseEntity<>(branchService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Branch>> searchBranches(
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "type", required = false) String type) {

        if (city != null && type != null) {
            return new ResponseEntity<>(branchService.findBranchesByBranchTypeAndBranchName(type, city), HttpStatus.OK);
        } else if (city != null) {
            return new ResponseEntity<>(branchService.findBranchesByBranchName(city), HttpStatus.OK);
        } else if (type != null) {
            return new ResponseEntity<>(branchService.findBranchesByBranchType(type), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
