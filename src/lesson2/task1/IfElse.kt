@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.max
import kotlin.math.sqrt
import kotlin.math.abs

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    if ((age % 100) in 11..19) return "$age лет"
    return when {
        (age % 10) == 1 -> "$age год"
        (age % 10) in 2..4 -> "$age года"
        (age % 10) in 5..9 -> "$age лет"
        else -> "$age лет"
    }
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val halfRoute = (t1 * v1 + t2 * v2 + t3 * v3) / 2
    return when {
        halfRoute <= t1 * v1 -> halfRoute / v1
        halfRoute > (t1 * v1 + t2 * v2) -> t1 + t2 + (halfRoute - t1 * v1 - t2 * v2) / v3
        else -> t1 + (halfRoute - t1 * v1) / v2
    }
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    var threaten1 = 0
    var threaten2 = 0
    if (kingX == rookX1) threaten1 = 1
    if (kingX == rookX2) threaten2 = 1
    if (kingY == rookY1) threaten1 = 1
    if (kingY == rookY2) threaten2 = 1

    return when {
        threaten1 == 1 && threaten2 == 0 -> 1
        threaten1 == 0 && threaten2 == 1 -> 2
        threaten1 == 1 && threaten2 == 1 -> 3
        else -> 0
    }
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {
    var threaten1 = 0
    var threaten2 = 0

    // проверем угрозы от ладьи и слона
    if (kingX == rookX) threaten1 = 1
    if (kingY == rookY) threaten1 = 1
    if (abs(bishopX - kingX) == abs(bishopY - kingY)) threaten2 = 1

    return when {
        threaten1 == 1 && threaten2 == 0 -> 1
        threaten1 == 0 && threaten2 == 1 -> 2
        threaten1 == 1 && threaten2 == 1 -> 3
        else -> 0
    }
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    //проверяем существование треугольника
    if (!(((a + b) > c) && ((b + c) > a) && ((a + c) > b))) return -1

    val hypotenuse: Double
    val cathetus1: Double
    val cathetus2: Double

    //проверяем соотвествие теореме Пифагора
    if (a >= b && a >= c) {
        hypotenuse = a
        cathetus1 = b
        cathetus2 = c
    } else if (b >= a && b >= c) {
        hypotenuse = b
        cathetus1 = a
        cathetus2 = c
    } else {
        hypotenuse = c
        cathetus1 = a
        cathetus2 = b
    }

    return when {
        sqr(cathetus1) + sqr(cathetus2) > sqr(hypotenuse) -> 0
        sqr(cathetus1) + sqr(cathetus2) == sqr(hypotenuse) -> 1
        else -> 2
    }
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    return when {
        //отрезки не пересекаются
        b < c || d < a -> -1
        // отрезок CD внутри отрезка AB
        b >= d && c >= a -> d - c
        // отрезок CD правее отрезка AB
        b <= d && c >= a -> b - c
        // отрезок AB внутри отрезка CD
        a >= c && b <= d -> b - a
        // отрезок AB правее отрезка CD
        else -> d - a
    }
}
