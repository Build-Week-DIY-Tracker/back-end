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

	//constructor
	public ProjectPost()
	{
	}

	public ProjectPost(User user, String projectname)
	{
		this.user = user;
		this.projectname = projectname;
	}

	public ProjectPost(User user, String projectname, String projectlink)
	{
		this.user = user;
		this.projectname = projectname;
		this.projectlink = projectlink;
	}

	public ProjectPost(User user, String projectname, String projectlink, String description)
	{
		this.user = user;
		this.projectname = projectname;
		this.projectlink = projectlink;
		this.description = description;
	}

	public long getProjectid()
	{
		return projectid;
	}

	public void setProjectid(long projectid)
	{
		this.projectid = projectid;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public String getProjectname()
	{
		return projectname;
	}

	public void setProjectname(String projectname)
	{
		this.projectname = projectname;
	}

	public String getProjectlink()
	{
		return projectlink;
	}

	public void setProjectlink(String projectlink)
	{
		this.projectlink = projectlink;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public List<Photo> getPhotos()
	{
		return photos;
	}

	public void setPhotos(List<Photo> photos)
	{
		this.photos = photos;
	}

	public int getLikescount()
	{
		return likescount;
	}

	public void setLikescount(int likescount)
	{
		this.likescount = likescount;
	}
}
