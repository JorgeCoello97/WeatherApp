package com.jorch.weatherapp.domain.commands

interface Command<out T> {
    fun execute(): T
}