package com.lambdaschool.diytracker.repository;

import com.lambdaschool.diytracker.models.ProjectPost;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProjectPostRepository extends PagingAndSortingRepository<ProjectPost, Long>
{

}
