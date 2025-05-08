package com.example.desafioabril.controller;

import com.example.desafioabril.dto.ItemDTO;
import com.example.desafioabril.model.Item;
import com.example.desafioabril.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping("/cadastro")
    public ResponseEntity<ItemDTO> cadastrarItem(@RequestBody ItemDTO itemDto) {
        ItemDTO itemDTO = itemService.cadastrarItem(itemDto);
        return ResponseEntity.ok().body(itemDTO);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Item>> listarItems() {
        List<Item> items = itemService.listarItems();
        return ResponseEntity.ok(items);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDTO> editarItem(@PathVariable Long id, @RequestBody ItemDTO itemDto) {
        ItemDTO edicaoItem = itemService.editarItem(id, itemDto);
        return ResponseEntity.ok().body(edicaoItem);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletarItem(@PathVariable Long id) {
        itemService.excluirItem(id);
        return ResponseEntity.ok().build();
    }
}

