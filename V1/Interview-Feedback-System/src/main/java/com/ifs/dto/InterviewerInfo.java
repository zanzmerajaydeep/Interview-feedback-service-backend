package com.ifs.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class InterviewerInfo {
	

	
	private String interviewerName;
	private String interviewerDesignation;
	private String interviewerEmailId;
	

}
