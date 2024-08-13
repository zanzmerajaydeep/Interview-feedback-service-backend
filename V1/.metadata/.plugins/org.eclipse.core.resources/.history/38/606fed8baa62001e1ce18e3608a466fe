package com.ifs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.ifs.dto.InterviewFeedbackDto;
import com.ifs.model.InterviewFeedback;

public interface InterviewFeedbackService {
	
	  ResponseEntity<InterviewFeedback> saveFeedback(InterviewFeedbackDto dto);

	    List<InterviewFeedback> getAllFeedback();
	    
	    List<InterviewFeedback> getAllFeedbackByPaginatedAndSorted(String sortOrder,  String fieldName,int PageNo);

	    Optional<InterviewFeedback> getFeedbackById(String id);

	    String deleteFeedback(String id);

	    ResponseEntity<?> updateFeedback(String id, InterviewFeedback updatedFeedback);
	    
	    List<InterviewFeedback> getFeedbackByCandidateEmail(String candidateEmailId);
	
		List<InterviewFeedback> saveFromExcel(MultipartFile file);
		
		List<InterviewFeedback> getFeedbackBySearch(String searchSkill);

}
