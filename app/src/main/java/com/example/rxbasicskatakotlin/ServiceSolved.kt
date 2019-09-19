package com.example.rxbasicskatakotlin

import com.example.rxbasicskatakotlin.model.Country
import com.example.rxbasicskatakotlin.model.Person
import com.example.rxbasicskatakotlin.service.Service
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxkotlin.zipWith
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.FutureTask
import java.util.concurrent.TimeUnit

/**
 * Created by Furkan on 2019-09-13
 */

internal class ServiceSolved : Service {

    override fun countryNameInCapitals(country: Country): Single<String> {
        return Single.just(country.name.capitalize())
    }

    override fun countCountries(countries: List<Country>): Single<Long> {
        return Observable.fromIterable(countries)
            .count()
    }

    override fun listPopulationOfEachCountry(countries: List<Country>?): Observable<Long> {
        return Observable.fromIterable(countries)
            .flatMap { Observable.just(it.population) }
    }

    override fun listNameOfEachCountry(countries: List<Country>): Observable<String> {
        return Observable.fromIterable(countries)
            .flatMap { Observable.just(it.name) }
    }

    override fun listOnly3rdAnd4thCountry(countries: List<Country>): Observable<Country> {
        return Observable.fromIterable(countries)
            .skip(2)
            .take(2)
    }

    override fun isAllCountriesPopulationMoreThanOneMillion(countries: List<Country>): Single<Boolean> {
        return Observable.fromIterable(countries)
            .all { it.population > 1000000 }
    }

    override fun listPopulationMoreThanOneMillion(countries: List<Country>): Observable<Country> {
        return Observable.fromIterable(countries)
            .filter { it.population > 1000000 }
    }

    override fun listPopulationMoreThanOneMillionWithTimeoutFallbackToEmpty(
        countriesFromNetwork: FutureTask<List<Country>>
    ): Observable<Country> {
        return Observable.fromFuture(countriesFromNetwork, Schedulers.io())
            .flatMap { countriesList -> Observable.fromIterable(countriesList) }
            .filter { country -> country.population > 1000000 }
            .timeout(1, TimeUnit.SECONDS, Observable.empty())
    }

    override fun getCurrencyUsdIfNotFound(
        countryName: String,
        countries: List<Country>
    ): Observable<String> {
        return Observable.fromIterable(countries)
            .filter { country -> country.name == countryName }
            .map { country -> country.currency }
            .defaultIfEmpty("USD")
    }

    override fun sumPopulationOfCountries(countries: List<Country>): Observable<Long> {
        return Observable.fromIterable(countries)
            .map { it.population }
            .reduce { t1: Long, t2: Long -> t1 + t2 }
            .toObservable()
    }

    override fun sumPopulationOfCountries(
        countryObservable1: Observable<Country>,
        countryObservable2: Observable<Country>
    ): Observable<Long> {
        return Observable.merge(countryObservable1, countryObservable2)
            .map { it.population }
            .reduce { t1: Long, t2: Long -> t1 + t2 }
            .toObservable()
    }

    override fun mapCountriesToNamePopulation(countries: List<Country>): Single<Map<String, Long>> {
        return Observable.fromIterable(countries)
            .toMap(
                { country -> country.name },
                { country -> country.population }
            )
    }

    override fun areEmittingSameSequences(
        countryObservable1: Observable<Country>,
        countryObservable2: Observable<Country>
    ): Single<Boolean> {
        return Observable.sequenceEqual(countryObservable1, countryObservable2)
    }

    override fun isLastCountryHasName(countries: List<Country>): Maybe<Boolean> {
        return Observable.fromIterable(countries)
            .lastElement()
            .map { it.name }
            .isEmpty
            .toMaybe()
    }

    override fun zipOnly3rdAnd4thCountry(countries: List<Country>): Observable<Pair<Country, Country>> {
        return Observable.fromIterable(countries)
            .skip(2)
            .take(1)
            .zipWith(
                Observable.fromIterable(countries)
                    .skip(3)
                    .take(1)
            )
    }

    override fun writeCountriesFirstThenWritePeoples(
        countries: Observable<List<Country>>,
        peoples: Observable<List<Person>>
    ): Observable<List<Any>> {
        return Observable.concat(countries, peoples)
    }

    override fun sumPeoplesAgesUsingScanOperator(peoples: List<Person>): Observable<Int> {
        return Observable.fromIterable(peoples)
            .map { it.age }
            .scan { t1: Int, t2: Int -> t1 + t2 }
    }

    override fun listOnly1stAnd6thPeopleNamesUsingBuffer(peoples: List<Person>): Observable<MutableList<String>>? {
        return Observable.fromIterable(peoples)
            .map { it.name }
            .buffer(1, 5)
    }

    override fun returnStringWith4SecTimeout(): Observable<String> {
        return Observable.just("1 Sec delay")
            .delay(4L, TimeUnit.SECONDS)
    }
}