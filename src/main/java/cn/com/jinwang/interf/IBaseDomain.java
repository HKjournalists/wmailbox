package cn.com.jinwang.interf;

import java.io.Serializable;

import com.google.common.base.Optional;

import cn.com.jinwang.domain.BaseDomain;

public interface IBaseDomain<T extends BaseDomain<T>> extends Serializable, HasLongId, HasToJson {
  Optional<T> findById(long id);
  T save();
}
