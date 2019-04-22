package net.devlife.ppmtool.services;

import net.devlife.ppmtool.domain.Project;
import net.devlife.ppmtool.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {
        // logic

        return projectRepository.save(project);
    }
}
