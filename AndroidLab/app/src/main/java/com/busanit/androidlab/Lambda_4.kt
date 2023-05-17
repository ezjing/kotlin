package com.busanit.androidlab

// 1. 람다함수 {매개변수 -> 함수 본문} 함수 본문의 마지막 표현식(결과 값) 리턴 (람다함수 : 익명함수라는 뜻, 중괄호{}로 표현)
//fun sum(no1 : Int, no2 : Int) : Int { // 일반함수
//    return no1 + no2
//}
//val sum = {no1 : Int, no2 : Int -> no1 + no2} // 람다함수

// 1-1. 람다 함수 선언과 동시에 호출
//fun main() {
//    println("${{no1 : Int, no2 : Int -> no1 + no2}(40, 20)}")   // 왼쪽은 람다함수 오른쪽은 매개변수
//}

// 1-2. 매개변수가 없는 람다함수 (화살표 생략 가능) (매개변수가 없기 때문에 결과만 있는 함수인거 같음)
//fun main() {
//    run { -> println("function call..")}   // 매개변수가 없으면 왼쪽(매개변수자리) 비워두기 (run 함수 : )
//}
//fun main() {    // 화살표 생략
//    run {println("function call..")}
//}

// 1-3. 매개변수 한개인 경우 it 키워드 사용 가능
//fun main() {
//    val some = {no : Int -> println(no)}
//    some(10)
//}
//fun main() {
//    val some : (Int) -> Unit = {println(it)}    // int를 받아서 리턴값이 없는 함수{println(it)}를 출력. 잘 모르겠으면 위의 평범한 함수 참고)
//    some(10)
//}

// 1-4. 람다함수의 리턴(return 문 사용 안됨)
//val some = {no1 : Int, no2 : Int -> return no1 * no2}   // return 사용 불가
//fun main() {
//    val some = {no1 : Int, no2 : Int ->
//        println("in lambda function")
//        no1 * no2}  // 명시적으로 return은 사용 할 수 없지만, 함수 마지막에 오는 값이 리턴됨
//
//    println("result : ${some(10, 20)}")
//}

// 2. 고차 함수 (함수를 표현하는 또 다른 방법으로 접근하면 될 듯) 
// 2-1. 함수타입 이용
//fun some(no1 : Int, no2 : Int) : Int {  // 일반적 함수 선언
//    return no1 + no2
//}
//val some : (Int, Int) -> Int = {no1 : Int, no2 : Int -> no1 + no2}    // some : (int를 두개 받아서 int를 리턴하는 함수) = {내용} (람다함수 it 사용하던 식과 유사)
//fun main() {
//    println("${some(10,20)}")
//}

// 2-2. 타입 별칭으로 함수타입 선언
//typealias MyFunType = (Int, Int) -> Boolean   // typealias : 별칭 선언하는 키워드 ex. MyFunType 이름을 갖는, (Int, Int) -> Boolean 타입으로 리턴하는 함수
//
//fun main() {
//    val someFun : MyFunType = {no1 : Int, no2 : Int -> no1 > no2} // 2-1과 다르게 별칭으로 앞부분 대체함 (타입 별칭으로 함수 선언. 기본 함수선언법, 고차함수 선언법 비교하기)
//    println(someFun(10, 20))
//    println(someFun(20, 10))
//}

// 2-3. 매개변수 타입 생략 가능(타입 유추 가능할 때)
//typealias MyFunType = (Int, Int) -> Boolean // int 두개를 받아 boolean을 반환(리턴) (매개변수와 함수 리턴 타입 나와있음)
//
//fun main() {
//    val someFun : MyFunType = {no1, no2 -> no1 > no2}   // 데이터 타입 생략 가능(위의 함수에서 명시되어 있기 때문에 유추 가능)
//    println(someFun(10, 20))
//    println(someFun(20, 10))
//}

// 2-4. 고차함수(매개변수, 리턴값에 함수를 사용)
//fun hofFun(arg : (Int) -> Boolean) : () -> String {   // 매개변수도 함수 arg가 역할이 있는것은 아니고 함수의 이름임, 리턴되는거도 함수, 빈()는 함수이기떄문에 있는것 매개변수 : arg : (Int) -> Boolean 리턴타입 : String
//    // arg : int타입이 들어오면 boolean 타입으로 리턴하는 함수 (hofFun의 매개변수)
//    // hofFun : ()타입(함수 타입, 데이터 타입이라기 보다 함수를 호출하기 위해 인자로서 String으로 전달되는 역할)이 들어오면 String 타입으로 리턴하는 함수
//    val result = if (arg(10)) {   // 만약 10이 대입되는 함수라면
//        "valid"
//    }
//    else {    // 아니면
//        "invalid"
//    }
//    return{"hofFun result : $result"}
//}
//fun main() {
//    val result = hofFun({no -> no > 0}) // hofFun 함수식
//    println(result()) // 리턴값이 함수이기 때문에 result 뒤에 () 꼭 붙여야 한다
//}

// 3. null 안전성 (자바는 null이 들어가면 어떻게 계산할건지 일일이 지정해야하지만 코틀린은 그렇지 않다)
//fun main() {
//    var data : String? = null   //(null 나중에 "kkang"으로 바꿔볼것)
////    val length = if(data == null) {
////        0
////    }
////    else {
////        data.length
////    }
////    println("data length : $length")
//    println("data length : ${data?.length ?: 0}") // ?: 0 뜻 : ?(null) 인 경우 0 출력
//}

// 3-1. null 안전성 연산자
// null 허용 연산자 ?
//var data : String? = "kkang"
//fun main() {
//    data = null
//}

// 3-2. 엘비스 연산자 ? (변수가 null일 때 실행해야 하는 구문)
fun main() {
    var data : String? = "kkang"
    println("data = ${data?.length ?: -1}")  // ?:-1 뜻 : ?(null)인 경우 -1 출력

    data = null
    println("data = ${data?.length ?: -1}") // data객체에 null이 들어갈 수 있고 null인 경우 -1 출력
}