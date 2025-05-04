package org.example.salaryincrement.Service;

import jakarta.transaction.Transactional;
import org.example.salaryincrement.DTO.CreateCareerRequest;
import org.example.salaryincrement.Exceptions.UserNotFoundException;
import org.example.salaryincrement.Model.Career;

import org.example.salaryincrement.Model.User;
import org.example.salaryincrement.Repository.CareerRepo;
import org.example.salaryincrement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CareerService {

    private final CareerRepo careerRepository;

    private final UserRepository userRepository;

    @Autowired
    public CareerService(CareerRepo careerRepository, UserRepository userRepository) {
        this.careerRepository = careerRepository;
        this.userRepository = userRepository;
    }

    public Career createCareer(CreateCareerRequest request) {
        if (request.getCareerStartDate() == null) {
            throw new IllegalArgumentException("Career start date cannot be null");
        }

        if (request.getUserId() == null) {
            throw new IllegalArgumentException("User ID must be provided for the career");
        }

        User user = userRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + request.getUserId()));

        Career career = new Career();
        career.setUser(user);
        career.setCareerStartDate(request.getCareerStartDate());

        return careerRepository.save(career);
    }

    public List<Career> getAllCareers() {
        return careerRepository.findAll();
    }

    public Optional<Career> getCareerById(Long careerId) {
        return careerRepository.findById(careerId);
    }

    @Transactional
    public Career updateCareer(Long careerId, Career updatedCareer) {
        return careerRepository.findById(careerId)
                .map(career -> {
                    career.setCareerStartDate(updatedCareer.getCareerStartDate());
                    career.setCareerName(updatedCareer.getCareerName());
                    // Add other fields to update if needed
                    return careerRepository.save(career);
                })
                .orElseThrow(() -> new RuntimeException("Career not found with id: " + careerId));
    }


    @Transactional
    public void deleteCareer(Long careerId) {
        if (!careerRepository.existsById(careerId)) {
            throw new RuntimeException("Career not found with id: " + careerId);
        }
        careerRepository.deleteById(careerId);
    }

    public List<Career> getCareersByUserId(Long userId) {
        return careerRepository.findByUserUserId(userId);
    }

    public List<Career> getCareersByStartDate(LocalDate startDate) {
        return careerRepository.findByCareerStartDate(startDate);
    }
}