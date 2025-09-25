package org.example.backend.Controller.Api;

import org.example.backend.Exceptions.UserException;
import org.example.backend.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.example.backend.Models.Item;
import org.example.backend.Exceptions.ItemException;

@RestController
@RequestMapping("/api/items")
public class ItemApiController {

    private final ItemService itemService;

    @Autowired
    public ItemApiController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/add")
    public ResponseEntity<Item> add(@RequestBody Item item) {
        try{
            Item newItem = itemService.addItem(item);
            return org.springframework.http.ResponseEntity.ok(newItem);
        }catch (ItemException e){
            return org.springframework.http.ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return org.springframework.http.ResponseEntity.status(500).body(null);
        }
    }

}
