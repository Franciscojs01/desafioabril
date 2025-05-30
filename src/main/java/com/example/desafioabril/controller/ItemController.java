package com.example.desafioabril.controller;

import com.example.desafioabril.dto.ItemCadastroDTO;
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
    public ResponseEntity<ItemCadastroDTO> cadastrarItem(@RequestBody ItemCadastroDTO itemDto) {
        ItemCadastroDTO itemDTO = itemService.cadastrarItem(itemDto);
        return ResponseEntity.ok().body(itemDTO);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ItemDTO>> listarItems() {
        List<Item> items = itemService.listarItems();
        List<ItemDTO> itemDTOs = items.stream().map(ItemDTO::new).toList();
        return ResponseEntity.ok(itemDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemCadastroDTO> editarItem(@PathVariable Long id, @RequestBody ItemCadastroDTO itemDto) {
        ItemCadastroDTO edicaoItem = itemService.editarItem(id, itemDto);
        return ResponseEntity.ok().body(edicaoItem);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletarItem(@PathVariable Long id) {
        itemService.excluirItem(id);
        return ResponseEntity.ok().build();
    }
}

