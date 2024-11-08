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
        println("Количество этажей в городах:")
        floorCount.forEach { (floor, count) ->
            println("$floor этажных зданий: $count")
        }
    }

    override fun getTime() {
        println("Время выполнения $time мс")
    }
}