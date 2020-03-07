@file:Suppress("UNUSED_PARAMETER")

package lesson2.task2

import lesson1.task1.sqr
import kotlin.math.*

/**
 * Пример
 *
 * Лежит ли точка (x, y) внутри окружности с центром в (x0, y0) и радиусом r?
 */
fun pointInsideCircle(x: Double, y: Double, x0: Double, y0: Double, r: Double) =
    sqr(x - x0) + sqr(y - y0) <= sqr(r)

/**
 * Простая
 *
 * Четырехзначное число назовем счастливым, если сумма первых двух ее цифр равна сумме двух последних.
 * Определить, счастливое ли заданное число, вернуть true, если это так.
 */
fun isNumberHappy(number: Int): Boolean {
    val first = number / 1000
    val second = (number % 1000) / 100
    val third = (number % 100) / 10
    val fourth = number % 10

    return when {
        first + second == third + fourth -> true
        else -> false
    }
}

/**
 * Простая
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 * Считать, что ферзи не могут загораживать друг друга.
 */
fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean {
    return x1 == x2 || y1 == y2 || abs(x2 - x1) == abs (y2 - y1)
}


/**
 * Простая
 *
 * Дан номер месяца (от 1 до 12 включительно) и год (положительный).
 * Вернуть число дней в этом месяце этого года по григорианскому календарю.
 */
fun daysInMonth(month: Int, year: Int): Int {
    val isLeapYear: Boolean

    //проверяем високосный год
    isLeapYear = year % 4 == 0 && !(year % 100 == 0 && !(year % 400 == 0))

    //вычисляем длительность месяцев
    return when (month) {
        1, 3, 5, 7, 8, 10, 12 -> 31
        4, 6, 9, 11 -> 30
        else -> {
            return if (isLeapYear) 29
            else 28
        }
    }
}

/**
 * Средняя
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */
fun circleInside(
    x1: Double, y1: Double, r1: Double,
    x2: Double, y2: Double, r2: Double
): Boolean {
    //проверяем, лежит ли центр первой окружности внутри второй
    return sqr(x2 - x1) + sqr(y2 - y1) + sqr(r1) <= sqr(r2)
}

/**
 * Средняя
 *
 * Определить, пройдет ли кирпич со сторонами а, b, c сквозь прямоугольное отверстие в стене со сторонами r и s.
 * Стороны отверстия должны быть параллельны граням кирпича.
 * Считать, что совпадения длин сторон достаточно для прохождения кирпича, т.е., например,
 * кирпич 4 х 4 х 4 пройдёт через отверстие 4 х 4.
 * Вернуть true, если кирпич пройдёт
 */
fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean {
    val shortestSide: Int
    val shortSide: Int

    //находим две самых коротких стороны кирпича
    if ((a <= b) && (a <= c)) {
        shortestSide = a
        if (b <= c) shortSide = b
        else shortSide = c
    } else if ((b <= a) && (b <= c)) {
        shortestSide = b
        if (a <= c) shortSide = a
        else shortSide = c
    } else {
        shortestSide = c
        if (a <= b) shortSide = a
        else shortSide = b
    }

    //сравниваем самые короткие стороны кирпича с соответствующими отверстиями в стене
    return if (r <= s && shortestSide <= r && shortSide <= s) true
    else s <= r && shortestSide <= s && shortSide <= r
}
