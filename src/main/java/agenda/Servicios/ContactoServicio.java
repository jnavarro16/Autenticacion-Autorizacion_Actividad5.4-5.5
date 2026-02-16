package agenda.Servicios;

import agenda.Entidades.Contacto;

import java.util.List;

public interface ContactoServicio
{
    List<Contacto> obtenerTodos();
    Contacto obtenerPorId(Long id);
    Contacto guardar(Contacto contacto);
    void eliminar(Long id);

    //parte 2
    Contacto actualizarTelefono(Long id, String telefono);
}
