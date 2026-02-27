package org.example.backend.Service;

import org.example.backend.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.backend.Models.Item;
import org.example.backend.Exceptions.ItemException;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void addItem(Item item){
        if(itemRepository.findByItemName(item.getItemName()).isPresent()){
            throw new ItemException("Item with this name already exists.");
        }
        if (item.getItemName().isBlank()) {
            throw new ItemException("Item name cannot be blank.");
        }
        if (item.getPrice() < 0) {
            throw new ItemException("Price cannot be negative.");
        }
        if (item.getStock() < 0) {
            throw new ItemException("Stock cannot be negative.");
        }

        if (item.getItemType() == null || item.getItemType().getTypeName().isBlank()){
            throw new ItemException("The item Type cannot be null or the item Type cannot be blank");
        }

         itemRepository.save(item);
    }



}
