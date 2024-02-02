package monprojet.dao;

import monprojet.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import monprojet.entity.Country;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring 
//

public interface CountryRepository extends JpaRepository<Country, Integer> {

   @Query("SELECT SUM(city.population) AS sumPop FROM City city WHERE city.country.id= :idCountry")
    public Integer popCalcul(Integer idCountry);

   public Country findCountriesByName(String country);
   @Query("SELECT country.name AS name, SUM(city.population) AS popCalcul FROM Country country join country.cities city group by country.id")
    public List<CountryNamePop> countryPop() ;

   public interface CountryNamePop{
       String getname();
       Integer popCalcul();
   }
}
