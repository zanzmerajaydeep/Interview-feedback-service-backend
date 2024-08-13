package com.ifs.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ifs.dto.InterviewerDto;
import com.ifs.entity.Interviewer;
import com.ifs.mapper.InterviewerMapper;
import com.ifs.repository.InterviewerRepository;
import com.ifs.service.InterviewerService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InterviewerServiceImpl implements InterviewerService {

	private InterviewerRepository repository;
	
	@Override
	public InterviewerDto createInterviewer(InterviewerDto interviewerDto) {
		
		Interviewer interviewer = InterviewerMapper.toInterviewer(interviewerDto);
		
		Interviewer savedInterviewer = repository.save(interviewer);
		
		return InterviewerMapper.toInterviewerDto(savedInterviewer);
	}

	@Override
	public InterviewerDto getInterviewerById(Long interviewerId) {
		Interviewer interviewer = repository.findById(interviewerId).orElseThrow(()->new RuntimeException("Interviewer not found with Id"+interviewerId));
		
		return InterviewerMapper.toInterviewerDto(interviewer);
	}

	@Override
	public List<InterviewerDto> getAllInterviewer() {
		List<Interviewer> interviewers = repository.findAll();
		
		return interviewers.stream().map(InterviewerMapper::toInterviewerDto).toList();
	}

	@Override
	public InterviewerDto updateInterviewer(Long id,InterviewerDto interviewerDto) {
		Interviewer existingInterviewer = repository.findById(id).orElseThrow(()->new RuntimeException("Interviewer not found with Id"+interviewerDto.getInterviewerId()));
		
		existingInterviewer.setInterviewerName(interviewerDto.getInterviewerName());
		existingInterviewer.setInterviewerEmail(interviewerDto.getInterviewerEmail());
		existingInterviewer.setInterviewerDesignation(interviewerDto.getInterviewerDesignation());
		existingInterviewer.setInterviewerDepartment(interviewerDto.getInterviewerDepartment());
		
		
		Interviewer updatedInterviewer = repository.save(existingInterviewer);
		
		return InterviewerMapper.toInterviewerDto(updatedInterviewer);
	}

	@Override
	public void deleteInterviewer(Long interviewerId) {
		repository.findById(interviewerId).orElseThrow(()->new RuntimeException("Interviewer not found with Id"+interviewerId));
		repository.deleteById(interviewerId);
	}

}
