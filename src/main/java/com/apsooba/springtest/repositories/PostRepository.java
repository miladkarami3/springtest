/**
 * @author  Milad Karami
 * @since   2021-05-22
 */
package com.apsooba.springtest.repositories;

import com.apsooba.springtest.dtos.PostDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<PostDto, Long> {}