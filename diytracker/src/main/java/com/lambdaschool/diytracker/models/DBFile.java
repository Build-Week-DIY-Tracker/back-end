package com.lambdaschool.diytracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class DBFile {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String fileName;

	@ManyToOne
	@JoinColumn(name = "projectid",
			nullable = false)
	@JsonIgnoreProperties("photos")
	private ProjectPost projectPost;

	private String fileType;

	@Lob
	private byte[] data;

	public DBFile() {

	}

	public DBFile(String fileName, String fileType, byte[] data) {
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
	}

	public DBFile(String fileName, ProjectPost projectPost, String fileType, byte[] data)
	{
		this.fileName = fileName;
		this.projectPost = projectPost;
		this.fileType = fileType;
		this.data = data;
	}
	// Getters and Setters


	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public ProjectPost getProjectPost()
	{
		return projectPost;
	}

	public void setProjectPost(ProjectPost projectPost)
	{
		this.projectPost = projectPost;
	}

	public String getFileType()
	{
		return fileType;
	}

	public void setFileType(String fileType)
	{
		this.fileType = fileType;
	}

	public byte[] getData()
	{
		return data;
	}

	public void setData(byte[] data)
	{
		this.data = data;
	}
}
