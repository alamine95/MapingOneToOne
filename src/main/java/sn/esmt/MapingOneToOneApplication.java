package sn.esmt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sn.esmt.domaine.*;
import sn.esmt.repository.*;

@SpringBootApplication
public class MapingOneToOneApplication implements CommandLineRunner {
	
	@Autowired
	private CitoyenRepository repoCitoyen;
	
	@Autowired
	private PasseportRepository repoPasseport;
	
	public static void main(String[] args) {
		SpringApplication.run(MapingOneToOneApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//sauver();
		afficherPasseportCitoyen();
		afficherCitoyenPasseport();
	}
	private void sauver()
	{
		Citoyen c1 = new Citoyen();
		c1.setNumci("100E");
		c1.setNom("aaa1");
		c1.setPrenom("bbb1");
		
		Citoyen c2 = new Citoyen();
		c2.setNumci("200E");
		c2.setNom("aaa2");
		c2.setPrenom("bbb2");
		repoCitoyen.save(c1);
		repoCitoyen.save(c2);
		
		Passeport p1 = new Passeport();
		p1.setType("PD");
		
		Passeport p2 = new Passeport();
		p2.setType("PO");
		
		// Associer p1 à c1
		p1.setCitoyen(c1);
		c1.setPasseport(p1);
		
		// Associer p2 à c2
		c2.setPasseport(p2);
		p2.setCitoyen(c2);
		
		repoPasseport.save(p1);
		repoPasseport.save(p2);
	}
	
	private void afficherPasseportCitoyen()
	{
		List<Passeport> liste = repoPasseport.findAll();
		for (Passeport x: liste)
		{
			System.out.println("P->C"+"num.passeport:"+x.getNumpass()+" numero ci:"+x.getCitoyen().getNumci());
		}
	}
	
	private void afficherCitoyenPasseport()
	{
		List<Citoyen> liste = repoCitoyen.findAll();
		for (Citoyen x: liste)
		{
			System.out.println("C->P"+"num.ci:"+x.getNumci()+" numero pass:"+x.getPasseport().getNumpass());
		}
	}

}
