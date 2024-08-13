package com.ifs.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ifs.dto.InterviewerDto;
import com.ifs.entity.Interviewer;

@Component
public class InterviewerMapper {
	private static ModelMapper modelMapper;

    public InterviewerMapper(ModelMapper modelMapper) {
        InterviewerMapper.modelMapper = modelMapper;
    }
    
	public static Interviewer toInterviewer(InterviewerDto interviewerDto) {
		return modelMapper.map(interviewerDto, Interviewer.class);
	}

	public static InterviewerDto toInterviewerDto(Interviewer interviewer)
	{
		return modelMapper.map(interviewer, InterviewerDto.class);
	}
	
}
