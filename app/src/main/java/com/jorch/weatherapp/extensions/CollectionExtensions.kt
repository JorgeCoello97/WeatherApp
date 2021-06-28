package com.jorch.weatherapp.extensions

fun<K,V : Any> MutableMap<K,V?>.toVargargArray(): Array<out Pair<K,V>> = map { Pair(it.key, it.value!!) }.toTypedArray()