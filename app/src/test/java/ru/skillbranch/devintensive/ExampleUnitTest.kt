package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.*
import ru.skillbranch.devintensive.models.BaseMessage
import ru.skillbranch.devintensive.models.Chat
import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_parseFullName() {
        val user1 = Utils.parseFullName(null)
        val user2 = Utils.parseFullName("")
        val user3 = Utils.parseFullName(" ")
        val user4 = Utils.parseFullName("John")
        println("""
            $user1
            $user2
            $user3
            $user4
        """.trimIndent())
    }

    @Test
    fun test_toInitials() {
        val user1 = Utils.toInitials("john" ,"doe") //JD
        val user2 = Utils.toInitials("John", null) //J
        val user3 = Utils.toInitials(null, null) //null
        val user4 = Utils.toInitials(" ", "") //null
        println("""
            $user1
            $user2
            $user3
            $user4
        """.trimIndent())
    }

    @Test
    fun test_transliteration() {
        val user1 = Utils.transliteration("Женя Стереотипов") //Zhenya Stereotipov
        val user2 = Utils.transliteration("Amazing Петр","_") //Amazing_Petr
        println("""
            $user1
            $user2
        """.trimIndent())
    }

    @Test
    fun test_dateHumanizeDiff() {
        val date1 = Date().add(-2, TimeUnits.HOUR).humanizeDiff() //2 часа назад
        val date2 = Date().add(-5, TimeUnits.DAY).humanizeDiff() //5 дней назад
        val date3 = Date().add(2, TimeUnits.MINUTE).humanizeDiff() //через 2 минуты
        val date4 = Date().add(7, TimeUnits.DAY).humanizeDiff() //через 7 дней
        val date5 = Date().add(-400, TimeUnits.DAY).humanizeDiff() //более года назад
        val date6 = Date().add(400, TimeUnits.DAY).humanizeDiff() //более чем через год
        println("""
            $date1
            $date2
            $date3
            $date4
            $date5
            $date6            
        """.trimIndent())
    }

    @Test
    fun test_builder() {
        val user = User.Builder().id("s")
                .firstName("s")
                .lastName("s")
                .avatar(null)
                .rating(1)
                .respect(1)
                .lastVisit(null)
                .isOnline(true)
                .build()
        print(user)
    }

    @Test
    fun test_plural() {
        val t1 = TimeUnits.SECOND.plural(1) //1 секунду
        val t2 = TimeUnits.MINUTE.plural(4) //4 минуты
        val t3 = TimeUnits.HOUR.plural(19) //19 часов
        val t4 = TimeUnits.DAY.plural(222) //222 дня
        print("""
            $t1
            $t2
            $t3
            $t4
        """.trimIndent())
    }

    @Test
    fun test_truncate() {
        val t1 = "Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»".truncate() //Bender Bending R...
        val t2 = "Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»".truncate(15) //Bender Bending...
        val t3 = "A     ".truncate(3) //A
        print("""
            $t1
            $t2
            $t3
        """.trimIndent())
    }

    @Test
    fun test_stripHtml() {
        val t1 = "<p class=\"title\">Образовательное IT-сообщество Skill Branch</p>".stripHtml() //Образовательное IT-сообщество Skill Branch
        val t2 = "<p>Образовательное       IT-сообщество Skill Branch</p>".stripHtml() //Образовательное IT-сообщество Skill Branch
        val t3 = "qwerty                ytrewq".stripHtml()
        print("""
            $t1
            $t2
            $t3
        """.trimIndent())
    }
}