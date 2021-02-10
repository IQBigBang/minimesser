package algorithm

import File
import java.lang.Exception

fun validate(program: File): Result<Unit> {
    for (msg in program.messages) {
        val fieldNames = HashSet<String>()

        for (f in msg.fields) {
            if (fieldNames.contains(f.name)) {
                return Result.failure(Exception("Field ${f.name} is defined twice in message ${msg.name}"))
            }
            fieldNames.add(f.name)
        }
    }
    return Result.success(Unit)
}