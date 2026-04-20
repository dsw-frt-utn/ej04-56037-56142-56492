package app;

import data.Persistencia;
import java.util.InvalidPropertiesFormatException;
import views.ListarVehiculosView;
import views.AltaVehiculoView;
import views.MenuPrincipal;

public class Program {
    public static void main(String[] args) throws IllegalArgumentException, InvalidPropertiesFormatException {
        Persistencia.inicializar();
        MenuPrincipal menu = new MenuPrincipal();
        menu.setVisible(true);
        ListarVehiculosView viewlista = new ListarVehiculosView();
        AltaVehiculoView viewalta = new AltaVehiculoView();
        menu.SetAltaref(viewalta);
        menu.SetListaref(viewlista);
        viewlista.SetMenuref(menu);
        viewalta.SetMenuref(menu);
        //view.setVisible(true);

        
    }
}
