package com.devlife.ppmtool.controllers;

import com.devlife.ppmtool.domain.ProjectTask;
import com.devlife.ppmtool.services.ProjectTaskService;
import com.devlife.ppmtool.services.ValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.BindingType;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {
	@Autowired
	private ProjectTaskService projectTaskService;

	@Autowired
	private ValidationErrorService validationErrorService;

	@PostMapping("/{projectId}")
	public ResponseEntity<?> addPTtoBacklog(@Valid @RequestBody ProjectTask projectTask, @PathVariable String projectId, BindingResult result) {
		ResponseEntity<?> errorMap = validationErrorService.MapValidationService(result);
		if(errorMap != null) return errorMap;

		ProjectTask projectTask1 = projectTaskService.addProjectTask(projectId, projectTask);
		return new ResponseEntity<ProjectTask>(projectTask1,HttpStatus.CREATED);
	}

}
