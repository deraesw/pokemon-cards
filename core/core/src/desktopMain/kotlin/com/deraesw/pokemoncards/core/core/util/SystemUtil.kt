package com.deraesw.pokemoncards.core.core.util

object SystemUtil {
    private const val OS = "os.name"

    fun isWindow(): Boolean = System.getProperty(OS).contains("Windows")
    fun isMac(): Boolean = System.getProperty(OS).contains("Mac")
    fun isLinux(): Boolean = System.getProperty(OS).contains("Linux")
}
