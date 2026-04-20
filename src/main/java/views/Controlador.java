package views;

import data.Persistencia;
import domain.Vehiculo;
import domain.VehiculoTipo;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import domain.Marca;
import domain.Sucursal;
import domain.VehiculoCombustible;
import domain.VehiculoElectrico;

public class Controlador {
    
    public static ArrayList<VehiculoViewModel> getVehiculos(){
        ArrayList<VehiculoViewModel> vehiculos = new ArrayList<>();
        for(Vehiculo vehiculo : Persistencia.getVehiculos()) {
            vehiculos.add(new VehiculoViewModel(vehiculo));
        }
        return vehiculos;
    }
    
    public static double[] calcularConsumos(Map<String, Double> vehiculos){
        double consumoElectricos = 0;
        double consumoCombustible= 0;
        for(Map.Entry<String, Double> entry : vehiculos.entrySet()){
           double consumo = 0;
           Optional<Vehiculo> vehiculo = Persistencia.getVehiculo(entry.getKey());
           if(vehiculo.isPresent()){
               consumo = vehiculo.get().calcularConsumo(entry.getValue());
               consumoElectricos += vehiculo.get().esDe(VehiculoTipo.ELECTRICO) ? consumo : 0;
               consumoCombustible += vehiculo.get().esDe(VehiculoTipo.COMBUSTIBLE) ? consumo : 0;
           }
        }
        return new double[] {consumoElectricos, consumoCombustible};
    }
    public static ArrayList<String> getSucursales() {
    ArrayList<String> opciones = new ArrayList<>();
    for(Sucursal sucursal : Persistencia.getSucursales()) {
        opciones.add(sucursal.getDireccion() + " - " + sucursal.getCiudad());
    }
    return opciones;
}

public static ArrayList<String> getMarcas() {
    ArrayList<String> opciones = new ArrayList<>();
    for(Marca marca : Persistencia.getMarcas()) {
        opciones.add(marca.getMarca());
    }
    return opciones;
}

public static void agregarVehiculo(VehiculoTipo tipo, String patente, String nombreMarca,
        String modelo, int anio, double capacidadCarga, String codigoSucursal,
        double kwhBase, double kmPorLitro, double litrosExtra) {

    Sucursal sucursal = Persistencia.getSucursales().stream()
            .filter(s -> (s.getDireccion() + " - " + s.getCiudad()).equals(codigoSucursal))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Sucursal no encontrada"));

    Marca marca = Persistencia.getMarcas().stream()
            .filter(m -> m.getMarca().equals(nombreMarca))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Marca no encontrada"));

    Vehiculo vehiculo;
    if(tipo == VehiculoTipo.ELECTRICO) {
        vehiculo = new VehiculoElectrico(patente, marca, modelo, anio,
                capacidadCarga, sucursal, kwhBase);
    } else {
        vehiculo = new VehiculoCombustible(patente, marca, modelo, anio,
                capacidadCarga, sucursal, kmPorLitro, litrosExtra);
    }
    Persistencia.addVehiculo(vehiculo);
}
}
