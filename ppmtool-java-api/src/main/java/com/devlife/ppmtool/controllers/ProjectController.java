package com.devlife.ppmtool.controllers;

import com.devlife.ppmtool.domain.Project;
import com.devlife.ppmtool.services.ProjectService;
import com.devlife.ppmtool.services.ValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
	@Autowired
	private ProjectService projectService;

	@Autowired
	private ValidationErrorService validationErrorService;

	@PostMapping("")
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {
		ResponseEntity<?> errorMap = validationErrorService.MapValidationService(result);
		if (errorMap != null) return  errorMap;

		Project project1 = projectService.saveOrUpdateProject(project);
		return new ResponseEntity<Project>(project, HttpStatus.CREATED);
	}

	@GetMapping("/{projectId}")
	public ResponseEntity<?> getProjectById(@PathVariable String projectId) {
		Project project = projectService.findProjectByIdentifier(projectId);

		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}

	@GetMapping("/all")
	public Iterable<Project> findAllProjects() {
		return projectService.findAllProjects();
	}

	@DeleteMapping("/{projectId}")
	public ResponseEntity<?> deleteProject(@PathVariable String projectId) {
		projectService.deleteProjectByIdentifier(projectId);

		return new ResponseEntity<String>("Project was deleted", HttpStatus.OK);
	}
}
