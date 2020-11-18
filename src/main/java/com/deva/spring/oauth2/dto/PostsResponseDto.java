/**
 * project name : oauth2
 * Created by deva.jin on 2020/11/18.
 */


package com.deva.spring.oauth2.dto;

import com.deva.spring.oauth2.entity.Posts;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;


@ToString
@Getter
public class PostsResponseDto {
	private Long id;
	private String title;
	private String content;
	private String author;
	private LocalDateTime createDateTime;
	private LocalDateTime modifiyDateTime;

	public PostsResponseDto(Posts entity) {
		this.id =  entity.getId();
		this.title = entity.getTitle();
		this.content = entity.getContent();
		this.author = entity.getAuthor();
		this.modifiyDateTime = entity.getModifiyDateTime();
		this.createDateTime = entity.getCreateDateTime();

	}
}
