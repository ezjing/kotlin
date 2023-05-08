package com.busanit.androidlab

// 1. 클래스 선언
//class User {
//    var name = "kkang"  // 멤버변수 : 선언할때 초기화 해줘야함
//    constructor(name: String) { // 생성자
//        this.name = name
//    }
//    fun someFun () {    // 메소드
//        println("name : $name")
//    }
//    class SomeClass {   // 내부 클래스
//    }
//}
//
//fun main() {
//    val user = User("kim")
//    user.someFun()
//}

// 1-1. 주 생성자(클래스 생성자는 주 생성자, 보조 생성자로 구분)
//class User constructor() {  // 클래스 선언부에 constructor 키워드로 주생성자를 선언
//}

//class User() {  // constructor 키워드 생략 가능
//}

//class User {    // 주생성자 선언하지 않으면 매개변수 없는 주생성자 자동 추가
//}

// 1-2. 매개변수 있는 주생성자
//class User(name : String, count : Int) {
//    init {
//        println("I am init.....")   // init 영역은 객체 생성시 자동 실행
//    }
//}
//
//fun main() {
//    val user = User("kkang", 10)    // 객체가 만들어지며 상단 init 영역 자동 실행
//}

// 1-3. 매개변수를 멤버변수로 사용하는 방법
//class User(name : String, count : Int) {
//    lateinit var name : String
//    var count : Int = 0 // 멤버변수 선언
//    init {
//        this.name = name
//        this.count = count  // init 영역에서 매개변수 값 대입
//    }
//    fun someFun() {
//        println("name : $name, count : $count")
//    }
//}
//
//fun main() {
//    val user = User("kkang", 10)
//    user.someFun()
//}

// 1-4. 매개변수를 val var로 선언 -> 바로 멤버변수로 사용 가능
//class User(val name : String, val count : Int) {
//    // 멤버변수 선언, 초기화, 값 대입 생략
//    fun someFun() {
//        print("name : $name, count : $count")
//    }
//}
//
//fun main() {
//    val user = User("kkang", 10)
//        user.someFun()
//}

// 2. 보조 생성자, 클래스의 본문에 constructor 키워드로 선언, 여러개 추가 가능 (이부분 자바랑 비슷한거 있는데 찾아보기)
//class User {    // 매개변수 없는 생성자
//    constructor(name : String) {
//        println("constructor(name : String) call.....")
//    }
//    constructor(name : String, count : Int) {
//        println("constructor(name : String, count : Int) call.....")
//    }
//}
//
//fun main() {
//    val user1 = User("kkang")
//    val user2 = User("kkang", 10)
//}

// 2-1. 주생성자, 보조생성자 함께 사용
//class User(name : String) {
//    constructor(name : String, count : Int) : this(name) {  // this(name) 주생성자 호출
//    }
//}

// 2-2. 보조생성자 여러개 사용
//class User(name : String) {
//    // 보조생성자를 사용할때는 위에껄 순서대로 호출해주면 된다
//    constructor(name : String, count : Int) : this(name) {}
//    constructor(name : String, count : Int, email : String) : this(name, count)
//}
//
//fun main() {
//    val user = User("kkang", 10, "a@a.com")
//}

// 3. 상속
//open class Super { }    // open 키워드를 써서 선언해야 다른 클래스에서 상속 가능
//
//class Sub : Super() {} // 클래스 선언부에서 콜론 뒤에 상속받을 클래스명 입력. (Sub클래스는 Super 클래스를 상속 받는다)
//                       // Super() 상속 받으면서, super 클래스의 매개변수 없는 생성자 호출

// 3-1. 매개변수 있는 생성자
//open class Super(name : String) { }
//
//class Sub(name : String) : Super(name) { }   // 매개변수 구성에 맞게 전달

// 3-2. 부모클래스 함수 사용
//open class Super {
//    var superData = 10
//    fun superFun() {
//        println("I am superFun : $superData")
//    }
//}
//
//class Sub : Super()
//
//fun main() {
//    val obj = Sub() // Sub 클래스 객체 생성
//    obj.superData = 20  // Sub 클래스 객체 이용해서 Super 클래스 변수, 함수 사용
//    obj.superFun()
//}

// 3-3. 오버라이딩 규칙(상속받은 클래스에서 상속해준 클래스의 필드, 메소드 등을 순서, 데이터 타입 등을 변경시켜 쓰는것)
//open class Super {
//    open var someData = 10  // 오버라이딩 허용할 변수, 메소드에 open 키워드 사용
//    open fun someFun() {
//        println("I am super class function : $someData")
//    }
//}
//
//class Sub : Super() {
//    override var someData = 20  // 오버라이딩 할 변수, 메소드에 override 키워드 사용
//    override fun someFun() {
//        println("I am sub class function : $someData")
//    }
//}
//
//fun main() {
//    val obj = Sub()
//    obj.someFun()
//}

// 4. 접근 제한자 - public(모든 클래스), internal(같은 모듈(패키지)), protected(상속 관계), private(자기 클래스 내부)
//open class Super {
//    var publicData = 10 // 생략된 경우, public
//    protected var protectedData = 20
//    private var privateData = 30
//}
//
//class Sub : Super() {
//    fun subFun() {
//        publicData++
//        protectedData++
////        privateData++
//    }
//}
//
////fun main() {
////    val obj = Super()
////    obj.subFun()
////}
//
////fun main() {
////    val obj = Super()
////    obj.publicData++
////    obj.protectedData++
////    obj.privateData++
////}

// 5. 클래스 종류, 데이터 클래스(자바의 DTO)
//class NonDataClass(val name : String, val email : String, val age : Int) {}    // 일반 클래스
//
//data class DataClass(val name : String, val email : String, val age : Int) {}   // 데이터 클래스 선언(data 키워드)
//
//fun main() {
//    val non1 = NonDataClass("kkang", "a@a.com", 10)
//    val non2 = NonDataClass("kkang", "a@a.com", 10)
//
//    val data1 = DataClass("kkang", "a@a.com", 10)
//    val data2 = DataClass("kkang", "a@a.com", 10)
//
//    println("non data class equals : ${non1.equals(non2)}") // 일반클래스로 객체를 두개 만들어서 비교하면 객체 안의 내용이 같을지라도 객체 주소가 다르기 때문에 false (일반 클래스는 객체 비교)
//    println("data class equals : ${data1.equals(data2)}")   // 데이터클래스로 객체 두개를 만들어서 비교하면 객체의 주소가 다를지라도 안의 내용을 비교하는것이기 때문에 true (데이터 클래스는 객체의 데이터 비교)
//}

// 5-1. 데이터 클래스 equals() : 주생성자 매개변수 데이터만 비교
//data class DataClass(val name : String, val email : String, val age : Int) {
//    lateinit var address : String
//    constructor(name : String, email : String, age : Int, address : String) : this(name, email, age) {
//        this.address = address
//    }
//}
//
//fun main() {
//    val obj1 = DataClass("kkang", "a@a.com", 10, "Seoul")
//    val obj2 = DataClass("kkang", "a@a.com", 10, "Busan")
//    println("obj1.equals(obj2) : ${obj1.equals(obj2)}") // 보조생성자로 만든 데이터는 비교하지 않기 때문에 true 출력 (true : Seoul, Busan은 비교 안함)
//}

// 5-2. 데이터 클래스 toString() 함수
fun main() {
    class NonDataClass(val name : String, val email : String, val age : Int)
    data class DataClass(val name : String, val email : String, val age : Int)

    val non = NonDataClass("kkang", "a@a.com", 10)
    val data = DataClass("kkang", "a@a.com", 10)

    println("non data class toString : ${non.toString()}")  // 객체 주소값 출력
    println("data class toString : ${data.toString()}") // 객체의 데이터 출력
}