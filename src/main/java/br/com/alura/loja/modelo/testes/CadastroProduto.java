package br.com.alura.loja.modelo.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroProduto {
    public static void main(String[] args) {
        //cadastrarProduto();

        Long id = 1l;
        EntityManager em = JPAUtil.gEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        Produto p = produtoDao.buscarPorId(1l);
        System.out.println(p.getPreco());

        List<Produto> todos = produtoDao.buscarPorNomeDaCategoria("Celulares");
        todos.forEach(p2 ->System.out.println(p2.getNome()));

        BigDecimal predoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("Iphone 16");
        System.out.println(predoDoProduto);
    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("celulares");
        Produto celular = new Produto("Iphone 16 PRO", "Excepcional", new BigDecimal("800"), celulares);

        EntityManager em = JPAUtil.gEntityManager();
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ProdutoDao produtoDao = new ProdutoDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);

        em.getTransaction().commit();
        em.close();
    }
}
