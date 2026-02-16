package agenda.Controladores;

import agenda.Entidades.Contacto;
import agenda.Servicios.ContactoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contactos")
public class ContactoControlador {
    private final ContactoServicio contactoServicio;

    @Autowired
    public ContactoControlador(ContactoServicio contactoServicio) {
        this.contactoServicio = contactoServicio;
    }

    @GetMapping
    public List<Contacto> obtenerTodos() {
        return contactoServicio.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contacto> obtenerPorId(@PathVariable Long id) {
        Contacto c = contactoServicio.obtenerPorId(id);
        if (c == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(c);
    }

    @PostMapping
    public Contacto guardar(@RequestBody Contacto contacto) {
        return contactoServicio.guardar(contacto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        contactoServicio.eliminar(id);
    }

    //parte 2, PUT para actualizar teléfono
    @PutMapping("/{id}")
    public ResponseEntity<Contacto> actualizarTelefono(@PathVariable Long id, @RequestBody Contacto contacto)
    {
        Contacto actualizado = contactoServicio.actualizarTelefono(id, contacto.getTelefono());
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }
}
