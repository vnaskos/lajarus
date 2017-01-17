package com.bugbusters.lajarus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bugbusters.lajarus.entity.ItemEntity;
import com.bugbusters.lajarus.service.ItemService;
import com.bugbusters.lajarus.validation.ItemValidator;
import com.bugbusters.lajarus.validation.ValidationErrorBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controller for item management (support of CRUD actions)
 */
@RestController
@RequestMapping(value = "/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemValidator itemValidator;

    /**
     * Get a specific item by the provided item iD
     * 
     * @param id item ID
     * @return item
     * @throws Exception 
     */
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public ItemEntity getItemById(@PathVariable long id) throws Exception {
        return itemService.getItemById(id);
    }

    /**
     * Get items with a certain item type
     * (example Attack)
     * 
     * @param type
     * @return items
     * @throws Exception 
     */
    @RequestMapping(value = "/type/{type}", method = RequestMethod.GET)
    public ItemEntity getItemByType(@PathVariable String type) throws Exception {
        return itemService.getItemByType(type);
    }

    /**
     * Create a new item
     * this endpoint is used for administrative reasons
     * 
     * @param itemEntity required item info
     * @param errors validation errors on item info
     * @return item with ID
     * @throws Exception 
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createItem(@RequestBody ItemEntity itemEntity, Errors errors) throws Exception {
        itemValidator.validate(itemEntity, errors);
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest()
                    .body(ValidationErrorBuilder
                            .fromBindingErrors(errors));
        }

        itemService.createItem(itemEntity);
        return ResponseEntity.ok(itemEntity);
    }

    /**
     * Delete an item by id
     * 
     * @param id 
     */
    @RequestMapping(value = "/delete/id/{id}", method = RequestMethod.POST)
    public void deleteItem(@PathVariable long id) {
        itemService.deleteItem(id);
    }

}
