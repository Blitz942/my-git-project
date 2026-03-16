//Class Declaration
public class PlaceNameEntry {
    private final String placename;
    private final String municipality;
    private final String province;
    private final int population;

//constructor
public PlaceNameEntry (String placename, String municipality, String province, int population) {
    this.placename = placename;
    this.municipality = municipality;
    this.province = province;
    this.population = population;
    }

public String getPlaceName() {
        return placename;
    }

public String getMunicipality() {
        return municipality;
    }

public String getProvince() {
        return province;
    }

public int getPopulation() {
        return population;
    }
@Override
    public toString() {
        return placename + " | " + municipality + " | " + province + " | " + population;
    }
}