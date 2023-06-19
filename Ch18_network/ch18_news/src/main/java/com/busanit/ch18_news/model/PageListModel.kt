package com.busanit.ch18_news.model

class PageListModel {   // Dto 역할
    var articles: MutableList<ItemModel>? = null    // articles 아래 있는 컬럼들을 다 들고 와야 하기 때문에 List 필요
}