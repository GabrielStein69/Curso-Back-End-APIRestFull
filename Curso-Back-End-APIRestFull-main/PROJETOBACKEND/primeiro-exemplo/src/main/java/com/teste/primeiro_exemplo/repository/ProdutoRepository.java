package com.teste.primeiro_exemplo.repository;

import com.teste.primeiro_exemplo.PrimeiroExemploApplication;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import com.teste.primeiro_exemplo.model.Produto;
import org.springframework.stereotype.Repository;

@Repository
public class ProdutoRepository {
    
    private final PrimeiroExemploApplication primeiroExemploApplication;

    private ArrayList<Produto> produtos = new ArrayList<>();

    private Integer ultimoId = 0;

    ProdutoRepository(PrimeiroExemploApplication primeiroExemploApplication) {
        this.primeiroExemploApplication = primeiroExemploApplication;
    }

    /**
     * Metodo para retornar uma lista de produtos
     * @return Lista de produtos
     */

    public List<Produto> obterTodos(){
        return produtos;
    }

    /**
     * Método que retorna o produto encontrado pelo seu Id.
     * @param id do produto que será localizado.
     * @return Retorna um produto caso seja encontrado.
     */

    public Optional<Produto> obterPorId(Integer id){
        return produtos
            .stream()
            .filter(produto -> produto.getId() == id)
            .findFirst();
    }

    /**
     * Método para adicionar produto na lista.
     * @param produto que será adicionado.
     * @return retorna o produto que foi adicionado na lista.
     */

    public Produto adicionar(Produto produto){

        ultimoId++;

        produto.setId(ultimoId);
        produtos.add(produto);

        return produto;
    }

    /**
     * Método para deletar o produto por id.
     * @param id do produto a ser deletado.
     */

    public void deletar(Integer id){
        produtos.removeIf(produto -> produto.getId() == id);
    }

    /**
     * Método para atualizar o produto na lista.
     * @param produto que será atualizado.
     * @return Retorna o produto após atualizar a lsita.
     */

    public Produto atualizar(Produto produto) {
        //Eu tenho que primeiro encontrar o produto

        Optional<Produto> produtoEncontrado =
        obterPorId(produto.getId());

        if (produtoEncontrado.isEmpty()) {
            throw new InputMismatchException("Produto não encontrado");
        }
        //Depois remover o produto antigo da lista

        deletar(produto.getId());

        //Depois, adicionar o novo produto atualizado na lista.
        produtos.add(produto);
        //Isso é uma das formas de atualizar

        return produto;
    }
}