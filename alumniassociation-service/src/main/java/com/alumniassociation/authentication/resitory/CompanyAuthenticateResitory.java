package com.alumniassociation.authentication.resitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alumniassociation.authentication.entity.CompanyAuthenticate;

public interface CompanyAuthenticateResitory extends JpaRepository<CompanyAuthenticate, Integer> {

}
