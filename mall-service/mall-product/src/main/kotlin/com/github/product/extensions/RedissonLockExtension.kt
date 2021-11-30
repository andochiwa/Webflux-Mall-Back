package com.github.product.extensions

import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.redisson.api.RLockReactive

/**
 * @author Andochiwa
 * @version 1.0
 * @since 12-01-05:12
 */
suspend inline fun <T> RLockReactive.withLock(threadId: Long, action: () -> T): T {
    lock(threadId).awaitSingleOrNull()
    try {
        return action()
    } finally {
        unlock(threadId).awaitSingleOrNull()
    }
}
