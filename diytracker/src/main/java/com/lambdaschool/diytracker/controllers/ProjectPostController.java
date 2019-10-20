package com.lambdaschool.diytracker.controllers;

import com.lambdaschool.diytracker.models.ProjectPost;
import com.lambdaschool.diytracker.services.ProjectPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "projects")
public class ProjectPostController
{
	@Autowired
	ProjectPostService projectPostService;

	//localhost:2019//projects/all
	@GetMapping(value = "/all",
				produces = {"application/json"})
	public ResponseEntity<?> listAllProjects(@PageableDefault(page=0, size=20) Pageable pageable)
	{
		List<ProjectPost> projectPostList = projectPostService.findAll(pageable);
		return new ResponseEntity<>(projectPostList, HttpStatus.OK);
	}

	@PostMapping(value = "/add",
			consumes = {"application/json"},
			produces = {"application/json"})
	public ResponseEntity<?> addNewProject(@Valid
	                                      @RequestBody
			                                      ProjectPost newProject) throws URISyntaxException
	{
		newProject = projectPostService.save(newProject);

		// set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newStudentURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{projectid}").buildAndExpand(newProject.getProjectid()).toUri();
		responseHeaders.setLocation(newStudentURI);

		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}
}
