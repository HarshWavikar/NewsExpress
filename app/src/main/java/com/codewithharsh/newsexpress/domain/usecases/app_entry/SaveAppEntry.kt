package com.codewithharsh.newsexpress.domain.usecases.app_entry

import com.codewithharsh.newsexpress.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}