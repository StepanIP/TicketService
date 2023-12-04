package com.example.ticketservice.repository;

import com.example.ticketservice.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
    List<Branch> getBranchesByBranchName(String branchName);
    List<Branch> getBranchesByBranchType(String type);
    List<Branch> getBranchesByBranchTypeAndBranchName(String type, String branchName);
}
