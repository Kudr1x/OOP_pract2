package org.example.main

import controller.FileController

fun main() {
    val fileController = FileController("/home/kudrix/IdeaProjects/OOP_pract2/src/main/resources/address.csv")

    fileController.getDuplicate()
    fileController.getFloutCount()
    fileController.getTime()
}