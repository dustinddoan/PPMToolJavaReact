package com.devlife.ppmtool.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Backlog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int PTSequence = 0;
	private String projectIdentifier;
//	ONE TO ONE with Project
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id", nullable = false)
	@JsonIgnore
	private Project project;

//	ONE TO MANY with ProjectTask
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "backlog")
	private List<Backlog> projectTasks = new ArrayList<>();

	public Backlog() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPTSequence() {
		return PTSequence;
	}

	public void setPTSequence(int PTSequence) {
		this.PTSequence = PTSequence;
	}

	public String getProjectIdentifier() {
		return projectIdentifier;
	}

	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Backlog> getProjectTasks() {
		return projectTasks;
	}

	public void setProjectTasks(List<Backlog> projectTasks) {
		this.projectTasks = projectTasks;
	}
}
