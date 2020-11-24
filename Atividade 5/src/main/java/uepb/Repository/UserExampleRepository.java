package uepb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uepb.Model.UserExample;

public interface UserExampleRepository extends JpaRepository<UserExample, Long> {
    UserExample findByUsername(String username); // select u from UserExample where u.username = ?1
}