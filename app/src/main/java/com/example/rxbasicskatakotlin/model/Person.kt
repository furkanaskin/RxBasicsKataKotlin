package com.example.rxbasicskatakotlin.model

/**
 * Created by Furkan on 2019-09-16
 */

data class Person(val name: String, val username: String, val age: Int) {

    override fun toString(): String {
        return "Person{" +
            "name='" + name + '\''.toString() +
            ", username='" + username + '\''.toString() +
            ", age=" + age +
            '}'.toString()
    }
}