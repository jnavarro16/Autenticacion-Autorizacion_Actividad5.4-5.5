package agenda.Servicios;

import agenda.Entidades.Contacto;
import agenda.Repositorios.ContactoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactoServicioImpl implements ContactoServicio {
    private final ContactoRepositorio contactoRepositorio;

    @Autowired
    public ContactoServicioImpl(ContactoRepositorio contactoRepositorio) {
        this.contactoRepositorio = contactoRepositorio;
    }

    @Override
    public List<Contacto> obtenerTodos() {
        return contactoRepositorio.obtenerTodos();
    }

    @Override
    public Contacto obtenerPorId(Long id) {
        return contactoRepositorio.obtenerPorId(id);
    }

    @Override
    public Contacto guardar(Contacto contacto) {
        return contactoRepositorio.guardar(contacto);
    }

    @Override
    public void eliminar(Long id) {
        contactoRepositorio.eliminar(id);
    }

    //parte 2 - PUT
    @Override
    public Contacto actualizarTelefono(Long id, String telefono) {
        Contacto existente = contactoRepositorio.obtenerPorId(id);
        if (existente == null) return null;

        existente.setTelefono(telefono);
        return contactoRepositorio.guardar(existente);
    }

}
