package com.example.rxbasicskatakotlin

import com.example.rxbasicskatakotlin.model.Country
import com.example.rxbasicskatakotlin.model.Person
import com.example.rxbasicskatakotlin.service.Service
import io.reactivex.Observable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.*
import java.util.concurrent.FutureTask
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

/**
 * Created by Furkan on 2019-09-13
 */

class SolvedTest {

    private lateinit var service: Service
    private lateinit var allCountries: List<Country>
    private lateinit var allPeoples: List<Person>

    @Before
    fun setUp() {
        service = ServiceSolved()
        allCountries = TestProvider.countries()
        allPeoples = TestProvider.peoples()
        RxJavaPlugins.reset()
    }

    @After
    fun tearDown() = RxJavaPlugins.reset()

    @Test
    fun rx_CountryNameInCapitals() {
        val testCountry = TestProvider.countries()[0]
        val expected = testCountry.name.capitalize()
        val testObserver = service
            .countryNameInCapitals(testCountry)
            .test()
        testObserver.assertNoErrors()
        testObserver.assertValue(expected)
    }

    @Test
    fun rx_CountriesSize() {
        val testCountry = TestProvider.countries()
        val expected = testCountry.size.toLong()
        val testObserver = service
            .countCountries(testCountry)
            .test()
        testObserver.assertNoErrors()
        testObserver.assertValue(expected)
    }

    @Test
    fun rx_listPopulationOfEachCountry() {
        val expectedResult = TestProvider.populationOfCountries()
        val testObserver = service
            .listPopulationOfEachCountry(allCountries)
            .test()
        testObserver?.assertValueSet(expectedResult)
        testObserver?.assertNoErrors()
    }

    @Test
    fun rx_ListNameOfEachCountry() {
        val expectedResult = TestProvider.namesOfCountries()
        val testObserver = service
            .listNameOfEachCountry(allCountries)
            .test()
        testObserver.assertValueSet(expectedResult)
        testObserver.assertNoErrors()
    }

    @Test
    fun rx_ListOnly3rdAnd4thCountry() {
        val expectedResult: ArrayList<Country> = ArrayList()
        expectedResult.add(allCountries[2])
        expectedResult.add(allCountries[3])

        val testObserver = service
            .listOnly3rdAnd4thCountry(allCountries)
            .test()
        testObserver?.assertValueSet(expectedResult)
        testObserver?.assertNoErrors()
    }

    @Test
    fun rx_IsAllCountriesPopulationMoreThanOneMillion_Positive() {
        val testObserver = service
            .isAllCountriesPopulationMoreThanOneMillion(
                TestProvider.countriesPopulationMoreThanOneMillion()
            )
            .test()
        testObserver?.assertResult(true)
        testObserver?.assertNoErrors()
    }

    @Test
    fun rx_IsAllCountriesPopulationMoreThanOneMillion_Negative() {
        val testObserver = service
            .isAllCountriesPopulationMoreThanOneMillion(allCountries)
            .test()
        testObserver.assertResult(false)
        testObserver.assertNoErrors()
    }

    @Test
    fun rx_ListPopulationMoreThanOneMillion() {
        val expectedResult = TestProvider.countriesPopulationMoreThanOneMillion()
        val testObserver = service
            .listPopulationMoreThanOneMillion(allCountries)
            .test()
        testObserver.assertValueSet(expectedResult)
        testObserver.assertNoErrors()
    }

    @Test
    fun rx_ListPopulationMoreThanOneMillionWithTimeoutFallbackToEmpty_When_NoTimeout() {
        val futureTask = FutureTask {
            TimeUnit.MILLISECONDS.sleep(100)
            allCountries
        }
        Thread(futureTask).start()
        val testObserver = service
            .listPopulationMoreThanOneMillionWithTimeoutFallbackToEmpty(futureTask)
            .test()
        val expectedResult = TestProvider.countriesPopulationMoreThanOneMillion()
        testObserver.awaitTerminalEvent()
        testObserver.assertComplete()
        testObserver.assertValueSet(expectedResult)
        testObserver.assertNoErrors()
    }

    @Test
    fun rx_ListPopulationMoreThanOneMillionWithTimeoutFallbackToEmpty_When_Timeout() {
        val futureTask = FutureTask {
            TimeUnit.HOURS.sleep(1)
            allCountries
        }
        Thread(futureTask).start()
        val testObserver = service
            .listPopulationMoreThanOneMillionWithTimeoutFallbackToEmpty(futureTask)
            .test()
        testObserver.awaitTerminalEvent()
        testObserver.assertComplete()
        testObserver.assertNoValues()
        testObserver.assertNoErrors()
    }

    @Test
    fun rx_GetCurrencyUsdIfNotFound_When_CountryFound() {
        val countryRequested = "Austria"
        val expectedCurrencyValue = "EUR"
        val testObserver = service
            .getCurrencyUsdIfNotFound(countryRequested, allCountries)
            .test()
        testObserver.assertResult(expectedCurrencyValue)
        testObserver.assertNoErrors()
    }

    @Test
    fun rx_GetCurrencyUsdIfNotFound_When_CountryNotFound() {
        val countryRequested = "Senegal"
        val expectedCurrencyValue = "USD"
        val testObserver = service
            .getCurrencyUsdIfNotFound(countryRequested, allCountries)
            .test()
        testObserver.assertResult(expectedCurrencyValue)
        testObserver.assertNoErrors()
    }

    @Test
    fun rx_sumPopulationOfCountries() {
        val testObserver = service
            .sumPopulationOfCountries(
                Observable.fromIterable(allCountries),
                Observable.fromIterable(allCountries)
            )
            .test()
        testObserver.assertResult(
            TestProvider.sumPopulationOfAllCountries()!! +
                    TestProvider.sumPopulationOfAllCountries()!!
        )
        testObserver.assertNoErrors()
    }

    @Test
    fun rx_SumPopulationOfCountries() {
        val testObserver = service
            .sumPopulationOfCountries(allCountries)
            .test()
        testObserver.assertResult(TestProvider.sumPopulationOfAllCountries()!!)
        testObserver.assertNoErrors()
    }

    @Test
    fun rx_MapCountriesToNamePopulation() {
        val values = service.mapCountriesToNamePopulation(allCountries).test()
        val expected = HashMap<String, Long>()
        for (country in allCountries) {
            expected[country.name] = country.population
        }
        values.assertResult(expected)
        values.assertNoErrors()
    }

    @Test
    fun rx_areEmittingSameSequences_Positive() {
        val testObserver = service
            .areEmittingSameSequences(
                Observable.fromIterable(allCountries),
                Observable.fromIterable(allCountries)
            )
            .test()
        testObserver.assertResult(true)
        testObserver.assertNoErrors()
    }

    @Test
    fun rx_areEmittingSameSequences_Negative() {
        val allCountriesDifferentSequence = ArrayList(allCountries)
        Collections.swap(allCountriesDifferentSequence, 0, 1)
        val testObserver = service
            .areEmittingSameSequences(
                Observable.fromIterable(allCountries),
                Observable.fromIterable(allCountriesDifferentSequence)
            )
            .test()
        testObserver.assertResult(false)
        testObserver.assertNoErrors()
    }

    @Test
    fun rx_isLastCountryHasName() {
        val testObserver = service
            .isLastCountryHasName(allCountries)
            .test()
        testObserver.assertResult(false)
        testObserver.assertNoErrors()
    }

    @Test
    fun rx_zipOnly3rdAnd4thCountry() {
        val testObserver = service
            .zipOnly3rdAnd4thCountry(allCountries)
            .test()
        testObserver.assertResult(Pair(allCountries[2], allCountries[3]))
        testObserver.assertNoErrors()
    }

    @Test
    fun rx_writeCountriesFirstThenWritePeoples() {
        val testObserver = service
            .writeCountriesFirstThenWritePeoples(
                Observable.fromArray(allCountries),
                Observable.fromArray(allPeoples)
            )
            .test()
        testObserver.assertResult(allCountries, allPeoples)
        testObserver.assertNoErrors()
    }

    @Test
    fun rx_sumPeoplesAgesUsingScanOperator() {
        val testObserver = service
            .sumPeoplesAgesUsingScanOperator(allPeoples)
            .test()
        val sumTotal = allPeoples.sumBy { it.age }
        testObserver.assertSubscribed()
        testObserver.assertValueCount(6)
        testObserver.assertValueAt(5, sumTotal)
        testObserver.assertNoErrors()
    }

    @Test
    fun rx_listOnly1stAnd6thPeopleNamesUsingBuffer() {
        val firstExpectedResult: MutableList<String> = mutableListOf()
        val secondExpectedResult: MutableList<String> = mutableListOf()

        firstExpectedResult.add(allPeoples[0].name)
        secondExpectedResult.add(allPeoples[5].name)

        val testObserver = service
            .listOnly1stAnd6thPeopleNamesUsingBuffer(allPeoples)
            ?.test()

        testObserver?.assertValueAt(0, firstExpectedResult)
        testObserver?.assertValueAt(1, secondExpectedResult)
        testObserver?.assertNoErrors()
    }

    @Test
    fun rx_returnStringWith4SecTimeout() {
        val testScheduler = TestScheduler()
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }

        val testObservable = service.returnStringWith4SecTimeout().test()
        testObservable.assertNotTerminated()
            .assertNoErrors()
            .assertValueCount(0)

        testScheduler.advanceTimeBy(3L, TimeUnit.SECONDS)
        testObservable.assertEmpty()
        testScheduler.advanceTimeBy(4L, TimeUnit.SECONDS)
        testObservable.assertValueCount(1)
        testObservable.assertValue(testObservable.values().first().toString())

        testObservable.dispose()
    }
}