package com.devlife.ppmtool.services;

import com.devlife.ppmtool.domain.Project;
import com.devlife.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepository;

	public Project saveOrUpdateProject(Project project) {
		return projectRepository.save(project);
	}
}
