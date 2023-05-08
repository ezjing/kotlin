package com.busanit.androidlab

import java.lang.Exception

// 1. 변수 선언하기 (변수 선언은 자바 보다 자바스크립트 느낌이다)
// val : 값 변경 불가(value), var : 값 변경 가능(variable)
//val data1 = 10  // 코틀린은 세미콜론 찍지 않는다
//var data2 = 10
//
//fun main() {
//   data1 = 20   // val : 값 변경 불가(value), 오류 발생
//   data2 = 20 
//}

// 1-1. 변수 타입 지정
//val data1 : Int = 10
//val data2 = 10  // 타입 생략 가능

// 1-2. 초기값 할당(최상위 변수(클래스 바깥에 제일 위에 존재하는 변수), 클래스 멤버 변수는 선언과 동시에 초기값 할당)
//val data1 : Int // 클래스 멤버 변수는 초기값 지정 없이 타입만 지정하면 오류 발생

//class User {
//    val data2: Int = 10
//}
//
//fun someFun() {
//    val data3 : Int // 함수 변수는 초기값 지정 없이 타입만 지정해도 오류 X
//}

// 1-3. 초기화 미루기(lateinit 키워드 이용)
//lateinit var data1 : Int    // int, long, short, float, double, boolean, byte 타입은 사용 불가
//lateinit var data2 : String
//lateinit val data3 : String // val은 바로 값을 할당해야 하기 때문에 오류 발생. val 사용 불가

// 1-4. 초기화 미루기(lazy 키워드 이용)
//val data1 : Int by lazy {   // lazy를 선언
//    println("in lazy.....") // 해야할 일을 하고
//    10  // 마지막에 초기화
//}
//
//fun main() {    // main을 가장 먼저 실행
//    println("in main.....")
//    println(data1 + 10) // data1 : lazy에서 지정한 10을 들고옴
//    println(data1 + 10)
//}

// 2. 데이터 타입
//fun someFun() {
//    var data1 : Int = 10
//    var data2 : Int? = null    // null이 들어갈 수 있으면 ? 붙이기, null 대입 가능(? 사용), Int가 객체이기 때문에 null을 넣을 수 있음
//
//    data1 = data1.plus(10)  // Int가 객체이기 때문에 메소드(.plus) 사용 가능
//}

// 2-1 기초 데이터 타입 '객체' (lateinit 사용 불가)
val a1 : Byte = 0b00001011
val a2 : Short = 123
val a3 : Long = 10L
val a4 : Double = 10.0
val a5 : Float = 10.0f
val a6 : Boolean = true

// 2-2 문자, 문자열
val a : Char = 'a'
// 자바에서는 문자를 숫자로 바꿀 수 있지만 코틀린에서는 불가
//if(a==1) {} // char 타입 데이터를 Number로 변환불가

//fun main() {
//    val str1 = "Hello\nWorld"   // \n : 강제 줄바꿈
//    val str2 = """
//        Hello
//        World
//        """.trimIndent()    // 큰따움표 3개를 쓰면 안에 쓴 그대로 출력 (띄어쓰기 줄바꿈 따로 안써도 됨)
//    println("str1 : $str1") // $를 써야 변수 가져오기 가능
//    println("str2 : $str2")
//}

// 2-3. 문자열 템플릿($) 사용
//fun main() {
//    fun sum(no : Int) : Int{    // sum : 파라미터로 int 값을 받고, 결과 값으로 int 값을 리턴. 함수 뒤에 : 데이터 타입 하면 리턴값, 함수 안에 : 데이터 타입은 매개변수
//        var sum = 0
//        for ( i in 1 .. no) {
//            sum += i
//        }
//        return sum
//    }
//    val name : String = "kkang"
//    println("name : $name, sum : ${sum(10)}, plus : ${10 + 20}")    // 변수는 $ 뒤 () 사용하면 되지만, 함수를 사용하거나 계산식 사용 할 때 $ 뒤 중괄호로 묶어줘야 함
//}

// 2-4. Any 타입 - 모든 타입 지정 가능 (자바로 치면 Object 인듯)
//val data1 : Any = 10    // int
//val data2 : Any = "hello"    // String
//class User{}
//val data3 : Any = User()    // 클래스 객체

// 2-5. Unit 타입 - 리턴 없는 함수 (자바로 치면 void)
//val data1 : Unit = Unit
//fun some() : Unit { // Unit 대신 Int 쓰면 리턴값이 없기 때문에 오류 발생
//    println(10 + 20)
//}
//fun some2() {   // 반환 타입 생략 시 자동으로 Unit 적용
//    println(20 + 30)
//}

// 2-6. Nothing 타입
//val data1 : Nothing? = null // Nothing 타입 변수에는 null 만 대입 가능
//// 데이터로서의 의미 없음 (값이 없으니까)
//fun some1() : Nothing? {
//    return null
//}   // 반환값이 null, 또는 예외를 던지는 함수의 리턴 타입으로 지정
//fun some2() : Nothing? {    // 예외를 던지는 함수
//    throw Exception()
//}

// 2-7. null 허용과 불허용
//var data1 : Int = 10
//fun some() {
//    data1 = null    // null 로 재지정 불가
//}
//var data2 : Int? = 10   // null 허용
//fun main() {
//    data2 = null    // null을 쓰려면 데이터 타입 뒤에 ? 를 붙여줘야 사용 가능
//}

// 3. 함수 선언하기(fun 키워드 사용)
//fun some(data1 : Int) : Int{
//    return data1 * 10
//}   // 함수명(파라미터 이름 : 파라미터 데이터 타입) : 리턴 데이터 타입

//fun some2(data1 : Int) {    // 리턴 데이터 타입 없으므로 Unit
//    data1 = 20 // 전달되는 매개변수는 val, 변경 불가능
//}

// 3-1. 파라미터에 기본값 선언
//fun main() {
//    fun some(data1 : Int, data2 : Int = 10) : Int {
//        return data1*data2
//    }
//    println(some(10))   // data2에 기본값 10 적용
//    println(some(10, 20))   // data2에 전달된 20 적용
//    println(some(data2 = 20, data1 = 10))   // 매개변수명 지정하여 전달 가능. 데이터 위치를 바꾼것. 이렇게 명시적으로 지정 가능
//}

// 4. 컬렉션 타입
// 4-1. Array - 배열
//fun main() {
//    val data1 : Array<Int> = Array(3, {0})  // 생성자는(크기, 초기값)으로 구성
//    println("""
//        array size : ${data1.size}
//        array data : ${data1[0]}, ${data1[1]}, ${data1[2]}
//    """.trimIndent())
//    data1[0] = 10
//    data1[1] = 20
//    data1.set(2, 30)    // 2번 인덱스에 30 대입
//    println("""
//        array size : ${data1.size}
//        array data : ${data1[0]}, ${data1[1]}, ${data1[2]}
//    """.trimIndent())
//}

// 4-2. 기초 타입의 배열 (사이즈 뒤에는 초기값 아무거도 입력 안됐을때 나오는 값)
//val data1 : IntArray = IntArray(3, {0})
//val data2 : BooleanArray = BooleanArray(3, {false})
//fun main() {
//    data2[2] = true
//    println("${data1[1]}, ${data2[2]}")
//}

// 4-3. arrayOf() - 배열 선언시 값 할당
//val data2 = intArrayOf(10, 20, 30)
//val data3 = booleanArrayOf(true, false, true)
//fun main() {
//    val data1 = arrayOf<Int>(10, 20, 30)
//    println("""
//        array size : ${data1.size}
//        array data ; ${data1[0]}, ${data1[1]}, ${data1.get(2)}
//    """.trimIndent())
//    println("""
//        array data ; ${data2[0]}, ${data2[1]}, ${data2.get(2)}
//    """.trimIndent())
//    println("""
//        array data ; ${data3[0]}, ${data3[1]}, ${data3.get(2)}
//    """.trimIndent())
//}

// 4-4. List, Set, Map (불변..get과 size 만 사용 가능)
//fun main() {
//    var list = listOf<Int>(10, 20, 30)    // 그냥 list는 array 와 별 차이 없음
//    println("""
//        list size : ${list.size}
//        list data ; ${list[0]}, ${list[1]}, ${list.get(2)}
//    """.trimIndent())
//    list.add(3, 40) // 새로운 값을 넣고자 했을때 오류 발생
//}

// 4-5. mutableList(앞에 mutable 붙으면 가변, add, set 사용 가능)
//fun main() {
//    var mutableList = mutableListOf<Int>(10, 20, 30)
//    mutableList.add(3, 40) // 새로운 값을 넣어도 오류 X
//    mutableList.set(0, 50)  // 0번째 인덱스 값 50으로 변경
//    println("""
//        list size : ${mutableList.size}
//        list data ; ${mutableList[0]}, ${mutableList[1]}, ${mutableList.get(2)}, ${mutableList.get(3)}
//    """.trimIndent())
//}
// list : 순서 유, 데이터 중복 허용
// set : 순서 무, 데이터 중복 불허용
// map : 키, 밸류 조합으로 구성, 순서 무, 키 중복 불허용
// 배열은 사이즈가 지정 돼있음(사이즈를 먼저 넣거나 변수와 동시에 자동으로 지정됨), List는 사이즈 지정 안돼있음

// 4-6. map
fun main() {
    var map = mapOf<String, String>(Pair("one", "hello"), "two" to "world") // 방법1 : Pair("키", "밸류"), 방법2 : "키" to "밸류"
    // 키, 밸류 지정하는 방법 : Pair 객체 이용, 또는 "키" to "밸류"
    println("""
        map size : ${map.size}
        map data : ${map.get("one")}, ${map.get("two")}
    """.trimIndent())
}


