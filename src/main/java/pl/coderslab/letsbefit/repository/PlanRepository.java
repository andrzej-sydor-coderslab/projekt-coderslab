package pl.coderslab.letsbefit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.letsbefit.entity.Plan;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

    Plan findFirstByStartDate(String startDate);

    @Query("Select p from Plan p")
    List<Plan> findAll();

    @Query("SELECT p FROM User u JOIN Plan p on u.id = p.id WHERE u.login = ?1")
    Plan getPlanByUserLogin(String username);

    @Query(value = "SELECT COUNT(DISTINCT u.id) FROM plans p JOIN users u on p.user_id = u.id WHERE u.login = ?1",nativeQuery = true)
    int plansQuantity(String username);

}

