package com.example.rxbasicskatakotlin.service

import com.example.rxbasicskatakotlin.model.Country
import com.example.rxbasicskatakotlin.model.Person
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import java.util.concurrent.FutureTask

/**
 * Created by Furkan on 2019-09-13
 */

internal interface Service {

    fun countryNameInCapitals(country: Country): Single<String>

    fun countCountries(countries: List<Country>): Single<Long>

    fun listPopulationOfEachCountry(countries: List<Country>?): Observable<Long>

    fun listNameOfEachCountry(countries: List<Country>): Observable<String>

    fun listOnly3rdAnd4thCountry(countries: List<Country>): Observable<Country>

    fun isAllCountriesPopulationMoreThanOneMillion(countries: List<Country>): Single<Boolean>

    fun listPopulationMoreThanOneMillion(countries: List<Country>): Observable<Country>

    /**
     * @param countriesFromNetwork an async task which is sometimes very very slow
     * @return the filtered values from the [FutureTask] or an [Observable.empty] if there are no values within 1 second
     */

    fun listPopulationMoreThanOneMillionWithTimeoutFallbackToEmpty(
        countriesFromNetwork: FutureTask<List<Country>>
    ): Observable<Country>

    fun getCurrencyUsdIfNotFound(countryName: String, countries: List<Country>): Observable<String>

    fun sumPopulationOfCountries(countries: List<Country>): Observable<Long>

    fun sumPopulationOfCountries(
        countryObservable1: Observable<Country>,
        countryObservable2: Observable<Country>
    ): Observable<Long>

    fun mapCountriesToNamePopulation(countries: List<Country>): Single<Map<String, Long>>

    fun areEmittingSameSequences(
        countryObservable1: Observable<Country>,
        countryObservable2: Observable<Country>
    ): Single<Boolean>

    fun isLastCountryHasName(countries: List<Country>): Maybe<Boolean>

    fun zipOnly3rdAnd4thCountry(countries: List<Country>): Observable<Pair<Country, Country>>

    fun writeCountriesFirstThenWritePeoples(
        countries: Observable<List<Country>>,
        peoples: Observable<List<Person>>
    ): Observable<List<Any>>

    fun sumPeoplesAgesUsingScanOperator(
        peoples: List<Person>
    ): Observable<Int>

    fun listOnly1stAnd6thPeopleNamesUsingBuffer(
        peoples: List<Person>
    ): Observable<MutableList<String>>?

    fun returnStringWith4SecTimeout(): Observable<String>
}