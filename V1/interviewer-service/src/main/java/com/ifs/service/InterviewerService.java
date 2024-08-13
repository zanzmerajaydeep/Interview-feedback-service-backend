package com.ifs.service;

import java.util.List;

import com.ifs.dto.InterviewerDto;

public interface InterviewerService {
	InterviewerDto createInterviewer(InterviewerDto interviewerDto);

	InterviewerDto getInterviewerById(Long interviewerId);

    List<InterviewerDto> getAllInterviewer();

    InterviewerDto updateInterviewer(Long id, InterviewerDto interviewerDto);

    void deleteInterviewer(Long interviewerId);
	
}
