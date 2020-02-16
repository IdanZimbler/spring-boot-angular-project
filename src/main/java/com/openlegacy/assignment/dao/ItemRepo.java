package com.openlegacy.assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openlegacy.assignment.model.Item;

public interface ItemRepo extends JpaRepository<Item, Integer>{

}
