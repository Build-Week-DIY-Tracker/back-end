package com.lambdaschool.diytracker.services;

import com.lambdaschool.diytracker.exceptions.ResourceNotFoundException;
import com.lambdaschool.diytracker.models.ProjectPost;
import com.lambdaschool.diytracker.models.User;
import com.lambdaschool.diytracker.repository.ProjectPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "projectPostService")
public class ProjectPostServiceImpl implements ProjectPostService
{
	@Autowired UserService userService;

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
		ProjectPost newproject = new ProjectPost();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (projectPost.getUser().getUsername().equalsIgnoreCase(authentication.getName()))
		{
			newproject.setProjectname(projectPost.getProjectname());
			newproject.setProjectlink(projectPost.getProjectlink());

			return projectPostRepository.save(projectPost);
		} else
		{
			throw new EntityNotFoundException(projectPost.getUser().getUsername() + " != " + authentication.getName());
		}
	}

	@Override
	public ProjectPost update(ProjectPost projectPost, long id)
	{
		ProjectPost currentProject = projectPostRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));

		if (projectPost.getProjectname() != null)
		{
			currentProject.setProjectname(projectPost.getProjectname());
		}
		if (projectPost.getProjectlink() != null)
		{
			currentProject.setProjectlink(projectPost.getProjectlink());
		}
		return projectPostRepository.save(currentProject);
	}

	@Override
	public void delete(long id) throws ResourceNotFoundException
	{
		if (projectPostRepository.findById(id).isPresent())
		{
			projectPostRepository.deleteById(id);
		} else
		{
			throw new ResourceNotFoundException(Long.toString(id) + "notfound");
		}
	}
}
