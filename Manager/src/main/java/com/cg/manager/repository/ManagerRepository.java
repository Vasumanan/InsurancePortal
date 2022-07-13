package com.cg.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.manager.entity.Manager;

/**
 * This ManagerRepository is used to hold manager details.
 * 
 * @author Vasumanan J
 *
 */
@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {
	public Manager findByManagerId(int managerId);
}
