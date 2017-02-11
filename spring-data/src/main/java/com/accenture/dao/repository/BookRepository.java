package com.accenture.dao.repository;

import com.accenture.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * Created by l.camacho.carbajal on 2/7/2017.
 */
public interface BookRepository extends JpaRepository<Book, String>{

    @Query("Select b from Book b where upper(b.title) like %?1%")
    List<Book> findByTitleLike (String title);
}
