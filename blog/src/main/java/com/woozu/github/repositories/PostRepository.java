package com.woozu.github.repositories;

import com.woozu.github.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long>{

    List<Post> findByCreatorId(Long id);

}
