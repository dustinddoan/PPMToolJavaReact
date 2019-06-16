package com.devlife.ppmtool.controllers;

import com.devlife.ppmtool.domain.Project;
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
import java.util.List;

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
		if (errorMap != null) return errorMap;

		ProjectTask projectTask1 = projectTaskService.addProjectTask(projectId, projectTask);
		return new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.CREATED);
	}

	@GetMapping("/{backlog_id}")
	public Iterable<ProjectTask> getProjectBacklog(@PathVariable String backlog_id) {
		return projectTaskService.findBacklogById(backlog_id);
	}

	@GetMapping("/{backlog_id}/{pt_id}")
	public ResponseEntity<?> getProjectTask(@PathVariable String backlog_id, @PathVariable String pt_id) {
		ProjectTask projectTask = projectTaskService.findPTByProjectSequence(backlog_id, pt_id);
		return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
	}

	@PatchMapping("/{backlog_id}/{pt_id}")
	public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult result,
	                                           @PathVariable String backlog_id, @PathVariable String pt_id) {
		ResponseEntity<?> errorMap = validationErrorService.MapValidationService(result);
		if (errorMap != null) return errorMap;

		ProjectTask updateTask = projectTaskService.updateByProjectSequence(projectTask, backlog_id, pt_id);
		return new ResponseEntity<ProjectTask>(updateTask, HttpStatus.OK);
	}
}
