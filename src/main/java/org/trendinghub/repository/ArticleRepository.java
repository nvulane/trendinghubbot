package org.trendinghub.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.trendinghub.structure.Article;

@Repository
public interface ArticleRepository extends MongoRepository<Article, String> {
	
	Article findByUniqueId(String uniqueId);

}
