package controller

import service.CSVFileService
import service.FileServiceInterface
import service.XMLFileService

class FileController(path: String): FileControllerInterface {
    private val service: FileServiceInterface = when (path.substringAfter(".")) {
        "csv" -> CSVFileService(path)
        "xml" -> XMLFileService(path)
        else -> throw IllegalArgumentException("Неизвестный тип: ${path.substringAfter(".")}")
    }

    private val triple = service.open()

    private val duplicates = triple.first
    private val floorCount = triple.second
    private val time = triple.third

    override fun getDuplicate() {
        println("Дублирующиеся записи с количеством повторений:")
        duplicates.forEach { (key, value) ->
            if (value > 1) println("$key: $value")
        }
    }

    override fun getFloutCount() {
        val cityFloorCounts = mutableMapOf<String, MutableMap<Int, Int>>()

        for ((key, count) in floorCount) {

            val (city, floor) = key

            if (city !in cityFloorCounts) {
                cityFloorCounts[city] = mutableMapOf()
            }

            cityFloorCounts[city]!![floor] = cityFloorCounts[city]!!.getOrDefault(floor, 0) + count
        }

        for ((city, counts) in cityFloorCounts) {

            println("Город: $city")

            for (floor in 1..5) {
                val count = counts.getOrDefault(floor, 0)
                println("Количество $floor-этажных зданий: $count")
            }

            println()
        }
    }

    override fun getTime() {
        println("Время выполнения $time мс")
    }
}