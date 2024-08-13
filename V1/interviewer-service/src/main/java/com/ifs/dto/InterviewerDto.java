package com.ifs.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewerDto {

	private Long interviewerId;

	@NotEmpty(message = "Name Should not be empty/null")
	private String interviewerName;

	@Email(message = "Email should be in proper format")
	@NotEmpty(message = "Email Should not be empty/null")
	private String interviewerEmail;

	@NotEmpty(message = "Designation Should not be empty/null")
	private String interviewerDesignation;

	@NotEmpty(message = "Department Should not be empty/null")
	private String interviewerDepartment;

}
