package com.stc.filesSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stc.filesSystem.model.File;


@Repository
public interface FileRepository extends JpaRepository<File,Long> {
}
