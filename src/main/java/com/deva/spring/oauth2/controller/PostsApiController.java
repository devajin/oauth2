/**
 * project name : oauth2
 * Created by deva.jin on 2020/11/18.
 */


package com.deva.spring.oauth2.controller;

import com.deva.spring.oauth2.dto.PostsResponseDto;
import com.deva.spring.oauth2.dto.PostsSaveRequestDto;
import com.deva.spring.oauth2.service.PostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PostsApiController {

	private final PostsService postsService;

	@PostMapping(value = "/api/v1/posts")
	public ResponseEntity<Long> save(@RequestBody PostsSaveRequestDto requestDto) {
		log.info(requestDto.toString());
		return new ResponseEntity<>(postsService.save(requestDto), HttpStatus.OK);
	}

	@PutMapping("api/v1/posts/{id}")
	public ResponseEntity<Long> update(@PathVariable Long id, @RequestBody PostsSaveRequestDto requestDto){
		return new ResponseEntity<>(postsService.update(id,requestDto), HttpStatus.OK);
	}

	@GetMapping("/api/v1/posts/{id}")
	public PostsResponseDto findById (@PathVariable Long id){
		return postsService.findById(id);
	}

}
