package service

interface FileServiceInterface {
    fun open(): Triple<MutableMap<String, Int>, MutableMap<Int, Int>, Long>
}