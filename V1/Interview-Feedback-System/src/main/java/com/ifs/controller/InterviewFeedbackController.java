package com.ifs.controller;


import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.ifs.dto.InterviewFeedbackDto;
import com.ifs.model.InterviewFeedback;
import com.ifs.serviceImp.InterviewFeedbackServiceImp;

import ch.qos.logback.classic.Logger;
import jakarta.validation.Valid;

@RestController
@CrossOrigin("http://localhost:3000")
public class InterviewFeedbackController {

	Logger logger = (Logger) LoggerFactory.getLogger(InterviewFeedbackController.class);

	static String validationmsg = "Validation errors : ";

	@Autowired
	private InterviewFeedbackServiceImp serviceImp;

	@PostMapping("/insert")
	public ResponseEntity<?> addInterviewFeedbackDetails(@Valid @RequestBody InterviewFeedbackDto dto,
			BindingResult bindingResult) {

		// Validate request
		if (bindingResult.hasErrors()) {
			StringBuilder errorMessage = new StringBuilder();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMessage.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ");
			}
			return ResponseEntity.badRequest().body(validationmsg + "" + errorMessage.toString());
		}

		logger.info(" interview-feedback Created API called with interviewFeedback {}", dto);

		// insert interviewFeedback details
		return serviceImp.saveFeedback(dto);
	}

	@PostMapping("/excel")
	public ResponseEntity<?> createAllFeedbackExcel(@Valid @RequestParam MultipartFile file,
			BindingResult bindingResult) {

		// Validate request
		if (bindingResult.hasErrors()) {
			StringBuilder errorMessage = new StringBuilder();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMessage.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ");
			}
			return ResponseEntity.badRequest().body(validationmsg + "" + errorMessage.toString());
		}

		logger.info(" All interview-feedback Created by Excel  API called with MultipartFile ");
		return (ResponseEntity<?>) serviceImp.saveFromExcel(file);
	}

	@GetMapping("/getAllinterviewFeedback")
	public List<InterviewFeedback> getAllinterviewFeedback() {

		logger.info("API call: Get all interview feedback data");
		return serviceImp.getAllFeedback();
	}

	@GetMapping("getInterviewFeedback/{id}")
	public Optional<InterviewFeedback> getInterviewFeedback(@PathVariable String id) {

		logger.info("API call: Get interview feedback data by Id");
		return serviceImp.getFeedbackById(id);
	}

	@GetMapping("/getByCandidateEmailID/{candidateEmailId}")
	public List<InterviewFeedback> getFeedbackByCandidateName(@PathVariable String candidateEmailId) {
		return serviceImp.getFeedbackByCandidateEmail(candidateEmailId);
	}

	@PutMapping("updateInterviewFeedback/{id}")
	public ResponseEntity<?> updateFeedback(@Valid @PathVariable String id,
			@RequestBody InterviewFeedback updatedFeedback, BindingResult bindingResult) {

		// Validate request
		if (bindingResult.hasErrors()) {

			StringBuilder errorMessage = new StringBuilder();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMessage.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ");
			}
			return ResponseEntity.badRequest().body(validationmsg + "" + errorMessage.toString());
		}

		return serviceImp.updateFeedback(id, updatedFeedback);
	}

	@DeleteMapping("deleteById/{id}")
	public String deleteFeedback(@PathVariable String id) {
		logger.info("API call: delete Feedback data by Id");
		return serviceImp.deleteFeedback(id);
	}

	@GetMapping("/getAllPS")
	public List<InterviewFeedback> getAllinterviewFeedbackPaginated(@RequestParam String sortOrder,
			@RequestParam String fieldName, @RequestParam int pageNo) {
		
		logger.info("API call: Get all interviewFeedback paginated and sorted ");
		return serviceImp.getAllFeedbackByPaginatedAndSorted(sortOrder, fieldName, pageNo);
	}

	@GetMapping("/getCandidateBysearchOnSkill")
	public List<InterviewFeedback> getAllInterviewBySearchSkill(@RequestParam String searchSkill) {
		logger.info("API call: Get all interviewFeedback paginated and sorted ");
		return serviceImp.getFeedbackBySearch(searchSkill);
	}

	@GetMapping("/decisionCounts")
	public Map<String, Integer> getDecisionCounts() {
		return serviceImp.getDecisionCounts();
	}
	
	@GetMapping("/getCountInterviews")
	public Long getCountInterviews() {
		return serviceImp.getCountInterviews();
	}
	
//	@GetMapping("/countInterviewsByMonth")
//	public List<InterviewCountByMonth> countInterviewsByMonth() {
//		return serviceImp.countInterviewsByMonth();
//	}
	
	

}
