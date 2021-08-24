package ru.skillbranch.devintensive.models

import java.lang.Exception

class Bender(
    var status: Status = Status.NORMAL,
    var question: Question = Question.NAME
) {
    fun askQuestion(): String = when (question) {
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {
        val resultValidation = question.validation(answer)
        return when (resultValidation.first) {
            1 -> "${resultValidation.second}\n${question.question}" to status.color
            2 -> {
                question = question.nextQuestion()
                "${resultValidation.second}\n${question.question}" to status.color
            }
            3 -> {
                val isLastStatus = status == Status.CRITICAL
                question = if (isLastStatus) Question.NAME else question
                status = status.nextStatus()
                "${resultValidation.second}${if (isLastStatus) ". Давай все по новой" else ""}\n${question.question}" to status.color
            }
            else -> throw Exception()
        }
//        if (resultValidation.first) {
//            question = question.nextQuestion()
//            "${resultValidation.second}\n${question.question}" to status.color
//        } else {
//            val isLastValue = status == Status.CRITICAL
//            question = if (isLastValue) Question.NAME else question
//            status = status.nextStatus()
//            "${resultValidation.second}${if (isLastValue) ". Давай все по новой" else ""}\n${question.question}" to status.color
//        }
    }

    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus(): Status {
            return if (this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]
            } else {
                values()[0]
            }
        }
    }

    enum class Question(val question: String, val answer: List<String>) {
        NAME("Как меня зовут?", listOf("бендер", "bender")) {
            override fun nextQuestion(): Question = PROFESSION

            override fun validation(userAnswer: String): Pair<Int, String> {
                return when {
                    userAnswer[0].isLowerCase() -> {
                        1 to "Имя должно начинаться с заглавной буквы"
                    }
                    answer.contains(userAnswer.toLowerCase()) -> {
                        2 to "Отлично - ты справился"
                    }
                    else -> {
                        3 to "Это неправильный ответ"
                    }
                }
            }
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")) {
            override fun nextQuestion(): Question = MATERIAL

            override fun validation(userAnswer: String): Pair<Int, String> {
                return when {
                    userAnswer[0].isUpperCase() -> {
                        1 to "Профессия должна начинаться со строчной буквы"
                    }
                    answer.contains(userAnswer.toLowerCase()) -> {
                        2 to "Отлично - ты справился"
                    }
                    else -> {
                        3 to "Это неправильный ответ"
                    }
                }
            }
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")) {
            override fun nextQuestion(): Question = BDAY

            override fun validation(userAnswer: String): Pair<Int, String> {
                return when {
                    userAnswer.matches(Regex(".*\\d+.*")) -> {
                        1 to "Материал не должен содержать цифр"
                    }
                    answer.contains(userAnswer.toLowerCase()) -> {
                        2 to "Отлично - ты справился"
                    }
                    else -> {
                        3 to "Это неправильный ответ"
                    }
                }
            }
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun nextQuestion(): Question = SERIAL

            override fun validation(userAnswer: String): Pair<Int, String> {
                return when {
                    userAnswer.matches(Regex("\\D+")) -> {
                        1 to "Год моего рождения должен содержать только цифры"
                    }
                    answer.contains(userAnswer.toLowerCase()) -> {
                        2 to "Отлично - ты справился"
                    }
                    else -> {
                        3 to "Это неправильный ответ"
                    }
                }
            }
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun nextQuestion(): Question = IDLE

            override fun validation(userAnswer: String): Pair<Int, String> {
                return when {
                    userAnswer.matches(Regex("\\D+")) && userAnswer.length != 7 -> {
                        1 to "Серийный номер содержит только цифры, и их 7"
                    }
                    answer.contains(userAnswer.toLowerCase()) -> {
                        2 to "Отлично - ты справился"
                    }
                    else -> {
                        3 to "Это неправильный ответ"
                    }
                }
            }
        },
        IDLE("На этом все, вопросов больше нет", listOf()) {
            override fun nextQuestion(): Question = IDLE

            override fun validation(userAnswer: String): Pair<Int, String>
                    = 2 to ""
        };

        abstract fun nextQuestion(): Question

        abstract fun validation(userAnswer: String): Pair<Int, String>
    }
}