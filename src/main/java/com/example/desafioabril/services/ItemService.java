package com.example.desafioabril.services;

import com.example.desafioabril.dto.ItemDTO;
import com.example.desafioabril.exceptions.ItemDuplicadoException;
import com.example.desafioabril.exceptions.ItemNotFoundException;
import com.example.desafioabril.exceptions.UsuarioDuplicadoException;
import com.example.desafioabril.exceptions.UsuarioNotFoundException;
import com.example.desafioabril.model.Item;
import com.example.desafioabril.model.Usuario;
import com.example.desafioabril.repository.ItemRepository;
import com.example.desafioabril.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public ItemDTO cadastrarItem(ItemDTO itemDTO) {
        var usuarioLogado = getUsuarioLogado();

        itemRepository.findByNome(itemDTO.getNome()).ifPresent(item -> {
            throw new ItemDuplicadoException("Item com este nome: " + itemDTO.getNome() + " já existe");
        });

        Item novoItem = new Item(
                itemDTO.getNome(),
                LocalDateTime.now(),
                itemDTO.getQuantidade(),
                usuarioLogado
        );

        itemRepository.save(novoItem);
        return new ItemDTO(novoItem.getNome(), novoItem.getQuantidade(), usuarioLogado.getId_usuario());
    }

    public List<Item> listarItems() {
        var usuarioLogado = getUsuarioLogado();

        return itemRepository.findByUsuario(usuarioLogado);
    }

    public ItemDTO editarItem(Long id, ItemDTO itemDTO) {
        Item itemExistente = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item com ID " + id + "não encontrado"));

        itemExistente.setNome(itemDTO.getNome());
        itemExistente.setQuantidade(itemDTO.getQuantidade());

        itemRepository.save(itemExistente);

        return new ItemDTO(itemDTO.getNome(), itemDTO.getQuantidade(), itemDTO.getUsuarioId());
    }

    public void excluirItem(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new ItemNotFoundException("Item não encontrado");
        }

        itemRepository.deleteById(id);
    }

    private Usuario getUsuarioLogado() {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
