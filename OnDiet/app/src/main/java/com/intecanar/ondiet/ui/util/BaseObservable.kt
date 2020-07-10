package com.intecanar.ondiet.ui.util

import java.util.Collections
import java.util.HashSet

abstract class BaseObservable<LISTENER_CLASS> {

    private val monitor = Any()

    private val mListeners = HashSet<LISTENER_CLASS>()

    protected val listeners: Set<LISTENER_CLASS>
        get() = synchronized(monitor) {
            return Collections.unmodifiableSet(HashSet(mListeners))
        }

    fun registerListener(listener: LISTENER_CLASS) {
        synchronized(monitor) {
            val hadNoListeners = mListeners.size == 0
            mListeners.add(listener)
            if (hadNoListeners && mListeners.size == 1) {
                onFirstListenerRegistered()
            }
        }
    }

    fun unregisterListener(listener: LISTENER_CLASS) {
        synchronized(monitor) {
            val hadOneListener = mListeners.size == 1
            mListeners.remove(listener)
            if (hadOneListener && mListeners.size == 0) {
                onLastListenerUnregistered()
            }
        }
    }

    protected fun onFirstListenerRegistered() {

    }

    protected fun onLastListenerUnregistered() {

    }

}