package com.example.projectwebbackend.service;

import com.example.projectwebbackend.entity.Coach;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CoachService {
    List<Coach> getAllCoach();

    Coach addCoachById(Long id, Coach coach);
    Coach addCoach(Coach coach, String token);

    void deleteCoach(Long id);

    void saveCoach(Coach coach);

    Optional<Coach> findCoachById(Long id);

    List<Coach> findCoachByToken(String token);

    List<Coach> getCoachByAdinToken(String token);


}
