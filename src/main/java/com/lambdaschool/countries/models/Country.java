package com.lambdaschool.countries.models;

import javax.persistence.*;

@Entity
@Table(name = "countries")
public class Country
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long countryid;
    private String name;
    private long population;
    private int landmasskm2;
    private double medianage;

    public Country(
        String name,
        long population,
        int landmasskm2,
        double medianage)
    {
        this.name = name;
        this.population = population;
        this.landmasskm2 = landmasskm2;
        this.medianage = medianage;
    }

    public Country()
    {
        // required by JPA
    }

    public long getCountryid()
    {
        return countryid;
    }

    public void setCountryid(long countryid)
    {
        this.countryid = countryid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public long getPopulation()
    {
        return population;
    }

    public void setPopulation(long population)
    {
        this.population = population;
    }

    public int getLandmasskm2()
    {
        return landmasskm2;
    }

    public void setLandmasskm2(int landmasskm2)
    {
        this.landmasskm2 = landmasskm2;
    }

    public double getMedianage()
    {
        return medianage;
    }

    public void setMedianage(double medianage)
    {
        this.medianage = medianage;
    }

    @Override
    public String toString()
    {
        return "Country{" +
            "countryid=" + countryid +
            ", name='" + name + '\'' +
            ", population=" + population +
            ", landmasskm2=" + landmasskm2 +
            ", medianage=" + medianage +
            '}';
    }
}
