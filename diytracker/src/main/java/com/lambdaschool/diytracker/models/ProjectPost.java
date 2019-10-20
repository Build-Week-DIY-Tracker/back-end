package com.lambdaschool.diytracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Entity(name="projectposts")
public class ProjectPost
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long projectid;

	@ManyToOne
	@JoinColumn(name = "userid",
			nullable = false)
	@JsonIgnoreProperties("projectposts")
	private User user;

	@Column(nullable = false,
			unique = true)
	private String projectname;

	@Column(nullable = false)
	private String projectlink;

	@Column(nullable = false)
	private String description;


	@OneToMany(mappedBy = "projectPost",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	@JsonIgnoreProperties("photo")
	private List<Photo> photos = new ArrayList<>();

	@Column(nullable = false)
	private int likescount;

}
