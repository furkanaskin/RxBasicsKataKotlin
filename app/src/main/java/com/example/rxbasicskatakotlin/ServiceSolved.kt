package com.example.rxbasicskatakotlin

import com.example.rxbasicskatakotlin.model.Country
import com.example.rxbasicskatakotlin.model.Person
import com.example.rxbasicskatakotlin.service.Service
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import java.util.concurrent.FutureTask

/**
 * Created by Furkan on 2019-09-13
 */

internal class ServiceSolved : Service {
    override fun countryNameInCapitals(country: Country): Single<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun countCountries(countries: List<Country>): Single<Long> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun listPopulationOfEachCountry(countries: List<Country>?): Observable<Long> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun listNameOfEachCountry(countries: List<Country>): Observable<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun listOnly3rdAnd4thCountry(countries: List<Country>): Observable<Country> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isAllCountriesPopulationMoreThanOneMillion(countries: List<Country>): Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun listPopulationMoreThanOneMillion(countries: List<Country>): Observable<Country> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun listPopulationMoreThanOneMillionWithTimeoutFallbackToEmpty(countriesFromNetwork: FutureTask<List<Country>>): Observable<Country> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCurrencyUsdIfNotFound(
        countryName: String,
        countries: List<Country>
    ): Observable<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun sumPopulationOfCountries(countries: List<Country>): Observable<Long> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun sumPopulationOfCountries(
        countryObservable1: Observable<Country>,
        countryObservable2: Observable<Country>
    ): Observable<Long> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun mapCountriesToNamePopulation(countries: List<Country>): Single<Map<String, Long>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun areEmittingSameSequences(
        countryObservable1: Observable<Country>,
        countryObservable2: Observable<Country>
    ): Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isLastCountryHasName(countries: List<Country>): Maybe<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun zipOnly3rdAnd4thCountry(countries: List<Country>): Observable<Pair<Country, Country>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun writeCountriesFirstThenWritePeoples(
        countries: Observable<List<Country>>,
        peoples: Observable<List<Person>>
    ): Observable<List<Any>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun sumPeoplesAgesUsingScanOperator(peoples: List<Person>): Observable<Int> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun listOnly1stAnd6thPeopleNamesUsingBuffer(peoples: List<Person>): Observable<MutableList<String>>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun returnStringWith4SecTimeout(): Observable<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}