package service

import model.Address
import java.io.File
import kotlin.system.measureTimeMillis

class XMLFileService(private val path: String): FileServiceInterface {
    override fun open(): Triple<MutableMap<String, Int>, MutableMap<Int, Int>, Long> {
        val lines = File(path).readLines()

        val addresses = mutableListOf<Address>()
        val duplicates = mutableMapOf<String, Int>()
        val floorCount = mutableMapOf<Int, Int>()

        val timeTaken = measureTimeMillis {
            lines.forEach { line ->
                val regex = """<item city="([^"]+)" street="([^"]+)" house="(\d+)" floor="(\d+)" />""".toRegex()
                val matchResult = regex.find(line)
                if (matchResult != null) {
                    val (city, street, house, floor) = matchResult.destructured
                    val address = Address(city, street, house.toInt(), floor.toInt())
                    addresses.add(address)
                    duplicates[address.toString()] = duplicates.getOrDefault(address.toString(), 0) + 1
                    floorCount[address.floor] = floorCount.getOrDefault(address.floor, 0) + 1
                    addresses.add(Address(city, street, house.toInt(), floor.toInt()))
                }
            }
        }

        return Triple(duplicates, floorCount, timeTaken)
    }
}