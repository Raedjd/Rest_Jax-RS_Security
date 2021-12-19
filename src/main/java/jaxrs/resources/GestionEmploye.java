package jaxrs.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jaxrs.entities.Employe;
import jaxrs.filters.Secured;
@Secured
@Path("employes")
public class GestionEmploye {
	//répresente notre base de donnée
	static List<Employe> employes = new ArrayList<Employe>();

	/*public GestionEmploye() {
		super();
		// TODO Auto-generated constructor stub
		employes.add(new Employe("2222222", "Ali", "Ben ahmed"));
		employes.add(new Employe("2222222", "Ali", "Ben ahmed"));
	}*/
	@Secured
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	//@Produces("application/xml")
	public List<Employe> afficherListeEmployes() {
		if (employes == null) {
			System.out.println("hello");
			return null;
		} else {
			System.out.println("hello2");
			return employes;
		}
	}
	@Secured
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public String ajouterEmploye(Employe employe) {
		if (employes.add(employe))
			return "Employe ajoute";
		else
			return "Employe non ajoute";
	}

	@GET
	@Path("{c}")
	@Produces("application/xml")
	public Employe chercherEmploye(@PathParam("c") String cin) {
		int index = this.getIndexByCin(cin);
		if (index != -1) {
			return employes.get(index);
		}
		return null;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public String updateEmploye(Employe e) {
		int index = getIndexByCin(e.getCin());

		if (index != -1) {
			employes.set(index, e);
			return "update successful";
		}
		return "update unsuccessful";
	}

	@DELETE
	@Path("{c}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteEmploye(@PathParam("c") String cin) {
		int index = getIndexByCin(cin);
		if (index != -1) {
			employes.remove(index);
			return "true";
		} else
			return "false";
	}

	public int getIndexByCin(String cin) {
		for (Employe emp : employes) {
			if (emp.getCin().equals(cin))
				return employes.indexOf(emp);
		}
		return -1;
	}
}
