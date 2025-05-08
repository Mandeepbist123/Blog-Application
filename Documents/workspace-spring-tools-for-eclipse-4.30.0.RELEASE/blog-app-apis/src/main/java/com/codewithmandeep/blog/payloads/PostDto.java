package com.codewithmandeep.blog.payloads;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.codewithmandeep.blog.entities.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	private Integer postid;
	
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addedSDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	private Set<CommentDto> comments=new HashSet<>();

}
