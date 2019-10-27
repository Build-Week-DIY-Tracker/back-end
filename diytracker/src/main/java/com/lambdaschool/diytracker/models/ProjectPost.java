package com.lambdaschool.diytracker.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="projects")
public class ProjectPost
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long projectid;

	@ManyToOne
	@JoinColumn(name = "userid",
			nullable = false)
	@JsonIgnoreProperties("projects")
	private User user;

	@Column(nullable = false, unique = false) //probably should take this out
	private String projectname;

	@Column(nullable = true)
	private String projectlink;


	@OneToMany(mappedBy = "projectPost",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	@JsonIgnoreProperties("photo")
	private List<DBFile> photos = new ArrayList<>();

	@Column(nullable = false)
	@JsonIgnoreProperties("projects")
	@ManyToMany(mappedBy = "likedProjects", cascade = CascadeType.ALL)
	Set<User> likes;

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

	public int getLikes()
	{
		return likes.size();
	}

	public void setLikes(Set<User> likes)
	{
		this.likes = likes;
	}

	public List<DBFile> getPhotos()
	{
		return photos;
	}

	public void setPhotos(List<DBFile> photos)
	{
		this.photos = photos;
	}
}
