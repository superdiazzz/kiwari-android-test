package com.ngerancang.kiwariandroidtest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ngerancang.kiwariandroidtest.repositories.LoginRepository
import com.ngerancang.kiwariandroidtest.viewmodels.ChatViewModel
import com.ngerancang.kiwariandroidtest.viewmodels.ListFriendViewModel
import com.ngerancang.kiwariandroidtest.viewmodels.LoginViewModel
import com.ngerancang.kiwariandroidtest.viewmodels.RegisterViewModel
import java.lang.IllegalArgumentException

/**
 * in this task, i think we do not really need dependency injection. Because it might created the project complicated,
 * lets just makes it easier to be understands :)
 * But if you want to make DI, you can config it here...
 *
 */
class AppViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(LoginViewModel::class.java)){

            return  LoginViewModel(LoginRepository()) as T
        }
        else if(modelClass.isAssignableFrom(RegisterViewModel::class.java)){

            return RegisterViewModel() as T
        }
        else if(modelClass.isAssignableFrom(ChatViewModel::class.java)){
            return ChatViewModel() as T
        }
        else if(modelClass.isAssignableFrom(ListFriendViewModel::class.java)){
            return ListFriendViewModel() as T
        }
        else{

            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}