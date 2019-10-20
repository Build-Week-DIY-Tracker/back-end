package com.lambdaschool.diytracker.repository;

import com.lambdaschool.diytracker.models.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; //reminder look this up

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, String>
{
}
