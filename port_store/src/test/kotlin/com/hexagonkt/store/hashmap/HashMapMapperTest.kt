package com.hexagonkt.store.hashmap

import org.testng.annotations.Test
import java.util.*

class HashMapMapperTest {
    data class MappedClass (
        val oneString: String = "String",
        val oneBoolean: Boolean = true,
        val anInt: Int = 42,
        val oneLong: Long = 1_234L,
        val oneFloat: Float = 1.23F,
        val oneDouble: Double = 2.345,
        val oneList: List<String> = listOf("One", "Two"),
        val oneMap: Map<String, *> = mapOf("One" to 1, "Two" to true, "Three" to 0.12),
        val oneNullable: String? = null,
        val otherData: String = "other",
        val atHome: Int = 0,
        val onePlus: Char = 'c'
    )

    @Test
    fun `A mapper transform a data class to a map and back`() {
        val instance = MappedClass()
        val mapper = HashMapMapper(MappedClass::class, MappedClass::oneString)
        val map = mapper.toStore(instance)

        assert(instance == mapper.fromStore(map))
    }

    @Test(expectedExceptions = [ IllegalStateException::class ])
    fun `Mapping a date to an invalid field type results in error`() {
        val mapper = HashMapMapper(MappedClass::class, MappedClass::oneString)
        mapper.fromStore("onePlus", Date())
    }
}
