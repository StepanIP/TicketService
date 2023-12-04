package com.example.ticketservice.service;

import com.example.ticketservice.model.Branch;
import com.example.ticketservice.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BranchService {

    Branch create(Branch user);
    Branch readById(long id);
    Branch update(Branch user);
    void delete(long id);
    List<Branch> getAll();
    List<Branch> findBranchesByBranchName(String branchName);
    List<Branch> findBranchesByBranchType(String type);
    List<Branch> findBranchesByBranchTypeAndBranchName(String type, String branchName);

}
