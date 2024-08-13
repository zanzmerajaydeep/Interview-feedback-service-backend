package com.ifs.serviceImp;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import com.ifs.dto.Candidate;
import com.ifs.dto.InterviewerInfo;
import com.ifs.dto.SoftSkillsEvaluation;
import com.ifs.dto.TechnicalEvaluation;
import com.ifs.model.InterviewFeedback;

public class ExcelInterviewFeedbackServiceImp {

	public static List<InterviewFeedback> parseExcel(MultipartFile file) {
		List<InterviewFeedback> feedbackList = new ArrayList();

		try (InputStream inputStream = file.getInputStream()) {
			Workbook workbook = WorkbookFactory.create(inputStream);
			Sheet sheet = workbook.getSheetAt(0);

			for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
				Row row = sheet.getRow(rowIndex);
				System.out.println(row);
				InterviewFeedback feedback = createFeedbackFromRow(row);
				feedbackList.add(feedback);
			}

			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return feedbackList;
	}

	private static InterviewFeedback createFeedbackFromRow(Row row) {
		InterviewFeedback feedback = new InterviewFeedback();

		feedback.setDocumentNo(getStringCellValue(row.getCell(0)));
		feedback.setRevisionNo(getStringCellValue(row.getCell(1)));
		System.out.println("===" + getDateCellValue(row.getCell(2)));
		// System.out.println("==="+getDateCellValue(row.getCell(2)));

//		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//	     LocalDate TemplocalDate = LocalDate.parse(getDateCellValue(row.getCell(2)), formatter);
		feedback.setEffectiveDate(getDateCellValue(row.getCell(2)));

		// Candidate Info
		Candidate candidateInfo = new Candidate();
		candidateInfo.setCandidateFullName(getStringCellValue(row.getCell(3)));
		candidateInfo.setCandidateEmailId(getStringCellValue(row.getCell(4)));
		candidateInfo.setCandidateContactNo(getStringCellValue(row.getCell(5)));
		candidateInfo.setCandidatePosition(getStringCellValue(row.getCell(6)));
		candidateInfo.setCandidateDivision(getStringCellValue(row.getCell(7)));
		candidateInfo.setCandidateTotalExp(getStringCellValue(row.getCell(8)));
		candidateInfo.setCandidateRelevantExp(getStringCellValue(row.getCell(9)));
		candidateInfo.setCandidatePrimarySkills(getStringCellValue(row.getCell(10)));
		candidateInfo.setCandidateSecondarySkills(getStringCellValue(row.getCell(11)));

		// set the candidate info
		feedback.setCandidate(candidateInfo);

		InterviewerInfo interviewerInfo = new InterviewerInfo();
		interviewerInfo.setInterviewerName(getStringCellValue(row.getCell(12)));
		interviewerInfo.setInterviewerDesignation(getStringCellValue(row.getCell(13)));
		interviewerInfo.setInterviewerEmailId(getStringCellValue(row.getCell(14)));

		// set interviewer details
		feedback.setInterviewerInfo(interviewerInfo);

		// softSkill
		SoftSkillsEvaluation softSkillsEvaluation = new SoftSkillsEvaluation();

		// set softSkill
		softSkillsEvaluation.setSkill(getStringCellValue(row.getCell(15)));
		softSkillsEvaluation.setSkillRating(getStringCellValue(row.getCell(16)));

		// softSkills added to list
		List<SoftSkillsEvaluation> ListOfSoftSkil = new ArrayList<>();
		ListOfSoftSkil.add(softSkillsEvaluation);

		// Technical Evaluations
		List<TechnicalEvaluation> technicalEvaluations = new ArrayList<>();

		TechnicalEvaluation technicalEvaluation = new TechnicalEvaluation();

		technicalEvaluation.setSkill(getStringCellValue(row.getCell(17)));
		technicalEvaluation.setSkillRating(getStringCellValue(row.getCell(18)));

		technicalEvaluations.add(technicalEvaluation);
		feedback.setTechnicalEvaluation(technicalEvaluations);

		// Hiring Decision
		feedback.setHiringDecision(getStringCellValue(row.getCell(19)));

		// Date of Interview
		// LocalDate TemplocalDate2 =
		// LocalDate.parse(getStringCellValue(row.getCell(20)), formatter);
		feedback.setDateOfInterview(getDateCellValue(row.getCell(20)));

		// Date of interview feedback updated
		// LocalDate TemplocalDate3 =
		// LocalDate.parse(getStringCellValue(row.getCell(21)), formatter);
		feedback.setDateOfFeedbackUpdated(getDateCellValue(row.getCell(21)));

		return feedback;
	}

	private static String getStringCellValue(Cell cell) {
		if (cell == null) {
			return "";
		}
		if (cell.getCellType() == CellType.STRING) {
			return cell.getStringCellValue();
		} else if (cell.getCellType() == CellType.NUMERIC) {
			return String.valueOf((Double) cell.getNumericCellValue());
		} else {
			return "";
		}
	}

	private static LocalDate getDateCellValue(Cell cell) {

		if (cell == null) {
			return null;
		}
		if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
			Date dateValue = cell.getDateCellValue();
			return dateValue.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		} else {
			return null;
		}
	}
}
