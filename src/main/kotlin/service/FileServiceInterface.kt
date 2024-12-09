package service

interface FileServiceInterface {
    fun open(): Triple<MutableMap<String, Int>, MutableMap<Pair<String, Int>, Int>, Long>
}