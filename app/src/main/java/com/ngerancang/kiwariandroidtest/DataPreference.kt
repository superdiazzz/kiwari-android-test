package com.ngerancang.kiwariandroidtest

import com.orhanobut.hawk.Hawk

object DataPreference {

    val DATA_NAME = "name"
    val DATA_IMAGE = "image"
    val DATA_UID = "uid"
    val DATA_NONE = "none"


    fun save(uid : String, name: String, image: String){
        Hawk.put(DATA_NAME, name)
        Hawk.put(DATA_UID, uid)
        Hawk.put(DATA_IMAGE, image)
    }

    fun remove(){
        Hawk.delete(DATA_NAME)
        Hawk.delete(DATA_UID)
        Hawk.delete(DATA_IMAGE)
    }

    fun getUid() : String = Hawk.get(DATA_UID, DATA_NONE)

    fun getName() : String = Hawk.get(DATA_NAME)

    fun getImage() : String = Hawk.get(DATA_IMAGE)

}