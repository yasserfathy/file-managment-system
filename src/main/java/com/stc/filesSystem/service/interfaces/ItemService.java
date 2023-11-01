package com.stc.filesSystem.service.interfaces;


import com.stc.filesSystem.dto.ItemDto;
import com.stc.filesSystem.model.File;
import com.stc.filesSystem.model.Item;

import java.util.List;

public interface ItemService {


    Item getItemById(Long itemId) throws Exception;

    List<Item> getAllItems();

    Item saveItem(ItemDto itemDto) throws Exception;

    Item updateItem(Item item);

    void deleteItem(Long ItemId);
    
    File downloadFileById(ItemDto itemDto) throws Exception;


}
