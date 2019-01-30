package com.arocketman.github.repositories;

import com.arocketman.github.entities.Comment;
import com.arocketman.github.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long>{

    List<Post> findByCreatorId(Long id);

}
