/**
 * Represents a single place name entry with linked data.
 * Stores the place name, municipality, province, and population.
 */
public class PlaceNameEntry {
    private final String placename;
    private final String municipality;
    private final String province;
    private final int population;

/**
     * Constructs a PlaceNameEntry with the given details.
     *
     * @param placename    the name of the place
     * @param municipality the municipality the place belongs to
     * @param province     the province the place belongs to
     * @param population   the population of the place
     */
public PlaceNameEntry (String placename, String municipality, String province, int population) {
    this.placename = placename;
    this.municipality = municipality;
    this.province = province;
    this.population = population;
    }

    /**
     * Returns the place name.
     *
     * @return the place name
     */
public String getPlaceName() {
        return placename;
    }

    /**
     * Returns the municipality.
     *
     * @return the municipality
     */
public String getMunicipality() {
        return municipality;
    }

    /**
     * Returns the province.
     *
     * @return the province
     */
public String getProvince() {
        return province;
    }

    /**
     * Returns the population.
     *
     * @return the population
     */
public int getPopulation() {
        return population;
    }

    /**
     * Returns a string representation of the entry.
     *
     * @return a formatted string containing all fields separated by " | "
     */
@Override
    public String toString() {
        return placename + " | " + municipality + " | " + province + " | " + population;
    }
}