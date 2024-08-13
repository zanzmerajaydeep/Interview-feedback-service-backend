package com.ifs.serviceImp;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ifs.dto.InterviewCountByMonth;
import com.ifs.dto.InterviewFeedbackDto;
import com.ifs.model.InterviewFeedback;
import com.ifs.repository.InterviewFeedbackRepository;
import com.ifs.service.InterviewFeedbackService;

@Service
public class InterviewFeedbackServiceImp  implements InterviewFeedbackService{
	
//	@Autowired
//	private MongoTemplate mongoTemplate;
//	
	@Autowired
	private  InterviewFeedbackRepository interviewFeedbackRepository;
	
	@Autowired
	private ModelMapper mapper;

	public InterviewFeedback toInterviewDto(InterviewFeedbackDto interviewFeedbackDto) {
		return this.mapper.map(interviewFeedbackDto, InterviewFeedback.class);
	}

	@Override
	public ResponseEntity<InterviewFeedback> saveFeedback(InterviewFeedbackDto dto) {
		
		InterviewFeedback interviewFeedback= this.toInterviewDto(dto);
		InterviewFeedback interviewFeedback1 = interviewFeedbackRepository.save(interviewFeedback);
		return ResponseEntity.status(HttpStatus.OK).body(interviewFeedback1);
	}

	@Override
	public List<InterviewFeedback> getAllFeedback() {
		return interviewFeedbackRepository.findAll();
	}

	@Override
	public Optional<InterviewFeedback> getFeedbackById(String id) {
		return interviewFeedbackRepository.findById(id);
	}

	@Override
	public String deleteFeedback(String id) {
		interviewFeedbackRepository.deleteById(id);
		return "InetrviewFeedback delete successfully with id : "+id;
		
	}

	@Override
	public ResponseEntity<?> updateFeedback(String id, InterviewFeedback updatedFeedback) {
		if(interviewFeedbackRepository.existsById(id))
		{
			InterviewFeedback feedback=  interviewFeedbackRepository.save(updatedFeedback);
			return ResponseEntity.ok(feedback);
		}
		
			throw new RuntimeErrorException( null, "Record not found with ID : "+id);
	}

	@Override
	public List<InterviewFeedback> getFeedbackByCandidateEmail(String candidateEmailId) {
		
		return interviewFeedbackRepository.findByCandidateCandidateEmailId(candidateEmailId);
	}

	
	@Override
	public List<InterviewFeedback> saveFromExcel(MultipartFile file) {
	
		return interviewFeedbackRepository.saveAll(ExcelInterviewFeedbackServiceImp.parseExcel(file));
	}

	@Override
	public List<InterviewFeedback> getAllFeedbackByPaginatedAndSorted(String sortOrder, String fieldName,int pageNo) {
		Sort sort =  sortOrder.equalsIgnoreCase("asc") ? Sort.by(fieldName).ascending() : Sort.by(fieldName).descending();
		org.springframework.data.domain.Pageable pageable=  PageRequest.of(pageNo, 5, sort);
		return interviewFeedbackRepository.findAll( pageable).getContent();
	}

	@Override
	public List<InterviewFeedback> getFeedbackBySearch(String searchSkill) {
		
		return   interviewFeedbackRepository.findByTechnicalEvaluationSkillRegex(searchSkill);
		
		
	}
	
	 public Map<String, Integer> getDecisionCounts() {
	        List<InterviewFeedback> feedbacks = interviewFeedbackRepository.findAll();

	        int hiredCount = 0;
	        int notHiredCount = 0;
	        int pendingCount = 0;

	        for (InterviewFeedback feedback : feedbacks) {
	            String decision = feedback.getHiringDecision();

	            if ("Hired".equalsIgnoreCase(decision)) {
	                hiredCount++;
	            } else if ("Not Hired".equalsIgnoreCase(decision)) {
	                notHiredCount++;
	            } else if ("Pending".equalsIgnoreCase(decision)) {
	                pendingCount++;
	            }
	        }

	        Map<String, Integer> decisionCounts = new HashMap();
	        decisionCounts.put("Hired", hiredCount);
	        decisionCounts.put("Not_Hired", notHiredCount);
	        decisionCounts.put("Pending", pendingCount);

	        return decisionCounts;
	    }
	 
	 
	 
	 public Long getCountInterviews() {
	        return interviewFeedbackRepository.count();
	    }
	 
//	   public List<Map<String, Object>> getMonthwiseInterviewFeedbackCount() {
//	        LocalDate oneYearAgo = LocalDate.now().minusYears(1);
//	        Date oneYearAgoDate = Date.from(oneYearAgo.atStartOfDay(ZoneId.systemDefault()).toInstant());
//
//	        // Create an aggregation pipeline to group interview feedback by month and count
//	        Aggregation aggregation = Aggregation.newAggregation(
//	            Aggregation.match(org.springframework.data.mongodb.core.query.Criteria.where("dateOfFeedbackUpdated").gte(oneYearAgoDate)),
//	            Aggregation.project()
//	                .and(DateOperators.DateToString.dateOf("dateOfFeedbackUpdated")
//	                .toString("%Y/%m/%d")).as("month"),
//	            Aggregation.group("month").count().as("count")
//	        );
//
//	        List<Map<String, Object>> results = mongoTemplate.aggregate(aggregation, "interviews", Map.class)
//	                .getMappedResults();
//
//	        return results;
//	    }

}
