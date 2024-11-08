package org.example.main

import controller.FileController

fun main() {
    val fileController = FileController("/home/kudrix/IdeaProjects/OOP_pract2/src/main/resources/address.csv")

    println(fileController.getDuplicate())
    println(fileController.getFloutCount())
    println(fileController.getTime())
}