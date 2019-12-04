package br.com.hbsis.linhaCategoria;

import br.com.hbsis.categoria.Categoria;

import javax.persistence.*;

@Entity
@Table(name = "linha_categoria")
public class LinhaCategoria {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "cod_linha_categoria", unique = true, nullable = false, length = 10)
        private String codLinhaCategoria;

        @Column(name = "nome_linha_categoria", unique = true, nullable = false, length = 255)
        private String nomeLinhaCategoria;

        @ManyToOne
        @JoinColumn(name = "categoria_id", referencedColumnName = "id")
        private Categoria categoriaId;

        public LinhaCategoria() {
        }

        public LinhaCategoria(Long id, String codLinhaCategoria, String nomeLinhaCategoria, Categoria categoriaId) {
            this.id = id;
            this.codLinhaCategoria = codLinhaCategoria;
            this.nomeLinhaCategoria = nomeLinhaCategoria;
            this.categoriaId = categoriaId;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getCodLinhaCategoria() {
            return codLinhaCategoria;
        }

        public void setCodLinhaCategoria(String codLinhaCategoria) {
            this.codLinhaCategoria = codLinhaCategoria;
        }

        public String getNomeLinhaCategoria() {
            return nomeLinhaCategoria;
        }

        public void setNomeLinhaCategoria(String nomeLinhaCategoria) {
            this.nomeLinhaCategoria = nomeLinhaCategoria;
        }

        public Categoria getCategoriaId() {
            return categoriaId;
        }

        public void setCategoriaId(Categoria categoriaId) {
            this.categoriaId = categoriaId;
        }

        @Override
        public String toString() {
            return "Linha Categoria{" +
                    "ID:" + id +
                    "Codigo Linha Categoria= " + codLinhaCategoria + '\'' +
                    ", Nome Linha Categoria='" + nomeLinhaCategoria + '\'' +
                    ", Categoria Id ='" + categoriaId + '\'' +
                    '}';
        }
    }

