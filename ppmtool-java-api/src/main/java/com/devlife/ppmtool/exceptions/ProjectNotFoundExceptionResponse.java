package com.devlife.ppmtool.exceptions;
//step 1: create response message
public class ProjectNotFoundExceptionResponse {
//	message
	private String ProjectNotFound;

	public ProjectNotFoundExceptionResponse(String projectNotFound) {
		ProjectNotFound = projectNotFound;
	}

	public String getProjectNotFound() {
		return ProjectNotFound;
	}

	public void setProjectNotFound(String projectNotFound) {
		ProjectNotFound = projectNotFound;
	}
}
