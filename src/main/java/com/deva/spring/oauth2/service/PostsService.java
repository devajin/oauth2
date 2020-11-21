/**
 * project name : oauth2
 * Created by deva.jin on 2020/11/18.
 */


package com.deva.spring.oauth2.service;


import com.deva.spring.oauth2.dto.PostsResponseDto;
import com.deva.spring.oauth2.dto.PostsSaveRequestDto;
import com.deva.spring.oauth2.entity.Posts;
import com.deva.spring.oauth2.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostsService {

	private final PostsRepository postsRepository;

	public Long save(PostsSaveRequestDto requestDto){
		return postsRepository.save(requestDto.toEntity()).getId();
	}

	@Transactional // 트렌젝션으로 인한 더티 체킹 (dirty checking) update
	public Long update(Long id, PostsSaveRequestDto requestDto) {
		Posts posts = postsRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("해당 글이 없습니다.  id : "+ id));
		log.info(posts.toString());
		posts.update(requestDto.getTitle(), requestDto.getContent());
		return id;
	}

	public PostsResponseDto findById (Long id) {
		Posts entity = postsRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("해당 글이 없습니다.  id : "+ id));
		return new PostsResponseDto(entity);
	}


}
