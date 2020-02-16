package com.openlegacy.assignment.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openlegacy.assignment.dao.ItemRepo;
import com.openlegacy.assignment.model.Item;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ItemController {

	@Autowired
	ItemRepo repo;

//	@GetMapping("/")
//	public String home() {
//		return "home.jsp";
//	}
	
	//List of the inventory items list (item no, name, amount, inventory code)
	@GetMapping("/getListOfItems")
	public List<Item> getListOfItems(Model model) {
		return repo.findAll();
	}
	
	//Read item details (by item no)
	@GetMapping("/getItem/{itemNo}")
	public List<Item> getItem(@PathVariable int itemNo) {
		List<Item> listToReturn = new ArrayList<Item>();
		Optional<Item> optItem = repo.findById(itemNo);
		if(optItem.isPresent()) {
			listToReturn.add(optItem.get());
		}
		return listToReturn;
	}
	
	//Withdrawal quantity of a specific item from stock
	@PutMapping("/withdrawalItem/{itemNo}/{amountToWithdrawal}")
	public Item withdrawalItem(@PathVariable int itemNo, @PathVariable int amountToWithdrawal) {
		Item item = changeAmount(itemNo,amountToWithdrawal,"withdrawal");
		return item;
	}
	
	//Deposit quantity of a specific item to stock
	@PutMapping("/depositItem/{itemNo}/{amountToDeposit}")
	public Item depositItem(@PathVariable int itemNo, @PathVariable int amountToDeposit) {
		Item item = changeAmount(itemNo,amountToDeposit,"deposit");
		return item;
	}
	
	//Add item to stock
	
	@PostMapping("/addItem")
	public Item addItem(@RequestBody Item item) {
		repo.save(item);
		return item;
	}
	
	//Delete an item from stock  
	@DeleteMapping("/deleteItem/{itemNo}")
	public List<Item> deleteItem(@PathVariable int itemNo) {
		Item item = repo.getOne(itemNo);
		repo.delete(item);
		return repo.findAll();
		
	}
	
	private Item changeAmount(int itemNo,int amount,String type) {
		Optional<Item> optItem = repo.findById(itemNo);
		Item item = new Item();
		if(optItem.isPresent()) {
			item = optItem.get();
			int newAmount = getNewAmount(item.getAmount(),amount,type);
			item.setAmount(newAmount);
			repo.save(item);
		}
		return item;
		
	}
	
	private int getNewAmount(int amount1, int amount2, String type) {
		int amountToReturn = 0;
		switch (type){
		case "withdrawal":
			amountToReturn = amount1-amount2;
			break;
		case "deposit":
			amountToReturn = amount1+amount2;
			break;
		}
		return amountToReturn;
	}

}
