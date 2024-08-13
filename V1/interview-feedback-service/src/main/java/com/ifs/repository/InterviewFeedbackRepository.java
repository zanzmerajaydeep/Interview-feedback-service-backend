package com.ifs.repository;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ifs.entity.InterviewFeedback;

public interface InterviewFeedbackRepository extends MongoRepository<InterviewFeedback, String> {
	List<InterviewFeedback> findByCandidateInfoFullName(String fullName);
}
