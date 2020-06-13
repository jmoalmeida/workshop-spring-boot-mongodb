package com.joaomarcos.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaomarcos.workshopmongo.domain.Post;
import com.joaomarcos.workshopmongo.repository.PostRepository;
import com.joaomarcos.workshopmongo.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;

	public List<Post> findAll() {
		return repo.findAll();
	}

	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public Post insert(Post obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public Post update(Post obj) {
		Post newObj = repo.findById(obj.getId()).get();
		updateData(newObj, obj);
		return  repo.save(newObj);
	}
	
	private void updateData(Post newObj, Post obj) {
		newObj.setDate(obj.getDate());
		newObj.setTitle(obj.getTitle());
		newObj.setBody(obj.getBody());
		newObj.setAuthor(obj.getAuthor());
	}

//	public Post fromDTO(PostDTO objDto) {
//		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
//	}

}
