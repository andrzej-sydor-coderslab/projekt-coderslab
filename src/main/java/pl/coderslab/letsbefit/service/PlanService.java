package pl.coderslab.letsbefit.service;

import pl.coderslab.letsbefit.entity.Plan;

import java.util.List;

public interface PlanService {

    List<Plan> getAllPlans();

    void add(Plan plan);

    Plan get(Long id);

    void remove(Long id);

    void update(Plan plan);

    Plan getPlanByUserLogin(String username);

    int plansQuantity(String username);



}
