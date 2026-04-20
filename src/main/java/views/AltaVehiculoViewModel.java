
package views;

import domain.VehiculoTipo;
import java.util.ArrayList;

public class AltaVehiculoViewModel {
    private String patente;
    private String nombreCompleto;
    private String dni;
    private String telefono;
    private String marca;
    private String modelo;
    private VehiculoTipo tipo;
    private int anio;
    private double capacidadCarga;
    private double kmPorLitroOkwh;
    private double litrosExtra;
    private double kmARecorrer;
    private String sucursal;

    private ArrayList<String> marcas;
    private ArrayList<String> sucursales;

    public AltaVehiculoViewModel() {
        marcas = Controlador.getMarcas();
        sucursales = Controlador.getSucursales();
    }

    // Listas para cargar los combos
    public ArrayList<String> getMarcas() { return marcas; }
    public ArrayList<String> getSucursales() { return sucursales; }

    // Getters y Setters
    public String getPatente() { return patente; }
    public void setPatente(String patente) { this.patente = patente; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String n) { this.nombreCompleto = n; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public VehiculoTipo getTipo() { return tipo; }
    public void setTipo(VehiculoTipo tipo) { this.tipo = tipo; }

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    public double getCapacidadCarga() { return capacidadCarga; }
    public void setCapacidadCarga(double c) { this.capacidadCarga = c; }

    public double getKmPorLitroOkwh() { return kmPorLitroOkwh; }
    public void setKmPorLitroOkwh(double v) { this.kmPorLitroOkwh = v; }

    public double getLitrosExtra() { return litrosExtra; }
    public void setLitrosExtra(double l) { this.litrosExtra = l; }

    public double getKmARecorrer() { return kmARecorrer; }
    public void setKmARecorrer(double k) { this.kmARecorrer = k; }

    public String getSucursal() { return sucursal; }
    public void setSucursal(String sucursal) { this.sucursal = sucursal; }

    public boolean esCombustible() {
        return tipo == VehiculoTipo.COMBUSTIBLE;
    }

    public void guardar() {
        double kwh = esCombustible() ? 0 : kmPorLitroOkwh;
        double kmLitro = esCombustible() ? kmPorLitroOkwh : 0;
        Controlador.agregarVehiculo(tipo, patente, marca, modelo, anio,
                capacidadCarga, sucursal, kwh, kmLitro, litrosExtra);
    }
}
