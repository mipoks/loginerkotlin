package design.kfu.loginerkotlin

import java.util.HashMap

class Person(name: String, surname: String, email: String, hash: Int, drawable: Int) {
    private var logHash: HashMap<String, Int>? = null

    private val name: String
    private val surname: String
    private val drawable: Int

    fun getName(): String {
        return name
    }

    fun getDrawable(): Int {
        return drawable
    }

    fun getSurname(): String {
        return surname
    }

    fun getlogHash(): HashMap<String, Int>? {
        return logHash
    }

    init {
        logHash = HashMap()
        logHash?.put(email, hash)
        this.name = name
        this.surname = surname
        this.drawable = drawable
    }


}
