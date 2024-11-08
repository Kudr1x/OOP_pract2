package service

import java.io.File

class XMLFileService(private val path: String): FileServiceInterface{
    override fun open(): Triple<MutableMap<String, Int>, MutableMap<Int, Int>, Long> {
        TODO("Not yet implemented")
    }
}