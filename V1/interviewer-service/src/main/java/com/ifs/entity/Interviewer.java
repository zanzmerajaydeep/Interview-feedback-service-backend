package com.ifs.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "interviewers")
public class Interviewer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long interviewerId;

	private String interviewerName;

	private String interviewerEmail;

	private String interviewerDesignation;

	private String interviewerDepartment;
	
	
}
