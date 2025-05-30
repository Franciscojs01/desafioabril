package com.example.desafioabril.dto;

import com.example.desafioabril.model.Item;

import java.time.LocalDateTime;

public class ItemDTO {
    private Long id_item;
    private String nome;
    private LocalDateTime data;
    private Integer quantidade;
    private Long id_usuario;

    public ItemDTO(Item item) {
        this.id_item = item.getId_item();
        this.nome = item.getNome();
        this.data = item.getData();
        this.quantidade = item.getQuantidade();
        this.id_usuario = item.getUsuario().getId_usuario();

    }

    public Long getId_item() {
        return id_item;
    }

    public void setId_item(Long id_item) {
        this.id_item = id_item;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }
}



