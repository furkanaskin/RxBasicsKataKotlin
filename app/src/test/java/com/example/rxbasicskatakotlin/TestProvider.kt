package com.example.rxbasicskatakotlin

import com.example.rxbasicskatakotlin.model.Country
import com.example.rxbasicskatakotlin.model.Person

/**
 * Created by Furkan on 2019-09-13
 */

internal object TestProvider {
    val CURRENCY_EUR = "EUR"
    val CURRENCY_PLN = "PLN"
    val CURRENCY_GBP = "GBP"
    val CURRENCY_UAH = "UAH"
    val CURRENCY_CHF = "CHF"
    val CURRENCY_TRY = "TRY"

    private val countries: ArrayList<Country> = ArrayList()
    private val peoples: ArrayList<Person> = ArrayList()

    init {
        countries.add(Country("Germany", CURRENCY_EUR, 80620000))
        countries.add(Country("France", CURRENCY_EUR, 66030000))
        countries.add(Country("United Kingdom", CURRENCY_GBP, 64100000))
        countries.add(Country("Poland", CURRENCY_PLN, 38530000))
        countries.add(Country("Ukraine", CURRENCY_UAH, 45490000))
        countries.add(Country("Austria", CURRENCY_EUR, 8474000))
        countries.add(Country("Switzerland", CURRENCY_CHF, 8081000))
        countries.add(Country("Luxembourg", CURRENCY_EUR, 576249))
        countries.add(Country("", CURRENCY_TRY, 81000000))

        peoples.add(Person("Furkan", "faskN", 22))
        peoples.add(Person("John", "john123", 20))
        peoples.add(Person("Jake", "jake_123", 30))
        peoples.add(Person("Eliot", "eliott", 33))
        peoples.add(Person("Mia", "miaaaaa", 39))
        peoples.add(Person("Samuel", "sammuel", 14))
    }

    fun countries(): List<Country> {
        return ArrayList(countries)
    }

    fun peoples(): List<Person> {
        return ArrayList(peoples)
    }

    fun countriesPopulationMoreThanOneMillion(): List<Country> {
        val result: ArrayList<Country> = ArrayList()
        for (country in countries) {
            if (country.population > 1000000) {
                result.add(country)
            }
        }
        return result
    }

    fun populationOfCountries(): List<Long> {
        val result: ArrayList<Long> = ArrayList(countries.size)
        for (country in countries) {
            result.add(country.population)
        }
        return result
    }

    fun namesOfCountries(): List<String> {
        val result: ArrayList<String> = ArrayList(countries.size)
        for (country in countries) {
            result.add(country.name)
        }
        return result
    }

    fun sumPopulationOfAllCountries(): Long? {
        var result: Long? = 0L
        for (country in countries) {
            result = result?.plus(country.population)
        }
        return result
    }
}