package service

import model.Address
import java.io.File

class CSVFileService : FileServiceInterface {
    override fun open(path: String) {
        val lines = File(path).readLines()

        val addresses = mutableListOf<Address>()
        val duplicates = mutableMapOf<String, Int>()
        val floorCount = mutableMapOf<Int, Int>()

        var firstLineFlag = true

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
}