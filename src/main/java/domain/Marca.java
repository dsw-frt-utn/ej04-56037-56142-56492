package domain;

/**
 *
 * @author lopez
 */
public class Marca {
    private String marca;
    private String pais;

    public Marca(String marca, String pais) {
        this.marca = marca;
        this.pais = pais;
    }

    public String getMarca() {
        return marca;
    }

    public String getPais() {
        return pais;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
