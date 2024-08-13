package com.ifs.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Data
public class InterviewFeedbackDto {
	
	
	@Id
	private String feedbackId;
	
	private String documentNo;
	private String revisionNo;
	
	
	private LocalDate effectiveDate;
	

	private Candidate candidate;
	
	
	private InterviewerInfo interviewerInfo;
	
	
	private List<SoftSkillsEvaluation> softSkillsEvaluations;
	
	
	private List<TechnicalEvaluation> technicalEvaluation;
	

	private ArrayList<String> techEvalcomments;
	
	private String hiringDecision;
	
	
	private LocalDate dateOfInterview;
	

	
	private LocalDate dateOfFeedbackUpdated;

}
