package com.example.desafioabril.dto;

public class ItemCadastroDTO {
    private String nome;
    private int quantidade;
    private Long usuarioId;

    public ItemCadastroDTO(String nome, int quantidade, Long usuarioId) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.usuarioId = usuarioId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
