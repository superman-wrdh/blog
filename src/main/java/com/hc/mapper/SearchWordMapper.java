package com.hc.mapper;

import com.hc.entity.SearchWord;

import java.util.List;


/**
 * Created by hc on 2017/3/20.
 */
public interface SearchWordMapper {
    List<SearchWord> list();
    void update(SearchWord searchWord);
    void insert(SearchWord searchWord);
    SearchWord findByWord(String word);
}
