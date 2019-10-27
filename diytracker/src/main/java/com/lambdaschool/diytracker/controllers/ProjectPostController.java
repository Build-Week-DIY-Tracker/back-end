package com.lambdaschool.diytracker.controllers;

import com.lambdaschool.diytracker.models.ProjectPost;
import com.lambdaschool.diytracker.services.ProjectPostService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
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

	@ApiOperation(value = "don't use this yet its broken. add projects through the users endpoint")
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

		return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
	}


	//PUT http://localhost:2019/edit/7
	@PutMapping(value = "/edit/{projectid}",
			consumes = {"application/json"})
	public ResponseEntity<?> updateProject(@RequestBody ProjectPost projectPost,
	                                          @PathVariable long projectid)
	{
		projectPostService.update(projectPost, projectid);
		return new ResponseEntity<>(HttpStatus.OK);
	}



	//DELETE http://localhost:2019/delete/7
	@ApiOperation(value = "")
	@DeleteMapping("/project/{projectid}")
	public ResponseEntity<?> deleteProjectById(@PathVariable long projectid)
	{

		projectPostService.delete(projectid);
		return new ResponseEntity<>("Project" + projectid + "Deleted Successfully", HttpStatus.OK);
	}

}
