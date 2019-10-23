package com.lambdaschool.diytracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lambdaschool.diytracker.logging.Loggable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Loggable
@Entity
@Table(name = "projectlikes",
		uniqueConstraints = {@UniqueConstraint(columnNames = {"projectid", "userid"})})
public class ProjectLikes implements Serializable
{

	@Id
	@ManyToOne
	@JoinColumn(name = "projectid")
	@JsonIgnoreProperties("projectlikes")
	private ProjectPost projectPost;

	@Id
	@ManyToOne
	@JoinColumn(name = "userid")
	@JsonIgnoreProperties("projectlikes")
	private User user;

	public ProjectLikes()
	{
	}

	public ProjectLikes(ProjectPost projectPost, User user)
	{
		this.projectPost = projectPost;
		this.user = user;
	}

	public ProjectPost getProjectPost()
	{
		return projectPost;
	}

	public void setProjectPost(ProjectPost projectPost)
	{
		this.projectPost = projectPost;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof ProjectLikes))
		{
			return false;
		}
		ProjectLikes that = (ProjectLikes) o;
		return getProjectPost().equals(that.getProjectPost()) && getUser().equals(that.getUser());
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(getProjectPost(), getUser());
	}

	@Override
	public String toString()
	{
		return "ProjectLikes{" + "projectPost=" + projectPost + ", user=" + user + '}';
	}
}


