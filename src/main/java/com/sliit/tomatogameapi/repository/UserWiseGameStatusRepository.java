package com.sliit.tomatogameapi.repository;

import com.sliit.tomatogameapi.entity.UserMst;
import com.sliit.tomatogameapi.entity.UserWiseGameStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWiseGameStatusRepository extends JpaRepository<UserWiseGameStatus,Long> {
    UserWiseGameStatus findByUserMst(UserMst userMst);
}
