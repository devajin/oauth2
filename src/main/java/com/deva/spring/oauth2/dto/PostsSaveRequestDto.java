/**
 * project name : oauth2
 * Created by deva.jin on 2020/11/18.
 */


package com.deva.spring.oauth2.dto;

import com.deva.spring.oauth2.entity.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

	private String title;
	private String content;
	private String author;

	@Builder
	public PostsSaveRequestDto(String title, String content, String author) {
		this.title = title;
		this.content = content;
		this.author = author;
	}

	public Posts toEntity(){
		return Posts.builder()
				.title(title)
				.content(content)
				.author(author)
				.build();
	}

}
