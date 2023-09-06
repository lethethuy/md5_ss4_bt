package ra.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    // tìm kiếm theo tên người dùng
    Page<User> findAllByFullNameContains(String name, Pageable pageable);
}
