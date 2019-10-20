package com.lambdaschool.diytracker.services;

import com.lambdaschool.diytracker.models.ProjectPost;
import com.lambdaschool.diytracker.repository.ProjectPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "projectPostService")
public class ProjectPostServiceImpl implements ProjectPostService
{
	@Autowired
	private ProjectPostRepository projectPostRepository;

	@Override
	public List<ProjectPost> findAll(Pageable pageable)
	{
		ArrayList<ProjectPost> list = new ArrayList<>();

		projectPostRepository.findAll(pageable).iterator().forEachRemaining(list :: add);
		return list;
	}

	@Override
	public ProjectPost save(ProjectPost projectPost)
	{
		ProjectPost newProject = new ProjectPost();

		newProject.setProjectname(projectPost.getProjectname());

		return projectPostRepository.save(newProject);
	}

}
