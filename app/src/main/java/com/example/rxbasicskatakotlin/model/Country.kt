package com.example.rxbasicskatakotlin.model

/**
 * Created by Furkan on 2019-09-13
 */

class Country(val name: String, val currency: String, val population: Long) {

    override fun toString(): String {
        return "Country{" +
            "name='" + name + '\''.toString() +
            ", currency='" + currency + '\''.toString() +
            ", population=" + population +
            '}'.toString()
    }
}