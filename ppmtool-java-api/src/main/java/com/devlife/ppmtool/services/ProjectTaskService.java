package com.devlife.ppmtool.services;

import com.devlife.ppmtool.domain.Backlog;
import com.devlife.ppmtool.domain.Project;
import com.devlife.ppmtool.domain.ProjectTask;
import com.devlife.ppmtool.exceptions.ProjectNotFoundException;
import com.devlife.ppmtool.repositories.BacklogRepository;
import com.devlife.ppmtool.repositories.ProjectRepository;
import com.devlife.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTaskService {
	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private BacklogRepository backlogRepository;

	@Autowired
	private ProjectTaskRepository projectTaskRepository;

	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
		try {
			Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
			projectTask.setBacklog(backlog);

			Integer backlogSequence = backlog.getPTSequence();
			backlogSequence++;
			backlog.setPTSequence(backlogSequence);

			projectTask.setProjectSequence(projectIdentifier+"-"+backlogSequence);
			projectTask.setProjectIdentifier(projectIdentifier);

			if(projectTask.getPriority() == 0 || projectTask.getPriority() == null) {
				projectTask.setPriority(3);
			}

			if(projectTask.getStatus() == "" || projectTask.getStatus() == null) {
				projectTask.setStatus("TO_DO");
			}

			return projectTaskRepository.save(projectTask);
		} catch (Exception e) {
			throw new ProjectNotFoundException("Project Not Found");
		}

	}

	public Iterable<ProjectTask> findBacklogById(String id) {
		Project project = projectRepository.findByProjectIdentifier(id);
		if(project == null) {
			throw new ProjectNotFoundException("Project with ID: '" + id + "' does not exist");
		}
		return projectTaskRepository.findByProjectIdentifier(id);
	}

	public ProjectTask findPTByProjectSequence(String backlog_id, String pt_id) {
//		make sure backlog exist
		Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id);
		if(backlog == null) {
			throw new ProjectNotFoundException("Project with ID: '" + backlog_id + "' does not exist");
		}
//		make sure projectTask exist
		ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id);
		if(projectTask == null) {
			throw new ProjectNotFoundException("Project Task ID: '" + pt_id + "' does not exist");
		}

//		make sure backlog_id/project_id in the path corresponds to the right project
		if (!projectTask.getProjectIdentifier().equals(backlog_id)) {
			throw new ProjectNotFoundException("Project Task '" + pt_id + "' does not exsit in project '" + backlog_id + "'");
		}

		return projectTask;
	}

	public ProjectTask updateByProjectSequence(ProjectTask updateTask, String backlog_id, String pt_id) {
		ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id);
		projectTask = updateTask;

		return projectTaskRepository.save(projectTask);
	}

	public void deletePTByProjetSequence(String backlog_id, String pt_id) {
		ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id);

		projectTaskRepository.delete(projectTask);
	}
}
