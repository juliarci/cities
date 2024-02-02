package monprojet.dao;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import monprojet.entity.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@Log4j2 // Génère le 'logger' pour afficher les messages de trace
@DataJpaTest
public class CountryRepositoryTest {

    @Autowired
    private CountryRepository countryDAO;

    @Test
    void lesNomsDePaysSontTousDifferents() {
        log.info("On vérifie que les noms de pays sont tous différents ('unique') dans la table 'Country'");
        
        Country paysQuiExisteDeja = new Country("XX", "France");
        try {
            countryDAO.save(paysQuiExisteDeja); // On essaye d'enregistrer un pays dont le nom existe
            fail("On doit avoir une violation de contrainte d'intégrité (unicité)");
        } catch (DataIntegrityViolationException e) {
            // Si on arrive ici, c'est normal, l'exception attendue s'est produite
        }
    }

    @Test
    @Sql("test-data.sql") // On peut charger des données spécifiques pour un test
    void onSaitCompterLesEnregistrements() {
        log.info("On compte les enregistrements de la table 'Country'");
        int combienDePaysDansLeJeuDeTest = 3 + 2; // 3 dans data.sql, 1 dans test-data.sql
        long nombre = countryDAO.count();
        assertEquals(combienDePaysDansLeJeuDeTest, nombre, "On doit trouver 4 pays" );
    }
    @Test
    @Sql("test-data.sql") // On peut charger des données spécifiques pour un test
    void onSaitCalculerLesPopulations(){
        log.info("On calcule la population par pays");
        int idCountry = 3;
        Integer pop= countryDAO.popCalcul(idCountry);
        assertEquals(4220000, pop, "La population doit être de 4220000");
    }

    @Test
    @Sql("test-data.sql") // On peut charger des données spécifiques pour un test
    void onSaitTrouverLeNomDuPaysEtSaPop(){
        log.info("On récuprère le nom des pays et leur population");
        List<CountryRepository.CountryNamePop> listPays = countryDAO.countryPop();
        assertNotNull(listPays);
        for(CountryRepository.CountryNamePop country: listPays){
            System.out.println("Le pays est "+country.getname()+ " sa population est "+ country.getpop());
        }
    }
}
