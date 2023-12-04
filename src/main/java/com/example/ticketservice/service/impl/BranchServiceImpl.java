package com.example.ticketservice.service.impl;

import com.example.ticketservice.exception.NullEntityReferenceException;
import com.example.ticketservice.model.Branch;
import com.example.ticketservice.repository.BranchRepository;
import com.example.ticketservice.service.BranchService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    public BranchServiceImpl(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public Branch create(Branch branch) {
        if (branch != null) {
            return branchRepository.save(branch);
        }
        throw new NullEntityReferenceException("Branch cannot be 'null'");
    }

    @Override
    public Branch readById(long id) {
        return branchRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Branch with id " + id + " not found"));
    }

    @Override
    public Branch update(Branch branch) {
        if (branch != null) {
            readById(branch.getId());
            return branchRepository.save(branch);
        }
        throw new NullEntityReferenceException("Branch cannot be 'null'");
    }

    @Override
    public void delete(long id) {
        branchRepository.delete(readById(id));
    }

    @Override
    public List<Branch> getAll() {
        List<Branch> branches = branchRepository.findAll();
        return branches.isEmpty() ? new ArrayList<>() : branches;
    }

    @Override
    public List<Branch> findBranchesByBranchName(String branchName) {
        return branchRepository.getBranchesByBranchName(branchName);
    }

    @Override
    public List<Branch> findBranchesByBranchType(String type) {
        return branchRepository.getBranchesByBranchType(type);
    }

    @Override
    public List<Branch> findBranchesByBranchTypeAndBranchName(String type, String branchName) {
        return branchRepository.getBranchesByBranchTypeAndBranchName(type, branchName);
    }

}
