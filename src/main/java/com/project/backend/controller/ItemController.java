package com.project.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.backend.model.Item;
import com.project.backend.service.ItemService;
import com.project.backend.util.ResponseStructure;
@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class ItemController {

	@Autowired
	private ItemService service;

	@PostMapping("/item/{menuId}")
	public ResponseEntity<ResponseStructure<Item>> saveItem(@RequestBody Item item, @PathVariable int menuId) {
		return service.saveItem(item, menuId);
	}

	@PutMapping("/item")
	public ResponseEntity<ResponseStructure<Item>> updateItem(@RequestBody Item item, @RequestParam int id) {
		return service.updateItem(item, id);
	}

	@DeleteMapping("/item/{itemId}")
	public ResponseEntity<ResponseStructure<Item>> deleteItem(@PathVariable int itemId) {
		return service.deleteItem(itemId);
	}

	@GetMapping("/item/{id}")
	public ResponseEntity<ResponseStructure<Item>> getItemById(@PathVariable int id) {
		return service.getItemById(id);
	}

	@GetMapping("/item")
	public ResponseEntity<ResponseStructure<List<Item>>> findAllItem() {
		return service.findAllItem();
	}
}
