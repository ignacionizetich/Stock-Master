package org.example.backend.Controller.Api;


import org.example.backend.Models.ItemType;
import org.example.backend.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<String> add(@RequestBody ItemDTO itemRequest) {
        try{
            Item newItem = new Item();
            newItem.setItemName(itemRequest.itemName());
            newItem.setStock(itemRequest.stock());
            newItem.setPrice(itemRequest.price());
            newItem.setItemType(itemRequest.itemType());
            itemService.addItem(newItem);
            return ResponseEntity.ok().body("The item has been added to the catalog!");
        }catch (ItemException e){
            return org.springframework.http.ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return org.springframework.http.ResponseEntity.status(500).body(null);
        }
    }

    private record ItemDTO(String itemName, int stock, double price, ItemType itemType){}

}
