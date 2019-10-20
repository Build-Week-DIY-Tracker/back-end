package com.lambdaschool.diytracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity(name = "photos")
public class Photo
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long photoid;

	@ManyToOne
	@JoinColumn(name = "projectid",
			nullable = false)
	@JsonIgnoreProperties("photos")
	private ProjectPost projectPost;

	@Column(nullable = false,
			unique = true)
	private String filename;

	@Column(nullable = false,
			unique = true)
	private String path;
}
