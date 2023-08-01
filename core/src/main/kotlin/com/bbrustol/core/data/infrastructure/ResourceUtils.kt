package com.bbrustol.core.data.infrastructure

import java.util.*

class ResourceUtils {

    fun openFile(pathFile: String): String {

        val classLoader = this.javaClass.classLoader
        val resourceAsStream = classLoader?.getResourceAsStream(pathFile)

        val result = StringBuilder("")

        val scanner = Scanner(resourceAsStream)

        while (scanner.hasNextLine()) {
            result.append(scanner.nextLine())
        }

        return result.toString()
    }
}