package com.stc.filesSystem.service.impl;

import com.stc.filesSystem.dto.ItemDto;
import com.stc.filesSystem.enums.ItemType;
import com.stc.filesSystem.enums.PermissionLevel;
import com.stc.filesSystem.exception.ObjectNotFoundException;
import com.stc.filesSystem.exception.dto.CustomErrorDTO;
import com.stc.filesSystem.model.File;
import com.stc.filesSystem.model.Item;
import com.stc.filesSystem.model.Permission;
import com.stc.filesSystem.model.PermissionGroup;
import com.stc.filesSystem.repository.ItemRepository;
import com.stc.filesSystem.service.interfaces.FileService;
import com.stc.filesSystem.service.interfaces.ItemService;
import com.stc.filesSystem.service.interfaces.PermissionGroupService;
import com.stc.filesSystem.service.interfaces.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static com.stc.filesSystem.enums.ErrorCode.DATA_NOT_VALID;


@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    PermissionGroupService permissionGroupService;

    @Autowired
    FileService fileService;
    @Autowired
    PermissionService permissionService;

    @Override
    public Item getItemById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> {
                    throw new ObjectNotFoundException(String.format("No Item Exist with id [{}]", itemId));
                });
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item saveItem(ItemDto itemDto) throws Exception {


        if (itemDto.getType().equals(ItemType.SPACE)) {
            return createNewSpace(itemDto);

        } else if (itemDto.getType().equals(ItemType.FOLDER)) {
            return createNewFolder(itemDto);

        } else if (itemDto.getType().equals(ItemType.FILE)) {
           return createNewFile(itemDto);

        } else {
            CustomErrorDTO customErrorDTO = new CustomErrorDTO(String.format("item type can't be empty and must be one of these values [{}]", ItemType.values()), DATA_NOT_VALID.getValue());
            throw customErrorDTO;
        }
    }


    private Item createNewSpace(ItemDto itemDto) throws Exception {
        try {

            PermissionGroup permissionGroup = permissionGroupService.getPermissionGroupById(itemDto.getPermissionGroupId());
            Item newSpace = new Item(null, itemDto.getType(), itemDto.getName(), permissionGroup, null, null);
            return itemRepository.saveAndFlush(newSpace);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    private Item createNewFolder(ItemDto itemDto) {

        try {
            List<Permission> permissions = permissionService.getUserPermissionsUnderGroupX(itemDto.getUserName(), itemDto.getPermissionGroupId());

            if (permissions.stream().anyMatch(permission -> permission.getPermissionLevel().equals(PermissionLevel.EDIT))) {
                Item parentItem = getItemById(itemDto.getParentId());
                Item newfolder = new Item(null, itemDto.getType(), itemDto.getName(), parentItem.getPermissionGroup(), parentItem, null);
                return itemRepository.saveAndFlush(newfolder);

            } else {
                throw new ObjectNotFoundException(String.format("The UserEmail doesn't has access", itemDto.getUserName()));

            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    private Item createNewFile(ItemDto itemDto) throws Exception {
        try {

            Item parentItem =  getItemById(itemDto.getParentId());
            List<Permission> permissions = permissionService.getUserPermissionsUnderGroupX(itemDto.getUserName(), parentItem.getPermissionGroup().getId());

            if (permissions.stream().anyMatch(permission -> permission.getPermissionLevel().equals(PermissionLevel.EDIT))) {
                Item newfileItem = new Item(null, itemDto.getType(), itemDto.getName(), parentItem.getPermissionGroup(), parentItem, null);
                itemRepository.saveAndFlush(newfileItem);
               
                File newFile = new File(null, fileService.getBinaryData(itemDto.getFile()),itemDto.getFile().getContentType(), newfileItem);
                return fileService.saveFile(newFile).getItem();

            } else {
                throw new ObjectNotFoundException(String.format("The UserEmail doesn't has access", itemDto.getUserName()));
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public File downloadFileById(ItemDto itemDto) throws Exception {
        try {

            Item parentItem =  getItemById(itemDto.getParentId());
            List<Permission> permissions = permissionService.getUserPermissionsUnderGroupX(itemDto.getUserName(), parentItem.getPermissionGroup().getId());

            if (permissions.stream().anyMatch(permission -> permission.getPermissionLevel().equals(PermissionLevel.EDIT))) {
            	File file = fileService.getFileById(itemDto.getFileId());
                file.setBinary(fileService.decompressFile(file.getBinary()));
                
                return file;

            } else {
                throw new ObjectNotFoundException(String.format("The UserEmail doesn't has access", itemDto.getUserName()));
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Item updateItem(Item newItem) {
        try {
            Item oldItem = getItemById(newItem.getId());
            if (oldItem != null) {
                itemRepository.saveAndFlush(newItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return newItem;
    }

    @Override
    public void deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }
}