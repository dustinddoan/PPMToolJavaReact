package com.devlife.ppmtool.services;

import com.devlife.ppmtool.domain.Backlog;
import com.devlife.ppmtool.domain.Project;
import com.devlife.ppmtool.exceptions.ProjectIdException;
import com.devlife.ppmtool.repositories.BacklogRepository;
import com.devlife.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private BacklogRepository backlogRepository;

	public Project saveOrUpdateProject(Project project) {
		String projectIdentifier = project.getProjectIdentifier().toUpperCase();
		try {
			project.setProjectIdentifier(projectIdentifier);
			//system.out.println("ID " + project.getId());
//			 CREATE NEW PROJECT
			if (project.getId() == null) {
				Backlog newBacklog = new Backlog();
				newBacklog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
				newBacklog.setProject(project);

				project.setBacklog(newBacklog);
			}
//			UPDATE PROJECT
			if(project.getId() != null) {
				project.setBacklog(backlogRepository.findByProjectIdentifier(projectIdentifier));
			}
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
