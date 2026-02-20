package agenda;

import agenda.Entidades.Contacto;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ClientePrueba
{
	private static final String BASE_URL = "http://localhost:8080/contactos";

	public static void main(String[] args) {
		Contacto nuevo = new Contacto("Juan", "987654321");
		ClientePrueba c = new ClientePrueba();
		c.realizarPruebas(nuevo);
	}

	public void realizarPruebas(Contacto nuevoContacto) {
		//POST
		nuevoContacto = agregarContacto(nuevoContacto);
		System.out.println("Contacto agregado: " +nuevoContacto);

		//GET todos
		listarContactos();

		//GET por id
		obtenerContactoPorId(nuevoContacto.getId());

		//parte 2 - PUT cambiar telefono
		nuevoContacto.setTelefono("622947560");
		Contacto actualizado = modificarContacto(nuevoContacto.getId(), nuevoContacto);
		System.out.println("Contacto actualizado: " +actualizado);

		//Comprobar con GET
		obtenerContactoPorId(nuevoContacto.getId());

		//DELETE
		eliminarContacto(nuevoContacto.getId());
		System.out.println("Contacto eliminado con ID: " +nuevoContacto.getId());

		//GET todos
		listarContactos();
	}

	private Contacto agregarContacto(Contacto contacto) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Contacto> response =
				restTemplate.postForEntity(BASE_URL, contacto, Contacto.class);
		return response.getBody();
	}

	private void listarContactos() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Contacto[]> response =
				restTemplate.getForEntity(BASE_URL, Contacto[].class);

		Contacto[] contactos = response.getBody();
		System.out.println("Lista de contactos:");
		if(contactos != null) {
			for(Contacto c : contactos) {
				System.out.println(c);
			}
		}
	}

	private void obtenerContactoPorId(Long id) {
		RestTemplate restTemplate = new RestTemplate();
		Contacto contacto = restTemplate.getForObject(BASE_URL + "/" + id, Contacto.class);
		System.out.println("Contacto obtenido por ID: "+contacto);
	}

	private void eliminarContacto(Long id) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(BASE_URL + "/" +id);
	}

	//metodo PUT parte 2
	private Contacto modificarContacto (Long id, Contacto contacto) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Contacto> requestEntity = new HttpEntity<>(contacto, headers);

		ResponseEntity<Contacto> response =
				restTemplate.exchange(BASE_URL + "/" + id, HttpMethod.PUT,
						requestEntity, Contacto.class);
		return response.getBody();
	}
}
