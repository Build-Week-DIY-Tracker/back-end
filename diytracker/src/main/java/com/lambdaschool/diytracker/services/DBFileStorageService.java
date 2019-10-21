package com.lambdaschool.diytracker.services;

import com.lambdaschool.diytracker.exceptions.FileStorageException;
import com.lambdaschool.diytracker.exceptions.MyFileNotFoundException;
import com.lambdaschool.diytracker.models.DBFile;
import com.lambdaschool.diytracker.models.ProjectPost;
import com.lambdaschool.diytracker.models.User;
import com.lambdaschool.diytracker.repository.DBFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public class DBFileStorageService {

	@Autowired
	private DBFileRepository dbFileRepository;

	public DBFile storeFile(MultipartFile file) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Check if the file's name contains invalid characters
			if(fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());

			return dbFileRepository.save(dbFile);
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	public DBFile getFile(String fileId) {
		return dbFileRepository.findById(fileId)
				.orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
	}

//	public DBFile storeFileToProject(MultipartFile dbFile, long projectid)
//	{
		// Normalize file name
//		String fileName = StringUtils.cleanPath(dbFile.getOriginalFilename());
//
//		try {
//			// Check if the file's name contains invalid characters
//			if(fileName.contains("..")) {
//				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
//			}
//
//			DBFile dbFilep = new DBFile(fileName, dbFile.getContentType(), dbFile.getBytes(), dbFile);
//
//			return dbFileRepository.save(dbFilep);
//		} catch (IOException ex) {
//			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
//		}
//		ProjectPost newproject = new ProjectPost();
//		User currentUser = findUserById(userid);
//		newproject.setProjectname(projectPost.getProjectname());
//		newproject.setLikescount(projectPost.getLikescount());
//		newproject.setProjectlink(projectPost.getProjectlink());
//		newproject.setUser(currentUser);
//		return projectPostRepository.save(newproject);
//	}

}