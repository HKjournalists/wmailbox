package cn.com.jinwang.repository;

import java.util.List;

import cn.com.jinwang.domain.LocalLoginLog;
import cn.com.jinwang.jpql.SortBy;

import com.google.common.base.Optional;

public class LocalLoginLogJpaRepository extends GenericJpaRepository<LocalLoginLog, Long>
    implements
    LocalLoginLogRepository {

  private static final LocalLoginLogJpaRepository INSTANCE = new LocalLoginLogJpaRepository();

  public static LocalLoginLogJpaRepository getInstance() {
    return INSTANCE;
  }

  private LocalLoginLogJpaRepository() {
    super(LocalLoginLog.class);
  }

  @Override
  public List<LocalLoginLog> findAll(int startRow, int endRow, Optional<SortBy> sortBy,
      Optional<String> qstr) {
    return null;
  }

  @Override
  public long countAll(Optional<String> qstr) {
    return 0;
  }

  @Override
  public SortBy getDefaultSortBy() {
    return null;
  }
}
