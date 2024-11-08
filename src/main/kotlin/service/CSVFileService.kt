package service

import model.Address
import java.io.File
import kotlin.system.measureTimeMillis

class CSVFileService(private val path: String) : FileServiceInterface {
    override fun open(): Triple<MutableMap<String, Int>, MutableMap<Int, Int>, Long> {
        val lines = File(path).readLines()

        val addresses = mutableListOf<Address>()
        val duplicates = mutableMapOf<String, Int>()
        val floorCount = mutableMapOf<Int, Int>()

        var firstLineFlag = true

        val timeTaken = measureTimeMillis {
            lines.forEach { line ->
                val parts = line.split(";").map {
                    it.trim('"')
                }

                if (firstLineFlag) {
                    firstLineFlag = false
                }else{
                    if (parts.size == 4) {
                        val address = Address(parts[0], parts[1], parts[2].toInt(), parts[3].toInt())
                        addresses.add(address)
                        duplicates[address.toString()] = duplicates.getOrDefault(address.toString(), 0) + 1
                        floorCount[address.floor] = floorCount.getOrDefault(address.floor, 0) + 1
                    }
                }
            }
        }

        return Triple(duplicates, floorCount, timeTaken)
    }
}