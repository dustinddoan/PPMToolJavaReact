package com.devlife.ppmtool.repositories;

import com.devlife.ppmtool.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {
//	List<ProjectTask> findByProjectIdentifierOrderByPriority(String id);
	List<ProjectTask> findByProjectIdentifier(String id);

	ProjectTask findByProjectSequence(String sequence);
}
