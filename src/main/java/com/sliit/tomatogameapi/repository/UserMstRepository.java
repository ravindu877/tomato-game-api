package com.sliit.tomatogameapi.repository;

import com.sliit.tomatogameapi.entity.UserMst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMstRepository extends JpaRepository<UserMst,Long> {
    Boolean existsByUsername(String username);
    UserMst findByUsername(String userName);
}
