package com.lambdaschool.diytracker.repository;

import com.lambdaschool.diytracker.models.ProjectPost;
import com.lambdaschool.diytracker.view.JustTheCount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ProjectPostRepository extends PagingAndSortingRepository<ProjectPost, Long>
{
	@Query(value = "SELECT COUNT(*) as count FROM projectlikes WHERE userid = :userid AND projectid = :projectid",
			nativeQuery = true)
	JustTheCount checkprojectlikes(long userid, long projectid);

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO projectlikes(userid, projectid) VALUES (:userid, :projectid)",
			nativeQuery = true)
	void insertProjectLikes(long userid, long projectid);
}
