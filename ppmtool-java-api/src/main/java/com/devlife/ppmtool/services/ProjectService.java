package com.devlife.ppmtool.services;

import com.devlife.ppmtool.domain.Project;
import com.devlife.ppmtool.exceptions.ProjectIdException;
import com.devlife.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepository;

	public Project saveOrUpdateProject(Project project) {
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		} catch (Exception e) {
			throw new ProjectIdException("Project identifier " + project.getProjectIdentifier().toUpperCase() + " already exist");
		}

	}

	public Project findProjectByIdentifier(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId);
		if (project == null) {
			throw new ProjectIdException("Project identifier " + projectId.toUpperCase() + " does not  exist");
		}
		return project;
	}

	public Iterable<Project> findAllProjects() {
		return  projectRepository.findAll();
	}

	public void deleteProjectByIdentifier(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

		if (project == null) {
			throw new ProjectIdException("Project identifier " + projectId.toUpperCase() + " does not  exist");
		}

		projectRepository.delete(project);
	}
}
