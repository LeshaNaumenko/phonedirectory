package com.course.phonedirectory.rep;

import com.course.phonedirectory.model.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Integer> {

    @Transactional
    @Modifying
    @Query(value = "update PHONE_NUMBER p set p.COMPANY_NAME  = ?, p.NUMBER = ? where p.id = ?", nativeQuery=true)
    int updateNumberById(String companyName, String number, int id);
}
