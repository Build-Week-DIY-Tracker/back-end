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

	public Photo()
	{
	}

	public long getPhotoid()
	{
		return photoid;
	}

	public void setPhotoid(long photoid)
	{
		this.photoid = photoid;
	}

	public ProjectPost getProjectPost()
	{
		return projectPost;
	}

	public void setProjectPost(ProjectPost projectPost)
	{
		this.projectPost = projectPost;
	}

	public String getFilename()
	{
		return filename;
	}

	public void setFilename(String filename)
	{
		this.filename = filename;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}
}
