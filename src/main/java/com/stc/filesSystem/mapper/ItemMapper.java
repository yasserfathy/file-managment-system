package com.stc.filesSystem.mapper;

import com.stc.filesSystem.dto.ItemDto;
import com.stc.filesSystem.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface ItemMapper {
    /** Remember to run "mvn clean install"  to trigger the MapStruct processing for every New Mapper you create.*/

    @Mappings({})
    ItemDto convertToDto(Item item);

    @Mappings({})
    Item convertToModel(ItemDto itemDto);
}

