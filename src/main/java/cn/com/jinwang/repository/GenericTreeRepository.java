package cn.com.jinwang.repository;

import java.io.Serializable;
import java.util.List;

import cn.com.jinwang.domain.BaseTreeDomain;
import cn.com.jinwang.jpql.SortBy;

import com.google.common.base.Optional;

public interface GenericTreeRepository<T extends BaseTreeDomain<T>, ID extends Serializable> extends GenericRepository<T, Long>{
  
  List<T> findAllTop(int startRow, int endRow, Optional<SortBy> sortBy);
  long countAllTop();
  
  void updateParent(T parent, T...child);

}
