package com.ifs.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifs.dto.InterviewerDto;
import com.ifs.service.InterviewerService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/interviewrs")
public class InterviewerController {

	private InterviewerService service;
	
	@PostMapping
	public ResponseEntity<InterviewerDto> createInterviewer(@Valid @RequestBody InterviewerDto dto){
		InterviewerDto interviewer = service.createInterviewer(dto);
		return new ResponseEntity<>(interviewer, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<InterviewerDto> getInterviewerById(@PathVariable Long id) {
		InterviewerDto interviewer = service.getInterviewerById(id);
		return ResponseEntity.ok(interviewer);
	}
	
	@GetMapping
	public ResponseEntity<List<InterviewerDto>> getInterviewer() {
		List<InterviewerDto> interviewers = service.getAllInterviewer();
		return ResponseEntity.ok(interviewers);
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<InterviewerDto> updateInterviewer(@PathVariable Long id,
                                           @RequestBody @Valid InterviewerDto interviewer){
        
        InterviewerDto updatedInterviewer = service.updateInterviewer(id,interviewer);
        return ResponseEntity.ok(updatedInterviewer);
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInterviewer(@PathVariable("id") Long id){
		service.deleteInterviewer(id);
		return ResponseEntity.ok("User successfully deleted!");
    }
	
}
