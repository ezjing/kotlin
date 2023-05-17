package com.busanit.androidlab

import kotlinx.coroutines.processNextEventInCurrentThread

// 조건문과 반복문
// 1. if ~ else
//fun main() {
//    var data = 10
//    if (data > 0) {
//        println("data > 0")
//    }
//    else {
//        println("data <= 0")
//    }
//}

// 1-1. else if
//fun main() {
//    var data = 10
//    if (data > 10) {
//        println("data > 10")
//    }
//    else if (data > 0 && data <= 10) {
//        println("(data > 0 && data <= 10")
//    }
//    else {
//        println("data <= 0")
//    }
//}

// 1-2. if else 표현식(값)
//fun main() {
//    var data = 10
//    val result = if(data > 0) { // 결과 값 반환 (여러 줄일때 마지막 줄 값 리턴)
//        println("data > 0")
//        true    // 중괄호 마지막에 있는 값이 result에 리턴값으로 대입됨, true 말고 숫자 문자 도 가능
//    }
//    else {  // else문 생략 불가
//        println("data <= 0")
//        false
//    }   // 표현식으로 사용할 때는 else 생략 불가능
//    println(result)
//}


// 2. when 조건문, (switch)
//fun main() {
//    var data = 30
//    when(data) {
//        10 -> println("data is 10")
//        20 -> println("data is 20")
//        else -> println("data is not valid data")
//    }
//}

// 2-1. when 조건에 String (equal() 안써도 돼서 편함)
//fun main() {
//    var data = "hello"
//    when(data) {
//        "hello" -> println("data is hello")
//        "world" -> println("data is world")
//        else -> println("data is not valid data")
//    }
//}

// 2-2. when 다양한 조건 사용 (문자열, 숫자 다 가능 대신 데이터 타입 any 해야함)
//fun main() {
//    var data : Any = 10 // any 타입을 지정하고 다양한 타입 넣어보기
//    when(data) {
//        is String -> println("data is String")  // is : 데이터 타입을 확인하는 연산자
//        20, 30 -> println("data is 20 or 30")
//        in 1..10 -> println("data is 1..10")    // in : 범위 지정 연산자. 사용 방법 :  in 1 .. 10
//        else -> println("data is not valid data")
//    }
//}

// 2-3. when 조건 없이 사용 (조건을 아래 case에 표현)
//fun main() {
//    var data = 10
//    when {
//        data <= 0 -> println("data is <= 0")
//        data > 100 -> println("data is > 100")
//        else -> println("data is valid")
//    }
//}

// 2-4. when 표현식 사용
//fun main() {
//    var data = 10
//    val result = when {
//        data <= 0 -> "data is <= 0"
//        data > 100 -> "data is > 100"
//        else -> "data is valid"
//    }
//    println(result)
//}

// 3. for(범위 연산자 in 사용)
//fun main() {
//    var sum : Int = 0
////    for (i in 1..10) {  // 1 ~ 10
////        sum += i
////    }
////    for (i in 1 until 10) { // 1 ~ 9
////        sum += i
////    }
////    for (i in 2 .. 10 step 2) {    // 2, 4, 6, 8, 10 (step : 지정한 값 만큼 증가, 감소 폭 지정)
////        sum += i
////    }
////    for (i in 10 downTo 1) { // 10 ~ 1 (downTo : 감소시키면서 반복)
////        sum += i
////    }
////    for (i in 10 downTo 2 step 2) { // downTo도 step 사용 가능
////        sum += i
////    }
//    println(sum)
//}

// 3-1. 컬렉션 크기 만큼 반복 indices
//fun main() {
//    var data = arrayOf<Int>(10, 20, 30)
//    for (i in data.indices) {   // indices : index의 복수형. 인덱스(0, 1, 2)를 순서대로 가져옴(인덱스 0 ~ 2)
//        print(data[i])
//        if (i != data.size - 1) print(", ") // 30은 콤마 안찍힘 (자바에서는 length 사용)
//    }
//}

// 3-2. 인덱스, 데이터 함께 가져오기 withIndex
//fun main() {
//    var data = arrayOf<Int>(10, 20, 30)
//    for ((index, value) in data.withIndex()) {    // withIndex : 인덱스 번호와 데이터를 함께 가져옴 (data.withIndex() 배열 안에있는 (index, value)를 가져와라)
//        print(value)    // value 찍고...
//        if(index != data.size - 1) print(", ")  // index 비교
//    }
//}

// 3-3. while문
fun main() {
    var x = 0
    var sum = 0
    while (x < 10) {
        sum += ++x
    }
    println(sum)
}