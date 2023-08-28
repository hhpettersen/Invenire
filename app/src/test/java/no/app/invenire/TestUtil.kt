package no.app.invenire

import java.io.File

fun readResourceFile(
    resourceName: String
): String {
    return File("./src/test/res/$resourceName").readBytes().decodeToString()
}
