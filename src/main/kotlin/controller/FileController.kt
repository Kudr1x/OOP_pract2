package controller

class FileController(path: String): FileControllerInterface {
    private val duplicates = mutableMapOf<String, Int>()
    private val floorCount = mutableMapOf<Int, Int>()
    private val extension = path.substringAfterLast(".")

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
        TODO("Not yet implemented")
    }
}