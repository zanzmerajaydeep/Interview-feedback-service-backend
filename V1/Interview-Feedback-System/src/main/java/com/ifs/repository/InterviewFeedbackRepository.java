package com.ifs.repository;


import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.ifs.dto.InterviewCountByMonth;
import com.ifs.model.InterviewFeedback;

public interface InterviewFeedbackRepository extends MongoRepository<InterviewFeedback, String> {
	
	 List<InterviewFeedback> findByCandidateCandidateEmailId(String emailId);
	 
	 List<InterviewFeedback> findByTechnicalEvaluationSkillRegex(String skillRegex);
	 
//	    @Query(value = "{}", fields = "{ 'dateOfInterview' : 1}")
//	    List<InterviewFeedback> findAllWithDateOfInterview();
//		List<InterviewCountByMonth> countInterviewsByMonth(MongoTemplate mongoTemplate);


	 long count();
	 


}
