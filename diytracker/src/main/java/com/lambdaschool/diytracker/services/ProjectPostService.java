package com.lambdaschool.diytracker.services;


import com.lambdaschool.diytracker.models.ProjectPost;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectPostService
{
	List<ProjectPost> findAll(Pageable pageable);

	ProjectPost save(ProjectPost projectPost);

	ProjectPost update(ProjectPost projectPost, long id);

	void delete(long id);
}
