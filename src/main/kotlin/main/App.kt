package main

import controller.FileController
import java.io.FileNotFoundException

fun main() {
    print("Введите название файла: ")
    val input: String = readlnOrNull().toString()
    val fileController: FileController

    try {
        fileController = FileController(input)
        fileController.getDuplicate()
        fileController.getFloutCount()
        fileController.getTime()
    }catch (e: FileNotFoundException) {
        println(e.message)
    }
}