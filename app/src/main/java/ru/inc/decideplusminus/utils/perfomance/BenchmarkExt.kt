package ru.inc.decideplusminus.utils.perfomance

import timber.log.Timber
import kotlin.system.measureTimeMillis

private const val BLOCK_OBJECT = "kotlin.Unit"
private const val BLOCK_OBJECT_NAME = "Unit"

/**
 * Простой бенчмарк для замера исполнения кода.
 *
 * @param logName имя бенчмарка, опционально.
 * Время отсчета стартует при запуске [bench], время завершения и результат считает после завершения блока [block]
 * Если лямбда ничего не возвращает в "result", в логах "object" будет [BLOCK_OBJECT_NAME], при возврате будет
 * объект или число в случае примитивов.
 *
 * Найти в логере по info и ключу "benchmark"
 */
internal fun <T> bench(logName: String = "", block: () -> T): T {
    val result: T
    val blockTime = measureTimeMillis {
        result = block.invoke()
    }

    val objectResultName = if (result == BLOCK_OBJECT) BLOCK_OBJECT_NAME else result.toString()

    Timber.i(
        "benchmark $logName: [object: $objectResultName, time: $blockTime m.sec, Thread: ${
            Thread.currentThread().name
        }]"
    )
    return result
}
