package com.deva.spring.oauth2.service;

import com.deva.spring.oauth2.entity.Posts;
import com.deva.spring.oauth2.repository.PostsRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsServiceTest {

	@Autowired
	PostsRepository postsRepository;

	@Test
	public void 게시글_불러오기(){
		String title = "test_title_1";
		String content = "test_content_1";

		postsRepository.save(Posts.builder()
				.title(title)
				.content(content)
		.author("sodoboy@gmail.com").build());

		List<Posts> postsList = postsRepository.findAll();
		log.info(postsList.toString());
		Posts posts = postsList.get(0);

		assertEquals(posts.getTitle(), title);
		assertEquals(posts.getContent(), content);

	}

}