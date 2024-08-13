package com.ifs.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ifs.dto.Candidate;
import com.ifs.dto.InterviewerInfo;
import com.ifs.dto.SoftSkillsEvaluation;
import com.ifs.dto.TechnicalEvaluation;
import com.ifs.dto.TechnicalEvaluationComments;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EnableMongoAuditing
@Document(collection = "interviewFeedback")
public class InterviewFeedback {
	
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
	
	@CreatedDate
	private LocalDate dateOfInterview;
	

	@LastModifiedDate
	private LocalDate dateOfFeedbackUpdated;


	@Override
	public String toString() {
		return "InterviewFeedback [feedbackId=" + feedbackId + ", documentNo=" + documentNo + ", revisionNo="
				+ revisionNo + ", effectiveDate=" + effectiveDate + ", candidate=" + candidate + ", interviewerInfo="
				+ interviewerInfo + ", softSkillsEvaluations=" + softSkillsEvaluations + ", technicalEvaluation="
				+ technicalEvaluation + ", techEvalcomments=" + techEvalcomments + ", hiringDecision=" + hiringDecision
				+ ", dateOfInterview=" + dateOfInterview + ", dateOfFeedbackUpdated=" + dateOfFeedbackUpdated + "]";
	}
	
	


	
	
	

}
