package com.busanit.ch18_news.model

class ItemModel {   // Dto 역할, articles의 하위 태그로 column이 되는 필드들임, 사용할 필드 외 생략
    var author: String? = null
    var title: String? = null
    var description: String? = null
    var urlToImage: String? = null
    var publishedAt: String? = null
}